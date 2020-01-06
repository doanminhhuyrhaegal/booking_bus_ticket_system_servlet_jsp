<%@page import="function.CookieHandle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@page import="model.User"%>
<%@page import="model.Ticket"%>
<%@page import="dao.UserDAO"%>
<%@page import="dao.TicketDAO"%>

<html lang="en">
<head>
    <title>Booking Bus</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/admin/img/favicon.ico" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Marimar Hotel template project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/styles/bootstrap-4.1.2/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/plugins/OwlCarousel2-2.3.4/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/plugins/OwlCarousel2-2.3.4/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/plugins/OwlCarousel2-2.3.4/animate.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/plugins/jquery-datepicker/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/customer/resource/styles/responsive.css">
</head>
<body>
    <%
        Cookie cookie = CookieHandle.find(request.getCookies(),"user_login");
        //Get user info
        User user = null;
        if(cookie!=null) {
            user = new UserDAO().get(cookie.getValue());
            // Check User role, if true direct to seller
            if(user.getRole()) response.sendRedirect(request.getContextPath()+"/seller.TicketList");
        }
        
    %>
    
    <div class="super_container">
	
	<!-- Header -->

	<header class="header">
		<div class="header_content d-flex flex-column align-items-center justify-content-lg-end justify-content-center">
			
			<!-- Logo -->
			<div class="logo"><a href="${pageContext.request.contextPath}/customer/home.jsp"><img class="logo_1" src="${pageContext.request.contextPath}/customer/resource/images/busabc.png" alt=""><img class="logo_2" src="resource/images/logo_2.png" alt=""><img class="logo_3" src="resource/images/logo_3.png" alt=""></a></div>

			<!-- Main Nav -->
			<nav class="main_nav">
				<ul class="d-flex flex-row align-items-center justify-content-start">
					<li class="active"><a href="${pageContext.request.contextPath}/customer.Index">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.TicketList">View Tickets</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.TicketBooking">Booking</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.EditInfo">User Info</a></li>
				</ul>
			</nav>
			<!-- Header Right -->
			<div class="header_right d-flex flex-row align-items-center justify-content-start">
				
				<!-- Search Activation Button -->
				<div class="search_button">
					<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" viewBox="0 0 512 512" enable-background="new 0 0 512 512" width="512px" height="512px">
						<g>
							<path d="M495,466.2L377.2,348.4c29.2-35.6,46.8-81.2,46.8-130.9C424,103.5,331.5,11,217.5,11C103.4,11,11,103.5,11,217.5   S103.4,424,217.5,424c49.7,0,95.2-17.5,130.8-46.7L466.1,495c8,8,20.9,8,28.9,0C503,487.1,503,474.1,495,466.2z M217.5,382.9   C126.2,382.9,52,308.7,52,217.5S126.2,52,217.5,52C308.7,52,383,126.3,383,217.5S308.7,382.9,217.5,382.9z" fill="#FFFFFF"></path>
						</g>
					</svg>
				</div>

				<!-- Header Link -->
				<div class="header_link"><a href="${pageContext.request.contextPath}/customer/ticket_list.jsp">Book Your Room Now</a></div>

				<!-- Hamburger Button -->
				<div class="hamburger"><i class="fa fa-bars" aria-hidden="true" ></i></div>

			</div>

		</div>
			
	</header>

	<!-- Logo Overlay -->

	<div class="logo_overlay">
		<div class="logo_overlay_content d-flex flex-column align-items-center justify-content-center">
			<div class="logo"><a href="#"><img src="${pageContext.request.contextPath}/customer/resource/images/busabc.png" alt=""></a></div>
		</div>
	</div>

	<!-- Menu Overlay -->

	<div class="menu_overlay">
		<div class="menu_overlay_content d-flex flex-row align-items-center justify-content-center">
			
			<!-- Hamburger Button -->
			<div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>

		</div>
	</div>

	<!-- Menu -->

	<div class="menu">
		<div class="menu_container d-flex flex-column align-items-center justify-content-center">

			<!-- Menu Navigation -->
			<nav class="menu_nav text-center">
				<ul>
					<li><a href="${pageContext.request.contextPath}/customer.Index">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.TicketList">View Tickets</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.TicketBooking">Booking</a></li>
					<li><a href="${pageContext.request.contextPath}/customer.EditInfo">User Info</a></li>
				</ul>
			</nav>
			<% 
				if(user != null){%>		
				<!-- Social -->	
					<div class="button menu_button"><a href="${pageContext.request.contextPath}/customer.Logout">Logout</a></div>
				<% }
			%>
		</div>
	</div>

	<!-- Home -->

	<div class="home">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="${pageContext.request.contextPath}/customer/resource/images/bus-banner-1.jpg" data-speed="0.8"></div>
		<div class="home_container d-flex flex-column align-items-center justify-content-center">
			<div class="button home_button"><a href="${pageContext.request.contextPath}/customer.TicketBooking">book now</a></div>
		</div>
	</div>

	<!-- Intro -->

	<div class="intro">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title text-center">
						<div>Welcome</div>
						<h1>You Show Bus Tour</h1>
					</div>
				</div>
			</div>
			<div class="row intro_row">
				<div class="col-xl-8 col-lg-10 offset-xl-2 offset-lg-1">
					<div class="intro_text text-center">
						<p style="font-size:20px">Bus Tour Express tự hào là một hãng xe lớn với các tuyến chạy dọc từ Bắc vào Nam. Hiện nay công ty có hơn 150 xe hoạt động trên 12 tuyến đường nối liền hơn 10 tỉnh thành trên toàn quốc Việt Nam. Với phương châm “xe khởi hành đúng giờ - không khói thuốc - không đón khách dọc đường”, cùng với phong cách phục vụ chuyên nghiệp, tận tình, chu đáo và trung thực, đến nay thương hiệu Mai Linh Express đã trở nên quen thuộc với khách hàng.
						</p>
					</div>
				</div>
			</div>
			<div class="row gallery_row">
				<div class="col">

					<!-- Gallery -->
					<div class="gallery_slider_container">
						<div class="owl-carousel owl-theme gallery_slider">
							
							<!-- Slide -->
							<div class="gallery_slide">
								<img src="${pageContext.request.contextPath}/customer/resource/images/bus-2.jpeg" style="height:515px" alt="">
								<div class="gallery_overlay">
									<div class="text-center d-flex flex-column align-items-center justify-content-center">
										<a href="#">
											<span>+</span>
											<span>See More</span>
										</a>
									</div>
								</div>
							</div>

							<!-- Slide -->
							<div class="gallery_slide">
								<img src="${pageContext.request.contextPath}/customer/resource/images/bus-1.jpeg" style="height:515px" alt="">
								<div class="gallery_overlay">
									<div class="text-center d-flex flex-column align-items-center justify-content-center">
										<a href="#">
											<span>+</span>
											<span>See More</span>
										</a>
									</div>
								</div>
							</div>

							<!-- Slide -->
							<div class="gallery_slide">
								<img src="${pageContext.request.contextPath}/customer/resource/images/bus-3.jpeg" style="height:515px" alt="">
								<div class="gallery_overlay">
									<div class="text-center d-flex flex-column align-items-center justify-content-center">
										<a href="#">
											<span>+</span>
											<span>See More</span>
										</a>
									</div>
								</div>
							</div>

							<!-- Slide -->
							<div class="gallery_slide">
								<img src="${pageContext.request.contextPath}/customer/resource/images/bus-4.jpeg" style="height:515px" alt="">
								<div class="gallery_overlay">
									<div class="text-center d-flex flex-column align-items-center justify-content-center">
										<a href="#">
											<span>+</span>
											<span>See More</span>
										</a>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	
	<!-- Discover -->

	<div class="discover">

		<!-- Discover Content -->
		<div class="discover_content">
			<div class="container">
				<div class="row">
					<div class="col-lg-5">
						<div class="section_title">
							<div>Bus</div>
							<h1>Bus Tour</h1>
						</div>
					</div>
				</div>
				<div class="row discover_row">
					<div class="col-lg-5">
						<div class="discover_highlight">
							<p>Tham quan thành phố Hà Nội Bus Tour là một tour du lịch linh hoạt và thuận tiện kết hợp giao thông và tham quan nhiều điểm nổi bật của du khách Hà Nội bằng xe buýt hai tầng.</p>
						</div>
						<div class="button discover_button"><a href="#">Tìm hiểu ngay</a></div>
					</div>
					<div class="col-lg-7">
						<div class="discover_text">
							<p>Tận hưởng chuyến đi không hạn chế Tour tham quan thành phố Hà Nội Hop On – Hop Off có 13 điểm dừng đi qua hơn 20 điểm tham quan nổi tiếng, cho phép du khách khám phá và trải nghiệm thành phố theo cách riêng của mình. Du khách sẽ đi qua Quảng trường Đông Kinh Nghĩa Thục – Hồ Hoàn Kiếm, Nhà thờ Thánh Giuse, Cột Cờ Hà Nội, Lăng Chủ tịch Hồ Chí Minh, Đền Quán Thánh, Chùa Trấn Quốc, Nhà thờ Cửa Bắc, Hoàng thành Thăng Long, Văn Miếu Quốc Tử Giám, Nhà tù Hoả Lò, Bảo tàng Phụ nữ Việt Nam, Nhà hát lớn Hà Nội, Bưu điện Hà Nội.</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Discover Slider -->
		<div class="discover_slider_container">
			<div class="owl-carousel owl-theme discover_slider">
				
				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(${pageContext.request.contextPath}/customer/resource/images/ho-guom.jpg)"></div>
					<div class="discover_overlay d-flex flex-column align-items-center justify-content-center">
						<h1><a href="#">Hà Nội</a></h1>
					</div>
				</div>

				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(${pageContext.request.contextPath}/customer/resource/images/nha-tho-duc-ba.jpeg)"></div>
					<div class="discover_overlay d-flex flex-column align-items-center justify-content-center">
						<h1><a href="#">Hồ Chí Minh</a></h1>
					</div>
				</div>

				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(${pageContext.request.contextPath}/customer/resource/images/sapa.jpg)"></div>
					<div class="discover_overlay d-flex flex-column align-items-center justify-content-center">
						<h1><a href="#">Sapa</a></h1>
					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- Testimonials -->

	<div class="testimonials">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="section_title text-center">
						<div>Khách Hàng</div>
						<h1>Đánh giá</h1>
					</div>
				</div>
			</div>
			<div class="row testimonials_row">
				<div class="col">
					
					<!-- Testimonials Slider -->
					<div class="testimonials_slider_container">
						<div class="owl-carousel owl-theme testimonials_slider">

							<!-- Slide -->
							<div>
								<div class="testimonial_text text-center">
									<p>Chuyến đi này thật là tuyệt vời với những điều không tưởng. Cảnh đẹp hữu tình làm ta biết được cảnh đẹp ở Việt Nam.</p>
								</div>
								<div class="testimonial_author text-center">
									<div class="testimonial_author_image"><img src="${pageContext.request.contextPath}/customer/resource/images/author_1.jpg" alt=""></div>
									<div class="testimonial_author_name"><a href="#">Đặng Hải Dũng,</a><span> Khách Hàng</span></div>
								</div>
							</div>

							<!-- Slide -->
							<div>
								<div class="testimonial_text text-center">
									<p>Xe buýt tiện lợi, êm ái, đi thật là sướng quá đi</p>
								</div>
								<div class="testimonial_author text-center">
									<div class="testimonial_author_image"><img src="${pageContext.request.contextPath}/customer/resource/images/author_1.jpg" alt=""></div>
									<div class="testimonial_author_name"><a href="#">Thiện Giàu,</a><span> Khách Hàng</span></div>
								</div>
							</div>

							<!-- Slide -->
							<div>
								<div class="testimonial_text text-center">
									<p>Thật tuyệt vời khi biết đến tuyến xe này. Cảm thấy thoải mãn.</p>
								</div>
								<div class="testimonial_author text-center">
									<div class="testimonial_author_image"><img src="${pageContext.request.contextPath}/customer/resource/images/author_1.jpg" alt=""></div>
									<div class="testimonial_author_name"><a href="#">Minh Huy,</a><span> Khách Hàng</span></div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="${pageContext.request.contextPath}/customer/resource/images/image-footer.jpg" data-speed="0.8"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="footer_logo text-center">
						<a href="#"><img src="${pageContext.request.contextPath}/customer/resource/images/busabc.png" alt=""></a>
					</div>
					<div class="footer_content">
						<div class="row">
							<div class="col-lg-4 footer_col">
								<div class="footer_info d-flex flex-column align-items-lg-end align-items-center justify-content-start">
									<div class="text-center">
										<div>Phone:</div>
										<div>+84 4404 2420</div>
									</div>
								</div>
							</div>
							<div class="col-lg-4 footer_col">
								<div class="footer_info d-flex flex-column align-items-center justify-content-start">
									<div class="text-center">
										<div>Address:</div>
										<div>F-Town, Khu Công Nghệ Cao, Q9</div>
									</div>
								</div>
							</div>
							<div class="col-lg-4 footer_col">
								<div class="footer_info d-flex flex-column align-items-lg-start align-items-center justify-content-start">
									<div class="text-center">
										<div>Mail:</div>
										<div>bustour@contact.com</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
</div>

<script src="${pageContext.request.contextPath}/customer/resource/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/styles/bootstrap-4.1.2/popper.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/greensock/TweenMax.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/greensock/TimelineMax.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/greensock/animation.gsap.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/OwlCarousel2-2.3.4/owl.carousel.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/easing/easing.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/progressbar/progressbar.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/parallax-js-master/parallax.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/plugins/jquery-datepicker/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/customer/resource/js/custom.js"></script>
</body>
</html>