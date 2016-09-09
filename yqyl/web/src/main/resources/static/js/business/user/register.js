layoutApp.controller('registerController', function($scope, $http, $window) {
	$scope.username = "";
	$scope.password = "";
	$scope.repeatPassword = "";
	$scope.register = function() {
		if ($scope.password != $scope.repeatPassword) {
			return;
		}

		$http({
			method : "POST",
			url : "/ajax/user/register",
			data : {
				username : $scope.username,
				password : $scope.password
			}
		}).success(function(response) {
			$window.location.href = "/user/login";
		}).error(function(response) {

		});
	};
});