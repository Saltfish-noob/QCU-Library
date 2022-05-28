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
</head>


<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<div id="home" >
    <!-- header -->
    <!-- navbar -->
    <jsp:include page="header.jsp"/>
    <!-- //navbar ends here -->
    <!-- banner -->
    <div class="banner-bg-inner" style="margin-bottom: 50px">
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
    <div class="col-lg-12" style="background-color: white;border: 1px black; margin-bottom: 50px">
        <form action="${pageContext.request.contextPath}/user/alertUserpw.do" role="form" class="form-horizontal col-lg-6 col-lg-offset-3">
            <h3 class="text-center">修改密码</h3>
            <div class="form-group">
                <label for="new_pw">新密码：</label>
                <input class="form-control" id="new_pw" type="password" name="passwd" placeholder="请输入新密码" value=""  required/>
            </div>
            <div class="form-group">
                <label for="new_pw2">重复新密码：</label>
                <input class="form-control" id="new_pw2" type="password" name="passwd2" placeholder="请重新输入密码" value="" required/>
            </div>
            <span id="tishi"></span>
            <input style="margin-top: 20px" class="form-control btn btn-primary" type="submit" onclick="return checkPassword()" value="确定">
        </form>
    </div>
    <div class="clearfix"></div>
    <!-- footer -->
    <jsp:include page="footer1.jsp"/>
</div>
<!-- //home -->

<!-- Common js -->
<script type="text/javascript">
    function checkPassword() {
        let password = document.getElementById("new_pw").value;
        let rePassword = document.getElementById("new_pw2").value;
        if (password === rePassword) {
            return true;
        } else {
            document.getElementById("new_pw").value="";
            document.getElementById("new_pw2").value="";
            alert('两次密码不一致，请重新输入')
            return false;
        }
    }
</script>
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