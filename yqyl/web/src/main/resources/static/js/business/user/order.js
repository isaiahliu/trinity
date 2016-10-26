layoutApp.controller('contentController', function($scope, $http, $window) {
	$scope.unprocessedOrderPopulated = false;
	$scope.processingOrderPopulated = false;
	$scope.processedOrderPopulated = false;

	$scope.processingOrderPaging = {
		pageIndex : 1,
		pageSize : 10
	};
	$scope.processedOrderPaging = {
		pageIndex : 1,
		pageSize : 10
	};
	$scope.unprocessedOrderPaging = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.populateUnprocessedOrders = function() {
		var ajaxUrl = "/ajax/user/order/unprocessed?rsexp=serviceInfo[serviceCategory,serviceSupplierClient]";

		ajaxUrl += "&pageIndex=" + ($scope.unprocessedOrderPaging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.unprocessedOrderPaging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.unprocessedOrders = response.data;
			response.meta.paging.pageIndex++;
			$scope.unprocessedOrderPaging = response.meta.paging;
		}).error(function(response) {
		});
	}

	$scope.populateProcessedOrders = function() {
		var ajaxUrl = "/ajax/user/order/processed?rsexp=serviceInfo[serviceCategory,serviceSupplierClient]";

		ajaxUrl += "&pageIndex=" + ($scope.processedOrderPaging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.processedOrderPaging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.processedOrders = response.data;
			response.meta.paging.pageIndex++;
			$scope.processedOrderPaging = response.meta.paging;
		}).error(function(response) {
		});
	}
	$scope.populateProcessingOrders = function() {
		var ajaxUrl = "/ajax/user/order/processing?rsexp=serviceInfo[serviceCategory,serviceSupplierClient]";

		ajaxUrl += "&pageIndex=" + ($scope.processingOrderPaging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.processingOrderPaging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.processingOrders = response.data;
			response.meta.paging.pageIndex++;
			$scope.processingOrderPaging = response.meta.paging;
		}).error(function(response) {
		});
	}

	$scope.$watch('status', function(newValue, oldValue) {
		switch (newValue) {
		case "unprocessedOrder":
			if (!$scope.unprocessedOrderPopulated) {
				$scope.unprocessedOrderPopulated = true;
				$scope.populateUnprocessedOrders();
			}
			break;
		case "processingOrder":
			if (!$scope.processingOrderPopulated) {
				$scope.processingOrderPopulated = true;
				$scope.populateProcessingOrders();
			}
			break;
		case "processedOrder":
			if (!$scope.processedOrderPopulated) {
				$scope.processedOrderPopulated = true;
				$scope.populateProcessedOrders();
			}
			break;
		default:
			break;
		}
	});

	$scope.status = 'unprocessedOrder';
	$scope.appraise = function(id) {
		$window.location.href = "/user/order/appraise/" + id;
	};
});