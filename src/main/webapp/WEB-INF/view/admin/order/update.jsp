<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Quang SE - Dự án laptopshop" />
                <meta name="author" content="Quang SE" />
                <title>Dashboard - Quang SE</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#productImgFile");
                        const orgImage = "${newProduct.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#productImgPreview").attr("src", urlImage);
                            $("#productImgPreview").css({ "display": "block" });
                        }
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#productImgPreview").attr("src", imgURL);
                            $("#productImgPreview").css({ "display": "block" });
                        });
                    });

                </script>


            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />


                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />


                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Order</li>
                                    <li class="breadcrumb-item active">Update</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="mx-auto  col-md-6 col-12">
                                            <h1>Update order</h1>
                                            <hr />
                                            <!-- order info -->
                                            <div>

                                            </div>
                                            <form:form action="/admin/order/update" method="post"
                                                modelAttribute="orderUpdate" enctype="multipart/form-data">
                                                <div style="display: none;">
                                                    <label class="form-label">ID:${updateOrder.id}</label>
                                                    <form:input type="text" class="form-control" path="id"
                                                        value="${updateOrder.id}" />
                                                </div>

                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="inputCity">User:</label>
                                                        <input type="text" class="form-control" id="inputCity" disabled
                                                            value="${user.fullName}">
                                                    </div>
                                                    <div class="form-group col-md-4">
                                                        <label for="inputState">Status:</label>
                                                        <form:select id="inputState" class="form-select" path="status">
                                                            <form:option value="PENDING">PENDING</form:option>
                                                            <form:option value="SHIPPING">SHIPPING</form:option>
                                                            <form:option value="COMPLETE">COMPLETE</form:option>
                                                            <form:option value="CANCEL">CANCEL</form:option>
                                                        </form:select>
                                                    </div>

                                                </div>
                                                <hr />
                                                <div>
                                                    <button class="btn btn-warning">Update</button>
                                                </div>
                                            </form:form>
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
                <script src="/js/scripts.js"></script>


            </body>

            </html>