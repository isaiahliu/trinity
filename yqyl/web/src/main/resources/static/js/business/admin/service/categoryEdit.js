layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/service/category"
	}).success(function(response) {
		$scope.categories = response.data;
	}).error(function(response) {
	});

	$scope.edit = function(category) {
		category.editing = true;
		category.newValue = category.name;
	}
});