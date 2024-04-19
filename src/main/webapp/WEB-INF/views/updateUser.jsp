<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="updateUser" modelAttribute="user" method="post">
    <form:label path="id">Id</form:label>
    <form:input path="id"/>
    <br>
    <form:label path="name">Name</form:label>
    <form:input path="name"/>
    <br>
    <form:label path="phone">Phone Number</form:label>
    <form:input path="phone"/>
    <br>
    <form:label path="email">Email</form:label>
    <form:input path="email"/>
    <br>
    <form:label path="password">Password</form:label>
    <form:input path="password"/>
    <br>
    <form:button>Update</form:button>
</form:form>
</body>
</html>