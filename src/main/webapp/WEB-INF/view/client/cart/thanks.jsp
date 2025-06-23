<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                    <div class="alert alert-success mt-5" role="alert"
                        style="display: flex; justify-content: space-between; align-items: center;">
                        <p>
                            ご注文、ありがとうございます。
                            発送の準備を進めてまいりますので、しばらくお待ちくさい。
                        </p>

                    </div>
                    <div><a href="/" class="btn btn-primary" style="color: beige;">Back
                            Home</a></div>
                </div>
            </div>
        </div>


        <jsp:include page="../layout/feature.jsp" />

        <jsp:include page="../layout/footer.jsp" />
    </body>

    </html>