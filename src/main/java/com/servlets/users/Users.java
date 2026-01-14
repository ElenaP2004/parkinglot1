package com.servlets.users;

import com.common.UserDto;
import com.ejb.InvoiceBean;
import com.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles; // Adaugă importul ăsta
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

// 1. Declarăm rolurile (Good Practice)
@DeclareRoles({"READ_USERS", "WRITE_USERS", "INVOICING"})

@WebServlet(name = "Users", value = "/Users")
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {
                // 2. AICI ERA GREȘEALA: Trebuie să permiți și INVOICING la POST!
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS", "INVOICING"})
        }
)
public class Users extends HttpServlet {
    @Inject
    private UserBean usersBean;

    @Inject
    private InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        if (request.isUserInRole("INVOICING")) {

            request.setAttribute("invoiceUserIds", invoiceBean.getUserIds());


            if (!invoiceBean.getUserIds().isEmpty()) {
                Collection<String> invoiceUsernames = usersBean.findUsernamesByUserIds(invoiceBean.getUserIds());
                request.setAttribute("invoices", invoiceUsernames);
            }
        }


        request.getRequestDispatcher("/WEB-INF/pages/users/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.isUserInRole("INVOICING")) {
            String[] userIdsAsString = request.getParameterValues("user_ids");


            invoiceBean.getUserIds().clear();

            if (userIdsAsString != null) {
                for (String userIdString : userIdsAsString) {
                    invoiceBean.getUserIds().add(Long.parseLong(userIdString));
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}