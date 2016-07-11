define(
		[
		 "jquery"
		 ], function($) {

			rucsokController.$inject = ["rucsokService"]		

			function rucsokController(rucsokService) {
				var vm = this;

				vm.rucsoks = [];

				vm.dirty = true;
				vm.preload = false;
				vm.addRucsok = addRucsok;
				vm.checkRucsok = checkRucsok;
				vm.visit = visit;
				vm.showAddModal = showAddModal;
				vm.rucsok = rucsok;
				vm.login = executeLogin;
				vm.showLoginModal = showLoginModal;
				vm.hideLoginModal = hideLoginModal;
				vm.invalidlogin = false; 
				vm.refresh = refresh;

				function refresh() {
					rucsokService.getRucsok().then(function(data) {
						vm.rucsoks = data;
					});
				}
				
				function executeLogin(username, password) {
					var csrf = $("[name='_csrf']").val();
					console.log(csrf)
					$.ajax({
				        type: 'POST',
				        url: '/login',
				        data: {
				        	"sec-user" : username,
				        	"sec-password" : password,
				        	"_csrf" : csrf				        	
				        },
				        cache: false,
				        dataType: "json",
				        crossDomain: false,
				        success: function (response) {
				            if (response.success == true) {
				                console.info("Authentication Success!");
				                window.location.href = "/";
				            }
				            else {
				            	console.log("else");
				            	vm.invalidlogin = true;
				            }
				        },
				        error: function (data) {
				        	console.log(data);
				        	vm.invalidlogin = true;
				        }
				    });
				}
				
				
				function visit(url) {
					  var win = window.open(url, '_blank');
					  win.focus();
				}

				function addRucsok() {
					vm.preload = true;
					rucsokService.addRucsok(vm.rucsok).then(function(){
						vm.preload = false;
						rucsokService.getRucsok().then(function(data) {
							vm.rucsoks = data;
							closeAddModal();
						});	
					})
				}
				
				function showLoginModal() {
					$('#login-modal').modal("show");
				}
				
				function hideLoginModal() {
					$('#login-modal').modal("hide");
				}
				

				function checkRucsok() {
					rucsokService.checkRucsok(vm.url).then(function(data) {
						vm.dirty = false;
						vm.rucsok = data;
					});
				}		

				function showAddModal() {
					$("#rucsok-modal").modal("show");
				}

				function closeAddModal() {
					$("#rucsok-modal").modal("hide");
				}

				function rucsok() {
					rucsokService.getRucsok().then(function(data) {
						vm.rucsoks = data;
						if (vm.rucsoks.length == 0) {
							showAddModal();
						}
					});
				}
			}

			return rucsokController;
		})