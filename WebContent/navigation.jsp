<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation Menu</title>
</head>
<body>
<tr>  
           <c:set var="titleURL">
           </c:set>
            <a href="${titleURL}">Back to Create Page</a>  
</tr><br/>
<tr>  
           <c:set var="titleURL2">
               <c:url value="products">     
                </c:url>  
           </c:set>
            <a href="${titleURL2}">Back Unit List Page</a>  
</tr><br/>
</body>
</html>