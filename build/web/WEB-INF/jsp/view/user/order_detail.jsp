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
        <style>
            img{
                width: 400px;
                height: 400px;
            }
        </style>
    </head>
    <body>
        <div class="text-center">
            <h2>Detail for OrderId #${requestScope.orderId}</h2>
            <p><i>Customer name - ${username} - <b>${user.fullname}</b></i></p>


            <c:if test="${requestScope.order_listDetail != null 
                          and not empty requestScope.order_listDetail}"> <!-- list all data of ArrayList<OrderDetail> -->
                <c:if test="${requestScope.order_listDetail.get(0).status == Order.CANCEL}">
                    <c:if test="${updateOrder_Successful}"><font color="red"><h5>Your order are updated</h5></font></c:if>
                    <h4>This Order was "Canceled" - To Order again click <a href="<c:url value="/user">
                                                                             <c:param name="action" value="reOrder"/>
                                                                             <c:param name="orderId" value="${requestScope.orderId}"/>
                                                                        </c:url>"><button class="btn btn-dark">Here</button></a></h4>
                </c:if>
                <c:if test="${requestScope.order_listDetail.get(0).status == Order.PROCESSING}">
                    <c:if test="${updateOrder_Successful}"><font color="red"><h5>Your order are updated</h5></font></c:if>
                    <h4>The Order was in "Processing" - To Cancel Order click <a href="<c:url value="/user">
                                                                             <c:param name="action" value="cancelOrder"/>
                                                                             <c:param name="orderId" value="${requestScope.orderId}"/>
                                                                                 </c:url>"><button class="btn btn-dark">Here</button></a></h4>
                </c:if>
        </div>    
            <c:forEach var="eachD" items="${requestScope.order_listDetail}">
                <div class="row m-0">
                    <div class="col-lg-4 left-side-product-box pb-3">
                        <img class="border p-3" src="<c:url value="${eachD.imgPath}" />" alt="alt"/>
                    </div>
                    
                    
                    <div class="col-lg-8">
                        <div class="right-side-pro-detail border p-3 m-0">
                            <div class="row">
                                <div class="col-lg-12">
                                    <p>PlantId: ${eachD.plantId}</p>
                                    <p>PlantName: ${eachD.plantName}</p>
                                    <p>Price: ${eachD.price}</p>
                                    <p>PlantStatus: ${eachD.plantStatus == Plant.AVAILABLE ? "available" : "unavailable"}</p>
                                    <p>Quantity: ${eachD.quantity}</p><br />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- Total money here-->
            <p class="text-center"><b>Total money: <utils:list_total_orderDetail list="${order_listDetail}"/>$</b></p>
        </c:if>
            
            <p class="text-center"><a href="<c:url value="/user" />"><button class="btn btn-dark">Return</button></a></p>
    </body>
</html>
