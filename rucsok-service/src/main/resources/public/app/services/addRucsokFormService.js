define([], function() {

	addRucsokFormService.$inject = [ "$http", "$q" ];

	function addRucsokFormService($http, $q) {

		var vm = this;
		vm.show = false;

		function toggleView() {
			vm.show = !vm.show;
		}

		function hideView() {
			vm.show = false;
		}

		function showView() {
			vm.show = true;
		}

		function isShow() {
			return vm.show;
		}

		function addRucsok(rucsok) {
			var deferred = $q.defer();

			$http({
				"url" : "/rucsok",
				"method" : "POST",
				"data" : {
					"rucsok" : rucsok
				},
			}).then(function(resp) {
				deferred.resolve(resp.data);
			});

			return deferred.promise;
		}

		return {
			toggleView : toggleView,
			hideView : hideView,
			showView : showView,
			isShow : isShow,
			addRucsok : addRucsok
		}
	}
	;

	return addRucsokFormService;
});