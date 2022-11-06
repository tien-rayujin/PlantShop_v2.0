<%-- 
    Document   : test_tag
    Created on : Oct 29, 2022, 11:25:32 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="test" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><test:simpleTagFile message="Hello from tag file">
                Hello RaeKyo
        </test:simpleTagFile></h1>
    </body>
</html>
