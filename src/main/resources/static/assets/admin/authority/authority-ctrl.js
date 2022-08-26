app.controller("authority-ctrl", function($scope, $http, $location) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initialize = function() {
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})
		
		$http.get("/rest/nguoidung?admin=true").then(resp => {
			$scope.admins = resp.data;			
		})
		
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
			console.log("item", $scope.authorities);
		}).catch(error => {
			console.log(error);
			$location.path("/unauthorized");
		})
	}
	
	$scope.authority_of = function(a, r){
		if($scope.authorities){
			return $scope.authorities.find(ur => 
				ur.nguoiDung.id == a.id && ur.role.id == r.id
			);
		}
	}
	
	$scope.authority_changed = function(a, r){
		var authority = $scope.authority_of(a, r);
		if(authority){ // 	đã cấp quyền => thu hồi quyền (xóa)
			$scope.revoke_authority(authority);
		}else{ // chưa được cấp quyền => cấp quyền (thêm mới)
			authority = {nguoiDung:a, role:r};
			$scope.grant_authority(authority);
		}
	}
	
	// cấp quyền
	$scope.grant_authority = function(authority){
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data)
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Cấp quyền thành công!'
			});
			$scope.initialize();
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Cấp quyền thất bại!'
			});
			$scope.initialize();
			console.log("Error", error);
		})
	}
	
	// hủy quyền
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.push(index, 1);
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Thu hồi quyền thành công!'
			});
			$scope.initialize();
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Thu hồi quyền thất bại!'
			});
			$scope.initialize();
			console.log("Error", error);
		})
	}
	
	$scope.initialize();
});