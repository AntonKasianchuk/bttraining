<body>
	<form id="checkout" method="post" action="/bttraining/checkout">
		<div id="payment-form"></div>
		Amount <input type="text" name="amount"> $
		<input type="submit" value="Pay">
	</form>


	<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
	<script>
		// Get client token from server side
		var clientToken = "${token}";

		var b = braintree.setup(clientToken, "dropin", {
			container : "payment-form"
		});
		
	</script>
</body>