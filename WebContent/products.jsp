<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listing of units available are here in WEB-INF</title>
</head>
<body>
<table style="border: 1px solid;">
  <c:forEach items="${products}" var="Unit"> 
  <tr>
    <td>${Unit.getName()}</td>
    <td>${Unit.getId()}</td>
    <td>
    <h3>  
           <c:set var="titleURL">
               <c:url value="detail">   
                  <c:param name="unitid" value="${Unit.getId()}"/>   
                </c:url>  
           </c:set>
            <a href="${titleURL}">Details</a>  
        </h3>  
    </td></tr>
</c:forEach>
   </table>
</body>
</html>