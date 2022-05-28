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
    <title>Chronicle Education Category Bootstrap Responsive website Template | Single Product :: w3layouts</title>
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
    <!-- flexslider-css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider.css" type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/css/front-end-style.css" type="text/css" rel="stylesheet"
          media="all">
    <!-- font-awesome icons -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
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
            width: 230px;
            line-height: 30px;
        }
    </style>
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
    <!-- Single -->
    <div class="innerf-pages section">
        <div class="container">
            <div class="col-md-4 single-right-left ">
                <div class="grid images_3_of_2">
                    <div class="flexslider1">
                        <ul class="slides">
                            <li data-thumb="${pageContext.request.contextPath}/images/books/${book.image}">
                                <div class="thumb-image">
                                    <img src="${pageContext.request.contextPath}/images/books/${book.image}"
                                         data-imagezoom="true" alt=" " class="img-responsive"></div>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>

            </div>
            <div class="col-md-8 single-right-left simpleCart_shelfItem">
                <h2>${book.bookName}</h2>
                <br/>
                <h4>综合评分：${book.rating}</h4>
                <div class="caption">
                    <ul class="rating-single">
                        <li>
                            <c:forEach begin="0" end="4" varStatus="var">
                            <c:choose>
                            <c:when test="${book.rating-var.index>=1}">
                        <li>
                            <a href="#">
                                <span class="fa fa-star" aria-hidden="true"></span>
                            </a>
                        </li>
                        </c:when>
                        <c:when test="${book.rating-var.index>0&&book.rating-var.index<1}">
                            <li>
                                <a href="#">
                                    <span class="fa fa-star-half-o " aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="#">
                                    <span class="fa fa-star-o" aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:otherwise>
                        </c:choose>
                        </c:forEach>
                        </li>

                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="occasional">
                    <h5>详细信息</h5>
                    <ul class="single_specific">
                        <li>
                            <span>类别 :</span> ${book.categoryStr}
                        </li>
                        <li>
                            <span>作者 :</span> ${book.author}
                        </li>
                        <li>
                            <span>出版社 :</span> ${book.press}
                        </li>
                        <li>
                            <span>馆藏剩余数 :</span> ${bookList.size()}
                        </li>
                    </ul>
                </div>
                <div class="clearfix"></div>
                <div class="occasion-cart">
                    <div class="chr single-item single_page_b">
                        <form action="${pageContext.request.contextPath}/book/addCart.do" method="post">
                            <input type="hidden" name="category" value="${book.category}">
                            <input type="hidden" name="bookName" value="${book.bookName}">
                            <input type="hidden" name="author" value="${book.author}">
                            <input type="hidden" name="press" value="${book.press}">
                            <input type="hidden" name="status" value="${book.status}">
                            <input type="hidden" name="ISBN" value="${book.ISBN}">
                            <input type="hidden" name="rating" value="${book.rating}">
                            <input type="hidden" name="image" value="${book.image}">
                            <c:choose>
                                <c:when test="${bookList.size()!=0}">
                                    <button type="submit" class="chr-cart pchr-cart"
                                            style="height: 50px; line-height: 40px;">
                                        我想借阅
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button disabled class="chr-cart pchr-cart "
                                            style="height: 50px; line-height: 40px;">
                                        暂无库存
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- /new_arrivals -->
    <div class="section singlep_btm">
        <div class="container">
            <div class="new_arrivals">
                <h4 class="rad-txt">
                    <span class="abtxt1">借阅过本书的人</span>
                    <span class="abtext">还借阅过</span>
                </h4>
                <c:forEach items="${recommendBookList}" var="book" >
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
                                <form action="${pageContext.request.contextPath}/book/bookDetails.do" method="post">
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
                <!-- //row3 -->
                <div class="clearfix"></div>
            </div>
            <!--//new_arrivals-->
            <div class="clearfix"></div>

        </div>
    </div>
    <!--// Single -->
    <!-- footer -->
    <jsp:include page="footer1.jsp"/>
    <!-- //footer -->
</div>
<!-- //home -->
<!-- Common js -->
<script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<!--// Common js -->
<!-- cart-js -->
<script src="${pageContext.request.contextPath}/js/minicart.js"></script>
<script>
    chr.render();

    chr.cart.on('chr_checkout', function (evt) {
        var items, len, i;

        if (this.subtotal() > 0) {
            items = this.items();
            for (i = 0, len = items.length; i < len; i++) {
            }
        }
    });
</script>
<!-- //cart-js -->
<!-- zoom -->
<script src="${pageContext.request.contextPath}/js/imagezoom.js"></script>
<!-- zoom-->
<!-- single -->
<!-- FlexSlider -->
<script src="${pageContext.request.contextPath}/js/jquery.flexslider.js"></script>
<script>
    // Can also be used with $(document).ready()
    $(window).load(function () {
        $('.flexslider1').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });
</script>
<!-- //FlexSlider-->

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
<!-- //dropdown nav -->
<!--search jQuery-->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!--search jQuery-->

<!-- Scrolling Nav JavaScript -->
<script src="${pageContext.request.contextPath}/js/scrolling-nav.js"></script>
<!-- //fixed-scroll-nav-js -->
<!--//scripts-->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<!-- start-smoth-scrolling -->
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
<!-- start-smoth-scrolling -->
<!-- here stars scrolling icon -->
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
<!-- //here ends scrolling icon -->
<!-- smoothscroll -->
<script src="${pageContext.request.contextPath}/js/SmoothScroll.min.js"></script>
<!-- //smoothscroll -->

</body>

</html>