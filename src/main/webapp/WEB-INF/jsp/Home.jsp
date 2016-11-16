<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>home Page </title>
        <body>
            <p>Hæ ${pageContext.request.userPrincipal.name}</p>


            <table>
                <thead>
                <tr style="font-weight: 600;">
                    <td>Title</td>
                    <td>UserId</td>
                    <td>Location</td>
                    <td>Description</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${title}</td>
                    <td>${userId}</td>
                    <td>${location}</td>
                    <td>${description}</td>
                </tr>
                </tbody>
            </table>
        </body>
    </head>

</html>