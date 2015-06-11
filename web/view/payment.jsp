<body>
	<form id="checkout" method="post" action="/bttraining/checkout">
		<div id="payment-form"></div>
		<input type="submit" value="Pay $10">
	</form>






	<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
	<script>
		// We generated a client token for you so you can copy and paste this code
		// and try it out right away. See the section below to generate your
		// own client token.
		var clientToken = "${token}";

		var b = braintree.setup(clientToken, "dropin", {
			container : "payment-form"
		});
		
		x = 1;
	</script>
</body>
