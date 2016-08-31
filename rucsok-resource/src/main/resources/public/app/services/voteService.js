define([], function() {
		
	voteService.$inject = ["$http", "$q"];
	
	function voteService($http, $q) {

		var vm = this;

		function upvote(rucsok) {
			vote(rucsok.id, "UP");
		}
			
		function vote(voteId, voteType) {
			$http({
				'url' : '/vote',
				'method' : 'POST',
				'data' : {
					'voteid' : voteId,
					'type' : voteType
				}
			}
			).then(function(reps) {

			});
		}
		
		function downvote(rucsok) {
			vote(rucsok.id, "DOWN");
		}
		
				
		return {
			upvote : upvote,
			downvote : downvote
		}
	};
	
	return voteService;

});