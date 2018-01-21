<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>登录</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="hplus/css/animate.min.css" rel="stylesheet">
    <link href="hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用 H+</h3>

            <form class="m-t" role="form" action="user/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required="" name="username" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" name="password" />
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>

            </form>
            ${message }
        </div>
    </div>
    <script src="hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
