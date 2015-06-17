<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
<title>Customer transactions list</title>
</head>
<body>
	<form id="payment" method="post" action="/bttraining/transactionEdit">
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
					<td>${transaction.id}</td>
					<td>${transaction.date}</td>
					<td>${transaction.type}</td>
					<td>${transaction.status}</td>
					<td>${transaction.customerName}</td>
					<td>${transaction.paymentInformation}</td>
					<td>${transaction.amount}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
