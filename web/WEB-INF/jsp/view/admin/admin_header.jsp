<%-- 
    Document   : admin_header
    Created on : Nov 6, 2022, 4:59:17 PM
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
        <!-- Navigator -->
        <h2>Welcome ${user.fullname} - [Manager]</h2>
        <ul>
            <li><a href="<c:url value="/admin?action=manageSession">
               </c:url>">Manage Session</a></li>
        
            <li><a href="<c:url value="/admin?action=manageAccount">
               </c:url>">Manage Accounts</a></li>
            
            <li><a href="<c:url value="/admin?action=managePlant">
               </c:url>">Manage Plants</a></li>

            <li><a href="<c:url value="/admin?action=manageCate">
                </c:url>">Manage Categories</a></li>
            
            <li><a href="<c:url value="/admin?action=manageOrder">
               </c:url>">Manage Orders</a></li>

            
        
            <li><a href="<c:url value="/user?action=logout">
               </c:url>">Logout</a></li>
        </ul>
    </body>
</html>
