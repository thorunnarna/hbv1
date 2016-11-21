<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <c:forEach var="item" items="${comparedSchedule}">
                            <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                            <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                            <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                            <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                            <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                            <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                            <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                            <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                            <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                            <c:choose>
                                <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 0}">
                                    <td align="center" valign="middle" width="100" bgcolor="red">
                                        <c:out value="${item.startstring}"/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td align="center" valign="middle" width="100"></td>
                                </c:otherwise>
                            </c:choose>
                    </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                    <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                    <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                    <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                    <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                    <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                    <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                    <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                    <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                    <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                    <c:choose>
                        <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 1}">
                            <td align="center" valign="middle" width="100" bgcolor="red">
                                <c:out value="${item.startstring}"/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td align="center" valign="middle" width="100"></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                        <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                        <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                        <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                        <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                        <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                        <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                        <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                        <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                        <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                        <c:choose>
                            <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 2}">
                                <td align="center" valign="middle" width="100" bgcolor="red">
                                    <c:out value="${item.startstring}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" valign="middle" width="100"></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                        <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                        <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                        <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                        <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                        <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                        <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                        <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                        <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                        <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                        <c:choose>
                            <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 3}">
                                <td align="center" valign="middle" width="100" bgcolor="red">
                                    <c:out value="${item.startstring}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" valign="middle" width="100"></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                        <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                        <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                        <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                        <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                        <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                        <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                        <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                        <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                        <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                        <c:choose>
                            <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 3}">
                                <td align="center" valign="middle" width="100" bgcolor="red">
                                    <c:out value="${item.startstring}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" valign="middle" width="100"></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                        <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                        <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                        <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                        <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                        <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                        <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                        <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                        <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                        <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                        <c:choose>
                            <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 4}">
                                <td align="center" valign="middle" width="100" bgcolor="red">
                                    <c:out value="${item.startstring}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" valign="middle" width="100"></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:forEach var="item" items="${comparedSchedule}">
                        <fmt:parseDate value="${item.startstring}" pattern="HH:mm" var="startTime"/>
                        <fmt:formatDate value="${startTime}" pattern="HH" var="startHour"/>
                        <fmt:formatDate value="${startTime}" pattern="mm" var="startMins"/>
                        <fmt:parseDate value="${item.endstring}" pattern="HH:mm" var="endTime"/>
                        <fmt:formatDate value="${endTime}" pattern="HH" var="endHour"/>
                        <fmt:formatDate value="${endTime}" pattern="mm" var="endMins"/>

                        <fmt:parseDate value="${slot}" pattern="HH:mm" var="slotTime"/>
                        <fmt:formatDate value="${slotTime}" pattern="HH" var="slotHour"/>
                        <fmt:formatDate value="${slotTime}" pattern="mm" var="slotMins"/>

                        <c:choose>
                            <c:when test="${slotHour>=startHour && slotHour<=endHour && slotMins>=startMins && slotMins<=endMins && item.weekDay eq 5}">
                                <td align="center" valign="middle" width="100" bgcolor="red">
                                    <c:out value="${item.startstring}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" valign="middle" width="100"></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>


</html>

