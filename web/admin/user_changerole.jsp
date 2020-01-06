<%-- 
    Document   : user_change_role
    Created on : Oct 28, 2019, 8:28:39 PM
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
            
            // Find username in cookies. If not go back to user_list.jsp
            cookie = CookieHandle.find(cookies,"admin_user_changerole");
            User user = null;
            if(cookie!=null) user = new UserDAO().get(cookie.getValue());
            if(user==null) {
                response.sendRedirect(request.getContextPath() + "/admin/user_list.jsp");
                return;
            }
            
            // Check alert
            String msg = null;
            cookie = CookieHandle.find(cookies,"admin_user_changerole_alert");
            if(cookie!=null) msg = cookie.getValue();
            // Remove alert
            response.addCookie(CookieHandle.set("admin_user_changerole_alert", "", 0));
            
        %>
        
        <header>
            <center><a href="user_list.jsp"><img src="img/logo.png" /></a></center>
            <h4>ADMIN</h4>
        </header>
        
        <article>
            <session>
            <center>
                <table class="form">
                <form method="post" action="${pageContext.request.contextPath}/admin.UserChangeRole">
                    <tr>
                        <td></td>
                        <td><h2>CHANGE ROLE</h2></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" value="<%= user.getUsername() %>" readonly></td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td>
                            <select name="role">
                                <option value="customer" <% if(!user.getRole()) out.print("selected"); %>>Customer</option>
                                <option value="seller" <% if(user.getRole()) out.print("selected"); %>>Seller</option>
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
