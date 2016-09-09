layoutApp.controller('receiverinfoController', function($scope, $http, $window,
		$filter, receiver) {
	$scope.receiver = receiver;

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
					cellphoneNo : $scope.receiver.cellphone,
					homephoneNo : $scope.receiver.homephone,
					email : $scope.receiver.email,
					address : $scope.receiver.address,
					yijinCode : $scope.receiver.yijinCode
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