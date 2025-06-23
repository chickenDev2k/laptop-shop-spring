<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error!</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            color: #333;
            text-align: center;
            padding: 50px;
        }

        .container {
            max-width: 700px;
            margin: 0 auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #dc3545;
            font-size: 3.5em;
            margin-bottom: 15px;
        }

        h2 {
            font-size: 1.8em;
            color: #6c757d;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.1em;
            line-height: 1.6;
            margin-bottom: 15px;
        }

        .details {
            background-color: #e9ecef;
            border-left: 5px solid #007bff;
            padding: 15px;
            margin-top: 25px;
            text-align: left;
            font-family: 'Courier New', monospace;
            font-size: 0.9em;
            color: #495057;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>Error Occurred!</h1>
        <h2>HTTP Status: <span id="statusCode">${statusCode}</span> - ${error}</h2>
        <p>${message}</p>

        <div class="details">
            <p><strong>Path:</strong> <span id="path">${path}</span></p>
            <p th:if="${exceptionMessage}"><strong>Details:</strong> <span th:text="${exceptionMessage}"></span></p>
        </div>

        <p style="margin-top: 30px;">
            Go back to <a href="/">Home Page</a>
        </p>
    </div>
</body>

</html>