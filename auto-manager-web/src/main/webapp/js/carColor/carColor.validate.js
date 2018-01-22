$(function() {
	// 新增表单校验
	$("#insertForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			colorName : {
				required : true,
			},
			colorRGB : {
				required : true,
			},
			colorHex : {
				required : true,
			}
		},
		messages : {
			colorName : {
				required : "请输入颜色名称",
			},
			colorRGB : {
				required : "请选择颜色",
			},
			colorHex : {
				required : "请选择颜色",
			}
		}
	});
	
	// 编辑表单校验
	$("#editForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			colorName : {
				required : true,
			},
			colorRGB : {
				required : true,
			},
			colorHex : {
				required : true,
			}
		},
		messages : {
			colorName : {
				required : "请输入颜色名称",
			},
			colorRGB : {
				required : "请选择颜色",
			},
			colorHex : {
				required : "请选择颜色",
			}
		}
	});
});