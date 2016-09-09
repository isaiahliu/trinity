layoutApp.controller('supplierController', function($scope, $http, $window,
		supplierData) {
	$scope.supplierData = supplierData;
	$scope.identityCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.identityCopy;
	$scope.licenseCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.licenseCopy;

	$scope.active = function() {
		$window.location.href = "/user/supplier/announcement1";
	};
});