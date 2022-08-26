app.controller("category-ctrl", function($scope, $http) {
	$scope.cates = []; //Hiển thị trên table
	$scope.form = {}; //Hiển thị trên form
	//Load tất cả loại sản phẩm + loại loại sản phẩm từ server
	$scope.initialize = function() {
		// Load loại loại sản phẩm
		$http.get("/rest/loaisanpham").then(resp => { //lấy loa loại sản phẩm từ server thông qua get()
			$scope.cates = resp.data; // đổ dữ liệu vào cates
			$scope.form.id = $scope.cates.at(-1).id+1;
		});

	}

	//Xóa form
	$scope.reset = function() {
		$scope.form = {};
	}

	//Hiển thị từ table lên form
	$scope.edit = function(cate) {
		$scope.form = angular.copy(cate); //copy đối tượng lên form
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

	//Cập nhật người dùng 
	$scope.update = function(cate) {
		var cate = angular.copy($scope.form); //copy thông tin người dùng vào user
		console.log("item", cate);
		$http.put(`/rest/loaisanpham/`+cate.id, cate).then(resp => {
			
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
		$scope.initialize();
		$scope.reset();
	}

	//Thêm loại sản phẩm - chưa bắt trùng id -> nó cập nhật đè lên id cũ
	$scope.create = function() {
		
		var cate = angular.copy($scope.form); //copy thông tin loại sản phẩm vào cate
		
		console.log(cate);
		$http.post('/rest/loaisanpham', cate).then(resp => {
			$scope.cates.push(resp.data); // bỏ dữ liệu  vào list cates
			$scope.reset(); // reset form
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

	//Xóa loại sản phẩm
	$scope.delete = function(cate) {
		$http.delete('/rest/loaisanpham/'+cate.id).then(resp => {
			var index = $scope.cates.findIndex(sp => sp.id == cate.id);
			$scope.cates.splice(index, 1);
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

	// Phân trang và điều hướng
	$scope.pager = {
		page: 0,
		size: 10,
		get cates() {
			var start = this.page * this.size;
			return $scope.cates.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.cates.length / this.size);
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

	$scope.initialize();
	$scope.reset();
});