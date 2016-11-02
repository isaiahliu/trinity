layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/user/yiquan"
	}).success(function(response) {
		$scope.yiquan = response.data[0].code;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/yiquan/bind",
			data : {
				data : [ {
					code : $scope.yiquan
				} ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});