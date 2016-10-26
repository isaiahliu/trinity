layoutApp.controller('contentController', function($scope, $http, $window, $filter, serviceOrderId) {
	$http({
		method : "GET",
		url : "/ajax/user/order/" + serviceOrderId + "?rsexp=serviceInfo[serviceSupplierClient,serviceCategory]"
	}).success(function(response) {
		$scope.serviceOrder = response.data[0];
	}).error(function(response) {
	});

	$scope.orderMore = function() {
		$window.location.href = "/service";
	};

	$scope.edit = function() {
		$window.location.href = "/user/order/edit/" + serviceOrderId;
	}
});