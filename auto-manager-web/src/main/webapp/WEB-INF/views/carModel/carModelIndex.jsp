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
    <title>车型管理</title>
    <link rel="shortcut icon" href="hplus/favicon.ico">
    <link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
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
                        <h5>车型管理</h5>
                    </div>
                    <div class="ibox-content">
                       	<form class="form-inline">
                       		<label class="control-label">车型名称:</label>
                        	<input type="text" placeholder="请输入车型名称" class="form-control" id="selectModelName" />
                        	<label class="control-label">所属品牌:</label>
                        	<select class="form-control m-b" style="width:100px;margin-top:15px" id="selectBrandId">
                        		<option value="">请选择</option>
                        		<c:forEach items="${carBrandList }" var="carBrand">
                        			<option value="${carBrand.brandId }">${carBrand.brandName }</option>
                        		</c:forEach>
							</select>
							<label class="control-label">状态:</label>
                        	<select class="form-control m-b" style="width:100px;margin-top:15px" id="selectModelStatus">
                        		<option value="">请选择</option>
								<option value="Y">启用</option>
								<option value="N">禁用</option>
							</select>
							<button type="button" class="btn btn-outline btn-primary select_form">查询</button>
							<button type="reset" class="btn btn-outline btn-primary">重置</button>
                       	</form>
						<div class="form-group">
							<a data-toggle="modal" class="btn btn-primary" href="#insert-form">新增</a>
							<button data-toggle="button" class="btn btn-primary carModel-update" type="button">修改</button>
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
                            <h3 class="m-t-none m-b">新增车型</h3>
                            <form role="form" id="insertForm">
                                <div class="form-group">
                                    <label class="must-label">车型名称(必填):</label>
                                    <input type="text" placeholder="请输入名称" class="form-control" id="modelName" name="modelName" />
                                </div>
                                <div class="form-group">
                                    <label>所属品牌:</label>
                                    <select class="form-control m-b" id="brandId" name="brandId">
		                        		<option value="">请选择</option>
		                        		<c:forEach items="${carBrandList }" var="carBrand">
		                        			<option value="${carBrand.brandId }">${carBrand.brandName }</option>
		                        		</c:forEach>
									</select>
                                </div>
                                <div class="form-group">
                                    <label>车型描述:</label>
                        			<textarea id="modelDes" name="modelDes" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="modelStatus" name="modelStatus">
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
                            <h3 class="m-t-none m-b">编辑车型</h3>
                            <form role="form" id="editForm">
                            	<input type="hidden" id="editModelId" name="modelId" />
                                <div class="form-group">
                                    <label class="must-label">车型名称(必填):</label>
                                    <input type="text" placeholder="请输入名称" class="form-control" id="editModelName" name="modelName" />
                                </div>
                                <div class="form-group">
                                    <label>所属品牌:</label>
                                    <select class="form-control m-b" id="editBrandId" name="brandId">
		                        		<option value="">请选择</option>
		                        		<c:forEach items="${carBrandList }" var="carBrand">
		                        			<option value="${carBrand.brandId }">${carBrand.brandName }</option>
		                        		</c:forEach>
									</select>
                                </div>
                                <div class="form-group">
                                    <label>车型描述:</label>
                        			<textarea id="editModelDes" name="modelDes" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <select class="form-control m-b" id="editModelStatus" name="modelStatus">
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
    
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/carModel/carModel.js"></script>
    <script type="text/javascript" src="js/carModel/carModel.validate.js"></script>
</body>
</html>
