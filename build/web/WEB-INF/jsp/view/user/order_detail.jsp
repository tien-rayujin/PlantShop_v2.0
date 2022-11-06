<%-- 
    Document   : order_detail
    Created on : Oct 22, 2022, 9:14:16 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/my_utils" prefix="utils" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Order</title>
    </head>
    <body>
        <h2>Detail for OrderId #${requestScope.orderId}</h2>
        <p><i>Customer name - ${username} - <b>${user.fullname}</b></i></p>
        
            
        <c:if test="${requestScope.order_listDetail != null 
                      and not empty requestScope.order_listDetail}"> <!-- list all data of ArrayList<OrderDetail> -->
            <c:if test="${requestScope.order_listDetail.get(0).status == Order.CANCEL}">
                <c:if test="${updateOrder_Successful}"><font color="red"><b>Your order are updated</b></font></c:if>
                <h3>The Order was "Canceled" - To Order again click <a href="<c:url value="/user">
                                                                         <c:param name="action" value="reOrder"/>
                                                                         <c:param name="orderId" value="${requestScope.orderId}"/>
                                                                    </c:url>">here</a></h3>
            </c:if>
            <c:if test="${requestScope.order_listDetail.get(0).status == Order.PROCESSING}">
                <c:if test="${updateOrder_Successful}"><font color="red"><b>Your order are updated</b></font></c:if>
                <h3>The Order was in "Processing" - To Cancel Order click <a href="<c:url value="/user">
                                                                         <c:param name="action" value="cancelOrder"/>
                                                                         <c:param name="orderId" value="${requestScope.orderId}"/>
                                                                    </c:url>">here</a></h3>
            </c:if>
            
            <c:forEach var="eachD" items="${requestScope.order_listDetail}">
                <p hidden >DetailId: ${eachD.detailId}</p>
                <p  ><img src="<c:url value="${eachD.imgPath}" />" alt="alt"/></p>
                <p  >OrderId: ${eachD.orderId}</p>
                <p  >PlantId: ${eachD.plantId}</p>
                <p  >PlantName: ${eachD.plantName}</p>
                <p  >Price: ${eachD.price}</p>
                <p  >PlantStatus: ${eachD.plantStatus == Plant.AVAILABLE ? "available" : "unavailable"}</p>
                <p  >Quantity: ${eachD.quantity}</p><br />
            </c:forEach>
            <!-- Total money here-->
            <p><b>Total money: <utils:list_total_orderDetail list="${order_listDetail}"/>$</b></p>
        </c:if>
            
        <a href="<c:url value="/user" />">Return to list Order</a>
    </body>
</html>
