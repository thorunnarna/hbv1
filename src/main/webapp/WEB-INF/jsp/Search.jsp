<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="">
    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <title>Search Page</title>
    </head>
    <body>
        <form action="/search">
            Search: <input type="text" name="username" placeholder="Type username.."/>
        </form>
        <table border="1px gray">
            <thead>
                <tr>
                    <td>Name: </td>
                    <td>School: </td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.school}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>