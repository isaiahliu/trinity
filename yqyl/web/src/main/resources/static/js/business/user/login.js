layoutApp.controller('loginController', function($scope, $http, $window) {
	$scope.username = "";
	$scope.password = "";
	$scope.login = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/authenticate",
			data : {
				username : $scope.username,
				password : $scope.password
			}
		}).success(function(response) {
			$window.location.href = "/home";
		}).error(function(response) {

		});
	};
	$scope.register = function() {
		$window.location.href = "/user/register";
	};
	$scope.keydown = function(e) {
		if (e.keyCode == 13) {
			$scope.login();
		}
	}
});