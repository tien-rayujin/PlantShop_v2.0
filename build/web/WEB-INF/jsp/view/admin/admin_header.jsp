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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
            <div class="container-fluid">
                <div class="navbar-nav">
                    <a class="nav-item nav-link active" href="<c:url value="/admin">
                       </c:url>">Home</a>

                    <a class="nav-item nav-link" href="<c:url value="/admin?action=manageAccount">
                       </c:url>">Manage Accounts</a>

                    <a class="nav-item nav-link" href="<c:url value="/admin?action=managePlant">
                       </c:url>">Manage Plants</a>

                    <a class="nav-item nav-link" href="<c:url value="/admin?action=manageCate">
                        </c:url>">Manage Categories</a>

                    <a class="nav-item nav-link" href="<c:url value="/admin?action=manageOrder">
                       </c:url>">Manage Orders</a>
                    
                    <a class="nav-item nav-link" href="<c:url value="/guest">
                </c:url>">Shopping</a>
                </div>
            
            <p><font color="white">[ADMIN] - ${user.fullname}</font></p>
            <a class="nav-item nav-link" href="<c:url value="/user?action=logout">
               </c:url>"><button class="btn btn-primary">Logout</button></a>
                
            </div>
        </nav>            
    </body>
</html>
