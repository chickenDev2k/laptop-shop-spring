// package vn.hoidanit.laptopshop.controller;

// import jakarta.servlet.RequestDispatcher;
// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// public class MyCustomErrorController implements ErrorController {

//     @RequestMapping("/error")
//     public String handleError(HttpServletRequest request, Model model) {
//         // 1. Get HTTP status code
//         Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//         HttpStatus httpStatus = null;
//         String errorMessage = "Something went wrong."; // Default message
//         String errorPath = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

//         if (status != null) {
//             Integer statusCode = Integer.valueOf(status.toString());
//             httpStatus = HttpStatus.resolve(statusCode);

//             // 2. Customize error message based on status
//             if (httpStatus == HttpStatus.NOT_FOUND) { // 404
//                 errorMessage = "The page you are looking for could not be found.";
//             } else if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) { // 500
//                 errorMessage = "We're sorry, but an unexpected error occurred on our server.";
//             } else if (httpStatus == HttpStatus.FORBIDDEN) { // 403
//                 errorMessage = "You do not have permission to access this resource.";
//             }
//             // Add more conditions for other status codes as needed
//         }

//         // 3. Add data to the Model to be displayed in the HTML view
//         model.addAttribute("statusCode", httpStatus != null ? httpStatus.value() : "N/A");
//         model.addAttribute("error", httpStatus != null ? httpStatus.getReasonPhrase() : "Error");
//         model.addAttribute("message", errorMessage);
//         model.addAttribute("path", errorPath != null ? errorPath : "N/A");

//         // Optional: Get the actual exception details for debugging (especially for 500s)
//         Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//         if (throwable != null) {
//             model.addAttribute("exceptionMessage", throwable.getMessage());
//             // For production, avoid exposing full stack traces directly to the user
//             // model.addAttribute("stackTrace", ExceptionUtils.getStackTrace(throwable));
//         }


//         // 4. Return the name of your custom error view (e.g., "error/error-page")
//         // Spring's View Resolver will look for src/main/resources/templates/error/error-page.html (or .jsp, .thymeleaf)
//         return "templates/error/error-page";
//     }
// }