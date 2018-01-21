<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
	String path = request.getContextPath();
	ResourceBundle res = ResourceBundle.getBundle("config/oss");
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>公司信息管理</title>

    <link rel="shortcut icon" href="hplus/favicon.ico"> <link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <!-- jqgrid-->
    <link href="hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">

    <link href="hplus/css/animate.min.css" rel="stylesheet">
    <link href="hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    
    <link rel="stylesheet" href="css/bootstrapStyle/bootstrapStyle.css" type="text/css">

    <style type="text/css">
        #alertmod_table_list_2 {
            top: 900px !important;
        }
        .must-label {
        	color : #ed5565;
        }
    </style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>公司信息管理</h5>
                    </div>
                    <div class="ibox-content">
                       	<form class="form-inline">
                       		<label class="control-label">公司名称:</label>
                        	<input type="text" placeholder="请输入公司名称" class="form-control" id="selectCompanyName" />
							<label class="control-label">状态:</label>
                        	<select class="form-control m-b" style="width:100px;margin-top:15px" id="selectCompanyStatus">
                        		<option value="">请选择</option>
								<option value="Y">启用</option>
								<option value="N">停用</option>
							</select>
							<button type="button" class="btn btn-outline btn-primary select_form">查询</button>
							<button type="reset" class="btn btn-outline btn-primary">重置</button>
                       	</form>
						<div class="form-group">
							<a class="btn btn-primary" href="company/toAdd">新增</a>
							<button data-toggle="button" class="btn btn-primary company-update" type="button">修改</button>
						</div>
                        <div class="jqGrid_wrapper">
                            <table id="grid_table"></table>
                            <div id="table_page"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="hplus/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="hplus/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
    <script src="hplus/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
    <script src="hplus/js/content.min.js?v=1.0.0"></script>
    <script src="hplus/js/plugins/layer/laydate/laydate.js"></script>
    <script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
    
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/company/company.js"></script>
</body>
</html>
