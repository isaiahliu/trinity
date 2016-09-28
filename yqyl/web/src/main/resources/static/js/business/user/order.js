layoutApp.controller('orderController', function($scope, $http, $window) {
	$scope.unprocessedOrderPopulated = false;
	$scope.processingOrderPopulated = false;
	$scope.processedOrderPopulated = false;

	$scope.$watch('status', function(newValue, oldValue) {
		switch (newValue) {
		case "unprocessedOrder":
			if (!$scope.unprocessedOrderPopulated) {
				$scope.unprocessedOrderPopulated = true;
			}
			break;
		case "processingOrder":
			if (!$scope.processingOrderPopulated) {
				$scope.processingOrderPopulated = true;
			}
			break;
		case "processedOrder":
			if (!$scope.processedOrderPopulated) {
				$scope.processedOrderPopulated = true;
			}
			break;
		default:
			break;
		}
	});

	$scope.status = 'unprocessedOrder';
});