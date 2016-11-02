layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/user/userinfo"
	}).success(function(response) {
		$scope.id = response.data[0].id;
		$scope.username = response.data[0].username;
		$scope.email = response.data[0].email;
		$scope.cellphone = response.data[0].cellphone;
	}).error(function(response) {
		$scope.errorMessage = response.errors[0].message;
	});

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/userinfo",
			data : {
				data : [ {
					id : $scope.id,
					email : $scope.email,
					cellphone : $scope.cellphone
				} ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
			$scope.errorMessage = response.errors[0].message;

		});
	};
});