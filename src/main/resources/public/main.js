var require = {
  baseUrl: 'app',
  paths: {
    'ionic': '../lib/ionic/js/ionic.bundle.min',
    'ionic-gallery' : '../lib/ion-gallery/dist/ion-gallery.min',
    'jquery' : '../lib/jquery/dist/jquery.min',
    'angular-http-auth' : '../lib/angular-http-auth-master/http-auth-interceptor',
  },
  shim : {
	  'ionic-gallery' : {
		  'deps' : ['ionic']
	  },
	  "app" : {
		  "deps" : ["ionic"]
	  },
	  "angular-http-auth" : {
		  "deps" : ["ionic"]
	  }
  },
  deps : ["app"]
};
