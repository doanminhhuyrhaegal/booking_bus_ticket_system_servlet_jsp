<%-- 
    Document   : user_change_password
    Created on : Oct 28, 2019, 8:28:09 PM
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
            
            // Find username in cookies. If not go back to user_list
            cookie = CookieHandle.find(cookies,"admin_user_changepassword");
            User user = null;
            if(cookie!=null) user = new UserDAO().get(cookie.getValue());
            if(user==null) {
                response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                return;
            }
            
            // Check alert
            String msg = null;
            cookie = CookieHandle.find(cookies,"admin_user_changepassword_alert");
            if(cookie!=null) msg = cookie.getValue();
            // Remove alert
            response.addCookie(CookieHandle.set("admin_user_changepassword_alert", "", 0));
            
        %>
        
        <header>
            <center><a href="user_list.jsp"><img src="img/logo.png" /></a></center>
            <h4>ADMIN</h4>
        </header>
        
        <article>
            <session>
            <center>
                <table class="form">
                <form method="post" action="${pageContext.request.contextPath}/admin.UserChangePassword">
                    <tr>
                        <td></td>
                        <td><h2>CHANGE PASSWORD</h2></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="<%= user.getUsername() %>" readonly></td>
                    </tr>
                    <tr>
                        <td>Old Password</td>
                        <td><input type="password" name="currentpassword" required></td>
                    </tr>
                    <tr>
                        <td>New Password</td>
                        <td><input type="password" name="newpassword" required></td>
                    </tr>
                    <tr>
                        <td>Repeat Password</td>
                        <td><input type="password" name="repeatpassword" required></td>
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
