<%-- 
    Document   : manage_order
    Created on : Nov 6, 2022, 2:01:41 PM
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
        <div class="text-center">
        <h2>Manage Order</h2>
        
        <form action="<c:url value="/admin"/>">
            <input type="hidden" name="action" value="searchOrderByName">
            <input type="text" name="customerName" placeholder="Search user email here ..." value="${customerName}"/>
            <input type="submit" value="Search"/>
        </form>
        
        <c:if test="${customerName != null}">
            <p><i>The result for Customer "${customerName}":</i></p>
        </c:if>
            
        <c:if test="${updateOrder_Successful}">
            <h3><font color = 'red'>Order update Successfully</font></h3>
        </c:if>
        </div>    
        <table class="table text-center">
            <c:choose>   
            <c:when test="${orderList != null and not empty orderList}">
            <c:forEach items="${orderList}" var="eachO" varStatus="status">
                <c:if test="${status.first}">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>ShipDate</th>
                        <th>accId</th>
                        <th>status</th>
                        <th>action</th>
                    </tr>
                </c:if>
                    <tr>
                        <td>${eachO.orderId}</td>
                        <td>${eachO.orderDate}</td>
                        <td>${(eachO.shipDate == null) ? "Not Yet" : eachO.shipDate}</td>
                        <td>${eachO.accId}</td>
                        <td><c:choose>
                            <c:when test="${eachO.status == 1}">PROCESSING</c:when>
                            <c:when test="${eachO.status == 2}">COMPLETE</c:when>
                            <c:when test="${eachO.status == 3}">CANCEL</c:when>
                    </c:choose></td>
                        <td><c:choose>
                                <c:when test="${eachO.status == 1}">
                                    <a href="<c:url value="/admin?action=cancelOrder&orderId=${eachO.orderId}"/>"><button class="btn btn-secondary">Cancel</button></a>
                                </c:when>
                            <c:when test="${eachO.status == 2}"></c:when>
                            <c:when test="${eachO.status == 3}">
                                        <a href="<c:url value="/admin?action=reOrder&orderId=${eachO.orderId}"/>"><button class="btn btn-secondary">Reorder</button></a>
                            </c:when>
                    </c:choose></td>
            </c:forEach>
            </c:when>    
            <c:otherwise>
                <h3><font color="red">The user "${customerName}" have no Order!</font></h3>
            </c:otherwise>
            </c:choose>
        </table>
    </body>
</html>
