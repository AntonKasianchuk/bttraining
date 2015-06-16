<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css"
	media="screen" />
<title>Edit customer</title>
</head>
<body>
	<form id="payment" method="post" action="/bttraining/register">
		<table>
			<tr>
				<td>First name</td>
				<td><input type="text" name="first_name" value="${customer.firstName}"></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input type="text" name="last_name" value="${customer.lastName}"/></td>
			</tr>
			<tr>
				<td>Company</td>
				<td><input type="text" name="company" value="${customer.company}"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${customer.email}"/></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="text" name="phone" value="${customer.phone}"/></td>
			</tr>
			<tr>
				<td>Fax</td>
				<td><input type="text" name="fax" value="${customer.fax}"/></td>
			</tr>
			<tr>
				<td>Website</td>
				<td><input type="text" name="website" value="${customer.website}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form>
</body>
</html>