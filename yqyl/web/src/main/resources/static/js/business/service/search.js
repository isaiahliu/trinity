layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/service/category?status=A&rsexp=serviceSubCategories"
	}).success(function(response) {
		$scope.categories = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.selectedCategory == undefined;

	$scope.searchServices = function(newSearch) {
		var paging = $scope.pagingData;
		if (newSearch) {
			paging = {
				pageIndex : 1,
				pageSize : 30
			}
		}

		var ajaxUrl = "/ajax/service/supplier?searchScope=all&rsexp=serviceInfos[serviceCategory]";

		ajaxUrl += "&pageIndex=" + (paging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + paging.pageSize;

		if ($scope.selectedCategory != undefined) {
			ajaxUrl += "&category=" + $scope.selectedCategory.id;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.results = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});