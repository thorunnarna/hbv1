<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Search Page</title>
    </head>
    <body>
        <sf:form method="GET" commandName="User" action="/Search">

        </sf:form>
        <table border="1px gray">
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>

</html>