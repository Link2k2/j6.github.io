app = angular.module("admin-app",["ngRoute"]);

//Tiến hành định tuyến
app.config(function ($routeProvider){
	$routeProvider
	.when("/user", {
		templateUrl: "/assets/admin/user/index.html",
		controller: "user-ctrl"
	})
	.when("/product", {
		templateUrl: "/assets/admin/product/index.html",
		controller: "product-ctrl"
	})
	.when("/category", {
		templateUrl: "/assets/admin/category/index.html",
		controller: "category-ctrl"
	})
	.when("/image-products", {
		templateUrl: "/assets/admin/galery/index.html",
		controller: "galery-ctrl"
	})
	.when("/order", {
		templateUrl: "/assets/admin/order/index.html",
		controller: "order-ctrl"
	})
	.when("/feedback", {
		templateUrl: "/assets/admin/feedback/index.html",
		controller: "feedback-ctrl"
	})
	.when("/report", {
		templateUrl: "/assets/admin/report/index.html",
		controller: "report-ctrl"
	})
	.when("/authorize", {
		templateUrl: "/assets/admin/authority/index.html",
		controller: "authority-ctrl"
	})
	.when("/unauthorized", {
		templateUrl: "/assets/admin/authority/unauthorized.html",
		controller: "authority-ctrl"
	})
	.otherwise({
		templateUrl: "/assets/admin/report/index.html",
		controller: "report-ctrl"
	})
})