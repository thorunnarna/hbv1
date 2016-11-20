<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Compare Page</title>
    </head>

    <body>
        <p>COmpare with friend</p>
        <form method="POST" action="/compareFriends">
            <sf:select path="selectedFriend">
                <sf:option value="NONE" label="--Select friend--"/>
                <sf:options items="${friendList}"/>
            </sf:select>
            <input type="submit" value="Compare!"/>
        </form>
        <sf:form method="POST" commandName="friendComp" action="/compareGroup">
            <sf:select path="selectedGroup">
                <sf:option value="NONE" label="--Select group--"/>
                <sf:options items="${groupList}"/>
            </sf:select>
            <input type="submit" value="Compare!"/>
        </sf:form>
    </body>

</html>

