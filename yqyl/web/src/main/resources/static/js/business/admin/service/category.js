layoutApp.controller('contentController', function($scope, $http, $window) {
	$scope.name = "";

	$scope.search = function() {
		var url = "/ajax/service/category?rsexp=serviceSubCategories";

		if ($scope.name != "") {
			url += "&name=" + $scope.name;
		}

		$http({
			method : "GET",
			url : url
		}).success(function(response) {
			$scope.categories = response.data;
		}).error(function(response) {
			$scope.errorMessage = response.errors[0].message;
		});
	}

	$scope.search();

	$scope.edit = function() {
		$window.location.href = "/admin/service/category/edit"
	}
});