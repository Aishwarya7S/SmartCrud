<%@ page import="com.smartcrud.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    boolean isEdit = (user != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Edit User" : "Add User" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f4f8;
        }
        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 50px auto;
        }
        .form-title {
            text-align: center;
            margin-bottom: 25px;
            font-weight: 600;
        }
    </style>
</head>
<body>

<div class="container form-container">
    <h3 class="form-title">
        <%= isEdit ? "Edit User" : "Add New User" %>
    </h3>
    <form action="<%= isEdit ? "update" : "insert" %>" method="post">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= user.getId() %>">
        <% } %>

        <div class="mb-3">
            <label class="form-label">
                <i class="bi bi-person-fill"></i> Name
            </label>
            <input type="text" name="name" class="form-control border border-primary"
                   value="<%= isEdit ? user.getName() : "" %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">
                <i class="bi bi-envelope-fill"></i> Email
            </label>
            <input type="email" name="email" class="form-control border border-primary"
                   value="<%= isEdit ? user.getEmail() : "" %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">
                <i class="bi bi-geo-alt-fill"></i> Country
            </label>
            <input type="text" name="country" class="form-control border border-primary"
                   value="<%= isEdit ? user.getCountry() : "" %>" required>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success px-4">
                <i class="bi bi-save-fill"></i> <%= isEdit ? "Update" : "Save" %>
            </button>
        </div>
    </form>
    
    <div class="mb-3 text-start">
        <a href="list.jsp" class="btn btn-outline-secondary">
            <i class="bi bi-arrow-left"></i> Back to List
        </a>
    </div>
</div>

</body>
</html>
