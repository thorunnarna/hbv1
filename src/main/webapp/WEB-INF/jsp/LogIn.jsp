<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Login page</title>
    </head>

    <body>

        <sf:form method="POST" commandName="LogIn" action ="/login">
            <table>
                <tr>
                    <td> Username:</td>
                    <td><sf:input path ="username" type="text" placeholder="Enter name"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><sf:input path ="password" type="text" placeholder="Enter password"/></td>
                </tr>
            </table>
            <input type="submit" value="Login!">

        </sf:form>
    </body>

</html>