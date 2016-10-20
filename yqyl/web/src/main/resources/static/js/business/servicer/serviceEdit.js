layoutApp.controller('contentController', function($scope, $http, $window, serviceInfoId) {
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

	$scope.uploadPhoto = function() {
	};
	$scope.apply = function() {
	};
	$scope.remove = function() {
	};
	$scope.back = function() {
		$window.location.href = "/servicer/service"
	};
});