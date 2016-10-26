layoutApp.directive('customOnChange', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var onChangeHandler = scope.$eval(attrs.customOnChange);
			element.bind('change', onChangeHandler);
		}
	};
});

layoutApp.controller('contentController', function($scope, $http, $window) {
	$http({
		method : "GET",
		url : "/ajax/common/lookup/ODSTAT"
	}).success(function(response) {
		$scope.statuses = response.data;
	}).error(function(response) {
	});

	$scope.pagingData = {
		pageIndex : 1,
		pageSize : 10
	};
	$scope.filterData = {
		id : "",
		category : "",
		status : ""
	};

	$scope.searchOrders = function() {
		var ajaxUrl = "/ajax/user/order?searchScope=supplier&rsexp=serviceInfo[serviceCategory]";

		ajaxUrl += "&pageIndex=" + ($scope.pagingData.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.pagingData.pageSize;
		ajaxUrl += "&sortedBy=id_desc";
		if ($scope.filterData.id != undefined && $scope.filterData.id != "") {
			ajaxUrl += "&id=" + $scope.filterData.id;
		}

		if ($scope.filterData.category != undefined && $scope.filterData.category != "") {
			ajaxUrl += "&category=" + $scope.filterData.category;
		}

		if ($scope.filterData.status != undefined && $scope.filterData.status != "") {
			ajaxUrl += "&status=" + $scope.filterData.status;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			for (var i = 0; i < response.data.length; i++) {
				response.data[i].selectImage = function(event) {
					if (event.target.files.length > 0) {
						response.data[i].newImage = event.target.files[0];
					} else {
						response.data[i].newImage = {};
					}
				};
			}
			$scope.orders = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
		});
	};
});