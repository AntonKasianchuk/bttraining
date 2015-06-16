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
			<c:forEach items="${customerIds}" var="customerId">
				<tr>
					<td>Customer Id</td>
					<td colspan="2"><a
						href="/bttraining/edit_customer?customerId=${customerId}">${customerId}</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>