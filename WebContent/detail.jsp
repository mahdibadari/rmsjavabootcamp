<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail in Web Content</title>
</head>
<body>
<tr>Unit name: <c:out value="${unit.getName()}"/></tr><br/>
<tr>Unit exp: <c:out value="${unit.getExp()}"/></tr><br/>
<tr>Unit bb level: <c:out value="${unit.getBblvl()}"/></tr><br/>
<tr>  
           <c:set var="titleURL">
               <c:url value="delete">   
                  <c:param name="unitid" value="${unit.getId()}"/>   
                </c:url>  
           </c:set>
            <a href="${titleURL}">Delete</a>  
</tr><br/>
<tr>  
           <c:set var="titleURL">
               <c:url value="update">   
                  <c:param name="unitid" value="${unit.getId()}"/>   
                </c:url>  
           </c:set>
            <a href="${titleURL}">Update</a>  
</tr><br/>
<tr>  
           <c:set var="titleURL">
               <c:url value="products">     
                </c:url>  
           </c:set>
            <a href="${titleURL}">Back To Unit List</a>  
</tr><br/>
</body>
</html>