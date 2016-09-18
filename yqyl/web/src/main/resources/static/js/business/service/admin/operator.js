layoutApp.controller('operatorController', function($scope, $http, $window,
		operatorId) {
	$http({
		method : "GET",
		url : "/ajax/service/admin/operator/" + operatorId
	}).success(function(response) {
		if (response.errors.length == 0) {
			$scope.operator = response.data[0];
		} else {
			$scope.back();
		}
	}).error(function(response) {
		$scope.back();
	});

	$scope.back = function() {
		$window.location.href = "/service/admin/operator";
	}

	$scope.active = function() {
		$http({
			method : "PUT",
			url : "/ajax/service/admin/operator",
			data : {
				data : [ {
					id : $scope.operator.id,
					name : $scope.operator.name,
					staffNo : $scope.operator.staffNo,
					status : {
						code : 'A'
					}
				} ]

			}
		}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	}

	$scope.disable = function() {
		$http({
			method : "PUT",
			url : "/ajax/service/admin/operator",
			data : {
				data : [ {
					id : $scope.operator.id,
					name : $scope.operator.name,
					staffNo : $scope.operator.staffNo,
					status : {
						code : 'D'
					}
				} ]
			}
		}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	}
});