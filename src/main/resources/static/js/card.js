var app = angular.module("home-app", []);
let itemSoluong ;


app.controller("cart-ctrl", function($scope, $http) {
	$scope.order={};
	$scope.cart = {
		items: [],//tạo một cái mảng đối tượng để chứa các đối tượng trong giỏ hàng
		load() {
			$http.get("/rest/giohang/list").then(resp => {
				this.items = resp.data; //lệnh này dùng để load dữ liệu giỏ hàng của người đang đăng nhập. nhớ truyền tham số là id của người đang đăng nhập
				
			})
		},
		add(idnd, idsp) {//cái lệnh này để add sp vào giỏ hàng, bỏ vào nút thêm vào giỏ rồi truyền vào 2 tham số là id người đang đăng nhập và id sản phẩm 
			$http.post("/rest/giohang/create/" + idnd + "/" + idsp).then(resp => { //lệnh này để thêm vào csdl, mở trang giohangapi để xem thêm
			})
		},
		update(id, sl) {//lệnh này thì dùng để update, bỏ nó vào trong chỗ chỉnh số lượng r bắt sự kiện change cho nó này có 2 tham số là id của đối tượng và số lượng muốn cập nhật
			
				
				let inputSoluong = document.querySelector("#sl-"+id);
				let valueInputSoluong = inputSoluong.value;
				
				if(!valueInputSoluong || valueInputSoluong == null){
					valueInputSoluong= 1;
				}else if(valueInputSoluong < 1){
					valueInputSoluong= 1;
				}
				 $scope.cart.items.map(o => {
					if(o.id == id){
						if(valueInputSoluong > o.sp.soLuong){
							valueInputSoluong = o.sp.soLuong;
							inputSoluong.value = valueInputSoluong;
						}
					}
				}); 
				sl = valueInputSoluong;
				
				$http.put("/rest/giohang/update/" + id + "/" + sl);
			 	
			
		},
		remove(id) { //lệnh này thì dùng để xóa id là id của đối tượng mình muốn xóa đó nhe
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
				$http.delete("/rest/giohang/remove/" + id).then(resp => { //cái này thì qua giohangapi xem thêm
			this.load();
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
			
		},
		clear(idnd) {
			this.items = [],
				$http.delete("/rest/giohang/clear/" + idnd).then(resp => { // cái này thì dùng để xóa hết dữ liệu trong giỏ hàng của người dùng, tham số là id người đang đăng nhập đó
				})//lệnh này thì sử dụng khi người ta đặt hàng thành công
		},
		get amount() { // lệnh này thì dùng để tính tổng số tiền các mặt hàng trong giỏ
			return this.items
				.map(item => item.soLuong * item.sp.gia)
				.reduce((total, soLuong) => total += soLuong, 0);
				
		},	
		
	};

	$scope.cart.load();//cái này là gọi hàm load dữ liệu mà mình vừa tạo ở trên nè. do chưa đăng nhập nên truyền đại thằng asdas
	
	$scope.checkMaxSl = function(id){
		let inputSoluong = document.querySelector("#sl-"+id);
		
		let valueInputSoluong = inputSoluong.value;
		
		if(!valueInputSoluong || valueInputSoluong == null){
			valueInputSoluong= 1;
		}else if(valueInputSoluong < 1){
			valueInputSoluong= 1;
		}
		 $scope.cart.items.map(o => {
			if(o.id == id){
				if(valueInputSoluong > o.sp.soLuong){
					valueInputSoluong = o.sp.soLuong;
					inputSoluong.value = valueInputSoluong;
				}
			}
		}); 
		inputSoluong.value = valueInputSoluong;
		
	};
	
	
	$scope.order = {// cái này là khởi tạo đối tượng order r thêm các thuộc tính cho giống cái entity của mình 
		
		nguoiDung: { id: $('#account').val() }, // chỗ này thì truyền vào id người đang đăng nhập nhe
		trangThai: false,
		daXoa: false,
		
		get ctdh() {
			return $scope.cart.items.map(item => {//lệnh này thì lấy cái đống dữ liệu trong mảng item để mình tạo ra cái list chi tiết đơn hàng
				return {
					sp: { id: item.sp.id, soLuong: item.sp.soLuong },
					soLuong: item.soLuong
				}
			})
		},
		dathang() { //hàm này là để đặt hàng
			var or = angular.copy($scope.order);
			var account2 = $('#account').val();
			$http.post("/rest/donhang/create", or).then(resp => {
			});
			$scope.cart.clear(account2); // đặt xong thì xóa hết dữ liệu giỏ hàng đó tham số truyền vào là id người đăng nhập á
			$scope.cart.load();
			location.href = "/home/account/finish";
		}

	};
})
app.controller("donhang-ctrl", function($scope, $http) { 
	$scope.form={};
	$scope.items=[];
	$scope.donhang = {
		
		load(nguoiDung) {// lệnh này là để load các đơn hàng của người dùng nè. tham số truyền vào là id người đang đăng nhập nha
			$http.get("/rest/donhang/listid").then(resp => {
				this.items = resp.data;
			})
		}
	};
	$scope.donhang.load();
})

app.controller("shopping-cart", function($scope, $http){
	
	
	$scope.form={};
	$scope.itemsCart =[];
	$scope.shoppingCart = {
		
		load() {
			$http.get("/rest/giohang/list").then(resp => {
				$scope.itemsCart = resp.data; //lệnh này dùng để load dữ liệu giỏ hàng của người đang đăng nhập. nhớ truyền tham số là id của người đang đăng nhập
				console.log($scope.itemsCart);
			})
		},
		add(idsp) {//cái lệnh này để add sp vào giỏ hàng, bỏ vào nút thêm vào giỏ rồi truyền vào 2 tham số là id người đang đăng nhập và id sản phẩm
			
			$http.post("/rest/giohang/create/"+idsp).then(resp => { //lệnh này để thêm vào csdl, mở trang giohangapi để xem thêm
				if($scope.itemsCart == idsp){
					update(idsp,1);
				}
					window.location.replace('http://localhost:8080/home/account/cart');
			})
		},
		update(id, sl) {//lệnh này thì dùng để update, bỏ nó vào trong chỗ chỉnh số lượng r bắt sự kiện change cho nó này có 2 tham số là id của đối tượng và số lượng muốn cập nhật
			let inputSoluong = document.querySelector("#sl-"+id);
		
				let valueInputSoluong = inputSoluong.value;
				
				if(!valueInputSoluong || valueInputSoluong == null){
					valueInputSoluong= 1;
				}else if(valueInputSoluong < 1){
					valueInputSoluong= 1;
				}
				 $scope.cart.items.map(o => {
					if(o.id == id){
						if(valueInputSoluong > o.sp.soLuong){
							valueInputSoluong = o.sp.soLuong;
							inputSoluong.value = valueInputSoluong;
						}
					}
				}); 
				sl = valueInputSoluong;
				$http.put("/rest/giohang/update/" + id + "/" + sl).then(resp => {
					
					//lệnh này để sửa số lượng trong csdl, mở trang giohangapi để xem thêm						
			})			
			this.load();
			
		}	
	}
	$scope.shoppingCart.load();
	
	
})
