layoutApp.controller('contentController', function($scope, $http, $window) {
	$scope.modeUpdate = false;

	$http({
		method : "GET",
		url : "/ajax/common/lookup/FARELTN"
	}).success(function(response) {
		$scope.relationships = response.data;
	}).error(function(response) {
		$scope.errorMessage = response.errors[0].message;
	});

	$http({
		method : "GET",
		url : "/ajax/user/receiver/me"
	}).success(function(response) {
		$scope.members = response.data;
	}).error(function(response) {
		$scope.errorMessage = response.errors[0].message;
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
			$scope.errorMessage = response.errors[0].message;
		});
	}
});