$(function() {
	var colNames = ["id", "角色名称", "角色描述", "状态" ];
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
	
	pageInit("grid_table", "角色列表", "role/list", colNames, colModel);
	
	// 多选框赋值
	$("#grid_table").jqGrid('setGridParam',{ 
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "roleId");
				curChk.attr("value", curRowData.roleId);
			}
		}
    })
	
	// 新增
	$("#insert_submit").click(function() {
		if (!$("#insertForm").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "role/insert",
			data : $("#insertForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid('setGridParam', {
	    				url : "role/list",
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
	$(".role-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$('input:checkbox[name=roleId]:checked').each(function(value) {
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
			url : "role/getById",
			data : {"roleId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#editRoleId").val(data.roleId);
				$("#editRoleName").val(data.roleName);
				$("#editRoleDes").val(data.roleDes);
				// 状态判断
				$("#editRoleStatus").find("option[value='" + data.roleStatus + "']").attr("selected", true);

				$("#edit-form").modal("show");
			}
		});
	});
	
	// 修改角色信息
	$("#edit_submit").click(function() {
		if (!$("#editForm").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "role/update",
			data : $("#editForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid('setGridParam', {
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