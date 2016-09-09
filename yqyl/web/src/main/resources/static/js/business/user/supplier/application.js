layoutApp.directive('customOnChange', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.customOnChange);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('applicationController', function($scope, $http, $window,
		supplierData) {
	$scope.uploadLicenseCopy = "";
	$scope.uploadIdentityCopy = "";
	$scope.supplierData = supplierData;

	$scope.identityCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.identityCopy;
	$scope.licenseCopyUrl = '/ajax/content/image/'
			+ $scope.supplierData.licenseCopy;

	$scope.back = function() {
		$window.location.href = "/user/supplier";
	};

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/supplier/proposal",
			data : {
				data : [ {
					email : $scope.supplierData.email,
					identity : $scope.supplierData.identity,
					name : $scope.supplierData.name,
					licenseCopy : $scope.supplierData.licenseCopy,
					identityCopy : $scope.supplierData.identityCopy
				} ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
		});
	};

	$scope.upload = function(fileType, fileSource, onFinish) {
		var fd = new FormData();
		fd.append(fileType, fileSource);
		$http({
			method : "POST",
			url : "/ajax/user/supplier/upload",
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			data : fd
		}).success(function(response) {
			onFinish();
		}).error(function(response) {
			onFinish();
		});
	};

	$scope.uploadLicenseCopy = function() {
		$scope.upload('license', $scope.newLicenseCopy, function() {
			$scope.licenseCopyUrl = '/ajax/content/image/'
					+ $scope.supplierData.licenseCopy + "?ticks="
					+ new Date().getTime();
		});

	};

	$scope.uploadIdentityCopy = function() {
		$scope.upload('identity', $scope.newIdentityCopy, function() {
			$scope.identityCopyUrl = '/ajax/content/image/'
					+ $scope.supplierData.identityCopy + "?ticks="
					+ new Date().getTime();
		});
	};

	$scope.selectLicenseCopy = function(event) {
		if (event.target.files.length > 0) {
			$scope.newLicenseCopy = event.target.files[0];
		} else {
			$scope.newLicenseCopy = {};
		}
	};

	$scope.selectIdentityCopy = function(event) {
		if (event.target.files.length > 0) {
			$scope.newIdentityCopy = event.target.files[0];
		} else {
			$scope.newIdentityCopy = {};
		}
	};
});