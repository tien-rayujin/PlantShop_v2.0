<%-- 
    Document   : register_successful
    Created on : Oct 25, 2022, 9:41:11 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h2>Your register account - <b>${username}</b> was registed successfully</h2>
        <p>Please click <a href="<c:url value="guest?action=login"/>"/><button class="btn btn-primary">here</button></a> to go to Login page </p>
    </center>
    </body>
</html>
