layoutApp.controller('auditSupplierController', function($scope, $http,
		$window, supplierData) {
	$scope.supplierData = supplierData;
	$scope.identityCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.identityCopy;
	$scope.licenseCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.licenseCopy;

	$scope.back = function() {
		$window.location.href = "/service/operator/audit/supplier";
	}

	$scope.audit = function() {
		$http(
				{
					method : "PUT",
					url : "/ajax/service/operator/audit/supplier/"
							+ $scope.supplierData.id
				}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	}
});