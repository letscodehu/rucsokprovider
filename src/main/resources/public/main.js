var require = {
  baseUrl: 'app',
  paths: {
    'ionic': '../lib/ionic/js/ionic.bundle.min',
    'ionic-gallery' : '../lib/ion-gallery/dist/ion-gallery.min',
    'jquery' : '../lib/jquery/dist/jquery.min',
  },
  shim : {
	  'ionic-gallery' : {
		  'deps' : ['ionic']
	  }	 
  }
  
};
