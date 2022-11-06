<%-- 
    Document   : testJS
    Created on : Oct 30, 2022, 8:53:53 PM
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
        <input type="text" id="needEdit_p1" disabled value="default_value">
        <input type="text" id="needEdit_p2" disabled value="default_value2">
        <input type="text" id="needEdit_p3"  disabled value="default_value4">
        <input type="text" id="needEdit_p4"  disabled value="default_value3">
        <input type="text" id="needEdit_p5"  disabled value="default_value5">
        
        <button onclick="doEdit('needEdit')">edit</button>
        
        
        <script>
            function doEdit(el_class){
//                document.getElementById(el_id).disabled = false;
                document.getElementById(el_class+"_p1").disabled = false;
                document.getElementById(el_class+"_p2").disabled = false;
                document.getElementById(el_class+"_p3").disabled = false;
                document.getElementById(el_class+"_p4").disabled = false;
                document.getElementById(el_class+"_p5").disabled = false;
            }
        </script>
    </body>
</html>
