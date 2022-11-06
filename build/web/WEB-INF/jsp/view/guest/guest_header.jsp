<%-- 
    Document   : guest_header
    Created on : Nov 6, 2022, 4:50:36 PM
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
                    <a class="nav-item nav-link active" href="<c:url value="/guest">
                    </c:url>">Home</a>

                    <a class="nav-item nav-link" href="<c:url value="/guest?action=login">
                    </c:url>">Login</a>

                    <a class="nav-item nav-link" href="<c:url value="/guest?action=register">
                    </c:url>">Register</a>

                    <a class="nav-item nav-link" href="<c:url value="/guest?action=viewCart">
                    </c:url>">ViewCart</a>
                </div>
            
                <form class="d-flex" action="<c:url value="/guest"/>">
                    <input class="form-control me-2" type="search" name="keySearch" placeholder="Search product in here..." value="${requestScope.keySearch}">
                    <select name="searchBy">
                        <option value="byName">By Name</option>
                        <option value="byCate">By Category</option>
                    </select>
                    <input type="hidden" name="action" value="search" />
                    <input class="btn btn-outline-success" type="submit" value="Search">
                </form>
            </div>        
        </nav>
    </body>
</html>
