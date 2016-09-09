layoutApp.controller('auditReceiverController', function($scope, $http,
		$window, receiverData) {
	$scope.receiverData = receiverData;
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