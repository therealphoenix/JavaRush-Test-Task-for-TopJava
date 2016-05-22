<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="New User" />
    <c:param name="body">
        <div class="container" role="main">
            <h1>New User</h1>
            <form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/create" class="form">

                <div class="form-group">
                    <label for="firstName">First name:</label>
                    <form:input path="firstName" class="form-control" />
                    <form:errors path="firstName" cssStyle="color: red;" />
                </div>
                <div class="form-group">
                    <label for="lastName">Last name:</label>
                    <form:input path="lastName" class="form-control" />
                    <form:errors path="lastName" cssStyle="color: red;" />
                </div>
                <div class="form-group">
                <label for="age">Age:</label>
                <form:input path="age" class="form-control" />
                <form:errors path="age" cssStyle="color: red;" />
                </div>
                <c:set var="admin" value="yes,no" scope="application" />
                <div class="form-group">
                    <label for="admins">Admin:</label>
                    <select name="admin" class="form-control">
                        <c:forEach items="${fn:split(admin, ',')}" var="admin">
                            <option value="${admin}">${admin}</option>
                        </c:forEach>
                    </select>
                    <form:errors path="admin" cssStyle="color: red;" />
                </div>


                <button type="submit" class="btn btn-default">Create</button>
            </form:form>
        </div>


    </c:param>
</c:import>