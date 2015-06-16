<head>
	<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
</head>
Payment current status - ${transactionStatus}.
<br />
Transaction is being processed...
<form id="refund" method="post"
	action="/bttraining/cancelPayment">
	<input type="hidden" name="transactionId" value="${transactionId}">
	<input type="submit" id="submit" value="Cancel payment">
</form>

