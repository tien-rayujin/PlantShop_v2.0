<%--@elvariable id="loginFailed" type="java.lang.Boolean" scope="REQUEST_SCOPE"--%> 
<%-- 
    Document   : login
    Created on : Oct 21, 2022, 11:18:27 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
    </head>
    <body>
            <section class="vh-100" style="background-color: #508bfc;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card shadow-2-strong" style="border-radius: 1rem;">
                                <div class="card-body p-5 text-center">
                                    <h3 class="mb-5">Sign in</h3>
                                    You must login to access more function<br /><br />

                                    <c:if test="${requestScope.loginFailed == true}">
                                        <b>The username or password you entered are not correct. Please try
                                        again.</b><br /><br />
                                    </c:if>


                                    <form method="POST" action="<c:url value="/guest" />">
                                        <div class="form-outline mb-4">
                                            <input class="form-control form-control-lg" type="email" name="username" required placeholder="Email"/>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input  class="form-control form-control-lg" type="password" name="password" required placeholder="Password"/>
                                        </div>
                                        
                                        <div class="form-check d-flex justify-content-start mb-4">
                                            <input class="form-check-input" id="formRememberPassword" type="checkbox" name="remember" value="true">
                                            <label class="form-check-label" for="formRememberPassword"> Remember password </label>
                                        </div>
                                        <input type="hidden" name="action" value="login" />
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="Log In" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
    </body>
        
</html>
