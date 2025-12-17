package com.servlets.users;

import com.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {
    @Inject
    UserBean usersBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = Long.parseLong(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Pass the raw password to the bean; the bean handles the empty check
        usersBean.updateUser(userId, username, email, password);

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}