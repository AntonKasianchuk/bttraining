function action(transactionId, action) {
	document.getElementById("transactionId").value = transactionId;
	var tranactionsForm = document.getElementById("transactions");
	tranactionsForm.action = "/bttraining/" + action;
	tranactionsForm.submit();
}
