<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/2/2020
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users Management</title>
</head>
<body>
<c:if test="${successMessage!=null}">
    <h3>${successMessage}</h3>
</c:if>
<a href="/users?action=create">Create new user</a>
<h1>Users List:</h1>
<div>
    <table style="width: 30%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getCountry()}</td>
                <td><a href="users?action=edit&id=${user.getId()}">Edit</a> |<a href="users?action=delete&id=${user.getId()}">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
