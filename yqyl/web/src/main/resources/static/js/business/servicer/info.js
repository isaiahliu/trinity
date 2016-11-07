layoutApp.directive('uploadLogo', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.uploadLogo);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('contentController', function($scope, $http, $window, errorHandler) {
	$scope.audited = true;

	$http({
		method : "GET",
		url : "/ajax/service/supplier?searchScope=me&rsexp=account&searchAllStatus=true"
	}).success(
			function(response) {
				if (response.data.length > 0) {
					$scope.serviceSupplierClient = response.data[0];
					$scope.logoUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.logo;
					$scope.audited =
							$scope.serviceSupplierClient.status.code == 'A'
									|| $scope.serviceSupplierClient.status.code == 'D';
				} else {
					$scope.step = "ANNOUNCEMENT1";
				}
			}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.accept = function() {
		$scope.step = "ANNOUNCEMENT2"
	};

	$http({
		method : "GET",
		url : "/ajax/common/lookup/BANK"
	}).success(function(response) {
		$scope.banks = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$http({
		method : "GET",
		url : "/ajax/common/lookup/CMTYPE"
	}).success(function(response) {
		$scope.companyTypes = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$http({
		method : "GET",
		url : "/ajax/common/lookup/ACCTYPE"
	}).success(function(response) {
		$scope.accountTypes = response.data;
	}).error(function(response) {
		errorHandler($scope, response);
	});

	$scope.registerSupplier =
			function() {
				$http({
					method : "POST",
					url : "/ajax/service/supplier/register"
				}).success(
						function(response) {
							if (response.data.length > 0) {
								$scope.serviceSupplierClient = response.data[0];
								$scope.logoUrl = "/ajax/content/image/" + $scope.serviceSupplierClient.logo;
								$scope.audited =
										$scope.serviceSupplierClient.status.code == 'A'
												|| $scope.serviceSupplierClient.status.code == 'D';
							}
						}).error(function(response) {
					errorHandler($scope, response);
				});
			}

	$scope.cancel = function() {
		$window.location.href = "/";
	};

	$scope.next = function() {
		if (!$scope.audited) {
			$http({
				method : "PUT",
				url : "/ajax/service/supplier/propose",
				data : {
					data : [ $scope.serviceSupplierClient ]
				}
			}).success(function(response) {
				$window.location.href = "/servicer/info/material";
			}).error(function(response) {
				errorHandler($scope, response);
			});
		}
	};

	$scope.uploadLogo =
			function(event) {
				if (event.target.files.length > 0) {
					var fd = new FormData();
					fd.append("IMAGE", event.target.files[0]);
					$http({
						method : "POST",
						url : "/ajax/service/supplier/" + $scope.serviceSupplierClient.id + "/upload",
						transformRequest : angular.identity,
						headers : {
							'Content-Type' : undefined
						},
						data : fd
					}).success(
							function(response) {
								$scope.logoUrl =
										'/ajax/content/image/' + $scope.serviceSupplierClient.logo + "?ticks="
												+ new Date().getTime();
							}).error(function(response) {
						errorHandler($scope, response);
					});
				}
			};
});