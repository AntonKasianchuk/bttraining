<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
<title>Customer list</title>
</head>
<body>
	<form id="payment" method="post" action="/bttraining/existedCustomer">
		<table>
				<tr>
					<th>CustomerId</th>
					<th>Customer Details</th>
					<th>Customer Transactions</th>
				</tr>
			<c:forEach items="${customerIds}" var="customerId">
				<tr>
					<td>${customerId}</td>
					<td><a
						href="/bttraining/customerDetails?customerId=${customerId}">Edit</a></td>
					<td><a
						href="/bttraining/transactionsList?customerId=${customerId}">View</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>