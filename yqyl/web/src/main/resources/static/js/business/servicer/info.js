layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/service/supplier?searchScope=me"
	}).success(function(response) {
		if (response.errors.length == 0) {
			if (response.data.length > 0) {
				$scope.serviceSupplierClient = response.data[0];
			} else {
				$scope.step = "ANNOUNCEMENT1";
			}
		}
	}).error(function(response) {
	});

	$scope.next = function() {
		$scope.step = "ANNOUNCEMENT2"
	};

	$http({
		method : "GET",
		url : "/ajax/common/lookup/BANK"
	}).success(function(response) {
		$scope.banks = response.data;
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/common/lookup/CMTYPE"
	}).success(function(response) {
		$scope.companyTypes = response.data;
	}).error(function(response) {
	});
	
	$http({
		method : "GET",
		url : "/ajax/common/lookup/ACCTYPE"
	}).success(function(response) {
		$scope.accountTypes = response.data;
	}).error(function(response) {
	});

	$scope.registerSupplier = function() {
		$http({
			method : "POST",
			url : "/ajax/service/supplier/register"
		}).success(function(response) {
			if (response.errors.length == 0) {
				if (response.data.length > 0) {
					$scope.serviceSupplierClient = response.data[0];
				}
			}
		}).error(function(response) {
		});
	}
});