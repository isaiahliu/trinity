layoutApp.controller('contentController', function($scope, $http, $window) {
	$scope.pagingData = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.filterData = {
		id : "",
		name : ""
	};

	$scope.searchStaffs = function() {
		var ajaxUrl = "/ajax/service/supplier/staff/me";

		ajaxUrl += "?pageIndex=" + ($scope.pagingData.pageIndex - 1);
		ajaxUrl += "&pageSize=" + $scope.pagingData.pageSize;

		if ($scope.filterData.id != undefined && $scope.filterData.id != "") {
			ajaxUrl += "&id=" + $scope.filterData.id;
		}

		if ($scope.filterData.name != undefined && $scope.filterData.name != "") {
			ajaxUrl += "&name=" + $scope.filterData.name;
		}

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.staffs = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
		});
	}
});