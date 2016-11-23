layoutApp.controller('contentController', function($scope, $http, $window, $location, $rootScope, errorHandler) {
	$scope.searchServices = function(newSearch) {
		var paging = $scope.pagingData;
		if (newSearch) {
			paging = {
				pageIndex : 1,
				pageSize : 30
			}
		}

		var ajaxUrl = "/ajax/service?rsexp=serviceSupplierClient,serviceCategory&searchScope=all";

		ajaxUrl += "&pageIndex=" + (paging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + paging.pageSize;

		if ($scope.categoryId != undefined) {
			ajaxUrl += "&categoryId=" + $scope.categoryId;
		} else if ($scope.parentCategoryId != undefined) {
			ajaxUrl += "&parentCategoryId=" + $scope.parentCategoryId;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.results = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
			errorHandler($scope, response);
		});
	};

	var reloadData = function() {
		if ($scope.categories == undefined) {
			return;
		}
		$scope.parentCategory = undefined;
		$scope.category = undefined;

		$scope.parentCategoryId = $location.search().parentCategory;
		$scope.categoryId = $location.search().category;

		if ($scope.parentCategoryId != undefined || $scope.categoryId != undefined) {
			for (var i = 0; i < $scope.categories.length; i++) {
				var category = $scope.categories[i];

				if ($scope.parentCategoryId == undefined) {
					for (var j = 0; j < category.serviceSubCategories.length; j++) {
						if (category.serviceSubCategories[j].id == $scope.categoryId) {
							$scope.category = category.serviceSubCategories[j];
							$scope.parentCategory = category;
							category.expanding = true;
							break;
						}
					}

					if (category.expanding) {
						break;
					}
				} else if (category.id == $scope.parentCategoryId) {
					$scope.parentCategory = category;
					category.expanding = true;
					break;
				}
			}
		}

		$scope.searchServices(true);
	};

	$rootScope.$on('$locationChangeSuccess', reloadData);

	$http({
		method : "GET",
		url : "/ajax/service/category?status=A&rsexp=serviceSubCategories"
	}).success(function(response) {
		$scope.categories = response.data;

		reloadData();
	}).error(function(response) {
		errorHandler($scope, response);
	});

});