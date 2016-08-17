define(["js/controller/loginController.js"], function(loginController) {
	function login() {
		return {
			"restrict" : 'E',
			'controller' : loginController,
			'bindToController': true,
			'controllerAs' : 'vm',
			'templateUrl' : 'js/templates/login.html'
		}
	}
	
	return login;
});