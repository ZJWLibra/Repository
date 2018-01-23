$(function() {
	// 新增表单校验
	$("#insertForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			plateName : {
				required : true,
			}
		},
		messages : {
			plateName : {
				required : "请输入车牌名称",
			}
		}
	});
	
	// 编辑表单校验
	$("#editForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			plateName : {
				required : true,
			}
		},
		messages : {
			plateName : {
				required : "请输入车牌名称",
			}
		}
	});
});