<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.smartcrud.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Smart CRUD</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <!-- Navbar -->
    <nav class="navbar navbar-dark bg-primary mb-4">
        <div class="container justify-content-center">
            <span class="navbar-brand mb-0 h1 text-uppercase fw-bold">Smart CRUD</span>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mb-5">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h4 class="fw-bold">User List</h4>
            <a href="new" class="btn btn-success">
                <i class="bi bi-plus-circle me-1"></i> Add User
            </a>
        </div>

        <table class="table table-bordered table-hover shadow-sm bg-white">
            <thead class="table-primary text-center">
                <tr>
                    <th style="width: 5%;">ID</th>
                    <th style="width: 20%;">Name</th>
                    <th style="width: 25%;">Email</th>
                    <th style="width: 20%;">Country</th>
                    <th style="width: 15%;">Actions</th>
                </tr>
            </thead>
            <tbody class="text-center">
                <%
                    List<User> userList = (List<User>) request.getAttribute("userList");
                    if (userList != null && !userList.isEmpty()) {
                        for (User user : userList) {
                %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getCountry() %></td>
                        <td>
                            <a href="edit?id=<%= user.getId() %>" class="btn btn-sm btn-warning me-1" title="Edit">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                            <a href="delete?id=<%= user.getId() %>" class="btn btn-sm btn-danger" title="Delete" onclick="return confirm('Are you sure to delete this user?');">
                                <i class="bi bi-trash-fill"></i>
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" class="text-muted text-center">No users found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <footer class="bg-primary text-white text-center py-2 mt-auto">
            <p class="mb-0">Smart CRUD  &copy; 2025 | Built using Java, JSP, Servlets, and MySQL.</p>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
