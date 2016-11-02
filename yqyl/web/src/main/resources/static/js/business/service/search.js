layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/service/category?status=A"
	}).success(function(response) {
		$scope.categories = response.data;
		if ($scope.categories.length > 0) {
			$scope.selectCategory($scope.categories[0]);
		}

	}).error(function(response) {
		$scope.errorMessage = response.errors[0].message;
	});

	$scope.selectCategory = function(category) {
		$scope.selectedCategory = category;

		if (category.children == undefined) {
			$http({
				method : "GET",
				url : "/ajax/service/category?&status=A&parentId=" + category.id
			}).success(function(response) {
				category.children = response.data;
			}).error(function(response) {
				$scope.errorMessage = response.errors[0].message;
			});
		}
	};

	$scope.searchServices = function(newSearch) {
		var paging = $scope.pagingData;
		if (newSearch) {
			paging = {
				pageIndex : 1,
				pageSize : 10
			}
		}

		$scope.searchingCategory = undefined;
		$scope.searchingSubCategories = "";
		if ($scope.selectedCategory != undefined) {
			$scope.searchingCategory = $scope.selectedCategory;

			for (var index = 0; index < $scope.selectedCategory.children.length; index++) {
				if ($scope.selectedCategory.children[index].checked) {
					if ($scope.searchingSubCategories != "") {
						$scope.searchingSubCategories += '&';
					}

					$scope.searchingSubCategories += "categoryChildren=" + $scope.selectedCategory.children[index].id;
				}
			}
		}

		var ajaxUrl = "/ajax/service/supplier?rsexp=serviceInfos[serviceCategory]";

		ajaxUrl += "&pageIndex=" + (paging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + paging.pageSize;

		if ($scope.searchingCategory != undefined) {
			ajaxUrl += "&categoryParent=" + $scope.searchingCategory.id;
		}

		if ($scope.searchingSubCategories != "") {
			ajaxUrl += "&" + $scope.searchingSubCategories;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.results = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
			$scope.errorMessage = response.errors[0].message;
		});
	};

	$scope.publish = function() {
		$window.location.href = "/service/publish"
	}
});