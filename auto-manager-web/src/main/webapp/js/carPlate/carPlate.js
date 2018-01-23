$(function() {
	var colNames = [ "id", "车牌名称", "车型描述", "状态" ];
	var colModel = [ {
		name : "plateId",
		index : "plateId",
		hidden : true
	}, {
		name : "plateName",
		index : "plateName"
	}, {
		name : "plateDes",
		index : "plateDes",
		width : 200
	}, {
		name : "plateStatus",
		index : "plateStatus",
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
	pageInit("grid_table", "车牌列表", "carPlate/list", colNames, colModel);

	// 多选框赋值
	$("#grid_table").jqGrid("setGridParam", {
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "plateId");
				curChk.attr("value", curRowData.plateId);
			}
		}
	});

	// 查询
	$(".select_form").click(function() {
		var plateName = $("#selectPlateName").val();
		var plateStatus = $("#selectPlateStatus").val();
		$("#grid_table").jqGrid("setGridParam", {
			url : "carPlate/list",
			postData : {
				"plateName" : plateName,
				"plateStatus" : plateStatus
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
			url : "carPlate/insert",
			data : $("#insertForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carPlate/list",
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
	$(".carPlate-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$("input:checkbox[name=plateId]:checked").each(function(value) {
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
			url : "carPlate/getById",
			data : {"plateId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#editPlateId").val(data.plateId);
				$("#editPlateName").val(data.plateName);
				$("#editPlateDes").val(data.plateDes);
				// 状态判断
				$("#editPlateStatus").find("option[value='" + data.plateStatus + "']").attr("selected", true);
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
			url : "carPlate/update",
			data : $("#editForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carPlate/list",
	    				page : 1
	    			}).trigger("reloadGrid");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});

});