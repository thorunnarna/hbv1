<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>



<html lang="">
    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <title>Search Page</title>
    </head>
    <body>
        <sf:form method="GET" commandName="User" action="/Search">
            <table >
                <tbody>
                    <tr>
                        <td> Search:</td>
                        <td><input type="text" placeholder="Type username.."/></td>
                    </tr>
                </tbody>
            </table>
        </sf:form>
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