layoutApp.controller('receiverinfoController', function($scope, $http, $window,
		$filter, receiverId) {

	if (receiverId > 0) {
		$http({
			method : "GET",
			url : "/ajax/user/receiver/" + receiverId
		}).success(function(response) {
			$scope.receiver = response.data[0];
		}).error(function(response) {
		});
	} else {
		$scope.receiver = {
			id : null
		};
	}

	$scope.dateOptions = {
		dateFormat : 'yy/mm/dd',
	}

	$scope.back = function() {
		$window.location.href = "/user/receiver";
	};

	$scope.apply = function() {
		$http({
			method : "PUT",
			url : "/ajax/user/receiver",
			data : {
				data : [ {
					id : $scope.receiver.id,
					name : $scope.receiver.name,
					gender : {
						code : $scope.receiver.gender.code
					},
					dob : $filter('date')($scope.receiver.dob, 'yyyy/MM/dd'),
					identityCard : $scope.receiver.identityCard,
					cellphoneNo : $scope.receiver.cellphoneNo,
					homephoneNo : $scope.receiver.homephoneNo,
					email : $scope.receiver.email,
					address : $scope.receiver.address,
					yijinCode : $scope.receiver.yijinCode,
					healthStatus : $scope.receiver.healthStatus
				} ]
			}
		}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	};

	$scope.cancel = function() {
		$http({
			method : "DELETE",
			url : "/ajax/user/receiver/" + $scope.receiver.id
		}).success(function(response) {
			$scope.back();
		}).error(function(response) {
		});
	};
});