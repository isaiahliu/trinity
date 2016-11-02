var layoutApp = angular.module('layoutApp', [ 'ui.bootstrap', 'ui.date' ]);

layoutApp.value("errorHandler", function(scope, response) {
	if (response.errors != undefined) {
		scope.errorMessage = response.errors[0].message;
	} else {
		scope.errorMessage = "请求失败";
	}
});