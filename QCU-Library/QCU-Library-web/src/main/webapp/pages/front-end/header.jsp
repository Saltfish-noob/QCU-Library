<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="main-nav">
        <div class="container">

            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-ex1-collapse">
                    <span class="sr-only">QCU Library</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/pages/front-end/index.jsp">QCU Library</a>
                </h1>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse nav-right">
                <ul class="nav navbar-nav navbar-left cl-effect-15">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/pages/front-end/index.jsp">首页</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/book/findAllBookByGroup.do">目录</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/book/checkout.do">结算</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/borrowLog/findBorrowLogByUserId.do">归还书籍</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle effect-3" data-toggle="dropdown">
                                <span class="fa fa-user nav-icon " aria-hidden="true">
                                </span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a>欢迎您：${sessionScope.nickname}</a>
                            </li>
                            <security:authorize access="hasAuthority('Permission_FindAllUser')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/pages/back-end/main.jsp">进入管理系统</a>
                                </li>
                            </security:authorize>
                            <li>
                                <a href="${pageContext.request.contextPath}/pages/front-end/alert.jsp">修改密码</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/logout.do">注销</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- search-bar -->
                <div class="search-bar-agileits">
                    <div class="cd-main-header">
                        <ul class="cd-header-buttons">
                            <li>
                                <a class="cd-search-trigger" href="#cd-search">
                                    <span></span>
                                </a>
                            </li>
                        </ul>
                        <!-- cd-header-buttons -->
                    </div>
                    <div id="cd-search" class="cd-search">
                        <form action="${pageContext.request.contextPath}/book/findBookByCondition.do" method="post">
                            <input name="Search" type="search" placeholder="请输入要查询的书名或作者名称">
                        </form>
                    </div>
                </div>
                <!-- search-bar ends here -->
            </div>
            <!-- /.navbar-collapse -->
            <div class="clearfix"></div>
        </div>
        <!-- /.container -->
    </div>
</nav>
