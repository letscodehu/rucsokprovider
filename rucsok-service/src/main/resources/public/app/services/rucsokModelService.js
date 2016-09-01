define([], function() {

	function rucsokModelService() {

		var rucsok = (function() {
			var obj = {
				title : "",
				imageUrl : "",
				videoUrl : "",
				link : "",
				upvotes : 0,
				comments : [],
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
			newRucsok.comments = data.comments = [];
			newRucsok.upvotes = data.upvotes || 0;
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