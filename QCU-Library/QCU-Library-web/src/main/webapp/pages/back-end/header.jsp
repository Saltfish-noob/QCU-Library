<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="${pageContext.request.contextPath}/pages/back-end/main.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<%--<img style="width: 120px; height: 50px" src="${pageContext.request.contextPath}/images/QCU-logo-lg.png">--%>
		<span class="logo-mini"><img style="width: 28px; height: 40px; margin-top: 5px;" src="${pageContext.request.contextPath}/images/QCU-logo-mini.png"/></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><img style="width: 120px; height: 50px" src="${pageContext.request.contextPath}/images/QCU-logo-lg.png"/></span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="${pageContext.request.contextPath}/images/user2-160x160.jpg"
						class="user-image" alt="User Image"> <span class="hidden-xs">
							${sessionScope.nickname}
					</span>

				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header">
							<img src="${pageContext.request.contextPath}/images/user2-160x160.jpg"
							class="img-circle" alt="User Image">
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="${pageContext.request.contextPath}/user/userSelf.do?userId=${sessionScope.userId}" class="btn btn-default btn-flat">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="${pageContext.request.contextPath}/logout.do"
									class="btn btn-default btn-flat">注销</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->