<%--@elvariable id="user" type="dto.Account" --%> <!-- sessionScope -->
<%--@elvariable id="username" type="java.lang.String" --%> <!-- sessionScope -->
<%--@elvariable id="update_Successful" type="java.lang.Boolean" --%> <!-- requestScope -->

<%-- 
    Document   : user_changeProfile
    Created on : Oct 24, 2022, 10:48:26 AM
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
        <h2>Change User Profile</h2>
        <p><i>Customer name - ${username}</i></p>
        <c:if test="${update_Successful}">
            <p><font color = "red">Your profile was update successful</font></p>
        </c:if>
        <table>
            <tr>
                <th></th>
                <th>Current</th>
                <th>Update</th>
                <th>Action</th>
            </tr>
            <tr>
                <th>FullName</th>
                <td><input disabled size="15" type="text" value="${user.fullname}"/></td>
            <form method="post" action="<c:url value="">
                      <c:param name="action" value="changeProfile"/>
                  </c:url>">
                <td><input name="newName" size="15" type="text" value="" placeholder="Name to update ..."/></td>
                <td><input size="7" type="submit" value="update"/></td>

                </tr>
                <tr>
                    <th>Phone</th>
                    <td><input disabled size="15" type="text" value="${user.phone}"/></td>

                    <td><input name="newPhone"" size="15" type="text" value="" placeholder="Phone to update ..."/></td>
                    <td><input size="7" type="submit" value="update"/></td>
            </form>
            </tr>
        </table>
    </body>
</html>
