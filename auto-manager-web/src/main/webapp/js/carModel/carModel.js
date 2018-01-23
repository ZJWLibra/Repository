$(function() {
	var colNames = [ "id", "车型名称", "车型描述", "所属品牌", "状态" ];
	var colModel = [ {
		name : "modelId",
		index : "modelId",
		hidden : true
	}, {
		name : "modelName",
		index : "modelName"
	}, {
		name : "modelDes",
		index : "modelDes",
		width : 200
	}, {
		name : "carBrand",
		index : "carBrand",
		formatter : function(value, options, rowData) {
			return value.brandName;
		}
	}, {
		name : "modelStatus",
		index : "modelStatus",
		width : 80,
		formatter : function(value, options, rowData) {
			if (value == "Y") {
				return "启用";
			} else {
				return "禁用";
			}
		}
	} ];
	// 初始化表格
	pageInit("grid_table", "车型列表", "carModel/list", colNames, colModel);

	// 多选框赋值
	$("#grid_table").jqGrid("setGridParam", {
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "modelId");
				curChk.attr("value", curRowData.modelId);
			}
		}
	});

	// 查询
	$(".select_form").click(function() {
		var modelName = $("#selectModelName").val();
		var brandId = $("#selectBrandId").val();
		var modelStatus = $("#selectModelStatus").val();
		$("#grid_table").jqGrid("setGridParam", {
			url : "carModel/list",
			postData : {
				"modelName" : modelName,
				"brandId" : brandId,
				"modelStatus" : modelStatus
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
			url : "carModel/insert",
			data : $("#insertForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carModel/list",
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
	$(".carModel-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$("input:checkbox[name=modelId]:checked").each(function(value) {
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
			url : "carModel/getById",
			data : {"modelId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#editModelId").val(data.modelId);
				$("#editModelName").val(data.modelName);
				$("#editModelDes").val(data.modelDes);
				// 所属品牌判断
				$("#editBrandId").find("option[value='" + data.brandId + "']").attr("selected", true);
				// 状态判断
				$("#editModelStatus").find("option[value='" + data.modelStatus + "']").attr("selected", true);

				$("#edit-form").modal("show");
			}
		});
	});
	
	// 修改车型
	$("#edit_submit").click(function() {
		if (!$("#editForm").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "carModel/update",
			data : $("#editForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carModel/list",
	    				page : 1
	    			}).trigger("reloadGrid");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});

});