<%@ page import="java.io.PrintWriter" %>
<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <title>Chronicle Education Category Bootstrap Responsive website Template | About :: w3layouts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Chronicle Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design"/>
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <!-- shop css -->
    <link href="${pageContext.request.contextPath}/css/shop.css" type="text/css" rel="stylesheet" media="all">
    <!-- gallery desoslide -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.desoslide.css">
    <!-- gallery desoslide -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-end-style.css">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <!-- font-awesome icons -->
    <!-- //Custom Theme files -->
    <!-- online-fonts -->
    <!-- logo -->
    <link href="//fonts.googleapis.com/css?family=Fredericka+the+Great" rel="stylesheet">
    <!-- titles -->
    <link href="//fonts.googleapis.com/css?family=Merriweather+Sans:300,300i,400,400i,700,700i,800,800i"
          rel="stylesheet">
    <!-- body -->
    <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
          rel="stylesheet">
    <!-- //online-fonts -->
    <style>
        .bookName {
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            overflow: hidden;
            display: -webkit-inline-box;
            width: 165px;
            line-height: 30px;
        }
    </style>
    <%
        String rating_now = "1";
        rating_now = request.getParameter("rating");
    %>
</head>


<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<div id="home">
    <!-- header -->
    <!-- navbar -->
    <jsp:include page="header.jsp"/>
    <!-- //navbar ends here -->
    <!-- banner -->
    <div class="banner-bg-inner">
        <!-- banner-text -->
        <div class="banner-text-inner">
            <div class="container">
                <h2 class="title-inner">
                    world of reading
                </h2>
            </div>
        </div>
        <!-- //banner-text -->
    </div>
    <!-- //banner -->
    <!-- breadcrumbs -->
    <div class="crumbs text-center">
        <div class="container">

        </div>
    </div>
    <!--//breadcrumbs ends here-->
    <!-- Shop -->
    <div class="innerf-pages section">
        <div class="container-cart">
            <!-- product left -->
            <div class="side-bar col-md-3">
                <!--preference -->
                <div class="left-side">
                    <h3 class="shopf-sear-headits-sear-head">
                        根据类别筛选</h3>
                    <ul>
                        <c:forEach items="${categoryList}" var="category">
                            <li class="bookName">
                                <a href="${pageContext.request.contextPath}/book/findBookByCategory.do?category=${category.id}">
                                    <h5 style="margin-left: 10px">${category.categoryName}</h5></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- // preference -->
                <div class="search-hotel">
                    <h3 class="shopf-sear-headits-sear-head">
                        查询</h3>
                    <form action="${pageContext.request.contextPath}/book/findBookByCondition.do" method="post">
                        <input name="Search" type="search" placeholder="请输入要查询的书名或作者名称">
                        <input type="submit" value="Search">
                    </form>
                </div>
                <!-- reviews -->
                <div class="customer-rev left-side">
                    <h3 class="shopf-sear-headits-sear-head">Customer Review</h3>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=5">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <span>5.0</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=4.5">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                <span>4.5</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=4">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>4.0</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=3.5">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>3.5</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=3">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>3.0</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=2.5">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>2.5</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=2">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>2.0</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=1.5">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-half-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>1.5</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/findBookByReview.do?rating=1">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <i class="fa fa-star-o" aria-hidden="true"></i>
                                <span>1.0</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- //reviews -->
            </div>
            <!-- //product left -->
            <!-- product right -->
            <div class="left-ads-display col-md-9">
                <div class="wrapper_top_shop">
                    <!-- product-sec1 -->
                    <div class="product-sec1">
                        <c:choose>
                            <c:when test="${pageInfo.list.size()==0}">
                                <div class="col-sm-12">
                                    <p class="text-center" style="font-size: 40px">该条件下暂无书籍</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${pageInfo.list}" var="book" varStatus="bookStatus">
                                    <div class="col-md-3 product-men">
                                        <div class="product-chr-info chr">
                                            <div class="thumbnail">
                                                <a title="${book.bookName}">
                                                    <img src="${pageContext.request.contextPath}/images/books/${book.image}">
                                                </a>
                                            </div>
                                            <div class="caption">
                                                <a title="${book.bookName}">
                                                    <h4 class="bookName">
                                                            ${book.bookName}
                                                    </h4>
                                                </a>
                                                <p>${book.author }</p>
                                                <div class="matrlf-mid">
                                                    <ul class="rating">
                                                        <c:forEach begin="0" end="4" varStatus="var">
                                                            <c:choose>
                                                                <c:when test="${book.rating-var.index>=1}">
                                                                    <li>
                                                                        <a href="#">
                                                                            <span class="fa fa-star"
                                                                                  aria-hidden="true"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:when test="${book.rating-var.index>0&&book.rating-var.index<1}">
                                                                    <li>
                                                                        <a href="#">
                                                                            <span class="fa fa-star-half-o "
                                                                                  aria-hidden="true"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <li>
                                                                        <a href="#">
                                                                    <span class="fa fa-star-o"
                                                                          aria-hidden="true"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <ul class="price-list">
                                                    <li>ISBN:</li>
                                                    <li>${book.ISBN}</li>
                                                </ul>
                                                <form action="${pageContext.request.contextPath}/book/bookDetails.do"
                                                      method="post">
                                                    <input type="hidden" name="category" value="${book.category}">
                                                    <input type="hidden" name="bookName" value="${book.bookName}">
                                                    <input type="hidden" name="author" value="${book.author}">
                                                    <input type="hidden" name="press" value="${book.press}">
                                                    <input type="hidden" name="status" value="${book.status}">
                                                    <input type="hidden" name="ISBN" value="${book.ISBN}">
                                                    <input type="hidden" name="rating" value="${book.rating}">
                                                    <input type="hidden" name="image" value="${book.image}">
                                                    <button type="submit" class="chr-cart pchr-cart"
                                                            style="height: 35px">
                                                        详情
                                                    </button>
                                                    <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        <div class="clearfix"></div>
                    </div>
                    <!-- //product-sec1 -->
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>
            <c:if test="${pageInfo.list.size()!=0}">
                <div class="box-tools pull-right">
                    <ul class="pagination">
                        <li>
                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=1&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>"
                               aria-label="Previous">首页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">上一页</a>
                        </li>
                        <c:choose>
                            <c:when test="${pageInfo.pages<=5}">
                                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageNum}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">${pageNum}</a>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${pageInfo.pageNum+2>pageInfo.pages}">
                                    <c:forEach end="${pageInfo.pageNum-pageInfo.pages+2}" begin="1" step="1"
                                               var="temp">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageInfo.pageNum-(5-temp)}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">${pageInfo.pageNum-(5-temp)}</a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <c:forEach begin="${pageInfo.pageNum-2>1?pageInfo.pageNum-2:1}"
                                           end="${pageInfo.pageNum+2>pageInfo.pages?pageInfo.pages:pageInfo.pageNum+2}"
                                           var="pageNum">

                                    <li>
                                        <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageNum}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">${pageNum}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageInfo.pageNum-2<=0}">
                                    <c:forEach end="${3-pageInfo.pageNum}" begin="1" step="1"
                                               var="temp">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${temp+pageInfo.pageNum+2}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">${temp+pageInfo.pageNum+2}</a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>">下一页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/book/${sessionScope.pageInfo}?page=${pageInfo.pages}&pageSize=${pageInfo.pageSize}<c:if test="${sessionScope.pageInfo=='findBookByCategory.do'}">&category=${pageInfo.list.get(0).category}</c:if><c:if test="${sessionScope.pageInfo=='findBookByReview.do'}">&rating=<%=rating_now%></c:if><c:if test="${sessionScope.pageInfo=='findBookByCondition.do'}">&Search=${sessionScope.condition}</c:if>"
                               aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
    <!--// Shop -->

    <!-- footer -->
    <jsp:include page="footer1.jsp"/>
    <!-- //footer -->
</div>
<!-- home -->
<!-- js -->
<script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<!-- js -->
<!--search jQuery-->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!--search jQuery-->
<!-- cart-js -->
<script src="${pageContext.request.contextPath}/js/minicart.js"></script>
<script>
    chr.render();

    chr.cart.on('new_checkout', function (evt) {
        var items, len, i;

        if (this.subtotal() > 0) {
            items = this.items();

            for (i = 0, len = items.length; i < len; i++) {
            }
        }
    });
</script>
<!-- cart-js -->
<!-- Scrolling Nav JavaScript -->
<script src="${pageContext.request.contextPath}/js/scrolling-nav.js"></script>
<!-- fixed-scroll-nav-js -->
<!-- dropdown nav -->
<script>
    $(document).ready(function () {
        $(".dropdown").hover(
            function () {
                $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                $(this).toggleClass('open');
            },
            function () {
                $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                $(this).toggleClass('open');
            }
        );
    });
</script>
<!-- dropdown nav -->
<!-- start-smooth-scrolling -->
<script src="${pageContext.request.contextPath}/js/move-top.js"></script>
<script src="${pageContext.request.contextPath}/js/easing.js"></script>
<script>
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();

            $('html,body').animate({
                scrollTop: $(this.hash).offset().top
            }, 1000);
        });
    });
</script>
<!-- end-smooth-scrolling -->
<!-- smooth-scrolling-of-move-up -->
<script>
    $(document).ready(function () {
        /*
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        */

        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<script src="${pageContext.request.contextPath}/js/SmoothScroll.min.js"></script>
<!-- smooth-scrolling-of-move-up -->
<!-- about bottom grid Numscroller -->
<script src="${pageContext.request.contextPath}/js/numscroller-1.0.js"></script>
<!-- about bottom grid Numscroller -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>

</html>