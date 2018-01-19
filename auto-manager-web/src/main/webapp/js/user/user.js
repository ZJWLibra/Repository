$(function() {
	var colNames = ["id", "邮箱", "手机号码", "昵称", "真实姓名", "性别", "生日", "头像", "状态" ];
	var colModel = [{
		name : "userId",
		index : "userId",
		hidden : true
	}, {
		name : "userEmail",
		index : "userEmail",
		width : 260
	}, {
		name : "userPhone",
		index : "userPhone",
	}, {
		name : "userNickname",
		index : "userNickname",
	}, {
		name : "userName",
		index : "userName",
	}, {
		name : "userGender",
		index : "userGender",
		width : 60,
		formatter : function(value, options, rowData) {
			if (value == "G") {
				return "男";
			} else {
				return "女";
			}
		}
	}, {
		name : "userBirthday",
		index : "userBirthday",
		formatter : "date"
	}, {
		name : "userIcon",
		index : "userIcon",
		sortable : false,
		formatter : function(value, options, rowData) {
			return "<img src='" + value + "' width='50' height='50' />";
		}
	}, {
		name : "userStatus",
		index : "userStatus",
		width : 80,
		sortable : false,
		formatter : function(value, options, rowData) {
			if (value == "Y") {
				return "启用";
			} else {
				return "禁用";
			}
		}
	} ];
	
	pageInit("user_table", "用户列表", "user/list", colNames, colModel);
	
	// 多选框赋值
	$("#user_table").jqGrid('setGridParam',{ 
		gridComplete : function() {
			var rowIds = jQuery("#user_table").jqGrid("getDataIDs");
			
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#user_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_user_table_" + (i + 1));
				curChk.attr("name", "userId");
				curChk.attr("value", curRowData.userId);
			}
		}
    })

    // 删除
	$(".user-remove").click(function() {
		swal({
			title : "您确定要进行删除操作吗",
			text : "删除后将无法恢复，请谨慎操作！",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "删除",
			cancelButtonText : "取消",
			closeOnConfirm : false
		}, function() {
			var ids = []; 
			
			// 获取勾选数据
    		$('input:checkbox[name=userId]:checked').each(function(value) {
    			ids.push($(this).val()); 
    		});
			
    		// 判断是否有勾选
    		if (ids.length == 0) {
    			swal("请选择要删除的数据");
    			return;
    		}
    		
    		$.ajax({
				url : "user/delete",
				type : "POST",
				dataType : "JSON",
				traditional : true,
				data : {"ids" : ids},
				success : function(data) {
					if (data.status == 200) {
						swal("成功", "数据已删除", "success");
						
						// 重新加载数据
						$("#user_table").jqGrid('setGridParam', {  
                            datatype : "json",  
                            url : "user/list"
                        }).trigger("reloadGrid");
					} else {
						swal("失败", data.msg);
					}
				}
			});
			
		});
	});

	// 查询
	$(".form_select").click(function() {
		var userGender = $("#user_gender").val();
		var userStatus = $("#user_status").val();
		$("#user_table").jqGrid('setGridParam', {
			url : "user/list",
			postData : {
				"userGender" : userGender,
				"userStatus" : userStatus
			},
			page : 1
		}).trigger("reloadGrid");
	});
	
	// 关闭新增表单
	$("#insert_close").click(function() {
		$("#insert-form").modal("hide");
	});
	
	// 新增
	$("#insert_submit").click(function() {
		if (!$("#user_insert_form").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "user/insert",
			data : $("#user_insert_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#user_table").jqGrid('setGridParam', {
	    				url : "user/list",
	    				page : 1
	    			}).trigger("reloadGrid");
					// 清空表单
					$("#user_insert_form")[0].reset();
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
	
	// 关闭编辑表单
	$("#edit_close").click(function() {
		$("#edit-form").modal("hide");
	});
		
	// 弹出修改表单
	$(".user-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$('input:checkbox[name=userId]:checked').each(function(value) {
			ids.push($(this).val()); 
		});
		
		// 判断是否有勾选
		if (ids.length == 0) {
			swal("请选择要修改的用户");
			return;
		} else if (ids.length > 1) {
			swal("只能选择一个用户");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "user/getById",
			data : {"userId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#edit_userId").val(data.userId);
				$("#edit_userEmail").val(data.userEmail);
				$("#edit_userPhone").val(data.userPhone);
				$("#edit_userPwd").val(data.userPwd);
				$("#edit_userSalt").val(data.userSalt);
				$("#edit_userIcon").val(data.userIcon);
				$("#edit_userCreatedTime").val(data.userCreatedTime);
				$("#edit_userLoginedTime").val(data.userLoginedTime);
				
				$("#edit_userNickname").val(data.userNickname);
				$("#edit_userIdentity").val(data.userIdentity);
				$("#edit_userName").val(data.userName);
				$("#edit_strUserBirthday").val(data.userBirthday);
				$("#edit_userAddress").val(data.userAddress);
				$("#edit_userAddress").val(data.userAddress);
				$("#edit_userAddress").val(data.userAddress);
				$("#edit_qqOpenId").val(data.qqOpenId);
				$("#edit_wechatOpenId").val(data.wechatOpenId);
				$("#edit_weiboOpenId").val(data.weiboOpenId);
				$("#edit_userDes").val(data.userDes);
				$("#edit_userSalary").val(data.userSalary);
				
				// 性别判断
				if (data.userGender == "G") {
					$("#edit_userGender1").attr("checked","checked");
				} else if (data.userGender == "M") {
					$("#edit_userGender2").attr("checked","checked");
				}
				
				// 公司判断
				$("#edit_companyId").find("option[value='" + data.companyId + "']").attr("selected", true);
				
				// 状态判断
				$("#edit_userStatus").find("option[value='" + data.userStatus + "']").attr("selected", true);

				$("#edit-form").modal("show");
			}
		});
	});
	
	// 修改用户信息
	$("#edit_submit").click(function() {
		if (!$("#user_edit_form").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "user/update",
			data : $("#user_edit_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#user_table").jqGrid('setGridParam', {
	    				url : "user/list",
	    				page : 1
	    			}).trigger("reloadGrid");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
	
});