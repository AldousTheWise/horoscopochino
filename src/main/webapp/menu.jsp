<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<nav class="navbar navbar-expand-lg bg-dark px-3 py-4 color-alert" data-bs-theme="dark">
    <a class="navbar-brand" href="${not empty sessionScope.usuario ? 'index.jsp?view=adminIF' : 'index.jsp?form=login'}">
        Horóscopo Chino
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
            <li class="nav-item active">
                <a class="nav-link" href="askHoroscopo">Tu Horóscopo Chino</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <c:if test="${not empty sessionScope.usuario}">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
