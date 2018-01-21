$(function() {
	// 新增表单校验
	$("#insertForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			roleName : {
				required : true,
			},
			roleDes : {
				required : true,
			}
		},
		messages : {
			roleName : {
				required : "请输入名称",
			},
			roleDes : {
				required : "请输入描述",
			}
		}
	});
	
	// 编辑表单校验
	$("#editForm").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			roleName : {
				required : true,
			},
			roleDes : {
				required : true,
			}
		},
		messages : {
			roleName : {
				required : "请输入名称",
			},
			roleDes : {
				required : "请输入描述",
			}
		}
	});
});