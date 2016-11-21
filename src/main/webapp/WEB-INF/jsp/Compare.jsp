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

        <%--Skoða f hvert slot hvort það sé item sem er með starttime/endtime utanum slotið => lita rautt, annars grænt--%>
        <table border="1" cellspacing="0">
            <tbody>
            <tr>
                <th align="center" valign="middle" width="80"></th>
                <th align="center" valign="middle" width="100">Monday</th>
                <th align="center" valign="middle" width="100">Tuesday</th>
                <th align="center" valign="middle" width="100">Wednesday</th>
                <th align="center" valign="middle" width="100">Thursday</th>
                <th align="center" valign="middle" width="100">Friday</th>
                <th align="center" valign="middle" width="100">Saturday</th>
                <th align="center" valign="middle" width="100">Sunday</th>
            </tr>
            <c:forEach var="slot" items="${timeSlots]">
                <td align="center" valign="middle" width="80" height="5">
                </td>
            </c:forEach>
            </tbody>
        <table/>
    </body>


</html>

