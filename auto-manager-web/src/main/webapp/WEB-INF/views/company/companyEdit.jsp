<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<title>修改公司信息</title>
	<link rel="shortcut icon" href="hplus/favicon.ico">
	
	<link rel="stylesheet" type="text/css" href="cropper/css/font-awesome.4.6.0.css">
	<link rel="stylesheet" href="cropper/css/amazeui.min.css">
	<link rel="stylesheet" href="cropper/css/amazeui.cropper.css">
	<link rel="stylesheet" href="cropper/css/custom_up_img.css">
	
	<link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
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
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h3 class="m-t-none m-b">编辑公司信息</h3>
				</div>
				<div class="ibox-content">
					<button data-toggle="button" class="btn btn-primary user-update" type="button" onclick="window.history.go(-1);">返回</button>
					<div class="form-group col-lg-offset-3">
						<label>公司logo:</label>
						<div class="form-group" style="margin-left:1px">
							<c:if test="${empty company.companyLogo }">
								<img id="companyLogoImg" src="img/default.png" style="width:150px;height:150px;" />
							</c:if>
							<c:if test="${not empty company.companyLogo }">
								<img id="companyLogoImg" src="<%=res.getString("OSS_URL_PREFIX") %>${company.companyLogo }" style="width:150px;height:150px;" />
							</c:if>
                            <button id="up-img-touch" data-toggle="button" class="btn btn-primary" type="button" style="margin-top:122px;margin-left:5px">上传</button>
						</div>
					</div>
					<form role="form" id="update_form" class="form-horizontal col-lg-6 col-lg-offset-3">
						<div class="form-group">
							<label class="must-label">公司名称(必填):</label>
							<input type="text" placeholder="请输入公司名称" class="form-control" id="companyName" name="companyName" value="${company.companyName }" />
						</div>
						<div class="form-group">
							<label>联系电话:</label>
							<input type="text" placeholder="请输入联系电话" class="form-control" id="companyTel" name="companyTel" value="${company.companyTel }" />
						</div>
						<div class="form-group">
							<label>负责人:</label>
							<input type="text" placeholder="请输入负责人" class="form-control" id="companyPricipal" name="companyPricipal" value="${company.companyPricipal }" />
						</div>
						<div class="form-group">
							<label>负责人电话:</label>
							<input type="text" placeholder="请输入负责人电话" class="form-control" id="companyPricipalPhone" name="companyPricipalPhone" value="${company.companyPricipalPhone }" />
						</div>
						<div class="form-group">
							<label>官网:</label>
							<input type="text" placeholder="请输入官网" class="form-control" id="companyWebsite" name="companyWebsite" value="${company.companyWebsite }" />
						</div>
						<div class="form-group">
                            <label>公司成立时间:</label>
                            <div>
                                <input readonly type="text" class="form-control layer-date" id="strCompanyOpenDate" name="strCompanyOpenDate" value="<fmt:formatDate value="${company.companyOpenDate }" />" />
                                <label class="laydate-icon inline demoicon" style="vertical-align:top;" onclick="laydate({elem: '#strCompanyOpenDate'});"></label>
                            </div>
                        </div>
						<div class="form-group">
							<label>公司规模:</label>
							<select class="form-control m-b" id="companySize" name="companySize">
								<c:if test="${company.companySize == '小于20人' }">
									<option value="小于20人" selected="selected">小于20人</option>
								</c:if>
								<c:if test="${company.companySize != '小于20人' }">
									<option value="小于20人">小于20人</option>
								</c:if>
								<c:if test="${company.companySize == '20-50人' }">
									<option value="20-50人" selected="selected">20-50人</option>
								</c:if>
								<c:if test="${company.companySize != '20-50人' }">
									<option value="20-50人">20-50人</option>
								</c:if>
								<c:if test="${company.companySize == '50-100人' }">
									<option value="20-50人" selected="selected">20-50人</option>
								</c:if>
								<c:if test="${company.companySize != '50-100人' }">
									<option value="20-50人">20-50人</option>
								</c:if>
								<c:if test="${company.companySize == '50-100人' }">
									<option value="50-100人" selected="selected">50-100人</option>
								</c:if>
								<c:if test="${company.companySize != '50-100人' }">
									<option value="50-100人">50-100人</option>
								</c:if>
								<c:if test="${company.companySize == '100-500人' }">
									<option value="50-100人" selected="selected">100-500人</option>
								</c:if>
								<c:if test="${company.companySize != '100-500人' }">
									<option value="50-100人">100-500人</option>
								</c:if>
								<c:if test="${company.companySize == '500人以上' }">
									<option value="50-100人" selected="selected">500人以上</option>
								</c:if>
								<c:if test="${company.companySize != '500人以上' }">
									<option value="50-100人">500人以上</option>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label>公司地址:</label>
							<div class="form-inline">
								<input type="text" placeholder="请输入公司地址" class="form-control" style="width:85%;" id="companyAddress" name="companyAddress" value="${company.companyAddress }" />
								<a data-toggle="modal" class="btn btn-primary" style="margin-top:5px;" href="#baidu-map">点击设置地址</a>
							</div>
						</div>
						<div class="form-group">
							<label>公司描述:</label>
							<input type="text" placeholder="请输入公司描述" class="form-control" id="companyDes" name="companyDes" value="${company.companyDes }" />
						</div>
						<div class="form-group">
							<label>状态:</label>
							<select class="form-control m-b" id="companyStatus" name="companyStatus">
								<c:if test="${company.companyStatus == 'Y' }">
									<option value="Y" selected="selected">启用</option>
									<option value="N">禁用</option>
								</c:if>
								<c:if test="${company.companyStatus == 'N' }">
									<option value="Y">启用</option>
									<option value="N" selected="selected">禁用</option>
								</c:if>
							</select>
						</div>
						<input type="hidden" id="companyId" name="companyId" value="${company.companyId }" />
						<input type="hidden" id="companyLogo" name="companyLogo" value="${company.companyLogo }" />
						<input type="hidden" id="companyLongitude" name="companyLongitude" value="${company.companyLongitude }" />
						<input type="hidden" id="companyLatitude" name="companyLatitude" value="${company.companyLatitude }" />
						<input type="hidden" id="createTime" name="createTime" value="${company.createTime }" />
						<div>
							<button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" id="update_submit">
								<strong>保存</strong>
							</button>
							<button class="btn btn-sm btn-default pull-right m-t-n-xs" type="reset" id="insert_close">
								<strong>重置</strong>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!--图片上传框-->
	<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
	  <div class="am-modal-dialog up-frame-parent up-frame-radius">
		<div class="am-modal-hd up-frame-header">
		   <label>公司logo</label>
		  <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close id="close-modal">&times;</a>
		</div>
		<div class="am-modal-bd  up-frame-body">
		  <div class="am-g am-fl">
			<div class="am-form-group am-form-file">
			  <div class="am-fl">
				<button type="button" class="am-btn am-btn-default am-btn-sm">
				  <i class="am-icon-cloud-upload"></i>请选择要上传的图片</button>
			  </div>
			  <form id="uploadForm">
			  <input type="file" id="inputImage" name="companyLogoFile" />
			  </form>
			</div>
		  </div>
		  <div class="am-g am-fl" >
			<div class="up-pre-before up-frame-radius">
				<img alt="" src="" id="image" />
			</div>
			<div class="up-pre-after up-frame-radius">
			</div>
		  </div>
		  <div class="am-g am-fl">
			<div class="up-control-btns">
				<!-- <span class="am-icon-rotate-left"  onclick="rotateimgleft()"></span>
				<span class="am-icon-rotate-right" onclick="rotateimgright()"></span> -->
				<span class="am-icon-check" id="up-btn-ok" onclick="cutImage()"></span>
			</div>
		  </div>
		</div>
	  </div>
	</div>
	
	<div id="baidu-map" class="modal fade" aria-hidden="true">
        <div class="modal-dialog" style="width:860px;">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="m-t-none m-b">选择公司位置</h3>
                            <div class="form-inline">
	                            <input type="text" placeholder="请输入查询地址" class="form-control" style="width:35%" id="selectAddress" />
	                            <button type="button" class="btn btn-outline btn-primary" style="margin-top:-1px;" onclick="selectMap();">查询</button>
                            </div>
                            <div id="allmap" style="width:800px;height:500px;margin-top:10px;"></div>
                            <div class="form-group" style="margin-top:20px;">
                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" onclick="saveCompanyAddress();">
                                <strong>保存</strong>
                                </button>
                                <button class="btn btn-sm btn-default pull-right m-t-n-xs" type="button" onclick="closeMap();">
                                <strong>关闭</strong>
                                </button>
                                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="" id="close-modal">×</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="hplus/js/content.min.js?v=1.0.0"></script>
	<script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="hplus/js/plugins/validate/messages_zh.min.js"></script>
    <script src="hplus/js/plugins/layer/laydate/laydate.js"></script>
	<script src="cropper/js/amazeui.min.js" charset="utf-8"></script>
	<script src="cropper/js/cropper.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cnMRBlDKarxop95k7R2f0hm9Dxdg067P"></script>
	
	<script type="text/javascript" src="js/company/company.validate.js"></script>
	<script type="text/javascript" src="js/company/companyAdd.js"></script>
	<script type="text/javascript">
		// 图片裁剪
		function cutImage() {
			// 获取裁剪属性
			var data = $("#image").cropper("getData", true);
			// 获取原url
			var relativePath = $("#companyLogo").val();
			// 原url拼接裁剪参数
			relativePath += "?x-oss-process=image/crop,x_"+data.x+",y_"+data.y+",w_"+data.width+",h_"+data.height+"";
			// 重置保存数据库的相对路径
			$("#companyLogo").val(relativePath);
			var prefix = "<%=res.getString("OSS_URL_PREFIX")%>";
			// 把图片路径设置给img标签
			$("#companyLogoImg").attr("src", prefix + relativePath);
			// 关闭modal
			$("#close-modal").click();
			// 将图像和裁剪框重置为初始状态
			//$("#image").cropper("replace", "img/default.png", true);
			//$("#inputImage").val("");
		}
	</script>
</body>
</html>
