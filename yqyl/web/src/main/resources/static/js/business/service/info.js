layoutApp.controller('contentController', function($scope, $http, $window,
		serviceSupplierClientId) {
	$scope.status = 'desc';

	$http({
		method : "GET",
		url : "/ajax/service/supplier/" + serviceSupplierClientId
	}).success(function(response) {
		$scope.serviceSupplierClient = response.data[0];
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/service/supplier/" + serviceSupplierClientId + "/services"
	}).success(function(response) {
		$scope.services = response.data;
	}).error(function(response) {
	});

	$scope.searchOrders = function() {
		var ajaxUrl = "/ajax/service/supplier/" + serviceSupplierClientId
				+ "/orders";

		ajaxUrl += "?pageIndex=" + ($scope.pagingData.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.pagingData.pageSize;

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

	$scope.pagingData = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.searchOrders();
});