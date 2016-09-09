layoutApp.controller('proposedSupplierController',
		function($scope, $http, $window) {
			$scope.paging = {
				pageIndex : 1,
				pageSize : 10
			};

			$scope.getProposalSuppliers = function(id, name, identity, email,
					paging) {
				var ajaxUrl = "/ajax/service/operator/proposalSupplier";
				var prefix = "?";

				if (id != null && id != "") {
					ajaxUrl += prefix + "id=" + id;
					prefix = "&";
				}

				if (name != null && name != "") {
					ajaxUrl += prefix + "name=" + name;
					prefix = "&";
				}

				if (identity != null && identity != "") {
					ajaxUrl += prefix + "identity=" + identity;
					prefix = "&";
				}

				if (email != null && email != "") {
					ajaxUrl += prefix + "email=" + email;
					prefix = "&";
				}

				ajaxUrl += prefix + "pageIndex=" + (paging.pageIndex - 1);
				ajaxUrl += "&pageSize=" + paging.pageSize;
				ajaxUrl += "&sortedBy=id_desc";

				$http({
					method : "GET",
					url : ajaxUrl
				}).success(function(response) {
					$scope.suppliers = response.data;
					response.meta.paging.pageIndex++;
					$scope.paging = response.meta.paging;
				}).error(function(response) {
				});
			};

			$scope.pageChanged = function() {
				$scope.getProposalSuppliers(null, null, null, null,
						$scope.paging);
			}

			$scope.getProposalSuppliers(null, null, null, null, $scope.paging);
		});