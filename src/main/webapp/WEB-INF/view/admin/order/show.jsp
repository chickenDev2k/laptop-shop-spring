<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Quang SE - Dự án laptopshop" />
                <meta name="author" content="Quang SE" />
                <title>Dashboard - Orders</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">


                <!-- header include -->
                <jsp:include page="../layout/header.jsp" />
                <!-- end header include -->
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage Orders</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Orders</li>
                                </ol>
                                <div class="mt-5">

                                    <div class="row">

                                        <div class="col-12 mx-auto">
                                            <div class="table-header d-flex justify-content-between">
                                                <h3>Table Orders</h3>

                                            </div>
                                            <div class="table mt-3">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">STT</th>
                                                            <th scope="col">Total Price</th>
                                                            <th scope="col">User</th>
                                                            <th scope="col">Status</th>
                                                            <th scope="col">Action</th>

                                                        </tr>
                                                    </thead>
                                                    <hr />
                                                    <tbody>
                                                        <c:forEach var="order" items="${orders}">
                                                            <tr>
                                                                <th scope="col">${orders.indexOf(order) + 1}</th>
                                                                <td>

                                                                    <fmt:formatNumber value="${order.totalPrice}"
                                                                        type="number" pattern="#,##0" />
                                                                    <span>vnd</span>
                                                                </td>
                                                                <td>${order.user.id}</td>
                                                                <td>${order.status}</td>

                                                                <td>
                                                                    <div class="btn-block">
                                                                        <a href="/admin/order/${order.id}"
                                                                            class="btn btn-success">View</a>
                                                                        <a href="/admin/order/update/${order.id}"
                                                                            class="btn btn-warning">Update</a>
                                                                        <a href="/admin/order/delete/${order.id}"
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
                                                            <a href="/admin/order?page=${currentPage > 2 ?(currentPage - 1):1}"
                                                                class="${1 == currentPage ? 'disabled':''} page-link">
                                                                <!-- previous <<  -->
                                                                <span aria-hidden="true">&lt;&lt;</span>

                                                            </a>
                                                        </li>

                                                        <c:forEach begin="0" end="${totalPages-1}" varStatus="loop">
                                                            <li class="page-item">
                                                                <a class="${(loop.index +1) == currentPage ? 'active':''} page-link"
                                                                    href="/admin/order?page=${loop.index + 1}">
                                                                    ${loop.index + 1}</a>
                                                            </li>
                                                        </c:forEach>


                                                        <li class="page-item">
                                                            <a class="page-link ${totalPages == currentPage ? 'disabled':''} page-link "
                                                                href="/admin/order?page=${currentPage >= totalPages ?currentPage :(currentPage + 1)}"
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