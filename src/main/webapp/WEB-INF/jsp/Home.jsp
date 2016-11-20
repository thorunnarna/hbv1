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

            <script>
                $( function() {
                    $( "#datepicker1" ).datepicker();
                } );
            </script>
            <script>
                $(function(){
                    $('#datetime24').combodate();
                });
            </script>

        <body>

            <form method="POST" action ="/home">
                <c:if test="${loggedInStatus}">
                    <p>welcome ${loggedInUser}!</p>
                </c:if>
                <table>
                    <tr>
                        <td> Title:</td>
                        <spring:bind path="scheduleItem.title">
                        <td><input  type="text" placeholder="Enter title"/></td>
                        </spring:bind>
                    </tr>
                    
                    <tr>
                        <td> Location:</td>
                        <spring:bind path="scheduleItem.location">
                        <td><input type="text" placeholder="Choose location"/></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <spring:bind path="scheduleItem.description">
                        <td><input type="text" placeholder="Enter description"/></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td> Day:</td>
                        <spring:bind path="date">
                            <td><input type="text" placeholder="Choose date" id="datepicker"/></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td> start time:</td>
                        <spring:bind path="sTime">
                            <td><input type="text" placeholder="HH:MM - please pick a time on the 10 min interval" /></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td> End time:</td>
                        <spring:bind path="eTime">
                            <td><input type="text" placeholder="HH:MM - please pick a time on the 10 min interval" /></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td> Color:</td>
                        <spring:bind path="scheduleItem.color">
                        <td><input type="text" placeholder="Choose color"/></td>
                        </spring:bind>
                    </tr>
                </table>
                <input type="submit" value="Create scheduleItem!">
            </form>
            <table>
                <thead>
                <tr style="font-weight: 600;">
                    <td>Title</td>
                    <td>UserId</td>
                    <td>Location</td>
                    <td>Description</td>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="schedItem" items="${scheduleItems}">
                        <tr>
                        <%--We can reference attributes of the Entity by just entering the name we gave--%>
                        <%--it in the singular item var, and then just a dot followed by the attribute name--%>
                            <td><a href="/schedItem/${schedItem.title}">${schedItem.title}</a></td>
                            <td>${schedItem.color}</td>
                        </tr>
                    </c:forEach>


                <table border="1" cellspacing="0">
                    <tbody>
                    <tr>
                        <th align="center" valign="middle" width="80"></th>
                        <th align="center" valign="middle" width="100">Monday</th>
                        <th align="center" valign="middle">Tuesday</th>
                        <th align="center" valign="middle">Wednesday</th>
                        <th align="center" valign="middle">Thursday</th>
                        <th align="center" valign="middle">Friday</th>
                        <th align="center" valign="middle">Saturday</th>
                        <th align="center" valign="middle">Sunday</th>
                    </tr>

                    <c:forEach var="timeSlot" items="timeSlots">
                    <tr>
                        <td align="center" valign="middle" width="80">
                            ${timeSlot}
                        </td>
                        <c:forEach begin="0" end="6" step="1" var="day">
                        <td align="center" valign="middle" width="100">
                            <c:forEach var="item" items="${scheduleItems}">
                                <c:if test="${item.startTime==timeSlot && item.startTime.getDayOfWeek().ordinal()==day}">
                                    <c:out value="${item.title}"/>
                                </c:if>
                            </c:forEach>
                        </td>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                </tbody>
            </table>
        </body>
    </head>

</html>