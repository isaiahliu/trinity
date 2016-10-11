layoutApp.controller('contentController', function($scope, $http, $window, $filter, serviceSupplierClientId,
		selectedServiceInfos, orderId) {
	$scope.dateOptions = {
		dateFormat : 'yy/mm/dd',
	};

	$scope.hours = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 ];

	if (orderId > 0) {
		$http({
			method : "GET",
			url : "/ajax/user/order/" + orderId
		}).success(function(response) {
			$scope.serviceOrder = response.data[0];
			serviceSupplierClientId = $scope.serviceOrder.serviceSubOrders[0].service.serviceSupplierClient.id;

			$http({
				method : "GET",
				url : "/ajax/service/supplier/" + serviceSupplierClientId
			}).success(function(response) {
				$scope.serviceSupplierClient = response.data[0];
			}).error(function(response) {
			});

			$http({
				method : "GET",
				url : "/ajax/service/supplier/" + serviceSupplierClientId + "/services"
			}).success(function(response) {
				$scope.services = response.data;
				for (var i = 0; i < $scope.services.length; i++) {
					for (var j = 0; j < $scope.serviceOrder.serviceSubOrders.length; j++) {
						var subOrder = $scope.serviceOrder.serviceSubOrders[j];
						if (subOrder.service.id == $scope.services[i].id) {
							$scope.services[i].checked = true;
							$scope.services[i].subOrder = {
								serviceDate : subOrder.serviceDate,
								serviceHour : subOrder.serviceHour,
								customAddress : subOrder.address,
								customPhoneNo : subOrder.phone
							};
							break;
						}
					}
				}

			}).error(function(response) {
			});
		}).error(function(response) {
		});
	} else {
		$http({
			method : "GET",
			url : "/ajax/service/supplier/" + serviceSupplierClientId
		}).success(function(response) {
			$scope.serviceSupplierClient = response.data[0];
		}).error(function(response) {
		});

		$http({
			method : "GET",
			url : "/ajax/service/supplier/" + serviceSupplierClientId + "/services"
		}).success(function(response) {
			$scope.services = response.data;
			for (var i = 0; i < $scope.services.length; i++) {
				for (var j = 0; j < selectedServiceInfos.length; j++) {
					if (selectedServiceInfos[j] == $scope.services[i].id) {
						$scope.services[i].checked = true;
						break;
					}
				}
			}

		}).error(function(response) {
		});
	}

	$http({
		method : "GET",
		url : "/ajax/user/receiver/me"
	}).success(function(response) {
		$scope.members = response.data;
	}).error(function(response) {
	});

	$scope.apply = function() {
		var order = {
			id : null
		};
		if (orderId > 0) {
			order.id = orderId;
		}
		order.serviceSubOrders = new Array();

		for (var i = 0; i < $scope.services.length; i++) {
			var serviceInfo = $scope.services[i];

			if (!serviceInfo.checked) {
				continue;
			}

			var address = serviceInfo.subOrder.customAddress;
			if (address == "" || address == null || address == undefined) {
				address = serviceInfo.subOrder.defaultMember.address;
			}

			var phone = serviceInfo.subOrder.customPhoneNo;
			if (phone == "" || phone == null || phone == undefined) {
				phone = serviceInfo.subOrder.defaultMember.cellphoneNo;
			}

			order.serviceSubOrders.push({
				service : {
					id : serviceInfo.id
				},
				serviceDate : serviceInfo.subOrder.serviceDate,
				serviceHour : serviceInfo.subOrder.serviceHour,
				phone : phone,
				address : address
			});
		}

		if (orderId > 0) {
			$http({
				method : "PUT",
				url : "/ajax/user/order/edit",
				data : {
					data : [ order ]
				}
			}).success(function(response) {
				$window.location.href = "/user/order/" + orderId;
			}).error(function(response) {
			});
		} else {
			$http({
				method : "POST",
				url : "/ajax/user/order/proposal",
				data : {
					data : [ order ]
				}
			}).success(function(response) {
				$window.location.href = "/user/order/" + response.data[0].id;
			}).error(function(response) {
			});
		}
	};

	$scope.cancel = function() {
		$window.location.href = "/service/info/" + serviceSupplierClientId;
	};
});