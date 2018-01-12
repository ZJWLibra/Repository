$(function() {
	var colNames = [ "id", "品牌名称", "品牌描述", "品牌logo", "状态" ];
	var colModel = [{
		name : "brandId",
		index : "brandId",
		hidden : true
	}, {
		name : "brandName",
		index : "brandName"
	}, {
		name : "brandDes",
		index : "brandDes",
	}, {
		name : "brandLogo",
		index : "brandLogo",
		formatter : function(value, options, rowData) {
			var prefix = "http://zjwimage.oss-cn-hangzhou.aliyuncs.com/";
			return "<img src='"+prefix + value+"' style='width:50px;height:50px;' />";
		}
	}, {
		name : "brandStatus",
		index : "brandStatus",
		width : 60,
		formatter : function(value, options, rowData) {
			if (value == "Y") {
				return "启用";
			} else {
				return "禁用";
			}
		}
	} ];
	// 初始化表格
	pageInit("grid_table", "用户列表", "carBrand/list", colNames, colModel);
	
	// 多选框赋值
	$("#grid_table").jqGrid('setGridParam',{ 
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "carBrandId");
				curChk.attr("value", curRowData.brandId);
			}
		}
    })
	
	// 查询
	$(".select_form").click(function() {
		var brandName = $("#selectBrandName").val();
		var brandStatus = $("#selectBrandStatus").val();
		$("#grid_table").jqGrid('setGridParam', {
			url : "carBrand/list",
			postData : {
				"brandName" : brandName,
				"brandStatus" : brandStatus
			},
			page : 1
		}).trigger("reloadGrid");
	});
		
	// 弹出修改表单
	$(".user-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$('input:checkbox[name=carBrandId]:checked').each(function(value) {
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
	
});