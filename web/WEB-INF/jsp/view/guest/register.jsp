<%--@elvariable id="registerFailed" type="java.lang.Boolean" scope="REQUEST_SCOPE"--%> 
<%-- 
    Document   : register
    Created on : Oct 21, 2022, 11:18:38 AM
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
        <section class="vh-100" style="background-color: #508bfc;">
            <div class="container h-100">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                  <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                      <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>
                            <p class="text-center">You must register to access more function.</p>

                            <div class="text-center">
                                <c:if test="${requestScope.registerFailed == true}">
                                    <b>The email you entered are invalid or already use.
                                        Try login ? <a href="<c:url value="/guest">
                                                           <c:param name="action" value="login" />
                                        </c:url>">here</a></b></c:if>
                            </div>
                                

                                    <form class="mx-1 mx-md-4" method="POST" action="<c:url value="/guest" />">
                                        <input type="hidden" name="action" value="register" />
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <input class="form-control" type="text" minlength="5" pattern="[a-zA-Z_0-9\s]+" title="Minimum length is 5" maxlength="20" name="username" required placeholder="Username"/>
                                        </div>
                                        
                                        
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <input class="form-control" type="password" pattern=".+" name="password" required placeholder="Password"/>
                                        </div>
                                        
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <input class="form-control" type="tel" pattern="[0-9]{10}" title="number must be 10 digit" name="phone" required placeholder="Phone"/>
                                        </div>
                                        
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <input class="form-control" type="email" name="email" required placeholder="Email"/>
                                        </div>
                                        
                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <input class="btn btn-primary btn-lg" type="submit" value="Register" />
                                        </div>
                                    </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>    
    </body>
</html>
