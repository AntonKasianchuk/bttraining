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
		<table>
			<tr>
					<th>ID</th>
					<th>Transaction Date</th>
					<th>Type</th>
					<th>Status</th>
					<th>Customer</th>
					<th>Payment Information</th>
					<th>Amount</th>
			</tr>
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td><a
						href="/bttraining/transactionDetails?transactionId=${transaction.id}">${transaction.id}</a></td>
					<td>${transaction.date}</td>
					<td>${transaction.type}</td>
					<td>${transaction.status}</td>
					<td>${transaction.customerName}</td>
					<td>${transaction.paymentInformation}</td>
					<td>${transaction.amount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7"><a
						href="/bttraining/createTransaction?customerId=${customerId}">New transaction</a></td>
			</tr>
		</table>
</body>
