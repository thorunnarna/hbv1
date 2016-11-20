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

    <jsp:include page="Header.jsp"/>

    <body>

    <main>

        <h1>Sign Up</h1>

        <sf:form class="form-group col-md-6" method="POST" commandName="SignUp" action ="/signup">
                <label> Username:</label>
                <sf:input class="form-control" path ="username" type="text" placeholder="Enter name"/>

                <label>Password:</label>
                <sf:input class="form-control" path="password" type="password" placeholder="Enter password"/>

                <label>Confirm password:</label>
                <sf:input class="form-control" path="passwordConfirm" type="password" placeholder="Enter password again"/></td>

                <label> Photo:</label>
                <sf:input class="form-control" path="photo" type="text" placeholder="Choose photo"/>

                <label> School:</label>
                <sf:input class="form-control" path="school" type="text" placeholder="Enter school"/>

                <button type="submit" class="btn btn-primary" value="Sign up!">Sign Up!</button>

                <c:if test = "${hasErrors}">
                    <c:forEach var="error" items="${errors}">
                    <p class="alert alert-danger">${error.getCode()}

                </c:forEach>
            </c:if>
        </sf:form>
    </main>

    </body>

</html>