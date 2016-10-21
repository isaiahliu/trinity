layoutApp.directive('customOnChange', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.customOnChange);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('contentController', function($scope, $http, $window, serviceInfoId) {
	$http({
		method : "GET",
		url : "/ajax/common/lookup/PMMTHD"
	}).success(function(response) {
		$scope.paymentMethods = response.data;
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/common/lookup/PMTYPE"
	}).success(function(response) {
		$scope.paymentTypes = response.data;
	}).error(function(response) {
	});

	$http({
		method : "GET",
		url : "/ajax/service/category/parents?status=A"
	}).success(function(response) {
		$scope.categories = response.data;
	}).error(function(response) {
	});
	var subCategoryMapping = {};

	$scope.populateSubCategory = function() {
		if (subCategoryMapping[$scope.serviceInfo.serviceCategory.parent.id] == undefined) {
			$scope.subCategories = [];
			$http({
				method : "GET",
				url : "/ajax/service/category/children/" + $scope.serviceInfo.serviceCategory.parent.id + "?status=A"
			}).success(function(response) {
				subCategoryMapping[$scope.serviceInfo.serviceCategory.parent.id] = response.data;
				$scope.subCategories = subCategoryMapping[$scope.serviceInfo.serviceCategory.parent.id];
			}).error(function(response) {
			});
		} else {
			$scope.subCategories = subCategoryMapping[$scope.serviceInfo.serviceCategory.parent.id];
		}
	}

	if (serviceInfoId > 0) {
		$http({
			method : "GET",
			url : "/ajax/service/me?id=" + serviceInfoId
		}).success(function(response) {
			$scope.serviceInfo = response.data[0];
			$scope.serviceInfo.active = $scope.serviceInfo.status.code == 'A'
			$scope.populateSubCategory();
			if ($scope.serviceInfo.image != undefined && $scope.serviceInfo.image != null) {
				$scope.imageUrl = "/ajax/content/image/" + $scope.serviceInfo.image;
			}
		}).error(function(response) {
		});
	} else {
		$scope.serviceInfo = {
			name : "",
			price : "",
			status : {
				code : 'A'
			},
			serviceCategory : {
				id : null,
				parent : {
					id : null
				}
			}
		};

		$scope.serviceInfo.active = true;
	}

	$scope.selectImage = function(event) {
		if (event.target.files.length > 0) {
			$scope.newImage = event.target.files[0];
		} else {
			$scope.newImage = {};
		}
	};

	$scope.uploadPhoto = function() {
		var fd = new FormData();
		fd.append("IMAGE", $scope.newImage);
		$http({
			method : "POST",
			url : "/ajax/service/" + serviceInfoId + "/upload",
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			data : fd
		}).success(function(response) {
			$scope.imageUrl = '/ajax/content/image/' + $scope.serviceInfo.image + "?ticks=" + new Date().getTime();
		})
	};

	$scope.apply = function() {
		$scope.serviceInfo.status.code = $scope.serviceInfo.active ? 'A' : 'O';

		if (serviceInfoId > 0) {
			$http({
				method : "PUT",
				url : "/ajax/service/",
				data : {
					data : [ $scope.serviceInfo ]
				}
			}).success(function(response) {
				$window.location.href = "/servicer/service"
			}).error(function(response) {
			});
		} else {
			$http({
				method : "POST",
				url : "/ajax/service/",
				data : {
					data : [ $scope.serviceInfo ]
				}
			}).success(function(response) {
				$window.location.href = "/servicer/service"
			}).error(function(response) {
			});
		}
	};
	$scope.remove = function() {
		$http({
			method : "DELETE",
			url : "/ajax/service/" + serviceInfoId
		}).success(function(response) {
			$window.location.href = "/servicer/service"
		}).error(function(response) {
		});
	};
	$scope.back = function() {
		$window.location.href = "/servicer/service"
	};
});