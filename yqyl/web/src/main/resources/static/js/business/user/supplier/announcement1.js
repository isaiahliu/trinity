layoutApp.controller('announcement1Controller', function($scope, $http, $window) {
	$scope.agree = function() {
		$window.location.href = "/user/supplier/announcement2";
	};
	$scope.disagree = function() {
		$window.location.href = "/user/supplier";
	};
});