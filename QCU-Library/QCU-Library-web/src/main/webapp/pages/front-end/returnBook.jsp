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
    <title>Chronicle Education Category Bootstrap Responsive website Template | Checkout :: w3layouts</title>
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
    <!-- checkout css -->
    <link href="${pageContext.request.contextPath}/css/checkout.css" type="text/css" rel="stylesheet" media="all">
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
    <!--checkout-->
    <div class="innerf-pages section">
        <div class="container">
            <div class="privacy about">
                <h4 class="rad-txt">
                    <span class="abtxt1">归还</span>
                    <span class="abtext">书籍</span>
                </h4>

                <div class="checkout-right">
                    <h4>您尚有
                        <span>${borrowLogList.size()} 本书未归还</span>
                    </h4>
                    <table class="timetable_sub table-responsive">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>书籍</th>
                            <th>书籍名称</th>
                            <th>评分</th>
                            <th>借阅日期</th>
                            <th>应还日期</th>

                            <th class="col-lg-1">归还</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${borrowLogList.size()!=0}">
                                <c:forEach items="${borrowLogList}" var="borrowLog" varStatus="b">
                                    <form action="${pageContext.request.contextPath}/borrowLog/returnSingleBook.do" method="post">
                                        <tr class="rem1" id="${borrowLog.id}">
                                            <input type="hidden" name="id" value="${borrowLog.id}">
                                            <input type="hidden" name="bookId" value="${borrowLog.bookId}">
                                            <input type="hidden" name="userId" value="${borrowLog.userId}">
                                            <td class="invert">${b.index+1}</td>
                                            <td class="invert-image">
                                                <a title="${borrowLog.book.bookName}">
                                                    <img src="${pageContext.request.contextPath}/images/books/${borrowLog.book.image}"
                                                         alt=" " class="img-responsive">
                                                </a>
                                            </td>
                                            <td class="invert">${borrowLog.book.bookName}</td>
                                            <td class="invert">
                                                <input type="number" name="rating" max="5" min="1" step="0.25" required>
                                            </td>
                                            <td class="invert">${borrowLog.borrowDateStr}</td>
                                            <td class="invert">${borrowLog.dueDateStr}</td>
                                            <td class="invert">
                                                <div class="rem">
                                                    <input type="submit" value="归还本书">
                                                </div>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="7">尚未有书籍</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
    <!--//checkout-->
    <div class="section singlep_btm">
        <div class="container">
            <div class="new_arrivals">
                <h4 class="rad-txt">
                    <span class="abtxt1">根据和您相似的用户</span>
                    <span class="abtext">向您推荐</span>
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
    <!-- footer -->
    <jsp:include page="footer1.jsp"/>
</div>
<!-- //home -->

<!-- Common js -->
<script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<!--// Common js -->
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
<!-- //cart-js -->
<!--quantity-->
<script>
    $('.value-plus').on('click', function () {
        var divUpd = $(this).parent().find('.value'),
            newVal = parseInt(divUpd.text(), 10) + 1;
        divUpd.text(newVal);
    });

    $('.value-minus').on('click', function () {
        var divUpd = $(this).parent().find('.value'),
            newVal = parseInt(divUpd.text(), 10) - 1;
        if (newVal >= 1) divUpd.text(newVal);
    });
</script>
<!--quantity-->
<!-- FadeOut-Script -->
<%--<script>
    $(document).ready(function (ret) {
        $('.close1').on('click', function (c) {
            let id ="#"+$(c).attr("id");
            alert(id);
            /*$('.rem1').fadeOut('slow', function (c) {
                $('.rem1').remove();
            });*/
        });
    });
</script>--%>
<script>
    function del(ret) {
        let id = "#" + $(ret).attr("id");
        alert(id);
        $(id).fadeOut('slow', function (c) {
            $(id).remove();
        })
        let arr = ${sessionScope.reserve};
        let new_arr = [];
        for (let i = 0; i < arr.length; i++) {
            if ("#" + arr[i] !== id) {
                new_arr.push(arr[i]);
            }
        }
        window.location.href = 'checkoutAfter.do?afterDel=' + new_arr;
    }
</script>
<!--// FadeOut-Script -->

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
<!-- //end-smooth-scrolling -->
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
<!-- //smooth-scrolling-of-move-up -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>

</html>