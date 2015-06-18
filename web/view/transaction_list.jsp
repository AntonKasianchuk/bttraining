<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
<title>Customer transactions list</title>
</head>
<h2>Customer transactions list</h2>
<body>
<form id="transactions" method="post" action="/bttraining/editCustomer">
		<input type="hidden" id="transactionId" name="transactionId"
			value="">
		<table>
			<tr>
					<th>ID</th>
					<th>Transaction Date</th>
					<th>Type</th>
					<th>Status</th>
					<th>Customer</th>
					<th>Payment Information</th>
					<th>Amount</th>
					<th>Action</th>
			</tr>
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td>${transaction.id}</td>
					<td>${transaction.date}</td>
					<td>${transaction.type}</td>
					<td>${transaction.status}</td>
					<td>${transaction.customerName}</td>
					<td>${transaction.paymentInformation}</td>
					<td>${transaction.amount}</td>
					<td><c:choose>
							<c:when test="${transaction.isSettled}">
								<a href="javascript:action('${transaction.id}', 'refundPayment')">Refund Payment</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:action('${transaction.id}','cancelPayment')">Cancel
									Payment</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7"><a
						href="/bttraining/createTransaction?customerId=${customerId}">New transaction</a></td>
			</tr>
		</table>
</form>
<script src="js/transactionListAction.js"></script>
</body>
