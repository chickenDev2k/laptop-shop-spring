<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>All Products</title>
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
                <jsp:include page="../layout/header.jsp" />

                <div class="container d-flex justify-content-between " style="margin-top: 200px;">
                    <!-- filter start -->
                    <div class="col-4 col-md-4">
                        <div class="row g-4">
                            <div class="col-12" id="factoryFilter">
                                <div class="mb-2"><b>Hãng sản xuất</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-1" value="APPLE">
                                    <label class="form-check-label" for="factory-1">Apple</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-2" value="ASUS">
                                    <label class="form-check-label" for="factory-2">Asus</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-3" value="LENOVO">
                                    <label class="form-check-label" for="factory-3">Lenovo</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-4" value="DELL">
                                    <label class="form-check-label" for="factory-4">Dell</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-5" value="LG">
                                    <label class="form-check-label" for="factory-5">LG</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="factory-6" value="ACER">
                                    <label class="form-check-label" for="factory-6">Acer</label>
                                </div>

                            </div>
                            <div class="col-12" id="targetFilter">
                                <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="target-1" value="GAMING">
                                    <label class="form-check-label" for="target-1">Gaming</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="target-2"
                                        value="SINHVIEN-VANPHONG">
                                    <label class="form-check-label" for="target-2">Sinh viên - văn
                                        phòng</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="target-3"
                                        value="THIET-KE-DO-HOA">
                                    <label class="form-check-label" for="target-3">Thiết kế đồ
                                        họa</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="target-4" value="MONG-NHE">
                                    <label class="form-check-label" for="target-4">Mỏng nhẹ</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="target-5" value="DOANH-NHAN">
                                    <label class="form-check-label" for="target-5">Doanh nhân</label>
                                </div>


                            </div>
                            <div class="col-12" id="priceFilter">
                                <div class="mb-2"><b>Mức giá</b></div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-2" value="duoi-10-trieu">
                                    <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-3" value="tu-10-15-trieu">
                                    <label class="form-check-label" for="price-3">Từ 10 - 15
                                        triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-4" value="tu-15-20-trieu">
                                    <label class="form-check-label" for="price-4">Từ 15 - 20
                                        triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-5" value="tren-20-trieu">
                                    <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="mb-2"><b>Sắp xếp</b></div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-1" value="gia-tang-dan"
                                        name="radio-sort">
                                    <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-2" value="gia-giam-dan"
                                        name="radio-sort">
                                    <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-3" value="gia-nothing"
                                        name="radio-sort" checked>
                                    <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                                </div>

                            </div>
                            <div class="col-12">
                                <button id="btnFilter"
                                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4">
                                    Lọc Sản Phẩm
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- filter end -->

                    <!-- all products start -->
                    <div class="tab-content col-8">
                        <div id="tab-1" class="tab-pane fade show p-0 active">
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="row g-4">
                                        <c:if test="${totalPages == 0}">
                                            <div>Khong tim thay san pham </div>
                                        </c:if>
                                        <c:forEach var="product" items="${products}">
                                            <div class="col-12 col-sm-6 col-md-4 col-lg-4 col-xl-4">
                                                <div
                                                    class="rounded position-relative fruite-item border border-secondary border-top-0">
                                                    <div class="fruite-img h-50">
                                                        <img src="/images/product/${product.image}"
                                                            onerror="this.onerror=null; this.src='/images/default/default.jpeg'"
                                                            class="img-fluid rounded-top " alt=""
                                                            style="height: 253px; object-fit: contain;">
                                                    </div>
                                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                        style="top: 10px; left: 10px;">Laptop</div>
                                                    <div class="p-4  rounded-bottom ">
                                                        <a href="/product/${product.id}">
                                                            <h4 style="font-size: 15px;">${product.name}</h4>
                                                            <p style="font-size: 13px;">${product.shortDesc}</p>
                                                        </a>
                                                        <div class="d-flex justify-content-center flex-lg-wrap ">
                                                            <p style="font-size: 15px;text-align: center;width: 100%;"
                                                                class="text-dark fs-5 fw-bold mb-3">
                                                                <fmt:formatNumber type="number"
                                                                    value="${product.price}" />

                                                                vnd

                                                            </p>
                                                            <form action="/add-product-to-cart/${product.id}"
                                                                method="post">
                                                                <input type="hidden" name="${_csrf.parameterName}"
                                                                    value="${_csrf.token}" />

                                                                <button type="submit"
                                                                    class=" mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                        class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                    Add to cart</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <!-- 
                            pagination block
                            -->
                                <c:if test="${totalPages > 0}">
                                    <nav aria-label="...">
                                        <ul class="pagination " style="display: flex; justify-content: center;">
                                            <li class=" ${currentPage == 1 ? 'disabled' : ''} page-item">
                                                <a href="/products?page=${currentPage > 2 ?(currentPage - 1):1}${queryString}"
                                                    class="${1 == currentPage ? 'disabled':''} page-link">
                                                    <!-- previous <<  -->
                                                    <span aria-hidden="true">&lt;&lt;</span>

                                                </a>
                                            </li>

                                            <c:forEach begin="0" end="${totalPages-1}" varStatus="loop">
                                                <li class="page-item">
                                                    <a class="${(loop.index +1) == currentPage ? 'active':''} page-link"
                                                        href="/products?page=${loop.index + 1}${queryString}">
                                                        ${loop.index + 1}</a>
                                                </li>
                                            </c:forEach>


                                            <li class="page-item ${totalPages == currentPage  ? 'disabled' : ''}">
                                                <a class="page-link ${totalPages == currentPage ? 'disabled':''} page-link "
                                                    href="/products?page=${currentPage >= totalPages ?currentPage :(currentPage + 1)}${queryString}"
                                                    aria-hidden="true">
                                                    <!-- next >>  -->
                                                    <span aria-hidden="true">&gt;&gt;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                            </div>
                        </div>

                    </div>
                </div>

                <jsp:include page="../layout/footer.jsp" />
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