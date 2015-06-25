<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
</head>
<body>
	<h2>Report</h2>
	<table>
		<tr>
			<th>Kind</th>
			<th>Count</th>
			<th>Merchant Account Id</th>
			<th>Card Type</th>
			<th>Amount Settled</th>
		</tr>
		<c:forEach items="${reports}" var="report">
			<tr>
				<td>${report.kind}</td>
				<td>${report.count}</td>
				<td>${report.merchantAccountId}</td>
				<td>${report.cardType}</td>
				<td>${report.amountSettled}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>