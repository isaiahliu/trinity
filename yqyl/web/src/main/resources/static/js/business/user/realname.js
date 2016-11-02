layoutApp.directive('customOnChange', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.customOnChange);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$scope.verified = true;

	$http({
		method : "GET",
		url : "/ajax/common/lookup/CDTYPE"
	}).success(function(response) {
		$scope.credentialTypes = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$http({
		method : "GET",
		url : "/ajax/user/realname/me"
	}).success(function(response) {
		if (response.data.length > 0) {
			$scope.realnameData = response.data[0];
			var verifyStatus = $scope.realnameData.status.code;
			$scope.verified = verifyStatus == 'V';
			$scope.credentialCopyUrl = '/ajax/content/image/' + $scope.realnameData.credentialCopy;
		}
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/realname",
			data : {
				data : [ $scope.realnameData ]
			}
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};

	$scope.selectCredentialCopy = function(event) {
		if (event.target.files.length > 0) {
			$scope.newCredentialCopy = event.target.files[0];
		} else {
			$scope.newCredentialCopy = {};
		}
	};

	$scope.upload = function() {
		var fd = new FormData();
		fd.append("CREDENTIAL_COPY", $scope.newCredentialCopy);
		$http({
			method : "POST",
			url : "/ajax/user/realname/upload",
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			data : fd
		}).success(function(response) {
			$scope.credentialCopyUrl = '/ajax/content/image/' + $scope.realnameData.credentialCopy + "?ticks=" + new Date().getTime();
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};
});