<%@page import="function.CookieHandle"%>
<!DOCTYPE html>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Bus</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/admin/img/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/styles/login.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
    <%
        Cookie[] cookies = request.getCookies();
        
        Cookie cookie = CookieHandle.find(cookies, "user_login");
        //Get user info
        User user = null;
        if(cookie!=null) {
            user = new UserDAO().get(cookie.getValue());
        }
        // Check Customer login. If yes direct to home.jsp
        if(user != null) {
            response.sendRedirect(request.getContextPath()+"/customer/home.jsp");
            return;
        }
        
        // Check alert
        String msg = null;
        cookie = CookieHandle.find(cookies, "customer_login_alert");
        if(cookie!=null) msg = cookie.getValue();
        // Remove alert
        response.addCookie(CookieHandle.set("customer_login_alert", "", 0));
        
    %>
    
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <!-- Tabs Titles -->
            <h2 class="active"> Sign In </h2>
            <!-- Icon -->
            <div class="fadeIn first">
                <img src="${pageContext.request.contextPath}/customer/resource/images/user.png" id="icon" alt="User Icon" style="height: 200px; width: 200px" />
            </div>

            <!-- Login Form -->
            <form action="${pageContext.request.contextPath}/customer.Login" method="post">
                <input type="text" id="login" class="fadeIn second" name="username" placeholder="Username">
                <input type="password" id="password" class="fadeIn third" name="password" placeholder="Password">
                <input type="submit" class="fadeIn fourth" name="action"  value="Login">
            </form>

            <!-- Remind Passowrd -->
            <div id="formFooter">
                <a class="underlineHover" href="${pageContext.request.contextPath}/customer/register.jsp">Register</a>
            </div>

        </div>
    </div>
</body>

<script>
    window.onload = function() {
        <% if(msg!=null && !msg.equals("")) { %>
            alert("<%= msg %>");
        <% } %>
    };
</script>

</html>