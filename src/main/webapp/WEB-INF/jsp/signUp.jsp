<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Sign Up Page</title>
        <link href="<c:url value="../../../resources/static/css/stylesheet.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>

        <h1>Sign Up</h1>

        <sf:form method="POST" commandName="SignUp" action ="/signup">
            <table>
                <tr>
                    <td> Username:</td>
                    <td><sf:input path ="username" type="text" placeholder="Enter name"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                        <td><sf:input path="password" type="" placeholder="Enter password"/></td>

                </tr>
                <tr>
                    <td>Confirm password:</td>
                    <td><sf:input path="passwordConfirm" type="" placeholder="Enter password again"/></td>

                </tr>
                <tr>
                    <td> Photo:</td>
                    <td><sf:input path="photo" type="text" placeholder="Choose photo"/></td>

                </tr>
                <tr>
                    <td> School:</td>
                    <td><sf:input path="school" type="text" placeholder="Enter school"/></td>
                </tr>
            </table>
            <input type="submit" value="Sign up!">
            <table>
                <c:if test = "${hasErrors}">
                    <c:forEach var="error" items="${errors}">
                        <tr class="errors"><td>${error.getCode()}</td></tr>
                    </c:forEach>
                </c:if>
            </table>

        </sf:form>

        <p>


        </p>

    </body>

</html>