<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>角色管理</title>

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
                        <h5>角色管理</h5>
                    </div>
                    <div class="ibox-content">
						<div class="form-group">
							<shiro:hasPermission name="role:insert">
							<a data-toggle="modal" class="btn btn-primary" href="#insert-form">新增</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="role:update">
							<button data-toggle="button" class="btn btn-primary role-update" type="button">修改</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="role:permission">
							<button data-toggle="button" class="btn btn-primary role-permission" type="button">授权</button>
							</shiro:hasPermission>
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
    
    <!-- 新增表单 -->
    <div id="insert-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="m-t-none m-b">新增角色</h3>
                            <form id="insertForm">
                                <div class="form-group">
                                    <label class="must-label">角色名称(必填):</label>
                                    <input type="text" placeholder="请输入名称" class="form-control" id="roleName" name="roleName" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">角色描述(必填):</label>
                                    <input type="text" placeholder="请输入描述" class="form-control" id="roleDes" name="roleDes" />
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="roleStatus" name="roleStatus">
										<option value="Y">启用</option>
										<option value="N">禁用</option>
									</select>
                                </div>
                                <div>
                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" id="insert_submit">
                                    <strong>保存</strong>
                                    </button>
                                    <button class="btn btn-sm btn-default pull-right m-t-n-xs" type="button" id="insert_close">
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
    
    <!-- 编辑表单 -->
    <div id="edit-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="m-t-none m-b">编辑角色</h3>
                            <form role="form" id="editForm">
                            	<input type="hidden" id="editRoleId" name="roleId" />
                                <div class="form-group">
                                    <label class="must-label">角色名称(必填):</label>
                                    <input type="text" placeholder="请输入名称" class="form-control" id="editRoleName" name="roleName" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">角色描述(必填):</label>
                                    <input type="text" placeholder="请输入描述" class="form-control" id="editRoleDes" name="roleDes" />
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="editRoleStatus" name="roleStatus">
										<option value="Y">启用</option>
										<option value="N">禁用</option>
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
    
    <!-- 权限菜单 -->
    <div id="permission_modal" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width:400px">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="m-t-none m-b">授权</h3>
                            <input type="hidden" id="userId" />
                            <div class="content_wrap">
                            	<input type="hidden" id="roleId" />
	                            <div class="zTreeDemoBackground left">
									<ul id="ztree" class="ztree"></ul>
								</div>
							</div>
                        </div>
                    </div>
					<div>
						<button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" id="permission_submit">
							<strong>保存</strong>
						</button>
						<button class="btn btn-sm btn-default pull-right m-t-n-xs" type="button" id="permission_close">
							<strong>关闭</strong>
						</button>
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
    <script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="hplus/js/plugins/layer/laydate/laydate.js"></script>
    <script src="hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="hplus/js/plugins/validate/messages_zh.min.js"></script>
	
	<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/role/role.js"></script>
    <script type="text/javascript" src="js/role/role.validate.js"></script>
    <script type="text/javascript" src="js/role/role.tree.js"></script>
    
    <!-- ztree -->
    <script type="text/javascript" src="js/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="js/ztree/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="js/ztree/jquery.ztree.exedit.js"></script>
    
</body>
</html>
