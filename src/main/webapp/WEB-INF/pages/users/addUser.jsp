<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%-- MODIFICARE CRITICĂ: Folosim jakarta.tags.core pentru GlassFish 7 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<t:pageTemplate pageTitle="Add User">
    <h1>Add New User</h1>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="" required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="" required>
                <div class="invalid-feedback">
                    Email is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="" required>
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="user_groups" class="form-label">Groups (Hold Ctrl to select multiple)</label>
                <select class="form-select" id="user_groups" name="user_groups" multiple size="5">
                    <c:forEach var="groupName" items="${userGroups}">
                        <option value="${groupName}">${groupName}</option>
                    </c:forEach>
                </select>
                <small class="text-muted">Available roles: READ_USERS, WRITE_USERS, READ_CARS, WRITE_CARS, INVOICING</small>
            </div>
        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg w-100" type="submit">Save User</button>

    </form>

    <script>
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

</t:pageTemplate>