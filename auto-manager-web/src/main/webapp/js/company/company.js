$(function() {
	var colNames = [ "id", "公司名称", "公司地址", "公司联系方式", "公司负责人", "公司负责人电话", "公司官网", "公司logo", "公司成立时间", "公司规模", "公司状态" ];
	var colModel = [ {
		name : "companyId",
		index : "companyId",
		hidden : true
	}, {
		name : "companyName",
		index : "companyName"
	}, {
		name : "companyAddress",
		index : "companyAddress",
		width : 200
	}, {
		name : "companyTel",
		index : "companyTel"
	}, {
		name : "companyPricipal",
		index : "companyPricipal",
		width : 80
	}, {
		name : "companyPricipalPhone",
		index : "companyPricipalPhone"
	}, {
		name : "companyWebsite",
		index : "companyWebsite"
	}, {
		name : "companyLogo",
		index : "brandLogo",
		width : 80,
		formatter : function(value, options, rowData) {
			var prefix = "http://zjwimage.oss-cn-hangzhou.aliyuncs.com/";
			return "<img src='" + prefix + value + "' style='width:50px;height:50px;' />";
		}
	}, {
		name : "companyOpenDate",
		index : "companyOpenDate"
	}, {
		name : "companySize",
		index : "companySize",
		width : 100
	}, {
		name : "companyStatus",
		index : "companyStatus",
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
	pageInit("grid_table", "公司列表", "company/list", colNames, colModel);

	// 多选框赋值
	$("#grid_table").jqGrid('setGridParam', {
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "companyId");
				curChk.attr("value", curRowData.companyId);
			}
		}
	});

	// 查询
	$(".select_form").click(function() {
		var companyName = $("#selectCompanyName").val();
		var companyStatus = $("#selectCompanyStatus").val();
		$("#grid_table").jqGrid('setGridParam', {
			url : "company/list",
			postData : {
				"companyName" : companyName,
				"companyStatus" : companyStatus
			},
			page : 1
		}).trigger("reloadGrid");
	});

	// 跳转到修改页面
	$(".company-update").click(function() {
		var ids = [];
		// 获取勾选数据
		$('input:checkbox[name=companyId]:checked').each(function(value) {
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
		window.location.href = "company/toEdit/" + ids[0];
	});

});