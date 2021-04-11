angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/api/articles/2').
        then(function(response) {
            $scope.article = response.data;
        });
});
