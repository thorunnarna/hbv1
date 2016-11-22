<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
                  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
            <link href="../css/stylesheet.css" rel="stylesheet" type="text/css">
        <title>home Page </title>

            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>jQuery UI Datepicker - Default functionality</title>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script src="../src/main/resources/static.css/combodate.js"></script>
            <script>
                $( function() {
                    $( "#datepicker" ).datepicker();
                } );
            </script>
    </head>
    <jsp:include page="Header.jsp"/>
    <body>

        <a href="/logout">Log out!</a>
        <table border="1">
            <tr><td>Your friends:</td></tr>
            <c:forEach var="friend" items="${friends}">
                <tr>
                    <td><c:out value="${friend.username}"/></td>
                </tr>
            </c:forEach>
        </table>
        <table border="1">
            <tr><td>Your groups:</td></tr>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td>
                        <c:out value="${group.grpName} "/>
                        <a href="/deleteGroup?grpId=${group.grpId}">x</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/compare">Compare your schedule with others!</a>
        <sf:form method="POST" commandName="scheduleItem" action ="/home">

                <c:if test="${loggedInStatus}">
                    <p>welcome ${loggedInUser}!</p>
                </c:if>
                <table>

                    <tr>
                        <td> Title:</td>
                        <td><sf:input path ="title" type="text" placeholder="Enter title"/></td>
                    </tr>

                    <tr>
                        <td> Location:</td>
                        <td><sf:input path="location" type="text" placeholder="Choose location"/></td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td><sf:input path="description" type="text" placeholder="Enter description"/></td>
                    </tr>
                    <tr>
                        <td> Day:</td>
                        <td><sf:input path="date" type="text" placeholder="Choose date" id="datepicker"/></td>
                    </tr>
                    <tr>
                        <td> Start time:</td>
                        <%--<td><sf:input path="startstrin" type="text" placeholder="HH:MM - please pick a time on the 10 min interval" /></td>--%>
                        <td><sf:select path="startstring">
                            <sf:options items="${timeSlots}"/>
                        </sf:select></td>
                    </tr>
                    <tr>
                        <td> End time:</td>
                        <%--td><sf:input path="endstring" type="text" placeholder="HH:MM - please pick a time on the 10 min interval" /></td--%>
                        <td><sf:select path="endstring">
                            <sf:options items="${timeSlots}"/>
                        </sf:select></td>
                    </tr>
                    <tr>
                        <td> Color:</td>
                        <td>
                            <sf:select path="color">
                                <sf:option value="Red"/>
                                <sf:option value="Blue"/>
                                <sf:option value="Green"/>
                                <sf:option value="Yellow"/>
                            </sf:select>
                        </td>
                    </tr>
                    <tr>
                        <td> Filter:</td>
                        <td>
                            <sf:select path="filter">
                                <sf:option value="School"/>
                                <sf:option value="Work"/>
                                <sf:option value="Appointment"/>
                                <sf:option value="Other"/>
                            </sf:select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Create scheduleItem!">
            </sf:form>

        <p>Choose filter you want to view</p>
        <form action="/scheduleByFilter">
            <select name="selectedFilter">
                <option value="1" label="--Select filter--"/>
                <c:forEach var="filters" items="${filters}">
                    <option value="${filters}" label="${filters}"></option>
                </c:forEach>

            </select>
            <input type="submit" value="Submit"/>
        </form>

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
            <c:set var="timespan" value="0"/>
            <c:set var="timespan2" value="0"/>
            <c:set var="timespan3" value="0"/>
            <c:set var="timespan4" value="0"/>
            <c:set var="timespan5" value="0"/>
            <c:set var="timespan6" value="0"/>
            <c:set var="timespan7" value="0"/>
            <c:set var="counter" value="0"/>
            <c:forEach var="slot" items="${timeSlots}">
                <tr>
                    <c:if test="${counter mod 6 == 0}">
                        <td align="center" valign="middle" width="80" height="10" rowspan="6">
                            <c:out value="${slot}"/>
                        </td>
                    </c:if>
                    <c:set var="counter" value="${counter+1}"/>
                    <%-- MÁNUDAGUR --%>
                    <c:set var="foundItem" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 0}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan" value="${timespan-1}"/>
                    <c:if test="${foundItem eq false && timespan<=0}">
                        <td align="center" valign="middle" width="100">

                        </td>
                    </c:if>
                        <%-- ÞRIÐJUDAGUR --%>
                    <c:set var="foundItem2" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 1}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan2" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem2" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan2" value="${timespan2-1}"/>
                    <c:if test="${foundItem2 eq false && timespan2<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                    <%-- MIÐVIKUDAGUR --%>
                    <c:set var="foundItem3" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 2}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan3" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem3" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan3" value="${timespan3-1}"/>
                    <c:if test="${foundItem3 eq false && timespan3<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                    <%-- FIMMTUDAGUR --%>
                    <c:set var="foundItem4" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 3}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan4" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem4" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan4" value="${timespan4-1}"/>
                    <c:if test="${foundItem4 eq false && timespan4<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                    <%-- FÖSTUDAGUR --%>
                    <c:set var="foundItem5" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 4}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan5" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem5" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan5" value="${timespan5-1}"/>
                    <c:if test="${foundItem5 eq false && timespan5<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                    <%-- LAUGARDAGUR --%>
                    <c:set var="foundItem6" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 5}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan6" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem6" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan6" value="${timespan6-1}"/>
                    <c:if test="${foundItem6 eq false && timespan6<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                    <%-- SUNNUDAGUR --%>
                    <c:set var="foundItem7" value="false"/>
                    <c:forEach var="item" items="${scheduleItems}">
                        <c:if test="${item.startstring eq slot && item.weekDay eq 6}">
                            <td align="center" valign="middle" width="100" rowspan="${item.timeSpan}" class="{$item.color}">
                                <c:out value="${item.title} ${item.location}"/>
                                <a href="/deleteItem?itemId=${item.id}">x</a>
                                <c:set var="timespan7" value="${item.timeSpan+1}"/>
                            </td>
                            <c:set var="foundItem7" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:set var="timespan7" value="${timespan7-1}"/>
                    <c:if test="${foundItem7 eq false && timespan7<=0}">
                        <td align="center" valign="middle" width="100"></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="/createGroup">
           <table>
               <tr>
                   <td colspan="3">Create a group here. Then go to <a href="/search">search</a> to add members to your group.</td>
               </tr>
               <tr>
                   <td>Group name:</td>
                   <td><input type="text" name="grpName" placeholder="Enter group name"/></td>
                   <c:if test="${groupFail}">
                       <td>A group with this name already exists. Please choose another!</td>
                   </c:if>
               </tr>
           </table>
            <input type="submit" value="Create Group!">
        </form>

        </body>

</html>