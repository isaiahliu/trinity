layoutApp.controller('announcement2Controller', function($scope, $http, $window) {
	$scope.agree = function() {
		$window.location.href = "/user/supplier/application";
	};
	$scope.disagree = function() {
		$window.location.href = "/user/supplier";
	};
});