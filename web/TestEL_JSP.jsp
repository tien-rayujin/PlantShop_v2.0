<%-- 
    Document   : TestEL_JSP
    Created on : Oct 21, 2022, 11:21:26 AM
    Author     : RaeKyo
--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         TestEL<br/>
        
        Is 'hello world' contain 'hello' : ${fn:contains('hello world', 'hello')} <br/>
        Test escapseXML for (<, >, <=, >=, ==, =, !=) = ${fn:escapeXml("(<, >, <=, >=, ==, =, !=)")}<br/>
        <c:set var="string1" value="email1@gmail.com" />
        <c:set var="string2" value="${fn:split(string1,'i')}" />
        <c:set var="string3" value="${fn:join(string2,'-')}" />
        string3 = ${string3}

        
        <jsp:useBean id="list" class="java.util.ArrayList"  />
        <c:set var='none' value="${list.add('animal1')}" />
        <c:set var='none' value="${list.add('animal2')}" />
        <c:set var='none' value="${list.add('animal3')}" />
        <c:set var='none' value="${list.add('animal4')}" /> <br/>
        <c:out value="${list}" /><br/>
        <c:forEach items="${list}" var="each">
            <c:out value="${each}" /> <br/>
        </c:forEach>
        
        Test size of String: ${fn:length("This is my String")}<br/>
        Test size of Collection : ${fn:length(list)}<br/>
        <c:set var="testString" value="Test my String" />
        To Upper my String : ${fn:toUpperCase(list.get(1))}<br/>
        To Lower my String : ${fn:toLowerCase(list.get(1))}<br/>
        
        
    </body>
</html>
