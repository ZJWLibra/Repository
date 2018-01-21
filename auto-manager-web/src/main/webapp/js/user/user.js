$(function() {
	var colNames = [ "id", "邮箱", "手机号码", "昵称", "真实姓名", "性别", "生日", "头像", "状态" ];
	var colModel = [ {
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

	pageInit("grid_table", "用户列表", "user/list", colNames, colModel);

	// 多选框赋值
	$("#grid_table").jqGrid('setGridParam', {
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");

			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
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
				swal("请选择");
				return;
			}

			$.ajax({
				url : "user/delete",
				type : "POST",
				dataType : "JSON",
				traditional : true,
				data : {
					"ids" : ids
				},
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
		var userGender = $("#selectUserGender").val();
		var userStatus = $("#selectUserStatus").val();
		$("#grid_table").jqGrid('setGridParam', {
			url : "user/list",
			postData : {
				"userGender" : userGender,
				"userStatus" : userStatus
			},
			page : 1
		}).trigger("reloadGrid");
	});

	// 新增
	$("#insert_submit").click(function() {
		if (!$("#insertForm").valid()) {
			return;
		}

		$.ajax({
			type : "POST",
			url : "user/insert",
			data : $("#insertForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid('setGridParam', {
						url : "user/list",
						page : 1
					}).trigger("reloadGrid");
					// 清空表单
					$("#insertForm")[0].reset();
				} else {
					swal("失败", data.msg);
				}
			}
		});
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
			swal("请选择");
			return;
		} else if (ids.length > 1) {
			swal("只能选择一条数据");
			return;
		}

		$.ajax({
			type : "POST",
			url : "user/getById",
			data : {
				"userId" : ids[0]
			},
			dataType : "JSON",
			success : function(data) {
				$("#editUserId").val(data.userId);
				$("#editUserEmail").val(data.userEmail);
				$("#editUserPhone").val(data.userPhone);
				$("#editUserPwd").val(data.userPwd);
				$("#editUserSalt").val(data.userSalt);
				$("#editUserIcon").val(data.userIcon);
				$("#editUserCreatedTime").val(data.userCreatedTime);
				$("#editUserLoginedTime").val(data.userLoginedTime);

				$("#editUserNickname").val(data.userNickname);
				$("#editUserIdentity").val(data.userIdentity);
				$("#editUserName").val(data.userName);
				$("#editStrUserBirthday").val(data.userBirthday);
				$("#editUserAddress").val(data.userAddress);
				$("#editUserAddress").val(data.userAddress);
				$("#editUserAddress").val(data.userAddress);
				$("#editQqOpenId").val(data.qqOpenId);
				$("#editWechatOpenId").val(data.wechatOpenId);
				$("#editWeiboOpenId").val(data.weiboOpenId);
				$("#editUserDes").val(data.userDes);
				$("#editUserSalary").val(data.userSalary);
				// 性别判断
				if (data.userGender == "G") {
					$("#editUserGender1").attr("checked", "checked");
				} else if (data.userGender == "M") {
					$("#editUserGender2").attr("checked", "checked");
				}
				// 公司判断
				$("#editCompanyId").find("option[value='" + data.companyId + "']").attr("selected", true);
				// 状态判断
				$("#editUserStatus").find("option[value='" + data.userStatus + "']").attr("selected", true);
				$("#edit-form").modal("show");
			}
		});
	});

	// 修改用户信息
	$("#edit_submit").click(function() {
		if (!$("#editForm").valid()) {
			return;
		}

		$.ajax({
			type : "POST",
			url : "user/update",
			data : $("#editForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid('setGridParam', {
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