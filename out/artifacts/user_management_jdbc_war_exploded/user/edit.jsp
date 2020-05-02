<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/2/2020
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Form</title>
</head>
<body>
<c:if test="${successMessage!=null}">
    <h3>${successMessage}</h3>
</c:if>
<a href="/users">Back to user list</a>
<h1>Update Information:</h1>
<div>
    <form action="users?action=edit&id=${user.getId()}" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${user.getName()}"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" value="${user.getEmail()}"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country" value="${user.getCountry()}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Update"></td>
                <td><input type="reset" name="Cancel"></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
