<%-- 
    Document   : manage_plant
    Created on : Oct 30, 2022, 7:51:19 PM
    Author     : RaeKyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Plant Page</title>
    </head>
    <body>
        <div class="text-center">
            <h2>Manage Plant</h2>
            <form action="<c:url value="/admin"/>">
                <input style="padding: 5px; width: 400px" type="text" name="keySearch" placeholder="Search plant name here..." value="${requestScope.keySearch}">
                <input type="hidden" name="action" value="searchPlant" />
                <input class="btn btn-outline-success" type="submit" value="Search">
            </form>
                
            <c:if test="${updatePlant_Successful}">
                <h4><font color='red'>Plant update Successfully</font></h4>
            </c:if>     
        </div><br/><br/>  
            
        
            
            
        <c:choose>
            <c:when test="${plantList != null and not empty plantList}">
                <div class="container-fluid">
                <table class="table text-center">
                <c:forEach var="eachP" items="${plantList}" varStatus="status">
                <c:if test="${status.first}">
                <tr>
                    <th>PlantID </th>
                    <th>PlantName</th>
                    <th>Price </th>
                    <th>Image</th>
                    <th>Description </th>
                    <th>Status</th>
                    <th>Categories</th>
                    <th>Edit</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>        
                </c:if>    
                <form action="<c:url value="/admin"/>" method="get">
                <input type="hidden" name="action" value="updatePlantInfo"/>
                <input type="hidden" name="plantId" value="${eachP.plantId}"/>
                <tr>
                    <td><a href="<c:url value="/guest?action=view&plantId=${eachP.plantId}"/>">${eachP.plantId}</a></td>
                    <td><input type="text" name="plantName" id="plantName_${eachP.plantId}" disabled="disabled" value="${eachP.plantName}"/></td>
                    <td><input type="number" name="price" id="price_${eachP.plantId}" disabled="disabled" value="${eachP.price}" size="7"/></td>
                    <td><input type="text" name="imgPath" id="imgPath_${eachP.plantId}" disabled="disabled" value="${fn:substring(eachP.imgPath, eachP.imgPath.lastIndexOf("/") + 1, eachP.imgPath.length())}" size="30"/></td>
                    <td><input type="text" name="description" id="description_${eachP.plantId}" disabled="disabled" value="${eachP.description}"/></td>
                    <td><input type="number" name="status" id="status_${eachP.plantId}" disabled="disabled" value="${eachP.status}" size="5"/></td>
                    <td><input type="number" name="cateId" id="cateId_${eachP.plantId}" disabled="disabled" value="${eachP.cateId}" size="5"/></td>
                    <td><button class="btn btn-secondary" id="editButton_${eachP.plantId}" onclick="doEdit(${eachP.plantId + ""})">Edit</button></td>
                    <td><input class="btn btn-secondary" type="submit" disabled id="updateButton_${eachP.plantId}" onclick="doEdit(${eachP.plantId + ""})" value="Update"></td>
                </form>    
                <td><button class="btn btn-secondary" onclick="doDeleteConfirm(${eachP.plantId})">Delete</button></td>
                </tr>
                
                
                </c:forEach>
                <c:if test="${addPlant_Successful}">
                    <h4 class="text-center"><font color="red">Plant added Successfully</font></h4>
                </c:if>
                        
                <form action="<c:url value="/admin"/>" method="get">
                <input type="hidden" name="action" value="addNewPlant"/>    
                <tr>
                    <td>#</td>
                    <td><input id="new_plantName" disabled="" type="text" name="new_plantName" required=""/></td>
                    <td><input id="new_price" disabled="" type="number" size="7" name="new_price" required=""/></td>
                    <td><input id="new_imgPath" disabled="" type="text" size="30" name="new_imgPath" required=""/></td>
                    <td><input id="new_description" disabled="" type="text" name="new_description" required=""/></td>
                    <td><input id="new_status" min="0" disabled="" type="number" name="new_status" required=""/></td>
                    <td><input id="new_cateId" min="1" disabled="" type="number" name="new_cateId" required=""/></td>
                    <td><input class="btn btn-secondary" id="add_newButton" disabled="" type="submit" value="Add"/></td>
                </tr>
                
                </form>
                </table> 
                </div>
                <button class="btn btn-secondary" onclick="doAdd()">Add...</button>
            </c:when>
            <c:otherwise>
                <h4><font color="red">Plant was not founded !</font></h4>
            </c:otherwise>
        </c:choose> 
                
                <br/>
                <h3>Instruction:</h3>
                To input valid data, follow the instruction here: <br/>
                - plantName must be String and no special character <br/>
                - price must be Integer and not negative <br/>
                - Image must reference to /images/Plants/* source <br/>
                - Description can be none<br/>
                - Status {0: UnAvailable, 1: Available} <br/>
                - Categories {1: Orchid, 2: Plant, 3: ....} follow Category<br/>
                - After input according to above click the button "Add"<br/>
                <br/>            
                
                
                
                
                
    <script>
        const editable = ["plantName", "price", "imgPath", "description", "status", "cateId"];
        function doEdit(el_id){
            document.getElementById("editButton_"+el_id).disabled = true;
            document.getElementById("updateButton_"+el_id).disabled = false;
            editable.forEach(element => {
                document.getElementById(element+"_"+el_id).disabled = false;
            });
        }
        
        function doDeleteConfirm(el_id){
            if(confirm("All data of {plantId=" + el_id + "} will be lost forever, process ?")){
                window.location.href = "http://localhost:8080/Workshop5/admin?action=deletePlant&plantId="+el_id;
            }else return;
        }
        
        function doAdd(){
            document.getElementById("add_newButton").disabled = false;
            editable.forEach(element => {
                document.getElementById('new_'+element).disabled = false;
            })
        }
    </script>            
    </body>
</html>
