<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
<title>Choose payment form</title>
</head>
<body>
	<form id="choosePaymentForm" method="post" action="/bttraining/paymentForm" >
	<input type="hidden" name="token" value="${token}">
	<table>
		<tr>
			<td>Choose payment form</td>
			<td><input type="radio" name="payment_form" value="Dropin"
				checked>Drop-in payment form <input type="radio"
				name="payment_form" value="Custom">Custom payment form</td>
		</tr>
		<tr>
				<td colspan="2"><input type="submit" value="Next" /></td>
			</tr>
	</table>
	</form>
</body>
</html>