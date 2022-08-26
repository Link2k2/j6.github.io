app.controller("product-ctrl", function($scope, $http){
    $scope.items=[]; //Hiển thị trên table
    $scope.form={}; //Hiển thị trên form
    $scope.cates = []; //Hiển thị trên combobox

    //Load tất cả sản phẩm + loại sản phẩm từ server
    $scope.initialize = function(){
        // Load sản phẩm lên table
        $http.get("/rest/sanpham").then(resp => { //lấy sản phẩm từ server thông qua get()
            $scope.items = resp.data; // đổ dữ liệu vào items
            $scope.items.forEach(item => { 
                item.ngayTao = new Date(item.ngayTao) // đổi định dạng
                item.ngaySua = new Date(item.ngaySua) // đổi định dạng
                
            })
            $scope.form.id="SP"+($scope.items.length+1);
            console.log( $scope.items);
        });
        // Load loại sản phẩm
        $http.get("/rest/loaisanpham").then(resp => { //lấy loa sản phẩm từ server thông qua get()
            $scope.cates = resp.data; // đổ dữ liệu vào items
        });

    }

    //Xóa form
    $scope.reset = function(){
        $scope.form = {
            ngayTao: new Date(),
            ngaySua: new Date(),
            image: 'image_default.png',
            daXoa: true,
            loaisp: {
	            id: 1
	        },
            thongTinCT: '',
        };
    }

    //Hiển thị từ table lên form
    $scope.edit = function(item){
        $scope.form = angular.copy(item); //copy đối tượng lên form
        
        $scope.toggleClass("#edit-tab","active");
		$scope.toggleClass("#edit-tab-pane","active");
		$scope.toggleClass("#edit-tab-pane","show");
		
		$scope.toggleClass("#list-tab","active");
		$scope.toggleClass("#list-tab-pane","active");
		$scope.toggleClass("#list-tab-pane","show");
		
        console.log($scope.form);
    }
    
    $scope.toggleClass= function(o,i){
		document.querySelector(o).classList.toggle(i);
	}
			
    //Thêm sản phẩm - chưa được
    $scope.create = function(){
        var item = angular.copy($scope.form); //copy thông tin sản phẩm vào item
        var sp = document.querySelector("#selectCbbL").value;
        $scope.form.sp = JSON.parse(sp);
      
        
        item.dvt= "Đ";
        
        
        
        $http.post('/rest/sanpham', item).then(resp => {
            resp.data.ngayTao = new Date(resp.data.ngayTao) // đổi định dạng ngày tháng của js
            resp.data.ngaySua = new Date(resp.data.ngaySua) // đổi định dạng ngày tháng của js
            
            // nếu k có hình thì tự thêm hình mặc định
             if(resp.data.image==null){
				resp.data.image = 'image_default.png';
			}else{
				resp.data.image = item.image;
			}
			
			
            $scope.items.push(resp.data); // bỏ dữ liệu  vào list items
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

    //Cập nhật sản phẩm 
    $scope.update = function(){
        var item = angular.copy($scope.form); //copy thông tin sản phẩm vào item
        
        console.log("Item", item);
        $http.put('/rest/sanpham/'+item.id, item).then(resp => {
            var index = $scope.items.findIndex(sp => sp.id == item.id);
            
            // nếu k có hình thì tự thêm hình mặc định
            /* if(resp.data.image==null){
				resp.data.image = 'image_default.png';
			}else{
				resp.data.image = item.image;
			}
			*/
            
            $scope.items[index] = item;
            console.log("Sp", $scope.items[index]);
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

    //Xóa sản phẩm
    $scope.delete = function(item){
        $http.delete(`/rest/sanpham/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(sp => sp.id == item.id);
            $scope.items.splice(index, 1);
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

    //Upload hình ảnh
    $scope.ImageChanged = function(files){
        var data = new FormData();
        data.append('file', files[0]); // adđ đối tượng files vào formData()
        $http.post('/rest/upload/images', data, { // upload hình lên server - thư mục images
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name; // add name for data vào thuộc tính image
        }).catch(error => {
            alert("Lỗi tải hình ảnh")
            console.log(error)
        })
    }

    // Phân trang và điều hướng
    $scope.pager = {
        page: 0,
        size: 10,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        f(){
            this.page = 0;
        },
        p(){
            this.page--;
            if(this.page < 0){
                this.l();
            }
        },
        n(){
            this.page++;
            if(this.page >= this.count){
                this.f();
            }
        },
        l(){
            this.page = this.count - 1;
        },
    }

    $scope.initialize();
    $scope.reset();
});