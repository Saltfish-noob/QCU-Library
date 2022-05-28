<!--
Author: W3layouts
Author URL: http://w3layouts.com
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <title>Fog error Responsive Widget Template :: W3layouts</title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8" />
    <meta name="keywords"
        content="Fog error web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <!-- //Meta tag Keywords -->

    <!-- google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:400,700&display=swap" rel="stylesheet">

    <!-- //google fonts -->

    <!--/Style-CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/403-style.css" type="text/css" media="all" />
    <!--//Style-CSS -->

    <!--/fontAwesome-CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/403-fontAwesome.css" type="text/css" media="all" />
    <!--/fontAwesome-CSS -->
</head>

<body>
    <div class="w3l-error-block">

        <div class="page">
            <div class="content">
                <div class="logo">
                    <a class="brand-logo">Fog Error</a>
                    <!-- if logo is image enable this   
                <a class="brand-logo" href="#index.html">
                    <img src="image-path" alt="Your logo" title="Your logo" style="height:35px;" />
                </a> 
            -->
                </div>
                <div class="w3l-error-grid">
                    <h1>403</h1>
                    <h2>权限不足</h2>
                    <p>您未拥有进入页面的权限，如有疑问请联系系统管理员</p>
                </div>

            </div>
            <img src="${pageContext.request.contextPath}/images/403-bg.jpg" class="img-responsive" alt="error image" />
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script>
            var lFollowX = 0,
                lFollowY = 0,
                x = 0,
                y = 0,
                friction = 1 / 30;

            function animate() {
                x += (lFollowX - x) * friction;
                y += (lFollowY - y) * friction;

                translate = 'translate(' + x + 'px, ' + y + 'px) scale(1.1)';

                $('img').css({
                    '-webit-transform': translate,
                    '-moz-transform': translate,
                    'transform': translate
                });

                window.requestAnimationFrame(animate);
            }

            $(window).on('mousemove click', function (e) {

                var lMouseX = Math.max(-100, Math.min(100, $(window).width() / 2 - e.clientX));
                var lMouseY = Math.max(-100, Math.min(100, $(window).height() / 2 - e.clientY));
                lFollowX = (20 * lMouseX) / 100; // 100 : 12 = lMouxeX : lFollow
                lFollowY = (10 * lMouseY) / 100;

            });

            animate();
        </script>
    </div>
</body>

</html>