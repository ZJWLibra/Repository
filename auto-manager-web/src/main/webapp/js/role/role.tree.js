$(function() {
	// 打开添加角色窗口
	$(".role-permission").click(function() {
		var ids = []; 
		
		// 获取勾选数据
		$("input:checkbox[name=roleId]:checked").each(function(value) {
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
			url : "role/getPermissionsByRoleId",
			data : {"roleId" : ids[0]},
			dataType : "JSON",
			success : function(data) {
				var setting = {
					check: {
						enable: true,
						chkboxType : {
							"Y" : "ps",
							"N" : "s"
						}
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
				
				$.fn.zTree.init($("#ztree"), setting, data);
				
				$("#permission_modal").modal("show");
				
				$("#roleId").val(ids[0]);
			}
		});
	});
	
	// 关闭添加角色窗口
	$("#permission_close").click(function() {
		$("#permission_modal").modal("hide");
	});
	
	// 添加角色
	$("#permission_submit").click(function() {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var checkeds = treeObj.getCheckedNodes(true);
		
		var ids = new Array(checkeds.length);
		
		for (var i = 0; i < checkeds.length; i++) {
			ids[i] = checkeds[i].id;
		}
		
		$.ajax({
			type : "POST",
			dataType : "JSON",
			traditional : true, // 传递数组参数
			url : "role/insertPermission",
			data : {"ids" : ids, "roleId" : $("#roleId").val()},
			success : function(data) {
				if (data.status == "200") {
					swal("成功", "授权成功", "success");
					$("#permission_modal").modal("hide");
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
});