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
	$("#user_edit_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			userIdentity : {
				rangelength : [18, 18]
			},
		},
		messages : {
			userIdentity : {
				rangelength : "请输入正确的身份证号码",
			}
		}
	});
});