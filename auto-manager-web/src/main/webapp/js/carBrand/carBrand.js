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
	pageInit("grid_table", "品牌列表", "carBrand/list", colNames, colModel);
	
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
    });
	
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
	$(".carBrand-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$('input:checkbox[name=carBrandId]:checked').each(function(value) {
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
		window.location.href = "carBrand/toEdit/" + ids[0];
	});
	
});