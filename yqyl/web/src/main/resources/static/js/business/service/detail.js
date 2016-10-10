layoutApp.controller('contentController', function($scope, $http, $window,
		$filter, serviceOrderId) {
	$http({
		method : "GET",
		url : "/ajax/user/order/" + serviceOrderId
	}).success(function(response) {
		$scope.serviceOrder = response.data[0];
	}).error(function(response) {
	});
});