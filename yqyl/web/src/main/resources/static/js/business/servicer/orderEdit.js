layoutApp.controller('contentController', function($scope, $http, $window, serviceOrderId) {
	$http({
		method : "GET",
		url : "/ajax/user/order/" + serviceOrderId + "?rsexp=serviceInfo[serviceCategory]"
	}).success(function(response) {
		$scope.order = response.data[0];
	}).error(function(response) {
	});

	$scope.back = function() {
		$window.location.href = "/servicer/order";
	}

	$scope.apply = function() {
		$window.location.href = "/servicer/order";
	}
});