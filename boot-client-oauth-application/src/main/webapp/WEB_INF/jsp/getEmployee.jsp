<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
	</head>		

	<body>
	
		<h1><strong>get employee info</strong></h1>
						
		<div id="getEmployee">
		<form:form action="http://localhost:8088/oauth/authorize" method="post" >
		
			<label>Enter Employee details</label>
			 <input type="text" name="response_type" value="code" />
			 <input type="text" name="client_id" value="clientId" />
			 <input type="text" name="redirect_uri" value="http://localhost:8099/showEmployess" />
			 <input type="text" name="scope" value="read" />
			<input type="submit" value="Get Info"/>
		
		</form:form>
		</div>
	</body>
	</html>