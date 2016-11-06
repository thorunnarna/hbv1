<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>Sign Up Page</title>
    </head>
    <body>

        <h1>Sign Up</h1>

        <form>
            Username: <input type="text" required="required">
            <br>
            Password: <input type="password" required="required">
        </form>

        <p>
            <input type="submit" value="Sign up!">
        </p>

    </body>

</html>