<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="">

    <head>
        <%@ page contentType="text/html; charset=UTF-8"%>
        <title>home Page </title>
        <body>
            <p>Hæ ${pageContext.request.userPrincipal.name}</p>

            <sf:form method="POST" commandName="scheduleItem" action ="/home">
                <table>
                    <tr>
                        <td> Title:</td>
                        <td><sf:input path ="title" type="text" placeholder="Enter title"/></td>
                    </tr>
                    <tr>
                        <td>UserId:</td>
                        <td><sf:input path="userId" type="int" placeholder="verður þannig að það fyllist inn sjálfkrafa"/></td>
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
                        <td><sf:input path="startTime" type="text" placeholder="Enter starttime"/></td>
                    </tr>
                    <tr>
                        <td> StartTime:</td>
                        <td><sf:input path="endTime" type="text" placeholder="Enter endtime"/></td>
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
                <tr>
                    <td>${title}</td>
                    <td>${userId}</td>
                    <td>${location}</td>
                    <td>${description}</td>
                </tr>
                </tbody>
            </table>
        </body>
    </head>

</html>