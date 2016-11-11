layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/user/yiquan?searchScope=me"
	}).success(function(response) {
		$scope.yiquan = response.data[0];
	}).error(function(response) {
		if (response.errors != undefined && response.errors[0] != undefined) {
			$scope.binding = true;
		} else {
			errorHandler($scope, response);
		}
	});

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/yiquan/bind",
			data : {
				data : [ $scope.newYiquan ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});