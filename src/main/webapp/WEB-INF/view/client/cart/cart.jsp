<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title> Laptopshop-Cart</title>
                    <!-- Google Web Fonts -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                        rel="stylesheet">

                    <!-- Icon Font Stylesheet -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                        rel="stylesheet">

                    <!-- Libraries Stylesheet -->
                    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                    <!-- Customized Bootstrap Stylesheet -->
                    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                    <!-- Template Stylesheet -->
                    <link href="/client/css/style.css" rel="stylesheet">


                </head>

                <body>


                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                        <div class="spinner-grow text-primary" role="status"></div>
                    </div>
                    <!-- Spinner End -->


                    <jsp:include page="../layout/header.jsp" />


                    <!-- Modal Search Start -->

                    <!-- Modal Search End -->


                    <!-- Single Page Header start -->

                    <!-- Single Page Header End -->


                    <!-- Single Product Start -->


                    <div class="container-fluid py-5 mt-5">

                        <div class="container py-5">
                            <div style="display: flex;justify-content: space-between;">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                                        <li class="breadcrumb-item active">Product Cart Details</li>
                                    </ol>
                                </nav>
                                <div class="btn"><a class="btn btn-warning" href="/">Move home page and order more?</a>
                                </div>
                            </div>
                            <table class="table">
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Handle</th>
                                </tr>

                                <c:forEach items="${allProductInCart}" var="productInCart">
                                    <tr>
                                        <td>
                                            <img src="/images/product/${productInCart.product.image}"
                                                onerror="if (this.src == null) this.src = '/images/default/default.jpg';"
                                                alt="product image" style="width: 100px;">
                                        </td>
                                        <td>${productInCart.product.name}</td>
                                        <td>
                                            <fmt:formatNumber value="${productInCart.price}" type="number"
                                                currencySymbol="VND " />
                                        </td>
                                        <td>
                                            <div class="input-group quantity mb-5" style="width: 100px;">
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                        <i class="fa fa-minus"></i>
                                                    </button>
                                                </div>
                                                <input type="text"
                                                    class="form-control form-control-sm text-center border-0 "
                                                    value="${productInCart.quantity}" style="padding :0 3px;"
                                                    data-cart-detail-id="${productInCart.id}"
                                                    data-cart-detail-price="${productInCart.price}">
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                        <i class="fa fa-plus"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </td>

                                        <td data-cart-detail-id="${productInCart.id}">
                                            <fmt:formatNumber value=" ${productInCart.quantity * productInCart.price}"
                                                type="number" currencySymbol="VND " />
                                        </td>


                                        <td>
                                            <form action="/cart/delete/${productInCart.id}" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <button type="submit" class="btn-close btn-danger"
                                                    aria-label="Close"></button>

                                            </form>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </table>

                            <!-- Cart total -->

                            <div class=" w-50 mt-3 rounded" style="background-color: #f5f5f5; padding: 20px;">
                                <table class="table ">
                                    <thead>
                                        <tr>
                                            <th>
                                                <h3 class="cart-total-title">Cart Total</h3>
                                            </th>
                                            <th></th>
                                        </tr>

                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Subtotal</td>
                                            <td data-cart-total-price="${totalPrice}">
                                                <fmt:formatNumber value="${totalPrice}" type="number"
                                                    currencySymbol="VND " />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Shipping</td>
                                            <td>0d</td>
                                        </tr>
                                        <br />
                                        <tr>
                                            <td>Total</td>
                                            <td data-cart-total-price="${totalPrice}">
                                                <fmt:formatNumber value="${totalPrice + 0}" type="number"
                                                    currencySymbol="VND " />

                                            </td>
                                        </tr>
                                        <br />

                                    </tbody>
                                </table>
                                <form:form action="/checkout" method="get" modelAttribute="cart">
                                    <div class="form-floating mb-3">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </div>
                                    <div style="display: none;">
                                        <c:forEach var="cartDetail" items="${cart.cartDetail}" varStatus="status">
                                            <div class="mb-3">
                                                <div class="form-group">
                                                    <label>Id:</label>
                                                    <form:input class="form-controll" type="text"
                                                        value="${cartDetail.id}"
                                                        path="cartDetail[${status.index}].id" />
                                                </div>
                                                <div class="form-group">
                                                    <label>Quantity:</label>
                                                    <form:input class="form-controll" type="text"
                                                        value="${cartDetail.quantity}"
                                                        path="cartDetail[${status.index}].quantity" />
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button
                                        class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
                                        type="submit">XÁC NHẬN ĐẶT HÀNG</button>
                                </form:form>

                            </div>
                        </div>
                    </div>
                    <!-- Single Product End -->


                    <jsp:include page="../layout/footer.jsp" />


                    <!-- Back to Top -->
                    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                            class="fa fa-arrow-up"></i></a>


                    <!-- JavaScript Libraries -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="/client/lib/easing/easing.min.js"></script>
                    <script src="/client/lib/waypoints/waypoints.min.js"></script>
                    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="/client/js/main.js"></script>



                </body>

                </html>