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
                <input style="padding: 5px; width: 400px" type="text" name="customerName" placeholder="Search username here ..." value="${customerName}"/>
                <input class="btn btn-outline-success" type="submit" value="Search"/>
            </form>
                
            <form class="d-flex" action="<c:url value="/admin"/>">
                <input type="hidden" name="action" value="searchOrderByDate"/>
                <input required="" class="form-control me-2" type="date" name="fromDate" min="2022-01-01" />
                <input required="" class="form-control me-2" type="date" name="toDate" min="2022-01-01" max="2222-01-01" />
                <input class="btn btn-outline-success" type="submit" value="Search"/>
            </form>    

            <c:if test="${customerName != null}">
                <p><i>The result for Customer "${customerName}":</i></p>
            </c:if>

            <c:if test="${updateOrder_Successful}">
                <h3><font color = 'red'>Order update Successfully</font></h3>
            </c:if>
        </div><br/><br/>    
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
                        <td><a href="<c:url value="/user?action=getDetail&orderId=${eachO.orderId}"/>">${eachO.orderId}</a></td>
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
