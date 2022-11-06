<%-- 
    Document   : admin_index
    Created on : Oct 30, 2022, 10:35:44 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MANAGER Page</title>
        <style>
            img{
                width: 100%;
                height: 50%;
            }
        </style>
    </head>
    <body>
        
        
        <!-- Background image here  -->
        <div class="has-bg-img bg-purple bg-blend-screen">
            <img class="bg-img" src="<c:url value="/images/wallpaper.jpg"/>" alt="wallpaper">
        </div>
        
        <!-- Session registry list-->
        <c:import url="/WEB-INF/jsp/view/admin/session.jsp"/>
        
        
    </body>
</html>
