<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="Quang SE - Dự án laptopshop" />
            <meta name="author" content="Quang SE" />
            <title>User Table</title>
            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        </head>

        <body class="sb-nav-fixed">
            <jsp:include page="../layout/header.jsp" />
            <div id="layoutSidenav">
                <jsp:include page="../layout/sidebar.jsp" />
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Manage Users</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Users</li>
                            </ol>
                            <div class="mt-5">

                                <div class="row">

                                    <div class="col-12 mx-auto">
                                        <div class="table-header d-flex justify-content-between">
                                            <h3>Table Users</h3>
                                            <a href="/admin/user/create" class="btn btn-primary">Create a user</a>
                                        </div>
                                        <div class="table mt-3">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">STT</th>
                                                        <th scope="col">ID</th>
                                                        <th scope="col">Email</th>
                                                        <th scope="col">Full Name</th>
                                                        <th scope="col">Role</th>
                                                        <th scope="col">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="user" items="${users}">
                                                        <tr>
                                                            <th scope="col">${user1.indexOf(user) + 1}</th>
                                                            <th>${user.id}</th>
                                                            <td>${user.email}</td>
                                                            <td>${user.fullName}</td>
                                                            <td>${user.role.name}</td>
                                                            <td>
                                                                <div class="btn-block">
                                                                    <a href="/admin/user/${user.id}"
                                                                        class="btn btn-success">View</a>
                                                                    <a href="/admin/user/update/${user.id}"
                                                                        class="btn btn-warning">Update</a>
                                                                    <a href="/admin/user/delete/${user.id}"
                                                                        class="btn btn-danger">Delete</a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>




                                                </tbody>
                                            </table>
                                            <nav aria-label="...">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item">
                                                        <a href="/admin/user?page=${currentPage > 2 ?(currentPage - 1):1}"
                                                            class="${1 == currentPage ? 'disabled':''} page-link">
                                                            <!-- previous <<  -->
                                                            <span aria-hidden="true">&lt;&lt;</span>

                                                        </a>
                                                    </li>

                                                    <c:forEach begin="0" end="${totalPages-1}" varStatus="loop">
                                                        <li class="page-item">
                                                            <a class="${(loop.index +1) == currentPage ? 'active':''} page-link"
                                                                href="/admin/user?page=${loop.index + 1}">
                                                                ${loop.index + 1}</a>
                                                        </li>
                                                    </c:forEach>


                                                    <li class="page-item">
                                                        <a class="page-link ${totalPages == currentPage ? 'disabled':''} page-link "
                                                            href="/admin/user?page=${currentPage >= totalPages ?currentPage :(currentPage + 1)}"
                                                            aria-hidden="true">
                                                            <!-- next >>  -->
                                                            <span aria-hidden="true">&gt;&gt;</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </main>
                    <jsp:include page="../layout/footer.jsp" />
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="js/scripts.js"></script>

        </body>

        </html>