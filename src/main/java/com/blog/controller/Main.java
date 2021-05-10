package com.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.blog.Repository.AccountRepository;
import com.blog.Repository.ArticleListRepository;
import com.blog.Repository.ArticleRepository;
import com.blog.Repository.ListContentRepository;
import com.blog.model.Account;
import com.blog.model.Article;
import com.blog.model.ArticleList;
import com.blog.model.ListContent;

@Controller
public class Main {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ArticleListRepository articlelistRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ListContentRepository listcontentRepository;

	@GetMapping(value = { "/home", "" })
	public String home(HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		return "home";
	}

	@GetMapping("articles_list")
	public String articles_list(Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		Long user_id = (Long) session.getAttribute("user_id");
		Optional<Article> temp =articleRepository.findById(user_id);
		model.addAttribute("articles_list",articleRepository.findByUser(temp.get().getUser()));
		return "article_list";
	}

	@GetMapping("/articles/{id}")
	public String articles(@PathVariable Long id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		Article article = restTemplate.getForObject("http://localhost:8080/api/articles/" + id, Article.class);
		model.addAttribute("article", article);
		model.addAttribute("article_id", id);
		model.addAttribute("article_list",articlelistRepository.findByUserid((long)session.getAttribute("user_id")));
		return "diary";
	}

	@GetMapping("/write")
	public String Write(HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		return "write";
	}

	@PostMapping("/writting")
	public String Writting(@ModelAttribute Article article, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		
		Long id = (Long) session.getAttribute("user_id");
		article.setUser((accountRepository.findById(id).get().getUsername()));
		article.setDate(getDateTime());
		articleRepository.save(article);

		return "redirect:/";
	}

	@GetMapping("/login")
	public String Login(HttpSession session) {
		if (session.getAttribute("user_id") != null)
			return "redirect:/";
		return "login";
	}

	@PostMapping("/logining")
	public String Logining(@ModelAttribute Account account, HttpSession session) {
		Account temp = accountRepository.findByEmail(account.getEmail());
		if (temp.getPassword().equals(account.getPassword())) {
			session.setAttribute("user_id", temp.getId());
			return "redirect:/home";
		}
		return "redirect:/login";
	}

	@GetMapping("/list/{id}")
	public String list(@PathVariable Long id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		List<ListContent> list = listcontentRepository.findById(id);
		model.addAttribute("list_content", list);
		return "list_content";
	}

	@GetMapping("/list_content")
	public String list_content(Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		List<ArticleList> list = articlelistRepository.findByUserid((Long)session.getAttribute("user_id"));
		model.addAttribute("list", list);
		return "list";
	}

	@PostMapping("/add_list")
	public String add_list(@ModelAttribute ArticleList title_name, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";

		ArticleList temp = new ArticleList();
		temp.setName(title_name.getName());
		temp.setUserid((long) session.getAttribute("user_id"));
		articlelistRepository.save(temp);
		
		return "redirect:/list_content";
	}
	
	@PostMapping("/insert2list")
	public String insert2list(@ModelAttribute ListContent content, HttpSession session) {

		System.out.println(content.getCategory());
		
		String temp[]=content.getCategory().split("=");
		
		ListContent listcontent=new ListContent();
		listcontent.setId(Long.parseLong(temp[0]));
		listcontent.setArticleid(Long.parseLong(temp[1]));
		
		Article article=articleRepository.findById(Long.parseLong(temp[1])).get();
		listcontent.setCategory(article.getCategory());
		listcontent.setTitle(article.getTitle());
		
		listcontentRepository.save(listcontent);
		
		return "redirect:/home";
	}
	
	public String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}

}
