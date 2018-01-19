$(function() {
	// 新增表单校验
	$("#insert_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			companyName : {
				required : true,
			}
		},
		messages : {
			companyName : {
				required : "请输入公司名称",
			}
		}
	});
	
	// 编辑表单校验
	$("#update_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			companyName : {
				required : true,
			}
		},
		messages : {
			companyName : {
				required : "请输入公司名称",
			}
		}
	});
});