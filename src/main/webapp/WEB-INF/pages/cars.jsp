<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- Am actualizat URI-ul JSTL la standardul Jakarta --%>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>

    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Nr.</th>
                <th>License Plate</th>
                <th>Parking Spot</th>
                <th>Owner</th>
                <th>Actions</th>          </tr>
            </thead>

            <tbody>
            <c:forEach var="car" items="${cars}" varStatus="status">
                <tr>
                    <td>${status.count}</td>

                    <td>${car.licensePlate}</td>

                    <td>${car.parkingSpot}</td>

                    <td>${car.ownerName}</td>

                    <td>
                        <a href="#" class="btn btn-sm btn-primary">Edit</a>
                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>