<%--
  Created by IntelliJ IDEA.
  User: Teodora Fleaca
  Date: 11/17/2025
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <div class="container text-center">
        <div class="row fw-bold border-bottom pb-2 mb-2">
            <div class="col">Username</div>
            <div class="col">Email</div>
        </div>

        <c:forEach var="user" items="${users}">
            <div class="row mb-2">
                <div class="col">${user.username}</div>
                <div class="col">${user.email}</div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>
