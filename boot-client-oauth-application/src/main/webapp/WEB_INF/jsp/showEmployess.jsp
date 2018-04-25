<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="false" %>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
	</head>		

	<body>
	
		<h1><strong>Show Employees</strong></h1>
		<ul>
		 <c:forEach var="listItem" items="${employees}">
		 <li>${listItem.empName }</li>
		 </c:forEach>
		</ul>
	</body>
	</html>