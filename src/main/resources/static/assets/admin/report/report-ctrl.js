app.controller("report-ctrl", function($scope, $http){
    $scope.items=[];
    
    $scope.report=function(){
	
		$http.get("/rest/report/get").then(resp => {
			$scope.items = resp.data
			
		});	
	
	}

	// Phân trang và điều hướng
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			
			return $scope.items.slice(start, start + this.size);
			
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
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

		$scope.report();
});