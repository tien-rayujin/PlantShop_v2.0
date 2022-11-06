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
        <title>JSP Page</title>
    </head>
    <body>
        <!-- navigation bar for user -->
        <ul>
            <li><a href="<c:url value="/guest">
                   </c:url>"><img src="<c:url value="/images/cute.jpg"/>" alt="Img Logo"/></a></li>
            
            <li><a href="<c:url value="/user">
            </c:url>">Home</a></li>
            
            <li><a href="<c:url value="/user">
                       <c:param name="action" value="changeProfile"/>
            </c:url>">Change Profile</a></li>
            
            <li><a href="<c:url value="/user">
                       <c:param name="action" value="viewComplete"/>
            </c:url>">Complete Order</a></li>
            
            <li><a href="<c:url value="/user">
                       <c:param name="action" value="viewCancel"/>
            </c:url>">Canceled Order</a></li>
            
            <li><a href="<c:url value="/user">
                       <c:param name="action" value="viewProcessing"/>
            </c:url>">Processing Order</a></li>
            
            
            
            <li><form action="<c:url value="/user"/>">
                    <input type="hidden" name="action" value="searchOrder"/>
                    <input type="date" name="fromDate" min="2022-01-01" />
                    <input type="date" name="toDate" min="2022-01-01" max="2222-01-01" />
                    <input type="submit" value="Search"/>
                </form></li>
            
        </ul>        
        
        <!-- welcome and lgoout :)))-->
        <h1>Welcome back, This is ${user.fullname} personal Page!</h1>
        <p><a href="<c:url value="user">
                  <c:param name="action" value="logout" />
        </c:url>">Logout</a></p><br/><br/>
    </body>
</html>
