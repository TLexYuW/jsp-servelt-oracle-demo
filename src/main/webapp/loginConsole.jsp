<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>成功畫面</title>
</head>
<body>
 <div align="center">
 	<c:if test="${sessionScope.flag == true}">
  		<h1> Login Successfully !!</h1>
	</c:if>
	<c:if test="${sessionScope.flag == false}">
  		<h1> Login failed !!</h1>
	</c:if>
  
 </div>
</body>
</html>