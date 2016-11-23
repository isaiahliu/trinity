layoutApp.controller('contentController', function($scope, $http, $window, errorHandler, serviceSupplierClientId) {
	$scope.status = 'desc';

	$http({
		method : "GET",
		url : "/ajax/service/supplier/" + serviceSupplierClientId + "?searchAllStatus=true"
	}).success(
			function(response) {
				$scope.serviceSupplierClient = response.data[0];
				$http(
						{
							method : "GET",
							url : "/ajax/service/supplier/" + serviceSupplierClientId
									+ "/services?rsexp=serviceCategory&searchAllStatus=true"
						}).success(function(response) {
					$scope.services = response.data;

					$scope.searchOrders();
				}).error(function(response) {
					errorHandler($scope, response);
				});
			}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.searchOrders =
			function() {
				var ajaxUrl =
						"/ajax/service/supplier/orders?rsexp=serviceInfo[serviceCategory],appraise&searchScope=all&serviceSupplierClientId="
								+ serviceSupplierClientId;

				ajaxUrl += "&pageIndex=" + ($scope.pagingData.pageIndex - 1);
				ajaxUrl += "&pageSize=" + $scope.pagingData.pageSize;

				$http({
					method : "GET",
					url : ajaxUrl
				}).success(function(response) {
					$scope.orders = response.data;
					response.meta.paging.pageIndex++;
					$scope.pagingData = response.meta.paging;
				}).error(function(response) {
					errorHandler($scope, response);
				});
			};

	$scope.pagingData = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.apply = function() {
		$window.location.href = "/service/proposal/" + serviceSupplierClientId;
	};

	$scope.passAudit = function() {
		$http({
			method : "PUT",
			url : "/ajax/service/supplier/audit/" + serviceSupplierClientId
		}).success(function(response) {
			$window.location.href = "/admin/supplier";
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
	$scope.denyAudit = function() {
		$window.location.href = "/admin/supplier";
	};

	$scope.login = function() {
		$window.location.href = "/login";
	};
});