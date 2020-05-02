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
    <title>Create user</title>
</head>
<body>
<c:if test="${successMessage!=null}">
    <h3>${successMessage}</h3>

</c:if>
<a href="/users">Back to user list</a>
<h1>Create new user:</h1>
<div>
    <form action="users?action=create" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text" name="country"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Create"></td>
                <td><input type="reset" name="Cancel"></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
