<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<%@page import="function.CookieHandle"%>
<%@page import="function.DateFormating"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking Bus</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/admin/img/favicon.ico" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Marimar Hotel template project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/styles/bootstrap-4.1.2/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/plugins/OwlCarousel2-2.3.4/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/plugins/OwlCarousel2-2.3.4/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/plugins/OwlCarousel2-2.3.4/animate.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/plugins/jquery-datepicker/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/styles/about.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seller/styles/about_responsive.css">
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
        // Check User login. If not direct to customer/login
        if(user == null) {
            response.sendRedirect(request.getContextPath()+"/customer/login.jsp");
            return;
        }
        // Check User role, if false direct to customer
        else if(!user.getRole()) {
            response.sendRedirect(request.getContextPath()+"/customer/home.jsp");
            return;
        }
        
    %>
	<div class="super_container">

		<!-- Header -->

		<header class="header">
			<div
				class="header_content d-flex flex-column align-items-center justify-content-lg-end justify-content-center">

				<!-- Logo -->
				<div class="logo">
					<a href="#"><img class="logo_1" src="${pageContext.request.contextPath}/seller/images/busabc.png" alt="">
				</div>

				<!-- Main Nav -->
				<nav class="main_nav">
					<ul
						class="d-flex flex-row align-items-center justify-content-start">
						<li><a href="${pageContext.request.contextPath}/seller.SeatList">View All Of Available Seat</a></li>
						<li class="active"><a href="${pageContext.request.contextPath}/seller.TicketList">View All Tickets Booked</a></li>
					</ul>
				</nav>
				<!-- Header Right -->
				<div
					class="header_right d-flex flex-row align-items-center justify-content-start">

					<!-- Search Activation Button -->
					<div class="search_button">
						<svg xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
							viewBox="0 0 512 512" enable-background="new 0 0 512 512"
							width="512px" height="512px">
					</svg>
					</div>
				</div>
			</div>

		</header>

		<!-- Logo Overlay -->

		<div class="logo_overlay">
			<div
				class="logo_overlay_content d-flex flex-column align-items-center justify-content-center">
				<div class="logo">
					<a href="#"><img src="${pageContext.request.contextPath}/seller/images/busmini1.png" alt=""></a>
				</div>
			</div>
		</div>

		<!-- Menu Overlay -->

		<div class="menu_overlay">
			<div
				class="menu_overlay_content d-flex flex-row align-items-center justify-content-center">

				<!-- Hamburger Button -->
				<div class="hamburger">
					<i class="fa fa-bars" aria-hidden="true"></i>
				</div>

			</div>
		</div>

		<!-- Menu -->

		<div class="menu">
			<div
				class="menu_container d-flex flex-column align-items-center justify-content-center">

				<!-- Menu Navigation -->
                                <% 
                                    if(user != null) {%>		
                                        <!-- Social -->	
                                        <div class="button menu_button"><a href="${pageContext.request.contextPath}/customer.Logout">Logout</a></div>
                                    <% }
                                %>
			</div>
		</div>

		<!-- Home -->

		<div class="home">
			<div class="parallax_background parallax-window"
				data-parallax="scroll" data-image-src="${pageContext.request.contextPath}/seller/images/bus-banner-1.jpg"
				data-speed="0.8"></div>
			<div
				class="home_container d-flex flex-column align-items-center justify-content-center">
				<div class="home_title">
					<h1>Ticket Seller</h1>
				</div>
			</div>
		</div>
		<!-- Offering -->

		<div class="offering">
                    <div class="container">
                        <div class="row">
                                <div class="col">
                                        <div class="section_title text-center">
                                                <div>BusTour</div>
                                        </div>
                                </div>
                        </div>

                        <div class="row">
                            <form class="form-inline " action="${pageContext.request.contextPath}/seller.TicketList" method="post"
                                    style="margin-bottom: -0.5rem !important;">
                                    <input class="form-control mr-sm-2" type="text"
                                            placeholder="key..." aria-label="Search" name = "key">
                                    <button type="submit" class="btn btn-outline-danger btn-rounded waves-effect">Search</button>
                            <div class="form-group"
                                    style="max-width: 24%; margin: 0px 13px; flex: 0 0 43.333333%; margin-bottom: 1rem;">
                                    <label for="inputState">Search By</label> <select id="inputState" name ="ticketfilter"
                                        class="form-control">
                                        <option value ="id">ID</option>
                                        <option value ="customer">Customer</option>
                                        <option value ="seat">Seat</option>
                                    </select>
                            </div>
                            </form>
                                <div class="container">
                                    <h2>All Tickets Booked</h2>
                                    <table class="table table-bordered" style="text-align: center;">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Seat</th>
                                                <th>Customer</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:if test="${not empty listticket}">
                                            <c:forEach var="ticket" items="${listticket}">
                                                <tr>
                                                    <td><input name="ticketid" value="${ticket.id}" readonly></td>
                                                    <td>${ticket.seat}</td>
                                                    <td>${ticket.customer}</td>
                                                    <td><span class="badge badge-success font-medium-1">Booked</span></td>
                                                    <td class ="buttons">
                                                        <a><button type="button" id="btn-update"
                                                            data-id="${ticket.id}" class="btn-hover color-5 btnUpdate">
                                                            <i class="fa fa-pencil-square-o"></i> Update
                                                        </button></a>

                                                        <a><button type="button" id="btn-cancel"
                                                            data-id="${ticket.id}" class="btn-hover color-5 btnCancel">
                                                            <i class="fa fa-times"></i> Cancel
                                                        </button></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                        </div>
                    </div>
		</div>
		<!--Phần xử lý confirm delete bằng ajax -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="///cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
		<script type="text/javascript">
		
		
		$(document).ready(function(){
			$('.btnCancel').click(function() {
                            Swal.fire({
                                title: 'Are You Sure?',
                                text: " Cancel this ticket?",
                                type: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Yes',
                                confirmCancelText: 'No'
                                }).then((result) => {
                                    if(result.value){
                                        $.ajax({
                                            url : "${pageContext.request.contextPath}/seller.TicketCancel",
                                            type : "get",
                                            data : {
                                                id : $(this).data("id")
                                            }
                                        }).done((data) => {
                                            //Command: toastr["success"]("Xóa Thành Công", "Thông Báo")
                                            location.reload();
                                        }).fail(function(err){
                                            console.log(err);
                                        })
                                    }
                                })
			});
		});
		
		$(document).ready(function(){
                    $('.btnUpdate').click(function(){
                        Swal.fire({
                            title: 'Are you sure?',
                            text: " Update this ticket?",
                            type: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: 'Yes',
                            confirmCancelText: 'No'
                            }).then((result) => {
                                if(result.value){
                                    $.ajax({
                                        url : "${pageContext.request.contextPath}/seller.TicketConfirm",
                                        type : "get",
                                        data : {
                                            id : $(this).data("id")
                                        }
                                    }).done((data) => {
                                        //Command: toastr["success"]("Xóa Thành Công", "Thông Báo")
                                        location.reload();
                                    }).fail(function(err){
                                        console.log(err);
                                    })
                                }
                            })
                    });
		});
	</script>
		<!-- Footer -->

		<footer class="footer">
			<div class="parallax_background parallax-window"
				data-parallax="scroll" data-image-src="${pageContext.request.contextPath}/seller/images/image-footer.jpg"
				data-speed="0.8"></div>
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="footer_logo text-center">
							<a href="#"><img src="${pageContext.request.contextPath}/seller/images/busabc.png" alt=""></a>
						</div>
						<div class="footer_content">
							<div class="row">
								<div class="col-lg-4 footer_col">
									<div
										class="footer_info d-flex flex-column align-items-lg-end align-items-center justify-content-start">
										<div class="text-center">
											<div>Phone:</div>
											<div>+546 990221 123</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 footer_col">
									<div
										class="footer_info d-flex flex-column align-items-center justify-content-start">
										<div class="text-center">
											<div>Address:</div>
											<div>Main Str, no 23, New York</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 footer_col">
									<div
										class="footer_info d-flex flex-column align-items-lg-start align-items-center justify-content-start">
										<div class="text-center">
											<div>Mail:</div>
											<div>hotel@contact.com</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="footer_bar text-center"></div>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<script src="${pageContext.request.contextPath}/seller/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/styles/bootstrap-4.1.2/popper.js"></script>
	<script src="${pageContext.request.contextPath}/seller/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/greensock/TweenMax.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/greensock/TimelineMax.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/scrollmagic/ScrollMagic.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/greensock/animation.gsap.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/greensock/ScrollToPlugin.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/OwlCarousel2-2.3.4/owl.carousel.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/easing/easing.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/progressbar/progressbar.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/parallax-js-master/parallax.min.js"></script>
	<script src="${pageContext.request.contextPath}/seller/plugins/jquery-datepicker/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/seller/js/about.js"></script>
</body>
</html>