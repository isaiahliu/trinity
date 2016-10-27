layoutApp.directive('receiptUpload', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var uploadReceipt = scope.$eval(attrs.receiptUpload);
			element.bind('change', function(event) {
				if (event.target.files.length > 0) {
					uploadReceipt(scope.order, event.target.files[0]);
				}
			});
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
			$scope.orders = response.data;
			response.meta.paging.pageIndex++;
			$scope.pagingData = response.meta.paging;
		}).error(function(response) {
		});
	};

	$scope.uploadReceipt = function(order, file) {
		var fd = new FormData();
		fd.append("IMAGE", file);
		$http({
			method : "POST",
			url : "/ajax/user/order/" + order.id + "/receipt",
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			data : fd
		}).success(function(response) {
			order.receipt = response.data[0];
		})
	};

	$scope.assignPrepare = function(order) {
		$http({
			method : "GET",
			url : "/ajax/service/supplier/staff/available?serviceCategoryId=" + order.serviceInfo.serviceCategory.id
		}).success(function(response) {
			order.availableStaffs = response.data;
			order.assigning = true;
		})
	}

	$scope.assignCancel = function(order) {
		order.assigning = false;
	}

	$scope.assign = function(order) {
		if (order.availableStaffs.length == 0) {
			order.assigning = false;
			return;
		}

		if (order.selectedStaff == undefined || order.selectedStaff == "") {
			order.selectedStaff = order.availableStaffs[Math.floor(Math.random() * order.availableStaffs.length)].id
		}
		$http({
			method : "POST",
			url : "/ajax/user/order/assign",
			data : {
				data : [ {
					id : order.id,
					staff : {
						id : order.selectedStaff
					}
				} ]
			}
		}).success(function(response) {
			order.assigning = false;
			order.status.code = 'I';
			for (var i = 0; i < $scope.statuses.length; i++) {
				if ($scope.statuses[i].code == 'I') {
					order.status.message = $scope.statuses[i].message;
					break;
				}
			}
		})
	}
});