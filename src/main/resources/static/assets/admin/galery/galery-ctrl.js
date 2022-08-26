app.controller("galery-ctrl", function($scope, $http){
	
	//Hiển thị dữ liệu lên form
	$scope.form={};
	//Hiển thị dữ liệu lên table
	$scope.items=[];
	//Hiển thị dữ liệu lên combobox
	$scope.cbBoxs=[];
	//lấy id cuối cùng
	$scope.lastId = 0;
	
	//Load hình của sản phẩm
    $scope.initialize = function(){
        // Load hình của sản phẩm lên table lên table
        $http.get("/rest/galery").then(resp => { //lấy sản phẩm từ server thông qua get()
            $scope.items = resp.data; // đổ dữ liệu vào items
            $scope.form.id = ($scope.items.at(-1).id + 1);
            
        });
        
        $http.get("/rest/sanpham").then(resp => { 
            $scope.cbBox = resp.data;
        });

    }

	//Xóa form
	$scope.reset = function(){
		$scope.form = {
			sp: '',
			urlImage: 'image_default.png'
		}
	}
	
	//Hiển thị lên form
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		
		var btn = document.getElementById("selectCbb");
	
   		 // Setting new attributes
    	
    		btn.setAttribute("disabled", "");
		
		$scope.toggleClass("#edit-tab","active");
		$scope.toggleClass("#edit-tab-pane","active");
		$scope.toggleClass("#edit-tab-pane","show");
		
		$scope.toggleClass("#list-tab","active");
		$scope.toggleClass("#list-tab-pane","active");
		$scope.toggleClass("#list-tab-pane","show");
		
	}
	$scope.toggleClass= function(o,i){
		document.querySelector(o).classList.toggle(i);
	}
	
	//Thêm mới ảnh
	$scope.create = function(){
		let sp= document.querySelector("#selectCbb").value;
	
		$scope.form.sp = JSON.parse(sp);
		var item = angular.copy($scope.form);
		
		 
		$http.post('/rest/galery', item).then(resp => {
			console.log($scope.items.id + ' '+resp.data.id);
			if(resp.data.urlImage == null){
				resp.data.image = 'image_default.png';
			}else{
				resp.data.urlImage = item.urlImage;
			}
			
			$scope.items.push(resp.data); //đổ dữ liệu vào list Items
			
			$scope.initialize();
			$scope.reset();
			console.log("Cập nhật ảnh thành  công", resp)
		}).catch(error => {
			console.log("Cập nhật ảnh thất bại", error)
		});
		 
	}
	
	//Cập nhật ảnh
	$scope.update = function(){
		let sp= document.querySelector("#selectCbb").value;
		$scope.form.sp = JSON.parse(sp);
		var item = angular.copy($scope.form);
		
		$http.put('/rest/galery/'+item.id, item).then(resp => {
			
			console.log("Cập nhật ảnh thành  công", resp)
			$scope.initialize();
			$scope.reset();
		}).catch(error => {
			console.log("Cập nhật ảnh thất bại", error)
		});
		
	}
	
	//Xóa ảnh
	$scope.delete = function(item){
		$http.delete('/rest/galery/'+item.id).then(resp => {
			//var index = $scope.users.findIndex(sp => sp.id == item.id);
            //$scope.users.splice(index, 1);
            $scope.initialize();
			$scope.reset();
			console.log("Xóa ảnh thành  công", resp)
		}).catch(error => {
			console.log("Xóa ảnh thất bại", error)
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
            $scope.form.urlImage = resp.data.name; // add name for data vào thuộc tính image
        }).catch(error => {
            alert("Lỗi tải hình ảnh")
            console.log(error)
        })
    }
	
	// Phân trang và điều hướng
    $scope.pager = {
        page: 0,
        size: 4,
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