layoutApp.controller('contentController', function($scope, $http, $window,
		$filter) {
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

		if ($scope.filterData.username != undefined
				&& $scope.filterData.username != "") {
			ajaxUrl += "&receiverUserName=" + $scope.filterData.username;
		}

		if ($scope.filterData.supplierId != undefined
				&& $scope.filterData.supplierId != "") {
			ajaxUrl += "&serviceSupplierClientId="
					+ $scope.filterData.supplierId;
		}

		if ($scope.filterData.orderId != undefined
				&& $scope.filterData.orderId != "") {
			ajaxUrl += "&serviceOrderId=" + $scope.filterData.orderId;
		}

		if ($scope.filterData.status != undefined
				&& $scope.filterData.status != "") {
			ajaxUrl += "&status=" + $scope.filterData.status;
		}

		if ($scope.filterData.serviceTime != undefined
				&& $scope.filterData.serviceTime != "") {
			ajaxUrl += "&serviceDate="
					+ $filter('date')
							($scope.filterData.serviceTime, "yyyyMMdd");
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