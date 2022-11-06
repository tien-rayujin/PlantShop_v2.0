<%-- 
    Document   : session
    Created on : Oct 16, 2022, 10:42:46 PM
    Author     : RaeKyo
--%>

<%-- @elvariable id="numberOfSessions" type="java.lang.Integer" --%>
<%-- @elvariable id="sessionList" type="java.util.List" --%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%!
    private static String toString(long timeInterval) {
        if (timeInterval < 1_000) {
            return "less than one second";
        }
        if (timeInterval < 60_000) {
            return (timeInterval / 1_000) + " seconds";
        }
        return "about " + (timeInterval / 60_000) + " minutes";
    }
%>

<%
    @SuppressWarnings("unchecked")
    List<javax.servlet.http.HttpSession> sessionList = (List<HttpSession>)request.getAttribute("sessionList");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <h2>Sessions</h2>
        There are a total of ${requestScope.numberOfSessions} active sessions in this
        application.<br /><br />
        <%
            long timestamp = System.currentTimeMillis();
            for (HttpSession aSession : sessionList) {
                out.print(aSession.getId() + " - "
                        + aSession.getAttribute("username"));
                if (aSession.getId().equals(session.getId())) {
                    out.print(" (you)");
                }
                out.print(" - last active "
                        + toString(timestamp - aSession.getLastAccessedTime()));
                out.println(" ago<br />");
            }
        %>
    </body>
</html>