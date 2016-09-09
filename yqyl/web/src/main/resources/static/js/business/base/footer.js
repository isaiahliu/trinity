layoutApp.controller('footerController', function($scope, $http, $window) {
	$scope.refresh = function() {
		$http({
			method : "PUT",
			url : "/ajax/common/resource"
		}).success(function(response) {
			$window.location.reload();
		}).error(function(response) {
		});
	};
});