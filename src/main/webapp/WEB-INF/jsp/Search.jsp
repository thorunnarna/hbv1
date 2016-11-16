<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Search Page</title>
    </head>
    <body>
            <table border="1px gray">
                <tbody>
                <tr>
                    <td>${name}</td>
                    <td>${job}</td>
                    <td>${email}</td>
                    <td>${description}</td>
                </tr>
                </tbody>
            </table>
    </body>

</html>