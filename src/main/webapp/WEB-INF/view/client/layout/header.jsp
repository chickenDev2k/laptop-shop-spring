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
                            <img src="/client/img/logo-shop.png" alt="MY SHOP" class="logo-shop">
                        </a>
                        <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarCollapse">
                            <span class="fa fa-bars text-primary"></span>
                        </button>
                        <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                            <div class="navbar-nav mx-auto">
                                <a href="/" class="nav-item nav-link active">Home</a>
                                <a href="/" class="nav-item nav-link">All Product</a>
                            </div>
                            <div class="d-flex m-3 me-0">
                                <button
                                    class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
                                    data-bs-toggle="modal" data-bs-target="#searchModal"><i
                                        class="fas fa-search text-primary"></i></button>
                                <a href="#" class="position-relative me-4 my-auto">
                                    <i class="fa fa-shopping-bag fa-2x"></i>
                                    <span
                                        class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                        style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                                </a>
                                <a href="#" class="my-auto">
                                    <i class="fas fa-user fa-2x"></i>
                                </a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            <!-- Navbar End -->
        </body>

        </html>