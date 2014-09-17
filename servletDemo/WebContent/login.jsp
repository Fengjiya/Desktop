<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="errors.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<span style="color:red;font-weight:bold">
	<%
	if( request.getAttribute("errMsg") != null )
	{
		out.println( request.getAttribute("errMsg") + "<br/");
	}
	%>
</span>
<!-- form's id="login1" name="loginPage" method="post" action="login" -->
<form id="login1"  name="loginPage" method="post" action="login">
UserName:<input type="text" name="username"/><br/>
PassWord:<input type="password" name="pass"/><br/>
<input type="submit" value="Login"/><br/>
</form>
<p><b>Note:</b> The characters in a password field are masked (shown as asterisks or circles).</p>
</body>
</html>