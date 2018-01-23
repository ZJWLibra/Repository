$(function() {
	var colNames = [ "id", "颜色名称", "颜色RGB", "颜色16进制", "汽车颜色", "颜色描述", "状态" ];
	var colModel = [ {
		name : "colorId",
		index : "colorId",
		hidden : true
	}, {
		name : "colorName",
		index : "colorName"
	}, {
		name : "colorRGB",
		index : "colorRGB"
	}, {
		name : "colorHex",
		index : "colorHex"
	}, {
		name : "colorHex",
		index : "colorHex",
		formatter : function(value, options, rowData) {
			return "<div style='width:25px;height:25px;background:" + value + "'></div>";
		}
	}, {
		name : "colorDes",
		index : "colorDes"
	}, {
		name : "colorStatus",
		index : "colorStatus",
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
	pageInit("grid_table", "颜色列表", "carColor/list", colNames, colModel);

	// 多选框赋值
	$("#grid_table").jqGrid("setGridParam", {
		gridComplete : function() {
			var rowIds = jQuery("#grid_table").jqGrid("getDataIDs");
			for (var i = 0; i < rowIds.length; i++) {
				var curRowData = jQuery("#grid_table").jqGrid("getRowData", rowIds[i]);
				var curChk = $("#jqg_grid_table_" + (i + 1));
				curChk.attr("name", "colorId");
				curChk.attr("value", curRowData.colorId);
			}
		}
	});

	// 查询
	$(".select_form").click(function() {
		var colorName = $("#selectColorName").val();
		var colorStatus = $("#selectColorStatus").val();
		$("#grid_table").jqGrid("setGridParam", {
			url : "carColor/list",
			postData : {
				"colorName" : colorName,
				"colorStatus" : colorStatus
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
			url : "carColor/insert",
			data : $("#insertForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#insert-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carColor/list",
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
	$(".carColor-update").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$("input:checkbox[name=colorId]:checked").each(function(value) {
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
			url : "carColor/getById",
			data : {"colorId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				$("#editColorId").val(data.colorId);
				$("#editColorName").val(data.colorName);
				$("#editColorRGB").val(data.colorRGB);
				$("#editColorHex").val(data.colorHex);
				$("#editColorDes").val(data.colorDes);
				// 状态判断
				$("#editColorStatus").find("option[value='" + data.colorStatus + "']").attr("selected", true);
				// 设置颜色选择器默认颜色
				$("#edit_full").spectrum("set", $("#editColorHex").val());
				$("#edit-form").modal("show");
			}
		});
	});
	
	// 修改颜色
	$("#edit_submit").click(function() {
		if (!$("#editForm").valid()) {
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "carColor/update",
			data : $("#editForm").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					swal("成功", "添加成功", "success");
					// 关闭窗口
					$("#edit-form").modal("hide");
					// 重新加载数据
					$("#grid_table").jqGrid("setGridParam", {
	    				url : "carColor/list",
	    				page : 1
	    			}).trigger("reloadGrid");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
	
	// 初始化新增表单颜色选择框
	$("#insert_full").spectrum({
		allowEmpty : false,                   // 允许为空,显示清除颜色按钮
		color : "#ECC",                       // 初始化颜色
		showInput : true,
		containerClassName : "full-spectrum",
		showInitial : true,
		showPalette : true,
		showSelectionPalette : true,
		showAlpha : true,
		maxPaletteSize : 10,
		preferredFormat : "hex",
		localStorageKey : false,
		cancelText : "取消",                    //取消按钮文字
		chooseText : "确认",                    //选择按钮文字
		move : function(color) {
			updateColor(color);
		},
		hide : function(color) {
			updateColor(color);
		},
		show : function() {},
		beforeShow : function() {}
	});
	// 初始化编辑表单颜色选择框
	$("#edit_full").spectrum({
		allowEmpty : false,                   // 允许为空,显示清除颜色按钮
		color : "#ECC",                       // 初始化颜色
		showInput : true,
		containerClassName : "full-spectrum",
		showInitial : true,
		showPalette : true,
		showSelectionPalette : true,
		showAlpha : true,
		maxPaletteSize : 10,
		preferredFormat : "hex",
		localStorageKey : false,
		cancelText : "取消",                    //取消按钮文字
		chooseText : "确认",                    //选择按钮文字
		move : function(color) {
			updateEditColor(color);
		},
		hide : function(color) {
			updateEditColor(color);
		},
		show : function() {},
		beforeShow : function() {}
	});
});
/*16进制颜色转为RGB格式*/
var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
String.prototype.colorRgb = function(){
     var sColor = this.toLowerCase();
     if(sColor && reg.test(sColor)){
          if(sColor.length === 4){
               var sColorNew = "#";
               for(var i=1; i<4; i+=1){
                    sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));    
               }
               sColor = sColorNew;
          }
          //处理六位的颜色值
          var sColorChange = [];
          for(var i=1; i<7; i+=2){
               sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));    
          }
          return sColorChange.join(",");
     }else{
          return sColor;    
     }
};
// 颜色赋值
function updateColor(color) {
	var hexColor = "transparent";
	if (color) {
		hexColor = color.toHexString();
	}
	// 设置16进制
	$("#colorRGB").val(hexColor.colorRgb());
	// 设置rgb
	$("#colorHex").val(hexColor);
}
function updateEditColor(color) {
	var hexColor = "transparent";
	if (color) {
		hexColor = color.toHexString();
	}
	// 设置16进制
	$("#editColorRGB").val(hexColor.colorRgb());
	// 设置rgb
	$("#editColorHex").val(hexColor);
}