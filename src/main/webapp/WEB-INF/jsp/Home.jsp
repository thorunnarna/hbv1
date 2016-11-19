<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>home Page </title>

            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>jQuery UI Datepicker - Default functionality</title>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
            <script>
                $( function() {
                    $( "#datepicker" ).datepicker();
                } );
            </script>
        <body>

            <sf:form method="POST" commandName="scheduleItem" action ="/home">
                <c:if test="${loggedInStatus}">
                    <p>Velkominn ${loggedInUser}!</p>
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
                        <td> StartTime:</td>
                        <td><sf:input path="startTime" type="text" placeholder="Enter starttime" id="datepicker"/></td>
                    </tr>


                    <tr>
                        <td> EndTime:</td>
                        <td><sf:input path="endTime" type="text" placeholder="Enter endtime" id="datepicker"/></td>
                    </tr>
                    <tr>
                        <td> WeekNo:</td>
                        <td><sf:input path="weekNo" type="int" placeholder="Enter weekno"/></td>
                    </tr>
                    <tr>
                        <td> Year:</td>
                        <td><sf:input path="year" type="int" placeholder="Enter year"/></td>
                    </tr>
                    <tr>
                        <td> Color:</td>
                        <td><sf:input path="color" type="text" placeholder="Choose color"/></td>
                    </tr>
                </table>
                <input type="submit" value="Create scheduleItem!">
            </sf:form>
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

                </tbody>
            </table>
        </body>
    </head>

</html>