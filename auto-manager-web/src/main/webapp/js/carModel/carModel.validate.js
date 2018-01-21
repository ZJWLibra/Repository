$(function() {
	// 新增表单校验
	$("#insertForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			modelName : {
				required : true,
			}
		},
		messages : {
			modelName : {
				required : "请输入车型名称",
			}
		}
	});
	
	// 编辑表单校验
	$("#editForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			modelName : {
				required : true,
			}
		},
		messages : {
			modelName : {
				required : "请输入车型名称",
			}
		}
	});
});