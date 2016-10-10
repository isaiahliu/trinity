layoutApp.controller('contentController', function($scope, $http, $window,
		$filter, serviceSupplierClientId, selectedServiceInfos) {
	$scope.dateOptions = {
		dateFormat : 'yy/mm/dd',
	};

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

	$http({
		method : "GET",
		url : "/ajax/user/receiver/me"
	}).success(function(response) {
		$scope.members = response.data;
	}).error(function(response) {
	});

	$scope.apply = function() {
		var order = {};
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
	};

	$scope.cancel = function() {
		$window.location.href = "/service/info/" + serviceSupplierClientId;
	};
});