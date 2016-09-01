var allTestFiles = []
var TEST_REGEXP = /(spec|test)\.js$/i

//	Get a list of all the test files to include
	Object.keys(window.__karma__.files).forEach(function (file) {
		if (TEST_REGEXP.test(file)) {
			// Normalize paths to RequireJS module names.
			// If you require sub-dependencies of test files to be loaded as-is (requiring file extension)
			// then do not normalize the paths
			//var normalizedTestModule = file.replace(/^\/base\/|\.js$/g, '')
			allTestFiles.push(file.replace(/^\/base\//, 'http://localhost:9876/base/'))
			//allTestFiles.push(normalizedTestModule)
		}
	})

	require.config({
		baseUrl: '/',

		// dynamically load all test files
		deps: allTestFiles,
		  urlArgs: "bust=" + (new Date()).getTime(),
		paths: {
			'ionic': 'base/lib/ionic/js/ionic.bundle.min',
			'ionic-gallery' : 'base/lib/ion-gallery/dist/ion-gallery.min',
			'jquery' : 'base/lib/jquery/dist/jquery.min',
			'angular-mocks' : 'base/node_modules/angular-mocks/angular-mocks',
			'spring-token-interceptor' : 'base/lib/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
			'angular-http-auth' : 'base/lib/angular-http-auth-master/http-auth-interceptor',
		},
		shim : {
			'ionic-gallery' : {
				'deps' : ['ionic']
			},
			'spring-token-interceptor' : {
				'deps' : ['ionic']
			},
			"app" : {
				"deps" : ["ionic"]
			},
			"angular-http-auth" : {
				"deps" : ["ionic"]
			}
		},
		// we have to kickoff jasmine, as it is asynchronous
		callback: window.__karma__.start
	})
