app.controller("user-ctrl", function($scope, $http, $location) {
	$scope.users = []; //Hiển thị trên table
	$scope.form = {}; //Hiển thị trên form

	//Load tất cả người dùng từ server
	$scope.initialize = function() {
		// Load người dùng lên table
		$http.get("/rest/nguoidung").then(resp => { //lấy người dùng từ server thông qua get()
			$scope.users = resp.data; // đổ dữ liệu vào users
			$scope.users.forEach(user => {
				user.ngaySinh = new Date(user.ngaySinh) // đổi định dạng
			})
		});
		// Load chức vụ

	}

	//Xóa form
	$scope.reset = function() {
		$scope.form = {
			ngaySinh: new Date(),// đổi định dạng
			gioiTinh: true,
			trangThai: true,
			daXoa: false,
			chucVu: 0
		};
	}

	//Hiển thị từ table lên form
	$scope.edit = function(user) {
		$scope.form = angular.copy(user); //copy đối tượng lên form
		$scope.toggleClass("#edit-tab", "active");
		$scope.toggleClass("#edit-tab-pane", "active");
		$scope.toggleClass("#edit-tab-pane", "show");

		$scope.toggleClass("#list-tab", "active");
		$scope.toggleClass("#list-tab-pane", "active");
		$scope.toggleClass("#list-tab-pane", "show");

		console.log($scope.form);
	}

	$scope.toggleClass = function(o, i) {
		document.querySelector(o).classList.toggle(i);
	}

	//Thêm người dùng
	$scope.create = function() {
		var user = angular.copy($scope.form); //copy thông tin người dùng vào user
		$http.post(`/rest/nguoidung`, user).then(resp => {
			resp.data.ngaySinh = new Date(resp.data.ngaySinh) // đổi định dạng ngày tháng của js
			resp.data.daXoa = false;
			$scope.users.push(resp.data); // bỏ dữ liệu  vào list users
			$scope.reset(); // reset form
			$scope.initialize(); // reset table
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Thêm thành công!'
			});
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Thêm thất bại!'
			});
			console.log("Error", error);
		});
	}

	//Cập nhật người dùng
	$scope.update = function() {
		var user = angular.copy($scope.form); //copy thông tin người dùng vào user
		$http.put(`/rest/nguoidung/${user.id}`, user).then(resp => {
			var index = $scope.users.findIndex(sp => sp.id == user.id);
			$scope.users[index] = user;
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Cập nhật thành công!'
			});
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Cập nhật thất bại!'
			});
			console.log("Error", error);
		});
	}

	// hiển thị thông báo
	$scope.showAlert = function() {
		// Thông báo
		Swal.fire({
			icon: 'info',
			showConfirmButton: true,
			confirmButtonText: 'OK',
			confirmButtonColor: '#002bff',
			showCancelButton: true,
			cancelButtonText: 'Thoát',
			title: 'Bạn chắc chắn muốn xóa!'
		});
	}

	//Xóa người dùng
	$scope.delete = function(user) {
		Swal.fire({
			icon: 'warning',
			title: 'Bạn có chắc muốn xóa không',
			showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Có!',
            cancelButtonText: 'Không'
		}).then((result)=>{
			if(result.isConfirmed){
				$http.delete(`/rest/nguoidung/${user.id}`).then(resp => {
			var index = $scope.users.findIndex(sp => sp.id == user.id);
			$scope.users.splice(index, 1);
			$scope.reset();
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Xóa thành công!'
			});
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Xóa thất bại!'
			});
			console.log("Error", error);
		});
			}
		})
		
	}

	// Phân trang và điều hướng
	$scope.pager = {
		page: 0,
		size: 10,
		get users() {
			var start = this.page * this.size;
			return $scope.users.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.users.length / this.size);
		},
		f() {
			this.page = 0;
		},
		p() {
			this.page--;
			if (this.page < 0) {
				this.l();
			}
		},
		n() {
			this.page++;
			if (this.page >= this.count) {
				this.f();
			}
		},
		l() {
			this.page = this.count - 1;
		},
	}
	$scope.regex = '[^<>;"/?|]{3,15}';
	$scope.initialize();
	$scope.reset();
});