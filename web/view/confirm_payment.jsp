<head>
	<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
</head>
<body>
<h2>Payment processing</h2>

<p> Payment current status - ${transactionStatus}. </p>

<form id="settlePaymentForm" method="post"
	action="/bttraining/settlePayment">
	<input type="hidden" name="transactionId" value="${transactionId}">
	<input type="submit" id="submit" value="Settle payment">
</form>

<form id="cancelPaymentForm" method="post"
	action="/bttraining/cancelPayment">
	<input type="hidden" name="transactionId" value="${transactionId}">
	<input type="submit" id="submit" value="Cancel payment">
</form>
</body>

