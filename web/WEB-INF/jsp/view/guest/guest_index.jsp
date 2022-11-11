<%--@elvariable id="plantList" type="java.util.ArrayList" --%> <!-- scope="session_scope" -->
<%--@elvariable id="keySearch" type="java.lang.String" --%> <!-- scope="REQUEST_SCOPE"-->
<%-- 
    Document   : guest_index
    Created on : Oct 21, 2022, 2:40:27 PM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Plant Shop v2.0</title>
        <style>
            img{
                width: 300px;
                height: 300px;
            }
        </style>
    </head>
    <body>
        <div class="row product-list" align="center">
            <h3 class="mb-5"> Product Lists</h3>
                <c:choose>
                    <c:when test="${plantList != null && not empty plantList}">
                        <c:forEach var="eachP" items="${plantList}">
                            <div class="col-md-3" align="center" style="margin-bottom: 20px"> 
                                    <div class="card" style="width: 18rem;">
                                        <p hidden="">PlantId: ${eachP.plantId}</p>
                                        <img class="card-img-top" src="<c:url value="${eachP.imgPath}" />" alt="Img for '${eachP.plantName}'"/>
                                            
                                        <div class="card-body">
                                            <h5 class="card-title">${eachP.plantName}</h5>
                                            <p class="card-text">Price: ${eachP.price} $</p>
                                            <p class="card-text">Status: ${eachP.status == Plant.AVAILABLE ? "Available" : "Out of Stocks"}</p>
                                            <p><a class="btn btn-primary" href="<c:url value="/guest">
                                                      <c:param name="action" value="view"/>
                                                      <c:param name="plantId" value="${eachP.plantId}"/>
                                                  </c:url>">Detail</a></p><br />
                                        </div>
                                    </div>
                                </div>    
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                        <h2>No product was found !</h2>
                    </c:otherwise>
                </c:choose>
                     
        </div>
    </body>
</html>
