layoutApp.controller('contentController', function($scope, $http, $window, staffId) {
	$scope.dateOptions = {
		dateFormat : 'yy/mm/dd',
	};

	if (staffId > 0) {
		$http({
			method : "GET",
			url : "/ajax/service/supplier/staff/me?id=" + staffId
		}).success(function(response) {
			$scope.staff = response.data[0];
			if ($scope.staff.photo != undefined && $scope.staff.photo != null) {
				$scope.imageUrl = "/ajax/content/image/" + $scope.staff.photo;
			}
			$http({
				method : "GET",
				url : "/ajax/service/category?status=A&includeChildren=true"
			}).success(function(response) {
				for (var i = 0; i < response.data.length; i++) {
					for (var j = 0; j < response.data[i].serviceSubCategories.length; j++) {
						var currentCategory = response.data[i].serviceSubCategories[j];
						for (var k = 0; k < $scope.staff.serviceCategories.length; k++) {
							if (currentCategory.id == $scope.staff.serviceCategories[k].id) {
								currentCategory.checked = true;
								continue;
							}
						}
					}
				}

				$scope.categories = response.data;

			}).error(function(response) {
			});
		}).error(function(response) {
		});
	} else {
		$scope.staff = {
			id : null,
			name : "",
			dob : null,
			identityCard : "",
			staffRate : ""
		};

		$http({
			method : "GET",
			url : "/ajax/service/category?status=A&includeChildren=true"
		}).success(function(response) {
			$scope.categories = response.data;

		}).error(function(response) {
		});
	}

	$scope.selectImage = function(event) {
		if (event.target.files.length > 0) {
			$scope.newImage = event.target.files[0];
		} else {
			$scope.newImage = {};
		}
	};

	$scope.uploadPhoto = function() {
		var fd = new FormData();
		fd.append("IMAGE", $scope.newImage);
		$http({
			method : "POST",
			url : "/ajax/service/supplier/staff/" + staffId + "/upload",
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			},
			data : fd
		}).success(function(response) {
			$scope.imageUrl = '/ajax/content/image/' + $scope.staff.photo + "?ticks=" + new Date().getTime();
		})
	};

	$scope.apply = function() {
		if (staffId > 0) {
			$http({
				method : "PUT",
				url : "/ajax/service/supplier/staff",
				data : {
					data : [ $scope.staff ]
				}
			}).success(function(response) {
				$window.location.href = "/servicer/staff"
			}).error(function(response) {
			});
		} else {
			$http({
				method : "POST",
				url : "/ajax/service/supplier/staff",
				data : {
					data : [ $scope.staff ]
				}
			}).success(function(response) {
				$window.location.href = "/servicer/staff"
			}).error(function(response) {
			});
		}
	};
	$scope.back = function() {
		$window.location.href = "/servicer/staff";

	};
});