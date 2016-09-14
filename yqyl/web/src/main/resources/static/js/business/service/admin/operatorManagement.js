layoutApp.controller('operatorManagementController', function($scope, $http,
		$window) {
	$scope.paging = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.getOperators = function(id, staffNo, name, status,
			paging) {
		var ajaxUrl = "/ajax/service/admin/operator";
		var prefix = "?";

		if (id != null && id != "") {
			ajaxUrl += prefix + "id=" + id;
			prefix = "&";
		}

		if (staffNo != null && staffNo != "") {
			ajaxUrl += prefix + "staffNo=" + staffNo;
			prefix = "&";
		}

		if (name != null && name != "") {
			ajaxUrl += prefix + "name=" + name;
			prefix = "&";
		}

		if (status != null && status != "") {
			ajaxUrl += prefix + "status=" + status;
			prefix = "&";
		}

		ajaxUrl += prefix + "pageIndex=" + (paging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + paging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.operators = response.data;
			response.meta.paging.pageIndex++;
			$scope.paging = response.meta.paging;
		}).error(function(response) {
		});
	};

	$scope.pageChanged = function() {
		$scope.getOperators(null, null, null, null, $scope.paging);
	}

	$scope.getOperators(null, null, null, null, $scope.paging);
});