<%@page import="function.CookieHandle"%>
<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<!DOCTYPE html>
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
        
        // Remove username in cookie
        response.addCookie(CookieHandle.set("user_login", "", 0));
        
        // Check alert
        String msg = null;
        Cookie cookie = CookieHandle.find(cookies, "customer_register_alert");
        if(cookie!=null) msg = cookie.getValue();
        // Remove alert
        response.addCookie(CookieHandle.set("customer_register_alert", "", 0));
        
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
            <form action="${pageContext.request.contextPath}/customer.Register" method="post">
            	<input type="text" id="name" class="fadeIn first" name="name" placeholder="Name">
            	<input type="email" id="login" class="fadeIn second" name="email" placeholder="Email">
                <input type="text" id="login" class="fadeIn second" name="username" placeholder="Username">          
                <input type="password" id="password" class="fadeIn third" name="password" placeholder="Password">
                <input type="password" id="password" class="fadeIn third" name="repassword" placeholder="RePassword">
                <input type="number" id="phone" class="fadeIn third" name="phone" placeholder="Phone">
                <input type="submit" class="fadeIn fourth" name="action" value="Register">
            </form>
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