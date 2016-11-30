layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/user/receiver"
	}).success(function(response) {
		$scope.members = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.addNew = function() {
		$scope.members.push({});
	};

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/receiver",
			data : {
				data : $scope.members
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	}
});