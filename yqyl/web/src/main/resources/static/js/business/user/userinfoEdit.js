layoutApp.controller('contentController', function($scope, $http, $window, clientId, errorHandler) {
	$http({
		method : "GET",
		url : "/ajax/common/lookup/FARELTN"
	}).success(function(response) {
		$scope.relationships = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	if (clientId == 0) {
		$scope.client = {
			familyRelationship : {}
		};
	} else {
		$http({
			method : "GET",
			url : "/ajax/user/receiver?id=" + clientId
		}).success(function(response) {
			$scope.client = response.data[0];
		}).error(function(response) {
			errorHandler($scope, response);
		});
	}

	$scope.apply = function() {
		if (clientId == 0) {
			$http({
				method : "POST",
				url : "/ajax/user/receiver",
				data : {
					data : [ $scope.client ]
				}
			}).success(function(response) {
				$window.location.href = "/user/userinfo"
			}).error(function(response) {
				errorHandler($scope, response);
			});
		} else {
			$http({
				method : "PUT",
				url : "/ajax/user/receiver",
				data : {
					data : [ $scope.client ]
				}
			}).success(function(response) {
				$window.location.href = "/user/userinfo"
			}).error(function(response) {
				errorHandler($scope, response);
			});
		}
	}
});