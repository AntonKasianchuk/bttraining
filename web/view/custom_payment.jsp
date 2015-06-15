<head>
<title>Braintree Payment Form</title>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
</head>
<body>
	<h1>Braintree Payment Form</h1>
	<div>
		<form action="/bttraining/checkout" method="POST"
			id="braintree-payment-form">
			<p>
				<label>Card Number</label> <input type="text" size="20"
					autocomplete="off" data-braintree-name="number" />
			</p>
			<p>
				<label>CVV</label> <input type="text" size="4" autocomplete="off"
					data-braintree-name="cvv" />
			</p>
			<p>
				<label>Expiration (MM/YY)</label> <input type="text" size="2"
					data-braintree-name="expiration_date" />
			</p>
			Amount <input type="text" name="amount"> $ <input
				type="submit" id="submit" value="Pay">
		</form>
	</div>

	<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
	<script>
		// Get client token from server side
		var clientToken = "${token}";

		var b = braintree.setup(clientToken, "custom", {
			id : "braintree-payment-form"
		});
	</script>
</body>