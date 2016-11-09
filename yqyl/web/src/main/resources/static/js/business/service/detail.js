layoutApp.controller('contentController',
		function($scope, $http, $window, errorHandler, $filter, serviceOrderId, mode) {
			$http(
					{
						method : "GET",
						url : "/ajax/user/order/" + serviceOrderId
								+ "?rsexp=serviceInfo[serviceSupplierClient,serviceCategory],operations"
					}).success(function(response) {
				$scope.serviceOrder = response.data[0];
			}).error(function(response) {
				errorHandler($scope, response);
			});

			$scope.orderMore = function() {
				$window.location.href = "/service";
			};

			$scope.back = function() {
				if (mode == 'receiver') {
					$window.location.href = "/user";
				} else {
					$window.location.href = "/servicer/order";
				}
			};

			$scope.edit = function() {
				$window.location.href = "/user/order/edit/" + serviceOrderId;
			};

			$scope.pay = function() {
				$http({
					method : "POST",
					url : "/ajax/user/order/pay",
					data : {
						data : [ $scope.serviceOrder ]
					}
				}).success(function(response) {
					$window.location.href = "/user";
				}).error(function(response) {
					errorHandler($scope, response);
				});
			};
		});