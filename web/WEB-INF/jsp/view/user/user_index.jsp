<%-- 
    Document   : User_page
    Created on : Oct 21, 2022, 11:28:04 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>

        <!-- list all order of user here-->
        <c:choose>
            <c:when test="${orderList != null and not empty orderList}">
                <c:forEach var="eachO" items="${orderList}">
                    <p>OrderId: ${eachO.orderId}</p>
                    <p>OrderDate: ${eachO.orderDate}</p>
                    <p>ShipDate: ${eachO.shipDate}</p>
                    <p>Order's status: <c:choose>
                            <c:when test="${eachO.status == 1}">PROCESSING</c:when>
                            <c:when test="${eachO.status == 2}">COMPLETE</c:when>
                            <c:when test="${eachO.status == 3}">CANCEL</c:when>
                    </c:choose></p>
                    <p><a href="<c:url value="/user">
                              <c:param name="action" value="getDetail" />
                              <c:param name="orderId" value="${eachO.orderId}" />
                    </c:url>">Detail</a></p><br />
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>Dear Customer, you don't have any order!</h2>
            </c:otherwise>
        </c:choose>
        
        
        
        
        
    </body>
</html>
