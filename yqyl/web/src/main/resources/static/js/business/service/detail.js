layoutApp.controller('contentController', function($scope, $http, $window,
		$filter, orderId) {
	$http({
		method : "GET",
		url : "/ajax/user/order" + orderId
	}).success(function(response) {
		$scope.serviceOrder = response.data[0];
	}).error(function(response) {
	});
});