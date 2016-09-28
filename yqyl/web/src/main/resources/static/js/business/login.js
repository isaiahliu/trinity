layoutApp.controller('loginController', function($scope, $http, $window) {
	$scope.isReg = false;

	$scope.loginData = {
		username : "",
		password : ""
	};
	$scope.login = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/authenticate",
			data : $scope.loginData
		}).success(function(response) {
			$window.location.href = "/home";
		}).error(function(response) {

		});
	};

	$scope.loginKeydown = function(e) {
		if (e.keyCode == 13) {
			$scope.login();
		}
	}

	$scope.regKeydown = function(e) {
		if (e.keyCode == 13) {
			$scope.register();
		}
	}

	$scope.registerData = {
		username : "",
		password : ""
	};

	$scope.repeatPassword = "";
	
	$scope.register = function() {
		if ($scope.registerData.password != $scope.repeatPassword) {
			return;
		}

		$http({
			method : "POST",
			url : "/ajax/user/register",
			data : $scope.registerData
		}).success(function(response) {
			$window.location.href = "/login";
		}).error(function(response) {
		});
	};
});