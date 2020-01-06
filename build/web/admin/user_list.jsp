<%-- 
    Document   : admin
    Created on : Oct 25, 2019, 11:12:02 AM
    Author     : Lenvo
--%>

<%@page import="function.CookieHandle"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.SQLConnector"%>
<%@page import="dao.UserDAO"%>
<%@page import="java.util.ArrayList"%>
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
            
            // Find key search in cookies
            String key = "";
            cookie = CookieHandle.find(cookies,"admin_user_list_key");
            if(cookie!=null) key = cookie.getValue();
            
            // Find info in cookies
            String info = "username";
            cookie = CookieHandle.find(cookies,"admin_user_list_info");
            if(cookie!=null) info = cookie.getValue();
            
            // Find sort in cookies
            String sort = "none";
            cookie = CookieHandle.find(cookies,"admin_user_list_sort");
            if(cookie!=null) sort = cookie.getValue();
            
            // Find role in cookies
            String role = "all";
            cookie = CookieHandle.find(cookies,"admin_user_list_role");
            if(cookie!=null) role = cookie.getValue();
            
            UserDAO userDAO = new UserDAO();
            
            // Find length of filter
            int l = userDAO.sizeOfFilter(key, info, sort, role)/10 + 1;
            response.addCookie(CookieHandle.set("admin_user_list_length", String.valueOf(l), 60*60*24));
            
            // Set page number
            int p = 1;
            cookie = CookieHandle.find(cookies,"admin_user_list_page");
            if(cookie!=null) p = Integer.valueOf(cookie.getValue());
            if(p<1) p = 1; else if(p>l) p = l;
            
            // Filter user list
            ArrayList<User> users = new UserDAO().filter(key,info,sort,role,(p-1)*10,10);
            
            // Check alert
            String msg = null;
            cookie = CookieHandle.find(cookies,"admin_user_list_alert");
            if(cookie!=null) msg = cookie.getValue();
            // Remove alert
            response.addCookie(CookieHandle.set("admin_user_list_alert", "", 0));
            
        %>
        
        <header>
            <center><a href="user_list.jsp"><img src="img/logo.png" /></a></center>
            <h4>ADMIN</h4>
        </header>
        
        <article>
            <session>
            <center>
                <table class="filter">
                    <form method="get" action="${pageContext.request.contextPath}/admin.UserSearchAndFilter">
                    <tr>
                        <td>
                            <input name="key" value="<%= key %>" placeholder="key...">
                            <button name="search">
                                <img src="img/search.svg"/>
                                Search
                            </button>
                        </td>
                        <td>
                            BY 
                            <select name="info" onchange="this.form.submit()">
                                <option value="username" <%if(info.equals("username")) {%> selected <%}%>>Username</option>
                                <option value="name" <%if(info.equals("name")) {%> selected <%}%>>Name</option>
                                <option value="phone" <%if(info.equals("phone")) {%> selected <%}%>>Phone</option>
                                <option value="email" <%if(info.equals("email")) {%> selected <%}%>>Email</option>
                            </select>
                            SORT 
                            <select name="sort" onchange="this.form.submit()">
                                <option value="none" <%if(sort.equals("none")) {%> selected <%}%>>None</option>
                                <option value="asc" <%if(sort.equals("asc")) {%> selected <%}%>>ASC</option>
                                <option value="desc" <%if(sort.equals("desc")) {%> selected <%}%>>DESC</option>
                            </select>
                            ROLE 
                            <select name="role" onchange="this.form.submit()">
                                <option value="all" <%if(role.equals("all")) {%> selected <%}%>>All</option>
                                <option value="customer" <%if(role.equals("customer")) {%> selected <%}%>>Customer</option>
                                <option value="seller" <%if(role.equals("seller")) {%> selected <%}%>>Seller</option>
                            </select>
                        </td>
                    </form>
                        <td>
                            <button name="adduser" onclick="location.href = 'user_create.jsp';">
                                <img src="img/add.svg"/>
                                Add user
                            </button>
                        </td>
                    </tr>
                </table>
                
                <table class="data">
                    <tr>
                        <td></td>
                        <td>Username</td>
                        <td>Name</td>
                        <td>Phone</td>
                        <td>Email</td>
                        <td>Role</td>
                        <td>Action</td>
                    </tr>
                    
                    <%
                        for(int i=0,c=users.size();i<c;++i) {
                            User user = users.get(i);%>
                            <tr>
                                <form method="post" action="${pageContext.request.contextPath}/admin.UserList">
                                <td style="width:30px"><center><%= (p-1)*10+(i+1) %></center></td>
                                <td><input name="username" value="<%= user.getUsername() %>" readonly style="width:90px"></td>
                                <td><input value="<%= user.getName() %>" readonly style="width:170px"></td>
                                <td><input value="<%= user.getPhone() %>" readonly style="width:100px"></td>
                                <td><input value="<%= user.getEmail() %>" readonly style="width:170px"></td>
                                <%
                                    if(user.getRole()) {%>
                                        <td><input value="Seller" readonly style="width:70px"></td>
                                    <%}
                                    else {%>
                                        <td><input value="Customer" readonly style="width:70px"></td>
                                    <%}
                                %>
                                <td>
                                <center>
                                    <input type="submit" name="edit" value="Change role">
                                    <input type="submit" name="edit" value="Change password">
                                    <input type="submit" name="edit" value="Edit info">
                                </center>
                                </td>
                                </form>
                            </tr>
                        <%}
                    %>
                </table>
                
                <table class="page">
                    <tr>
                    <form method="get" action="${pageContext.request.contextPath}/admin.UserPages">
                        <td><input type="submit" name="page" value="<<"></td>
                        <td><input type="submit" name="page" value="<"></td>
                        <td><input type="submit" name="page" value="Go to"></td>
                        <td><input name="current" value="<%= p %>"></td>
                        <td><input type="submit" name="page" value=">"></td>
                        <td><input type="submit" name="page" value=">>"></td>
                    </form>
                    </tr>
                </table>
                
            </center>
            </session>
            
        </article>
        
    </body>
    
    <script>
        window.onload = function() {
            <% if(msg!=null && !msg.equals("")) { %>
                alert("<%= msg %>");
            <% } %>
        };
    </script>

</html>
