$(function() {
	// 新增表单校验
	$("#user_insert_form").validate({
		onfocusout : function(element) {
			$(element).valid();
		},
		rules : {
			userEmail : {
				required : true,
				email : true,
				remote : {
					type : "POST",
					url : "user/getByEmail",
					data : {
						userEmail : function() {
				        	return $("#userEmail").val();
			            }
					},
					dataFilter : function(data) {
						if (data == "true") {
							return false;
						}
						return true
					}
				}
			},
			userPhone : {
				required : true,
				rangelength : [11, 11]
			},
			userPwd : {
				required : true,
				min : 6
			},
			userPwd2 : {
				required : true,
				equalTo : "#userPwd"
			},
			userIdentity : {
				rangelength : [18, 18]
			},
		},
		messages : {
			userEmail : {
				required : "请输入邮箱",
				email : "请输入正确的邮箱",
				remote : "邮箱已被注册"
			},
			userPhone : {
				required : "请输入手机号码",
				rangelength : "请输入正确的手机号码"
			},
			userPwd : {
				required : "请输入密码",
				min : "密码长度最小为6位"
			},
			userPwd2 : {
				required : "请确认密码",
				equalTo : "两次密码不一致"
			},
			userIdentity : {
				rangelength : "请输入正确的身份证号码",
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