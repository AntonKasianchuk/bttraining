<head>
	<title>Braintree Payment Form</title>
	<style type="text/css">
		body {
			background: #fafafa
				url(http://jackrugile.com/images/misc/noise-diagonal.png);
			color: #444;
			font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
			text-shadow: 0 1px 0 #fff;
		}
		
		form {
			width: 300px;
			position: relative;
			left: 15%;
			margin-left: -150px;
			background-color: #eee;
			padding: 1em;
			margin-top: 2em;
		}
		
		#number {
			position: relative;
			height: 2em;
			border: 1px solid #ddd;
			padding: 0 0.5em;
			background-color: white;
		}
		
		#cvv {
			position: relative;
			height: 2em;
			border: 1px solid #ddd;
			padding: 0 0.5em;
			background-color: white;
		}
		
		#expiration-date {
			position: relative;
			height: 2em;
			border: 1px solid #ddd;
			padding: 0 0.5em;
			background-color: white;
		}
		
		#card-number.braintree-hosted-fields-invalid {
			border-bottom: 2px solid #D0041D;
		}
	</style>
</head>
<body>
	<h2>Payment details</h2>
	<div>
		<form action="/bttraining/payment" method="POST"
			id="braintree-payment-form">
			<div id="number"></div>
			<br />
			<div id="cvv"></div>
			<br />
			<div id="expiration-date"></div>
			<br /> Amount <input type="text" name="amount"> $ <br /> <input
				type="submit" id="submit" value="Pay">
		</form>
	</div>

	<script src="https://js.braintreegateway.com/v2/braintree.js"></script>
	<script>
		// Get client token from server side
		var clientToken = "${token}";

		var b = braintree.setup(clientToken, "custom", {
			id : "braintree-payment-form",
			hostedFields : {
				styles : {
					// Style all elements
					"input" : {
						"font-size" : "16pt"
					},

					// Styling a specific field
					".number" : {
						"font-family" : "monospace"
					},

					".cvv" : {
						"font-family" : "monospace"
					},
					
					// Styling element state
					":focus" : {
						"color" : "blue"
					},
					".valid" : {
						"color" : "green"
					},
					".invalid" : {
						"color" : "red"
					},
				},
				number : {
					selector : "#number",
					placeholder : "Card Number"
				},
				expirationDate : {
					selector : "#expiration-date",
					placeholder : "MM/YY"
				},
				cvv : {
					selector : "#cvv",
					placeholder : "CVV"
				}
			}
		});
	</script>
</body>