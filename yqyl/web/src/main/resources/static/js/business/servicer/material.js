layoutApp.directive('uploadPhoto', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.uploadPhoto);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$scope.audited = true;

	$http({
		method : "GET",
		url : "/ajax/service/supplier?searchScope=me&rsexp=material"
	}).success(function(response) {
		if (response.data.length > 0) {
			$scope.serviceSupplierClient = response.data[0];
			$scope.licenseUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.licenseCopy;
			$scope.corporateCheckingUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.corporateCheckingCopy;
			$scope.contractUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.contractCopy;
			$scope.applicationUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.applicationCopy;
			$scope.businessLicenseUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.businessLicenseCopy;
			$scope.jcvUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.jcv;
			$scope.audited = $scope.serviceSupplierClient.status.code == 'A' || $scope.serviceSupplierClient.status.code == 'D';
		}
	}).error(function(response) {
		errorHandler($scope, response);
	});
	$scope.uploadLicense = function(event) {
		$scope.uploadPhoto(event, "license", function() {
			$scope.licenseUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.licenseCopy + "?ticks="
					+ new Date().getTime();
		});
	};
	$scope.uploadCorporateChecking = function(event) {
		$scope.uploadPhoto(event, "corporateChecking", function() {
			$scope.corporateCheckingUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.corporateCheckingCopy + "?ticks="
					+ new Date().getTime();
		});
	};
	$scope.uploadContract = function(event) {
		$scope.uploadPhoto(event, "contract", function() {
			$scope.contractUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.contractCopy + "?ticks="
					+ new Date().getTime();
		});
	};
	$scope.uploadApplication = function(event) {
		$scope.uploadPhoto(event, "application", function() {
			$scope.applicationUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.applicationCopy + "?ticks="
					+ new Date().getTime();
		});
	};
	$scope.uploadBusinessLicense = function(event) {
		$scope.uploadPhoto(event, "businessLicense", function() {
			$scope.businessLicenseUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.businessLicenseCopy + "?ticks="
					+ new Date().getTime();
		});
	};
	$scope.uploadJcv = function(event) {
		$scope.uploadPhoto(event, "jcv", function() {
			$scope.jcvUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.material.jcv + "?ticks=" + new Date().getTime();
		});
	};
	$scope.uploadPhoto = function(event, file, urlHandler) {
		if (event.target.files.length > 0) {
			var fd = new FormData();
			fd.append(file, event.target.files[0]);
			$http({
				method : "POST",
				url : "/ajax/service/supplier/" + $scope.serviceSupplierClient.id + "/material/upload",
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				},
				data : fd
			}).success(function(response) {
				urlHandler();
			}).error(function(response) {
				errorHandler($scope, response);
			});
		}
	};

	$scope.save = function() {
		$http({
			method : "PUT",
			url : "/ajax/service/supplier/propose",
			data : {
				data : [ $scope.serviceSupplierClient ]
			}
		}).success(function(response) {
			$scope.back();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};

	$scope.apply = function() {
		$scope.serviceSupplierClient.status.code = 'P';
		$scope.save();
	};

	$scope.cancel = function() {
		$window.location.href = "/";
	};

	$scope.back = function() {
		$window.location.href = "/servicer";
	};
});