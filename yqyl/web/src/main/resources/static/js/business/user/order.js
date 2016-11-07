layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$scope.filter = {
		selectedStatus : "",
		selectedPaymentMethod : ""
	};

	$scope.orderPaging = {
		pageIndex : 1,
		pageSize : 10
	};

	$http({
		method : "GET",
		url : "/ajax/common/lookup/ODSTAT"
	}).success(function(response) {
		$scope.statuses = response.data;
		$http({
			method : "GET",
			url : "/ajax/common/lookup/PMMTHD"
		}).success(function(response) {
			$scope.paymentMethods = response.data;

			$scope.populateOrders();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.populateOrders = function() {
		var ajaxUrl = "/ajax/user/order?searchScope=me&rsexp=serviceInfo[serviceCategory,serviceSupplierClient]";

		ajaxUrl += "&pageIndex=" + ($scope.orderPaging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.orderPaging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		if ($scope.filter.selectedStatus != "") {
			ajaxUrl += "&status=" + $scope.filter.selectedStatus;
		}

		if ($scope.filter.selectedPaymentMethod != "") {
			ajaxUrl += "&paymentMethod=" + $scope.filter.selectedPaymentMethod;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.orders = response.data;
			response.meta.paging.pageIndex++;
			$scope.orderPaging = response.meta.paging;
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};

	$scope.appraise = function(id) {
		$window.location.href = "/user/order/appraise/" + id;
	};

	$scope.$watch('filter.selectedStatus', function(newValue, oldValue) {
		if (newValue != oldValue) {
			$scope.populateOrders();
		}
	});

	$scope.$watch('filter.selectedPaymentMethod', function(newValue, oldValue) {
		if (newValue != oldValue) {
			$scope.populateOrders();
		}
	});
});