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
    <title>Chronicle Education Category Bootstrap Responsive website Template | Shop Catalogue :: w3layouts</title>
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
    <!-- Range-slider-css -->
    <link rel="stylesheet" type="text/css" href="css/jquery-ui1.css">
    <!-- common-css -->
    <link href="${pageContext.request.contextPath}/css/front-end-style.css" type="text/css" rel="stylesheet"
          media="all">
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.desoslide.css">

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
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<div id="home">
    <!-- header -->
    <!-- navbar -->
    <jsp:include page="header.jsp"/>
    <!-- navbar ends here -->
    <!-- banner -->
    <div class="banner-bg-agileits">
        <!-- banner-text -->
        <div class="banner-text">
            <div class="container">
                <p class="b-txt"> 欢迎使用 </p>
                <h2 class="title">
                    电子图书馆
                </h2>
                <ul class="banner-txt">
                    <li>勤奋学习</li>
                    <li>实事求是</li>
                    <li>团结创新</li>
                </ul>
            </div>
        </div>
        <!-- banner-text -->
    </div>
    <!-- banner -->
    <!-- about -->
    <div class="about-sec section" id="about">
        <div class="container">
            <!-- about-left-grid -->
            <div class="col-md-7 about-left-grid">
                <div class="inner-about2">
                    <h4>图书馆读者守则</h4>
                    <p>图书馆是人员密集型场所，也消防安全重点单位，希望广大读者能够理解并配合我们的工作，共同为建设安全舒适的阅读环境而努力。</p>
                    <h5>读者须知</h5>
                    <ul class="about-list">
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>一、读者进入图书馆应注意言谈举止文明，衣着整齐
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>二、馆内严禁吸烟、保持安静，入馆后请将手机调至静音状态
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>三、未经同意，不得在馆内举办集会、娱乐等活动
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>四、爱护卫生、禁止随地吐痰、乱扔废弃物，文明使用卫生间
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>五、禁止在馆内借阅区域吃东西，严禁将剩水、茶和饮料泼洒地面
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>六、严禁随意涂抹刻画和破坏设备，严禁张帖、散发广告及其它宣传品
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>七、自觉维护馆内秩序，不抢占座位、书包柜等，不随意挪动阅览桌椅
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>八、离馆时，如遇出口监测仪报警，请配合检查
                        </li>
                        <li>
                            <i class="fa fa-play-circle-o" aria-hidden="true"></i>九、自觉遵守本馆的各项规章制度，支持工作人员按章办事
                        </li>

                    </ul>
                </div>
                <!-- about left bottom - services -->
                <!-- about left bottom - services ends here -->
            </div>
            <!-- about-left-grid ends here-->
            <!-- about-right-grid -->
            <div class="col-md-5 about-right-grid"></div>
            <!-- about-right-grid ends here -->
            <div class="clearfix"></div>
        </div>
    </div>
    <div id="section_demo" class="section">
        <div class="container">
            <h4 class="rad-txt">
                <span class="abtxt1">图书馆</span>
                <span class="abtext">展览</span>
            </h4>
            <div class="row">
                <!-- Result 1 -->
                <div class="col-lg-12 col-md-12">
                    <div class="row">
                        <div id="slideshow_1_thumbs_1" class="col-lg-2 col-md-2">
                            <ul class="slideshow1_thumbs desoslide-thumbs-vertical list-inline text-center">
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l3.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt3.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l2.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt2.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l5.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt5.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l4.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt4.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div id="slideshow1" class="col-lg-8 col-md-8"></div>
                        <div id="slideshow_1_thumbs_2" class="col-lg-2 col-md-2">
                            <ul class="slideshow1_thumbs desoslide-thumbs-vertical list-inline">
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l1.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt1.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l6.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt6.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l7.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt7.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/images/l8.jpg">
                                        <img src="${pageContext.request.contextPath}/images/lt8.jpg"
                                             alt="chronicle_image" data-desoslide-caption-title="图书馆展览"
                                             class="img-responsive">
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <br/>
        </div>
    </div>
    <!-- footer -->
    <jsp:include page="footer1.jsp"/>
    <!-- footer -->
</div>
<!-- home -->
<!-- js -->
<script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<!-- //js -->
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
<!-- gallery desoslide -->
<script src="${pageContext.request.contextPath}/js/jquery.desoslide.min.js"></script>
<script src="${pageContext.request.contextPath}/js/demo.js"></script>
<!-- gallery desoslide -->
<!-- Scrolling Nav JavaScript -->
<script src="${pageContext.request.contextPath}/js/scrolling-nav.js"></script>
<!-- //fixed-scroll-nav-js -->
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
<!-- about bottom grid Numscroller -->
<script src="${pageContext.request.contextPath}/js/numscroller-1.0.js"></script>
<!-- //about bottom grid Numscroller -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>

</html>