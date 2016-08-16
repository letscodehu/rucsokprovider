require.config({
	"basePath" : "/js",
	"paths": {
		"jquery" : "/js/lib/jquery/dist/jquery.min",
		"app" : "/js/app",
		"bootstrap" : "/js/lib/bootstrap/dist/js/bootstrap.min",
		"angular" : "/js/lib/angular/angular"
	},
	"shim": {
		"bootstrap" : {
			"deps" : ["jquery"]
		},
		"angular" : {
			"exports" : "angular"
		},
		"app" : {
			"deps" : ["bootstrap", "angular"]
		}
	},
	deps: ["app"]
});