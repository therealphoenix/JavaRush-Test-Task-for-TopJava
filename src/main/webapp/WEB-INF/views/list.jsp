<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="User List" />
    <c:param name="body">
        <div class="container" role="main">
        <div class="alert alert-info" role="alert">
            <h2 class="alert-heading">User List</h2>
            </div>
            <p><em>${message}</em></p>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Admin</th>
                    <th>createdDate</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.admin}</td>
                        <td>${user.createdDate}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/edit/${user.id}">Edit</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/delete/${user.id}">Delete</a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="pagination" align="right">

                <c:url value="list" var="prev">
                    <c:param name="page" value="${page-1}"/>
                </c:url>
                <c:if test="${page > 1}">
                    <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
                </c:if>

                <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                    <c:choose>
                        <c:when test="${page == i.index}">
                            <span>${i.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="list" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <a href='<c:out value="${url}" />'>${i.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:url value="list" var="next">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <c:if test="${page + 1 <= maxPages}">
                    <a href='<c:out value="${next}" />' class="pn next">Next</a>
                </c:if>
            </div>
        </div> <!-- /container -->
    </c:param>
</c:import>