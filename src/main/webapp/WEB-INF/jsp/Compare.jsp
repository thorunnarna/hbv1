<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>Compare Page</title>
    </head>

    <body>
        <p>Compare with friend!</p>
        <form action="/compareFriends">
            <select name="selectedFriend">
                <option value="68" label="--Select friend--"/>
                <c:forEach var="friend" items="${friendList}">
                    <option value="${friend.userId}" label="${friend.username}"></option>
                </c:forEach>
            </select>
            <input type="submit" value="Compare!"/>
        </form>
        <p>Compare with groups!</p>
        <form method="POST" action="/compareGroup">
            <select name="selectedGroup">
                <option value="NONE" label="--Select group--"/>
                <c:forEach var="group" items="${groupList}">
                    <option value="${group.grpId}" label="${group.grpName}"></option>
                </c:forEach>
            </select>
            <input type="submit" value="Compare!"/>
        </form>

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
            <c:forEach var="slot" items="${timeSlots}">
                <tr>
                    <td align="center" valign="middle" width="80" height="5">
                        <c:out value="${slot}"/>
                    </td>
                    <c:forEach begin="0" end="6" step="1">

                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>


</html>

