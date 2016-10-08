layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/user/userinfo"
	}).success(function(response) {
		$scope.id = response.data[0].id;
		$scope.yiquan = response.data[0].yiquan;
	}).error(function(response) {
	});

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/userinfo",
			data : {
				data : [ {
					id : $scope.id,
					yiquan : $scope.yiquan
				} ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {

		});
	};
});