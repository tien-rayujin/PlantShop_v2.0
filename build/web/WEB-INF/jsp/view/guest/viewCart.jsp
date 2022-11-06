<%--@elvariable id="cart" type="java.util.HashMap" --%> <!-- sessionScope -->
<%--@elvariable id="plantList" type="java.util.ArrayList" --%> <!-- sessionScope -->
<%-- 
    Document   : viewCart
    Created on : Oct 22, 2022, 8:19:08 PM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/my_utils" prefix="test"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            img{
                width: 400px;
                height: 400px;
            }
        </style>
    </head>
    <body>
        <div class="text-center">
            <h2>Cart</h2>
            <p><i>Customer name - ${(sessionScope.username == null) ? "guest" : sessionScope.user.fullname}</i></p>
        </div>
        
        
        <c:if test="${order_Successful}">
            <p class="text-center"><font color = "red">Your product was ordered Successfully, go to <a href="<c:url value="/user"/>">personal page</a> to check ? </font></p>
                </c:if>
        <c:if test="${updateCart_Successful}">
        <p class="text-center"><font color = "red">Your product was updated</font></p>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.cart != null and not empty sessionScope.cart and sessionScope.plantList != null and not empty sessionScope.plantList}">
            <p class="text-center"><a href="<c:url value="/guest">
                      <c:param name="action" value="emptyCartItem"/>
                    </c:url>">Empty my cart</a></p>
                <c:forEach items="${sessionScope.plantList}" var="eachP">
                    <c:forEach items="${sessionScope.cart.keySet()}" var="eachItem">
                        <c:if test="${eachP.getPlantId() == eachItem}">
                            <div class="row m-0">
                                <div class="col-lg-4 left-side-product-box pb-3">
                                    <img class="border p-3" src="<c:url value="${eachP.imgPath}" />" alt="Img for plant #${eachItem}"/>
                                </div>
                            
                                <div class="col-lg-8">
                                    <div class="right-side-pro-detail border p-3 m-0">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p><a href="<c:url value="/guest">
                                                          <c:param name="action" value="view"/>
                                                          <c:param name="plantId" value="${eachItem}"/>
                                                </c:url>">PlantId: ${eachP.plantId}</a></p>
                                                <p>Price ${eachP.price}$</p>
                                                <form action="<c:url value="/guest"/>" method="get">
                                                    <p>Quantity: <input type="number" name="quantityUpdate" min="0" value="${sessionScope.cart.get(eachItem)}"/></p>
                                                    <p><input type="hidden" name="plantId" value="${eachItem}"/></p>
                                                    <p><input type="submit" name="action" value="updateQuantity"/></p>
                                                    <p><input type="submit" name="action" value="deleteCartItem"/></p>
                                                </form>
                                            </div> 
                                        </div> 
                                    </div> 
                                </div> 
                            </div> 
                        </c:if> 
                    </c:forEach>
                </c:forEach>
                <!-- Total money here-->
                <p><b>Total money: <test:map_total list="${plantList}" map="${cart}"/>$</b></p> 
                
                <!-- Order now here-->
                <p><h3><a href="<c:url value="/user?action=orderNow"/>"><button class="btn btn-primary">Order now</button></a></h3></p>
            </c:when>
            <c:otherwise>
            <p class="text-center"><b>Your cart are empty, Please click <a href="<c:url value="/guest"/>">here</a> to add some products </b></p>                
            </c:otherwise>                
        </c:choose>
    </body>
</html>
