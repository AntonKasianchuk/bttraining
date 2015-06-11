<body>
	<form id="checkout" method="post" action="/checkout">
		<div id="payment-form"></div>
		<input type="submit" value="Pay $10">
	</form>






	<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
	<script>
		// We generated a client token for you so you can copy and paste this code
		// and try it out right away. See the section below to generate your
		// own client token.
		var clientToken = "${token}";

		braintree.setup(clientToken, "dropin", {
			container : "payment-form"
		});
	</script>
</body>
