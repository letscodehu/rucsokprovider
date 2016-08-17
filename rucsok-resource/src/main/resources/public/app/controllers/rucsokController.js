define([ "jquery" ], function($) {

	rucsokController.$inject = [ "$scope", "rucsokService",
			"addRucsokFormService", "$state" ]

	function rucsokController($scope, rucsokService, addRucsokFormService,
			$state) {
		var vm = this;

		$scope.rucsoks = [];

		vm.dirty = true;
		vm.preload = false;
		
		$scope.addRucsok = addRucsok;
		
		vm.checkRucsok = checkRucsok;
		vm.visit = visit;
		vm.showAddModal = showAddModal;
		vm.rucsok = rucsok;
		vm.login = executeLogin;
		$scope.showRucsok = showRucsok;
		vm.showLoginModal = showLoginModal;
		vm.hideLoginModal = hideLoginModal;
		vm.invalidlogin = false;
		vm.refresh = refresh;

		vm.refresh();
		
		var updateListener = $scope.$on('rucsok.added', function(event) {
			vm.refresh();
		});
		
		$scope.$on('$destroy', function() {
			updateListener();
		});

		function refresh() {
			rucsokService.getRucsok().then(function(data) {
				$scope.rucsoks = data;
			});
		}

		function executeLogin(username, password) {
			var csrf = $("[name='_csrf']").val();
			$.ajax({
				type : 'POST',
				url : '/login',
				data : {
					"sec-user" : username,
					"sec-password" : password,
					"_csrf" : csrf
				},
				cache : false,
				dataType : "json",
				crossDomain : false,
				success : function loginuccess(response) {
					if (response.success == true) {
						console.info("Authentication Success!");
						window.location.href = "/";
					} else {
						console.log("else");
						vm.invalidlogin = true;
					}
				},
				error : function loginError(data) {
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
			addRucsokFormService.showView();
		}

		function showLoginModal() {
			$('#login-modal').modal("show");
		}

		function hideLoginModal() {
			$('#login-modal').modal("hide");
		}

		function checkUrl(url) {
			return typeof url !== 'undefined' && url.substr(0, 4) === 'http';
		}
		// élethack
		$('#rucsok-url').on('focus', function() {
			vm.urlErrorClass = '';
		});

		function checkRucsok() {
			if (checkUrl(vm.url)) {
				rucsokService.checkRucsok(vm.url).then(function(data) {
					vm.dirty = false;
					vm.rucsok = data;
				});
			} else {
				$scope.urlErrorClass = 'alert-danger';
			}
		}

		function showRucsok(item) {
			$state.go('single', {
				id : item.id
			});
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