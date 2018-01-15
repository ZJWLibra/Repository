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

    <title>用户管理</title>

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
                        <h5>品牌管理</h5>
                    </div>
                    <div class="ibox-content">
                       	<form class="form-inline">
                       		<label class="control-label">品牌名称:</label>
                        	<input type="text" placeholder="请输入品牌名称" class="form-control" id="selectBrandName" />
							<label class="control-label">状态:</label>
                        	<select class="form-control m-b" style="width:100px;margin-top:15px" id="selectBrandStatus">
                        		<option value="">请选择</option>
								<option value="Y">启用</option>
								<option value="N">停用</option>
							</select>
							<button type="button" class="btn btn-outline btn-primary select_form">查询</button>
							<button type="reset" class="btn btn-outline btn-primary">重置</button>
                       	</form>
						<div class="form-group">
							<a class="btn btn-primary" href="carBrand/toAdd">新增</a>
							<button data-toggle="button" class="btn btn-primary carBrand-update" type="button">修改</button>
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
    
    <!-- 编辑表单 -->
    <div id="edit-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="m-t-none m-b">编辑用户</h3>
                            <form role="form" id="user_edit_form">
                            	<input type="hidden" id="edit_userId" name="userId" />
                            	<input type="hidden" id="edit_userEmail" name="userEmail" />
                            	<input type="hidden" id="edit_userPhone" name="userPhone" />
                            	<input type="hidden" id="edit_userPwd" name="userPwd" />
                            	<input type="hidden" id="edit_userSalt" name="userSalt" />
                            	<input type="hidden" id="edit_userIcon" name="userIcon" />
                            	<input type="hidden" id="edit_userCreatedTime" name="strUserCreatedTime" />
                            	<input type="hidden" id="edit_userLoginedTime" name="strUserLoginedTime" />
                                <div class="form-group">
                                    <label>昵称:</label>
                                    <input type="text" placeholder="请输入昵称" class="form-control" id="edit_userNickname" name="userNickname" />
                                </div>
                                <div class="form-group">
                                    <label>身份证号码:</label>
                                    <input type="text" placeholder="请输入身份证号码" class="form-control" id="edit_userIdentity" name="userIdentity" />
                                </div>
                                <div class="form-group">
                                    <label>真实姓名:</label>
                                    <input type="text" placeholder="请输入真实姓名" class="form-control" id="edit_userName" name="userName" />
                                </div>
                                <div class="form-group">
                                    <label>性别:</label>
                                    <label class="form-inline" style="margin-left:10px">
										男&nbsp;&nbsp;<input type="radio" id="edit_userGender1" name="userGender" value="G" />
									</label>
									<label class="form-inline" style="margin-left:10px">
										女&nbsp;&nbsp;<input type="radio" id="edit_userGender2" name="userGender" value="M" />
									</label>
                                </div>
                                <div class="form-group">
                                    <label>生日:</label>
                                    <div>
                                        <input readonly type="text" class="form-control layer-date" id="edit_strUserBirthday" name="strUserBirthday" />
                                        <label class="laydate-icon inline demoicon" onclick="laydate({elem: '#edit_strUserBirthday'});"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>地址:</label>
                                    <input type="text" placeholder="请输入地址" class="form-control" id="edit_userAddress" name="userAddress" />
                                </div>
                                <div class="form-group">
                                    <label>QQ:</label>
                                    <input type="text" placeholder="请输入QQ" class="form-control" id="edit_qqOpenId" name="qqOpenId" />
                                </div>
                                <div class="form-group">
                                    <label>微信:</label>
                                    <input type="text" placeholder="请输入微信" class="form-control" id="edit_wechatOpenId" name="wechatOpenId" />
                                </div>
                                <div class="form-group">
                                    <label>微博:</label>
                                    <input type="text" placeholder="请输入微博" class="form-control" id="edit_weiboOpenId" name="weiboOpenId" />
                                </div>
                                <div class="form-group">
                                    <label>描述:</label>
                                    <input type="text" placeholder="请输入描述" class="form-control" id="edit_userDes" name="userDes" />
                                </div>
                                <div class="form-group">
                                    <label>所属公司:</label>
                                    <select class="form-control m-b" id="edit_companyId" name="companyId">
		                        		<option value="">请选择</option>
									</select>
                                </div>
                                <div class="form-group">
                                    <label>基本工资:</label>
                                    <input type="text" placeholder="请输入基本工资" class="form-control" id="edit_userSalary" name="userSalary" />
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="edit_userStatus" name="userStatus">
										<option value="Y">启用</option>
										<option value="N">停用</option>
									</select>
                                </div>
                                <div>
                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" id="edit_submit">
                                    <strong>保存</strong>
                                    </button>
                                    <button class="btn btn-sm btn-default pull-right m-t-n-xs" type="button" id="edit_close">
                                    <strong>关闭</strong>
                                    </button>
                                </div>
                            </form>
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
    <script type="text/javascript" src="js/carBrand/carBrand.js"></script>
</body>
</html>
