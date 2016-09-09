layoutApp.controller('receiversController', function($scope, $http, $window) {
	$scope.add = function() {
		$window.location.href = "/user/receiver/new";
	};
});