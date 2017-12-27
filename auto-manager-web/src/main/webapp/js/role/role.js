$(function() {
	var colNames = ["id", "名称", "描述", "状态" ];
	var colModel = [{
		name : "roleId",
		index : "roleId",
		hidden : true
	}, {
		name : "roleName",
		index : "roleName",
	}, {
		name : "roleDes",
		index : "roleDes",
	}, {
		name : "roleStatus",
		index : "roleStatus",
		formatter : function(value, options, rowData) {
			if (value == "Y") {
				return "启用";
			} else {
				return "禁用";
			}
		}
	} ];
	
	pageInit("role_table", "角色列表", "role/list", colNames, colModel);
	
	// 多选框赋值
	$("#role_table").jqGrid('setGridParam',{ 
		gridComplete : function() {
			var rowIds = jQuery("#role_table").jqGrid("getDataIDs");
			
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#role_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_role_table_" + (i + 1));
				curChk.attr("name", "roleId");
				curChk.attr("value", curRowData.roleId);
			}
		}
    })
	
	// 关闭新增表单
	$("#insert_close").click(function() {
		$("#insert-form").modal("hide");
	});
	
	// 新增
	$("#insert_submit").click(function() {
		if (!$("#role_insert_form").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "role/insert",
			data : $("#role_insert_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#role_table").jqGrid('setGridParam', {
	    				url : "role/list",
	    				page : 1
	    			}).trigger("reloadGrid");
					// 清空表单
					$("#role_insert_form")[0].reset();
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
	$(".role-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$('input:checkbox[name=roleId]:checked').each(function(value) {
			ids.push($(this).val()); 
		});
		
		// 判断是否有勾选
		if (ids.length == 0) {
			swal("请选择要修改的角色");
			return;
		} else if (ids.length > 1) {
			swal("只能选择一个角色");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "role/getById",
			data : {"roleId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#edit_roleId").val(data.roleId);
				
				$("#edit_roleName").val(data.roleName);
				$("#edit_roleDes").val(data.roleDes);
				// 状态判断
				$("#edit_roleStatus").find("option[value='" + data.roleStatus + "']").attr("selected", true);

				$("#edit-form").modal("show");
			}
		});
	});
	
	// 修改用户信息
	$("#edit_submit").click(function() {
		if (!$("#role_edit_form").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "role/update",
			data : $("#role_edit_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#role_table").jqGrid('setGridParam', {
	    				url : "role/list",
	    				page : 1
	    			}).trigger("reloadGrid");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
	
});