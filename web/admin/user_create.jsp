<%-- 
    Document   : user_create
    Created on : Oct 29, 2019, 10:24:35 AM
    Author     : Lenvo
--%>

<%@page import="function.CookieHandle"%>
<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Bus</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <%
            String path = request.getContextPath();
            Cookie[] cookies = request.getCookies();
            
            // Check Admin login. If not direct to login
            Cookie cookie = CookieHandle.find(cookies,"admin_login");
            boolean login = false;
            if(cookie!=null) if(cookie.getValue().equals("true")) login = true;
            if(!login) {
                response.sendRedirect(path+"/admin/login.jsp");
                return;
            }
            
            // Find username in cookies
            String username = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_username");
            if(cookie!=null) username = cookie.getValue();
            
            // Find password in cookies
            String password = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_password");
            if(cookie!=null) password = cookie.getValue();
            
            // Find repeatpassword in cookies
            String repeatpassword = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_repeatpassword");
            if(cookie!=null) repeatpassword = cookie.getValue();
            
            // Find name in cookies
            String name = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_name");
            if(cookie!=null) name = cookie.getValue();
            
            // Find phone in cookies
            String phone = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_phone");
            if(cookie!=null) phone = cookie.getValue();
            
            // Find email in cookies
            String email = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_email");
            if(cookie!=null) email = cookie.getValue();
            
            // Find role in cookies
            String role = "";
            cookie = CookieHandle.find(cookies,"admin_user_create_role");
            if(cookie!=null) role = cookie.getValue();
            
            // Check alert
            String msg = null;
            cookie = CookieHandle.find(cookies,"admin_user_create_alert");
            if(cookie!=null) msg = cookie.getValue();
            // Remove alert
            response.addCookie(CookieHandle.set("admin_user_create_alert", "", 0));
            
        %>
        
        <header>
            <center><a href="user_list.jsp"><img src="img/logo.png" /></a></center>
            <h4>ADMIN</h4>
        </header>
        
        <article>
            <session>
            <center>
                <table class="form">
                <form method="post" action="${pageContext.request.contextPath}/admin.UserCreate">
                    <tr>
                        <td></td>
                        <td><h2>ADD NEW USER</h2></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input name="username" value="<%= username %>" required></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="<%= password %>" required></td>
                    </tr>
                    <tr>
                        <td>Repeat Password</td>
                        <td><input type="password" name="repeatpassword" value="<%= repeatpassword %>" required></td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input name="name" value="<%= name %>" required></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="number" name="phone" value="<%= phone %>" required></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="email" name="email" value="<%= email %>" required></td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td>
                            <select name="role">
                                <option value="customer" <%if(role.equals("customer")) out.print("selected");%>>Customer</option>
                                <option value="seller" <%if(role.equals("seller")) out.print("selected");%>>Seller</option>
                            </select>
                        </td>
                    </tr>
                    <% if(msg!=null && !msg.equals("")) { %>
                        <tr>
                            <td></td>
                            <td><p><%= msg %></p></td>
                        </tr
                    <% } %>
                    <tr>
                        <td></td>
                        <td>
                            <button name="edit" value="Save">
                                <img src="img/save.svg"/>
                                Save
                            </button>
                </form>
                            <button onclick="location.href = 'user_list.jsp';">
                                <img src="img/cancel.svg"/>
                                Cancel
                            </button>
                        </td>
                    </tr>
                </table>
            </center>
            </session>
            
        </article>
        
    </body>
</html>
