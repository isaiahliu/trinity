layoutApp.controller('auditReceiverController', function($scope, $http,
		$window, receiverId) {
	if (receiverId > 0) {
		$http({
			method : "GET",
			url : "/ajax/service/operator/audit/receiver/" + receiverId
		}).success(function(response) {
			$scope.receiverData = response.data[0];
		}).error(function(response) {
			$scope.receiverData = {
				id : 0
			}
		});
	} else {
		$scope.receiverData = {
			id : 0
		}
	}
	$scope.back = function() {
		$window.location.href = "/service/operator/audit/receiver";
	}

	$scope.audit = function() {
		$http(
				{
					method : "PUT",
					url : "/ajax/service/operator/audit/receiver/"
							+ $scope.receiverData.id
				}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	}
});