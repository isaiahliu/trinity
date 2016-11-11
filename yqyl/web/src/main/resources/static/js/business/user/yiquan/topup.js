layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/user/yiquan?searchScope=me&rsexp=balance"
	}).success(function(response) {
		$scope.yiquan = response.data[0];
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.apply = function() {
		$http({
			method : "POST",
			url : "/ajax/user/yiquan/topup",
			data : {
				data : [ $scope.yiquan ]
			}
		}).success(function(response) {
			$window.location.href = "/user/yiquan/topup";
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});