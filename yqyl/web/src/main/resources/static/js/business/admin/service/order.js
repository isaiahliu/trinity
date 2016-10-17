layoutApp.controller('contentController', function($scope, $http, $window) {
	$scope.dateOptions = {
		dateFormat : 'yy/mm/dd',
	};

	$scope.pagingData = {
		pageIndex : 1,
		pageSize : 10
	};

	$http({
		method : "GET",
		url : "/ajax/common/lookup/ODSTAT"
	}).success(function(response) {
		$scope.orderStatusLookups = response.data;
	}).error(function(response) {
	});

	$scope.searchOrders = function() {
		var ajaxUrl = "/ajax/user/order";

		ajaxUrl += "?pageIndex=" + ($scope.pagingData.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.pagingData.pageSize;

		if ($scope.searchingCategory != undefined) {
			ajaxUrl += "&categoryParent=" + $scope.searchingCategory.id;
		}

		if ($scope.searchingSubCategories != "") {
			ajaxUrl += "&" + $scope.searchingSubCategories;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.orders = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
		});
	};
});