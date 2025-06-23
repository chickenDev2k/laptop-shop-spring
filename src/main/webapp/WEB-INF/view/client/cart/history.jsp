<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
            </head>

            <body>



                <div class="container" style="margin-top: 100px;">
                    <div>
                        <div>
                            <a href="/" class="navbar-brand">
                                <img src="/client/img/logo.png" alt="MY SHOP" class="logo-shop">
                            </a>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-12">
                            <h1>History page</h1>
                            <div class="alert alert-success mt-5" role="alert"
                                style="display: flex; justify-content: space-between; align-items: center;">

                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Thành tiền</th>
                                            <th scope="col">Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${orderDetails}" var="orderDetail">
                                            <tr>
                                                <td>
                                                    <img src="/images/product/${orderDetail.product.image}"
                                                        onerror="if (this.src == null) this.src = '/images/default/default.jpg';"
                                                        alt="product image" style="width: 100px;">
                                                </td>
                                                <td>${orderDetail.product.name}</td>
                                                <td>
                                                    <fmt:formatNumber value="${orderDetail.price}" type="number"
                                                        currencySymbol="VND " />
                                                </td>
                                                <td style="text-align: center;">
                                                    ${orderDetail.quantity}
                                                </td>

                                                <td data-cart-detail-id="${orderDetail.id}">
                                                    <fmt:formatNumber
                                                        value=" ${orderDetail.quantity * orderDetail.price}"
                                                        type="number" currencySymbol="VND " />
                                                </td>


                                                <td>
                                                    ${orderDetail.order.status}
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div>
                                <a href="/" class="btn btn-primary" style="color: beige;">Back
                                    Home
                                </a>
                            </div>
                        </div>
                    </div>
                </div>




                <jsp:include page="../layout/footer.jsp" />
            </body>

            </html>