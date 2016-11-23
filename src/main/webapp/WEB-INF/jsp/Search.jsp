<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="">
    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
        <title>Search Page</title>
    </head>
    <body>
        <form action="/search/q">
            Search: <input type="text" name="username" placeholder="Type username.."/>
        </form>
        <c:choose>
            <c:when test="${users==null}">
                This user does not exist, try again.
            </c:when>
            <c:otherwise>
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
                            <td>${user.user.username}</td>
                            <td>${user.user.school}</td>
                            <c:choose>
                                <c:when test="${user.friendship}">
                                    <td>
                                        <form method="POST" action="/search/addToGroup?addMember=${user.user.userId}">
                                            <select name="addToGroup">
                                                <option label="--Select group--"/>
                                                <c:forEach var="groups" items="${groupList}">
                                                    <option value="${groups.grpId}" label="${groups.grpName}"></option>
                                                </c:forEach>
                                            </select>
                                            <button type="submit"> Add to Group! </button>
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <form action="/search/addFriend" method="POST">
                                            <button type="submit" name="userId" value=${user.user.userId}>Add Friend</button>
                                        </form>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>