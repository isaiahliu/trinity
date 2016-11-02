layoutApp.controller('headerController', function($scope, $http, $window) {
	$scope.logout = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/logout",
			data : {
				username : $scope.username,
				password : $scope.password
			}
		}).success(function(response) {
			$window.location.href = "/home";
		}).error(function(response) {
			$scope.errorMessage = response.errors[0].message;
		});
	};
});