<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<title>品牌新增</title>
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
					<h3 class="m-t-none m-b">新增品牌</h3>
				</div>
				<div class="ibox-content">
					<button data-toggle="button" class="btn btn-primary user-update" type="button" onclick="window.history.go(-1);">返回</button>
					<div class="form-group col-lg-offset-3">
						<label>品牌logo:</label>
						<div class="form-group" style="margin-left:1px">
							<img id="brandLogoImg" src="img/default.png" style="width:150px;height:150px;" />
                            <button id="up-img-touch" data-toggle="button" class="btn btn-primary" type="button" style="margin-top:122px;margin-left:5px">上传</button>
						</div>
					</div>
					<form role="form" id="insert_form" class="form-horizontal col-lg-6 col-lg-offset-3">
						<div class="form-group">
							<label class="must-label">品牌名称(必填):</label>
							<input type="text" placeholder="请输入品牌名称" class="form-control" id="brandName" name="brandName" />
						</div>
						<div class="form-group">
							<label>品牌描述:</label>
							<input type="text" placeholder="请输入品牌描述" class="form-control" id="brandDes" name="brandDes" />
						</div>
						<div class="form-group">
							<label>状态:</label>
							<select class="form-control m-b" id="brandStatus" name="brandStatus">
								<option value="Y">启用</option>
								<option value="N">停用</option>
							</select>
						</div>
						<input type="hidden" id="brandLogo" name="brandLogo" />
						<div>
							<button class="btn btn-sm btn-primary pull-right m-t-n-xs" style="margin-left:5px" type="button" id="insert_submit">
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
		   <label>品牌logo</label>
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
			  <input type="file" id="inputImage" name="brandLogoFile" />
			  </form>
			</div>
		  </div>
		  <div class="am-g am-fl" >
			<div class="up-pre-before up-frame-radius">
				<img alt="" src="" id="image" >
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

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="hplus/js/content.min.js?v=1.0.0"></script>
	<script src="hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="hplus/js/plugins/validate/messages_zh.min.js"></script>
    
	<script src="cropper/js/amazeui.min.js" charset="utf-8"></script>
	<script src="cropper/js/cropper.min.js" charset="utf-8"></script>
	
	<script type="text/javascript" src="js/carBrand/carBrand.validate.js"></script>
	<script type="text/javascript" src="js/carBrand/carBrandAdd.js"></script>
	<script type="text/javascript">
		// 图片裁剪
		function cutImage() {
			// 获取裁剪属性
			var data = $("#image").cropper("getData", true);
			// 获取原url
			var relativePath = $("#brandLogo").val();
			// 原url拼接裁剪参数
			relativePath += "?x-oss-process=image/crop,x_"+data.x+",y_"+data.y+",w_"+data.width+",h_"+data.height+"";
			// 重置保存数据库的相对路径
			$("#brandLogo").val(relativePath);
			var prefix = "<%=res.getString("OSS_URL_PREFIX")%>";
			// 把图片路径设置给img标签
			$("#brandLogoImg").attr("src", prefix + relativePath);
			// 关闭modal
			$("#close-modal").click();
			// 将图像和裁剪框重置为初始状态
			//$("#image").cropper("replace", "img/default.png", true);
			//$("#inputImage").val("");
		}
	</script>
</body>
</html>
