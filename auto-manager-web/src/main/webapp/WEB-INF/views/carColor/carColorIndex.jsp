<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>颜色管理</title>
    <link rel="shortcut icon" href="hplus/favicon.ico">
    <link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <!-- jqgrid-->
    <link href="hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">
    <link href="hplus/css/animate.min.css" rel="stylesheet">
    <link href="hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrapStyle/bootstrapStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="spectrum/spectrum.css">
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
                        <h5>颜色管理</h5>
                    </div>
                    <div class="ibox-content">
                       	<form class="form-inline">
                       		<label class="control-label">颜色名称:</label>
                        	<input type="text" placeholder="请输入颜色名称" class="form-control" id="selectColorName" />
							<label class="control-label">状态:</label>
                        	<select class="form-control m-b" style="width:100px;margin-top:15px" id="selectColorStatus">
                        		<option value="">请选择</option>
								<option value="Y">启用</option>
								<option value="N">禁用</option>
							</select>
							<button type="button" class="btn btn-outline btn-primary select_form">查询</button>
							<button type="reset" class="btn btn-outline btn-primary">重置</button>
                       	</form>
						<div class="form-group">
							<a data-toggle="modal" class="btn btn-primary" href="#insert-form">新增</a>
							<button data-toggle="button" class="btn btn-primary carColor-update" type="button">修改</button>
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
                            <h3 class="m-t-none m-b">新增颜色</h3>
                            <form role="form" id="insertForm">
                                <div class="form-group">
                                    <label class="must-label">颜色名称(必填):</label>
                                    <input type="text" placeholder="请输入颜色名称" class="form-control" id="colorName" name="colorName" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">颜色rgb(必填):</label>
                                    <input type="text" placeholder="请选择颜色rgb" readonly="readonly" class="form-control" id="colorRGB" name="colorRGB" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">颜色hex(必填):</label>
                                    <div class="form-inline">
	                                    <input type="text" placeholder="请选择颜色hex" readonly="readonly" class="form-control" style="width:85%" id="colorHex" name="colorHex" />
	                                	<input id="insert_full" />
                                	</div>
                                </div>
                                <div class="form-group">
                                    <label>颜色描述:</label>
                        			<textarea id="colorDes" name="colorDes" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="colorStatus" name="colorStatus">
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
                            <h3 class="m-t-none m-b">编辑颜色</h3>
                            <form role="form" id="editForm">
                            	<input type="hidden" id="editColorId" name="colorId" />
								<div class="form-group">
                                    <label class="must-label">颜色名称(必填):</label>
                                    <input type="text" placeholder="请输入颜色名称" class="form-control" id="editColorName" name="colorName" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">颜色rgb(必填):</label>
                                    <input type="text" placeholder="请选择颜色rgb" readonly="readonly" class="form-control" id="editColorRGB" name="colorRGB" />
                                </div>
                                <div class="form-group">
                                    <label class="must-label">颜色hex(必填):</label>
                                    <div class="form-inline">
	                                    <input type="text" placeholder="请选择颜色hex" readonly="readonly" class="form-control" style="width:85%" id="editColorHex" name="colorHex" />
	                                	<input id="edit_full" />
                                	</div>
                                </div>
                                <div class="form-group">
                                    <label>颜色描述:</label>
                        			<textarea id="editColorDes" name="colorDes" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="editColorStatus" name="colorStatus">
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
    
    <script src="hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="hplus/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="hplus/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
    <script src="hplus/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
    <script src="hplus/js/content.min.js?v=1.0.0"></script>
    <script src="hplus/js/plugins/layer/laydate/laydate.js"></script>
    <script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="hplus/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript" src="spectrum/spectrum.js"></script>
	
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/carColor/carColor.js"></script>
    <script type="text/javascript" src="js/carColor/carColor.validate.js"></script>
</body>
</html>
