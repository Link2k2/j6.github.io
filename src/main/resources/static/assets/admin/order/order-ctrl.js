app.controller("order-ctrl", function($scope, $http) {
	$scope.orders = []; //Hiển thị trên table
	$scope.unorders = [];
	$scope.form = {}; //Hiển thị trên form

	//Load tất cả đơn hàng từ server
	$scope.initialize = function() {
		// Load đơn hàng lên table
		$http.get("/rest/donhang-all").then(resp => { //lấy đơn hàng từ server thông qua get()
			$scope.orders = resp.data; // đổ dữ liệu vào orders
			$scope.orders.forEach(order => {
				order.ngayTao = new Date(order.ngayTao) // đổi định dạng
			})
		});
		// Load đơn hàng bị hủy
		$http.get("/rest/donhang-huy").then(resp => { //lấy đơn hàng từ server thông qua get()
			$scope.unorders = resp.data; // đổ dữ liệu vào orders
			$scope.unorders.forEach(order => {
				order.ngayTao = new Date(order.ngayTao) // đổi định dạng
			})
		});
	}

	//Xóa form
	$scope.reset = function() {
		$scope.form = {
			ngayTao: new Date(),// đổi định dạng
			trangThai: false,
		};
	}

	//Hiển thị từ table lên form
	$scope.edit = function(order) {
		$scope.form = angular.copy(order); //copy đối tượng lên form
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

	//Cập nhật đơn hàng
	$scope.update = function(order) {
		var order = angular.copy($scope.form); //copy thông tin đơn hàng vào order
		console.log("item", order);
		$http.put(`/rest/donhang/${order.id}`, order).then(resp => {
			var index = $scope.orders.findIndex(od => od.id == order.id);
			order.daXoa = false;
			$scope.orders[index] = order;
			$scope.initialize();
			$scope.reset();
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

	//Xóa đơn hàng -- chưa được
	$scope.delete = function(order) {
		var order = angular.copy($scope.form); //copy thông tin đơn hàng vào order
		
		order.daXoa = true;
		
		console.log("item", order);
		$http.put('/rest/donhang/'+order.id, order).then(resp => {
			var index = $scope.orders.findIndex(od => od.id == order.id);
			
			$scope.orders[index] = order;
			$scope.initialize();
			$scope.reset();
			// Thông báo
			Swal.fire({
				icon: 'success',
				title: 'Hủy đơn thành công!'
			});
		}).catch(error => {
			// Thông báo
			Swal.fire({
				icon: 'error',
				title: 'Hủy đơn thất bại!'
			});
			console.log("Error", error);
		});
	}

	// Phân trang và điều hướng
	$scope.pager = {
		page: 0,
		size: 10,
		get orders() {
			var start = this.page * this.size;
			return $scope.orders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.orders.length / this.size);
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

	$scope.pager2 = {
		page: 0,
		size: 10,
		get unorders() {
			var start = this.page * this.size;
			return $scope.unorders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.orders.length / this.size);
		},
		f2() {
			this.page = 0;
		},
		p2() {
			this.page--;
			if (this.page < 0) {
				this.l();
			}
		},
		n2() {
			this.page++;
			if (this.page >= this.count) {
				this.f();
			}
		},
		l2() {
			this.page = this.count - 1;
		},
	}

	$scope.initialize();
	$scope.reset();
});