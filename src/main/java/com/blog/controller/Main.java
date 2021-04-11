package com.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		String user_id = (String) session.getAttribute("user_id");
		List<Article> articles_list = articleRepository.findByUser(user_id);
		model.addAttribute("articles_list", articles_list);
		return "article_list";
	}

	@GetMapping("/articles/{id}")
	public String articles(@PathVariable Long id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null)
			return "redirect:/login";
		Article article = restTemplate.getForObject("http://localhost:8080/api/articles/" + id, Article.class);
		model.addAttribute("article", article);
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
		article.setUser((String) session.getAttribute("user_id"));
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
			session.setAttribute("user_id", temp.getUsername());
			return "redirect:/home";
		}
		return "login";
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
		List<ArticleList> list = articlelistRepository
				.findByUserid(accountRepository.findByUsername((String) session.getAttribute("user_id")).getId());
		model.addAttribute("list", list);
		return "list";
	}

	public String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}

}
