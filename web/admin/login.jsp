<%-- 
    Document   : login
    Created on : Oct 24, 2019, 3:11:24 PM
    Author     : Lenvo
--%>

<%@page import="function.CookieHandle"%>
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
            
            // Check Admin login. If yes direct to user_list
            Cookie cookie = CookieHandle.find(cookies,"admin_login");
            boolean login = false;
            if(cookie!=null) if(cookie.getValue().equals("true")) login = true;
            if(login) {
                response.sendRedirect(path+"/admin/user_list.jsp");
                return;
            }
            
        %>
        
        <header>
            <center><a href="user_list.jsp"><img src="img/logo.png" /></a></center>
            <h4>ADMIN</h4>
        </header>
        
        <article>
            <session>
            <center>
                <form method="post" action="${pageContext.request.contextPath}/admin.Login">
                <table class="login">
                    <tr>
                        <td><h4>SIGN IN</h4></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="usename" placeholder="Usename" required /></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" placeholder="Password" required /></td>
                    </tr>
                    <%  // Display error
                        if(cookie!=null) if(cookie.getValue().equals("false")) {
                            %>
                            <tr>
                                <td style="padding:10px 0 0 2px;color:#ff4040">Usename or Password incorrect</td>
                            </tr
                            <%
                            response.addCookie(CookieHandle.set("admin_login", "", 0));
                        }
                    %>
                    <tr>
                        <td>
                            <button>
                                <img src="img/login.svg"/>
                                LOGIN
                            </button>
                        </td>
                    </tr>
                </table>
                </form>
            </center>
            </session>
            
        </article>
        
        
    </body>
</html>
