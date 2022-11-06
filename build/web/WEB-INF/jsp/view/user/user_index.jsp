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
                <table class="table text-center">
                    <c:forEach var="eachO" items="${orderList}" varStatus="status">
                        <c:if test="${status.first}">
                            <tr>
                                <th>No#</th>
                                <th>OrderId</th>
                                <th>OrderDate</th>
                                <th>ShipDate</th>
                                <th>Order Status</th>
                                <th>Action</th>
                            </tr>
                        </c:if>
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${eachO.orderId}</td>
                                <td>${eachO.orderDate}</td>
                                <td>${(eachO.shipDate) == null ? "Not yet" : eachO.shipDate}</td>
                                <td><c:choose>
                                        <c:when test="${eachO.status == 1}">PROCESSING</c:when>
                                        <c:when test="${eachO.status == 2}">COMPLETE</c:when>
                                        <c:when test="${eachO.status == 3}">CANCEL</c:when>
                                </c:choose></td>
                                <td><a href="<c:url value="/user">
                                        <c:param name="action" value="getDetail" />
                                        <c:param name="orderId" value="${eachO.orderId}" />
                                       </c:url>"><button class="btn btn-dark">Detail</button></a></td>
                            </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
            <h2 class="text-center">Dear Customer, you don't have any order!</h2>
            </c:otherwise>
        </c:choose>
        
        
        
        
        
    </body>
</html>
