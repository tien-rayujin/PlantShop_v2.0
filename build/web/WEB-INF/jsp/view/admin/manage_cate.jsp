<%-- 
    Document   : manage_cate
    Created on : Nov 6, 2022, 11:30:32 AM
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
        <div class="text-center">
            <h2>Manage Category</h2><br/>

            <c:if test="${updateCate_Successful}">
                <h3><font color='red'>Category update Successfully</font></h3>
            </c:if>
            
        </div>    
        <table class="table text-center">
            <c:forEach items="${cateList}" var="eachCate" varStatus="status">
                <c:if test="${status.first}">
                    <tr>
                        <th>CateID</th>
                        <th>CateName</th>
                        <th>Edit</th>
                        <th>Update</th>
                    </tr>
                </c:if>
                    <form action="<c:url value="/admin"/>">
                    <input type="hidden" name="action" value="updateCateInfo"/>
                    <input type="hidden" name="cateId" value="${eachCate.cateId}"/>
                    <tr>
                        <td><input type="text" disabled name="cateId" value="${eachCate.cateId}"/></td>
                        <td><input type="text" disabled name="cateName" id="cateName_${eachCate.cateId}" value="${eachCate.cateName}"/></td>
                        <td><button  class="btn btn-secondary" id="editButton_${eachCate.cateId}" onclick="doEdit(${eachCate.cateId + ""})">Edit</button></td>
                        <td><input  class="btn btn-secondary" type="submit" disabled id="updateButton_${eachCate.cateId}" onclick="doEdit(${eachP.plantId + ""})" value="Update"></td>
                    </tr>
                    </form>
            </c:forEach>
                    
                    
        <c:if test="${addCate_Successful}">
            <h3><font color='red'>Category added Successfully</font></h3>
        </c:if>
                        
        <form action="<c:url value="/admin"/>" method="get">
            <input type="hidden" name="action" value="addNewCate"/>    
            <tr>
                <td>#</td>
                <td><input id="new_cateName" disabled="" type="text" name="new_cateName" required=""/></td>
                <td><input  class="btn btn-secondary" id="add_newButton" disabled="" type="submit" value="Add"/></td>
            </tr>        
        </form>
            
        </table>    
        <button  class="btn btn-secondary" onclick="doAdd()">Add...</button>    
        
        <script>
            function doEdit(cate_id){
                document.getElementById("editButton_"+cate_id).disabled = true;
                document.getElementById("updateButton_"+cate_id).disabled = false;
                document.getElementById("cateName_"+cate_id).disabled = false;
            }
            function doAdd(){
                 document.getElementById("add_newButton").disabled = false;
                 document.getElementById("new_cateName").disabled = false;
            }
        </script>
    </body>
    
    
    
    
</html>
