<%-- 
    Document   : manage_account
    Created on : Oct 30, 2022, 11:23:05 AM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account Page</title>
    </head>
    <body>
        <h2>Manage User Account</h2>
        <form action="<c:url value="/admin"/>">
            <input type="text" name="keySearch" placeholder="Search user fullname here..." value="${requestScope.keySearch}">
            <input type="hidden" name="action" value="searchAccount" />
            <input type="submit" value="Search">
        </form>

        <c:if test="${updateAccount_Successful}">
            <h3><font color='red'>Account update Successfully</font></h3>
        </c:if>
            
            
        <c:choose>
            <c:when test="${listAccount != null and not empty listAccount}">
                <table>
                <tr>
                    <th>AccountID </th>
                    <th>Email</th>
                    <th>Full Name </th>
                    <th>Status</th>
                    <th>Phone </th>
                    <th>Role</th>
                    <th>Update</th>
                </tr>
                <c:forEach var="eachAc" items="${listAccount}">
                <tr>
                    <td>${eachAc.accId}</td>
                    <td>${eachAc.email}</td>
                    <td>${eachAc.fullname}</td>
                    <td>${(eachAc.status == Account.ACTIVE)? "Active" : "inActive"}</td>
                    <td>${eachAc.phone}</td>
                    <td>${(eachAc.role == Account.USER)? "User" : "Admin"}</td>
                    <td><a href="<c:url value="/admin">
                               <c:param name="action" value="updateAccountStatus"/>
                               <c:param name="email" value="${eachAc.email}"/>
                    </c:url>">${(eachAc.status == Account.ACTIVE)? (eachAc.email == username)? "" : "Block" : "UnBlock"}</a></td>
                </tr>        
                </c:forEach>
                </table> 
            </c:when>
            <c:otherwise>
                <h3><font color="red">Account was not founded !</font></h3>
            </c:otherwise>
        </c:choose>    
    </body>
</html>
