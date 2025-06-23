<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Product Details</title>
            <!-- Google Web Fonts -->
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link
                href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                rel="stylesheet">

            <!-- Icon Font Stylesheet -->
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

            <!-- Libraries Stylesheet -->
            <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
            <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


            <!-- Customized Bootstrap Stylesheet -->
            <link href="/client/css/bootstrap.min.css" rel="stylesheet">

            <!-- Template Stylesheet -->
            <link href="/client/css/style.css" rel="stylesheet">

            <!-- add class name to a tag -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const currentPath = window.location.pathname.replace(/\/$/, "").toLowerCase(); // normalize path

                    const navElement = document.querySelector("#navbarCollapse");
                    const links = navElement.querySelectorAll("a.nav-link");

                    links.forEach(link => {
                        const href = link.getAttribute("href").replace(/\/$/, "").toLowerCase(); // normalize href

                        if (href === currentPath) {
                            link.classList.add("active");
                        } else {
                            link.classList.remove("active");
                        }
                    });
                });
            </script>


        </head>

        <body>
            <!-- Navbar start -->
            <div class="container-fluid fixed-top">
                <!-- <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">

                <div class="top-link pe-2">
                <a href="#" class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
                <a href="#" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                <a href="#" class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
                </div>
                    </div>
                </div> -->
                <div class="container px-0">
                    <nav class="navbar navbar-light bg-white navbar-expand-xl">
                        <a href="/" class="navbar-brand">
                            <img src="/client/img/logo.png" alt="MY SHOP" class="logo-shop">
                        </a>
                        <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarCollapse">
                            <span class="fa fa-bars text-primary"></span>
                        </button>
                        <div class="collapse navbar-collapse bg-white justify-content-between mx-5" id="navbarCollapse">
                            <div class="navbar-nav">
                                <a href="/" class="nav-item nav-link ">Home</a>
                                <a href="/products" class="nav-item nav-link">All Product</a>
                            </div>
                            <div class="d-flex m-3 me-0">

                                <!-- if user has been login -->
                                <c:if test="${not empty pageContext.request.userPrincipal}">
                                    <a href="/cart" class="position-relative me-4 my-auto">
                                        <i class="fa fa-shopping-bag fa-2x"></i>
                                        <span
                                            class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                            style="top: -5px; left: 15px; height: 20px; min-width: 20px;" id="sumCart">
                                            ${sessionScope.sum}
                                        </span>
                                    </a>
                                    <div class="dropdown my-auto">

                                        <a href="#" class="dropdown" role="button" id="dropdownMenuLink"
                                            data-bs-toggle="dropdown" aria-expanded="false" data-bs-toggle="dropdown"
                                            aria-expanded="false">
                                            <i class="fas fa-user fa-2x"></i>
                                        </a>


                                        <ul class="dropdown-menu dropdown-menu-end p-4"
                                            aria-labelledby="dropdownMenuLink">
                                            <li class="d-flex align-items-center flex-column" style="min-width: 300px;">
                                                <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
                                                    src="/images/avatar/${sessionScope.avatar}" />
                                                <div class="text-center my-3">
                                                    <c:out value="${sessionScope.fullName}" />

                                                </div>
                                            </li>

                                            <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>

                                            <li><a class="dropdown-item" href="/client/history">Lịch sử mua hàng</a>
                                            </li>
                                            <li>
                                                <hr class="dropdown-divider">
                                            </li>
                                            <li>
                                                <form action="/logout" method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                        value="${_csrf.token}" />
                                                    <button class="dropdown-item">
                                                        Đăng xuất
                                                    </button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                                <!-- if user has not been login -->
                                <c:if test="${ empty pageContext.request.userPrincipal}">

                                    <a href="/login" class="position-relative me-4 my-auto">
                                        Login
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            <!-- Navbar End -->
        </body>

        </html>