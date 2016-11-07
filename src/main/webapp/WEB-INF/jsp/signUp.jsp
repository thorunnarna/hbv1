<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Sign Up Page</title>
    </head>
    <body>

        <h1>Sign Up</h1>

        <form>
            Username: <input type="text" required="required">
            <br>
            Password: <input type="password" required="required">
            <br>
            School:<input type="text">
            <br>
            Photo: <input type="text">
        </form>

        <p>
            <input type="submit" value="Sign up!">
        </p>

    </body>

</html>