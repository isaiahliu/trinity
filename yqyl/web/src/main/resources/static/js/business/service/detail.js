layoutApp.controller('contentController', function($scope, $http, $window, errorHandler, $filter, serviceOrderId) {
	$http({
		method : "GET",
		url : "/ajax/user/order/" + serviceOrderId + "?rsexp=serviceInfo[serviceSupplierClient,serviceCategory]"
	}).success(function(response) {
		$scope.serviceOrder = response.data[0];
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.orderMore = function() {
		$window.location.href = "/service";
	};

	$scope.edit = function() {
		$window.location.href = "/user/order/edit/" + serviceOrderId;
	};

	$scope.pay = function() {
		$http({
			method : "POST",
			url : "/ajax/user/order/pay",
			data : {
				data : [ $scope.serviceOrder ]
			}
		}).success(function(response) {
			$window.location.href = "/user";
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});