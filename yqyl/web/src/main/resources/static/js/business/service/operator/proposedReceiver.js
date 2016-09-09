layoutApp.controller('proposedReceiverController', function($scope, $http,
		$window) {
	$scope.paging = {
		pageIndex : 1,
		pageSize : 10
	};

	$scope.getproposalReceivers = function(id, yijincode, name, identity,
			paging) {
		var ajaxUrl = "/ajax/service/operator/proposalReceiver";
		var prefix = "?";

		if (id != null && id != "") {
			ajaxUrl += prefix + "id=" + id;
			prefix = "&";
		}

		if (yijincode != null && yijincode != "") {
			ajaxUrl += prefix + "yijincode=" + yijincode;
			prefix = "&";
		}

		if (name != null && name != "") {
			ajaxUrl += prefix + "name=" + name;
			prefix = "&";
		}

		if (identity != null && identity != "") {
			ajaxUrl += prefix + "identity=" + identity;
			prefix = "&";
		}

		ajaxUrl += prefix + "pageIndex=" + (paging.pageIndex - 1);
		ajaxUrl += "&pageSize=" + paging.pageSize;
		ajaxUrl += "&sortedBy=id_desc";

		$http({
			method : "GET",
			url : ajaxUrl
		}).success(function(response) {
			$scope.receivers = response.data;
			response.meta.paging.pageIndex++;
			$scope.paging = response.meta.paging;
		}).error(function(response) {
		});
	};

	$scope.pageChanged = function() {
		$scope.getproposalReceivers(null, null, null, null, $scope.paging);
	}

	$scope.getproposalReceivers(null, null, null, null, $scope.paging);
});