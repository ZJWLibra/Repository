$(function() {
	// 新增表单校验
	$("#insert_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			brandName : {
				required : true,
			}
		},
		messages : {
			brandName : {
				required : "请输入品牌名称",
			}
		}
	});
	
	// 编辑表单校验
	$("#update_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			brandName : {
				required : true,
			}
		},
		messages : {
			brandName : {
				required : "请输入品牌名称",
			}
		}
	});
});