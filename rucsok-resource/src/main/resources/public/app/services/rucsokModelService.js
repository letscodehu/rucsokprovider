define([], function() {

	function rucsokModelService() {

		var rucsok = (function() {
			var obj = {
				title : "",
				imageUrl : "",
				videoUrl : "",
				link : "",
				type: "",
				username: "",
				hasVideo : function() {
					return typeof this.videoUrl !== 'undefined'
							&& this.videoUrl.length > 0;
				}
			}
			return obj;
		})();

		function createRucsokFromRequest(data) {
			var newRucsok = createEmptyRucsok();
			newRucsok.title = data.title || "";
			newRucsok.imageUrl = data.imageUrl || "";
			newRucsok.videoUrl = data.videoUrl || "";
			newRucsok.link = data.link || "";
			newRucsok.type = data.type || "";
			newRucsok.username = data.username || "";
			return newRucsok;
		}

		function createEmptyRucsok() {
			return Object.create(rucsok);
		}

		return {
			createRucsokFromRequest : createRucsokFromRequest,
			createEmptyRucsok : createEmptyRucsok
		}
	}
	;

	return rucsokModelService;
});