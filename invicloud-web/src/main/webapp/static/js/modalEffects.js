
var ModalEffects = (function() {

	function init() {

		[].slice.call( document.querySelectorAll( '.md-trigger' ) ).forEach( function( el, i ) {


			el.addEventListener( 'click', function( ev ) {
				var customerId=this.getElementsByClassName('inputSubmit').customerId.value;
                window.location.href = baselocation + '/recommend/demo/view'+'?customerId='+customerId;

			});



		} );

	}

	init();

})();