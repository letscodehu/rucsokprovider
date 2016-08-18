var require = {
  baseUrl: 'app',
  paths: {
    'ionic': '../lib/ionic/js/ionic.bundle.min',
    'ionic-gallery' : '../lib/ion-gallery/dist/ion-gallery.min',
    'jquery' : '../lib/jquery/dist/jquery.min',
    'localforage' : '../lib/localforage/dist/localforage.min',
    "ion-floating-menu" : '../lib/ion-floating-menu/dist/ion-floating-menu',
    'spring-token-interceptor' : '../lib/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
    'angular-http-auth' : '../lib/angular-http-auth-master/http-auth-interceptor',
  },
  shim : {
	  'ionic-gallery' : {
		  'deps' : ['ionic']
	  },
	  'ion-floating-menu' : {
		  'deps' : ['ionic']
	  },
	  'spring-token-interceptor' : {
		  'deps' : ['ionic']
	  },
	  "app" : {
		  "deps" : ["ionic", "localforage"]
	  },
	  "angular-http-auth" : {
		  "deps" : ["ionic"]
	  }
  },
  deps : ["app"]
};
