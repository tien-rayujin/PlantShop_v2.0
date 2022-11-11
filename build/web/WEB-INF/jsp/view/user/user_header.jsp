<%-- 
    Document   : user_header
    Created on : Nov 6, 2022, 4:55:42 PM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Personal Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
    </head>
    <body>
        <!-- navigation bar for user -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
            <div class="container-fluid">
                <div class="navbar-nav">
                    <a class="navbar-brand" href="<c:url value="/guest"/>">DISPATCH COMPANY</a>
                    
                    <a class="nav-item nav-link active" href="<c:url value="/user">
                    </c:url>">Home</a>
                    
                    <a class="nav-item nav-link" href="<c:url value="/guest">
                    </c:url>">Shopping</a>

                    <a class="nav-item nav-link" href="<c:url value="/user">
                               <c:param name="action" value="changeProfile"/>
                    </c:url>">Change Profile</a>

                    <a class="nav-item nav-link" href="<c:url value="/user">
                               <c:param name="action" value="viewComplete"/>
                    </c:url>">Complete Order</a>

                    <a class="nav-item nav-link" href="<c:url value="/user">
                               <c:param name="action" value="viewCancel"/>
                    </c:url>">Canceled Order</a>

                    <a class="nav-item nav-link" href="<c:url value="/user">
                               <c:param name="action" value="viewProcessing"/>
                    </c:url>">Processing Order</a>
                </div>
            
            
                <form class="d-flex" action="<c:url value="/user"/>">
                    <input type="hidden" name="action" value="searchOrder"/>
                    <input class="form-control me-2" type="date" name="fromDate" min="2022-01-01" />
                    <input class="form-control me-2" type="date" name="toDate" min="2022-01-01" max="2222-01-01" />
                    <input class="btn btn-outline-success" type="submit" value="Search"/>
                </form>      
        
        <!-- welcome and lgoout :)))-->
        <p><font color="white">${user.fullname}</font></p>
        <p><a href="<c:url value="user">
                  <c:param name="action" value="logout" />
              </c:url>"><button class="btn btn-primary">Logout</button></a></p>
        
            </div>        
        </nav>
        <br/>
    </body>
</html>
