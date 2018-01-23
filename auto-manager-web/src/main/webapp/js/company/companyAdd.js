$(function() {
	// 新增
	$("#insert_submit").click(function() {
		if (!$("#insert_form").valid()) {
			return;
		}
		$.ajax({
			type : "POST",
			url : "company/insert",
			data : $("#insert_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					window.location.href = "company/toIndex";
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
	
	// 修改
	$("#update_submit").click(function() {
		if (!$("#update_form").valid()) {
			return;
		}
		$.ajax({
			type : "POST",
			url : "company/update",
			data : $("#update_form").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.status == 200) {
					window.location.href = "company/toIndex";
				} else {
					swal("失败", data.msg);
				}
			}
		});
	});
});

$(function() {
	// 弹出裁剪框
	$("#up-img-touch").click(function() {
		$("#doc-modal-1").modal({
			width : '600px'
		});
	});
	
	'use strict';
	// 初始化
	var $image = $("#image");
	$image.cropper({
		aspectRatio : '1',
		autoCropArea : 0.8,
		preview : ".up-pre-after",
	});

	// 上传图片
	var $inputImage = $("#inputImage");
	var URL = window.URL || window.webkitURL;
	var blobURL;
	
	if (URL) {
		$inputImage.change(function() {
			var files = this.files;
			var file;

			if (files && files.length) {
				file = files[0];
				if (/^image\/\w+$/.test(file.type)) {
					blobURL = URL.createObjectURL(file);
					$image.one("built.cropper", function() {
						// 当加载完成时撤消
						URL.revokeObjectURL(blobURL);
					}).cropper("reset").cropper("replace", blobURL);
					
					var option = {
						type : "POST",
						url : "company/upload",
						dataType : "JSON",
						data : {
							"fileName" : "companyLogoFile"
						},
						success : function(data) {
							// 把json格式的字符串转换成json对象
							var jsonObj = $.parseJSON(data);
							// 数据库保存相对路径
							$("#companyLogo").val(jsonObj.relativePath);
						}
					};
				
					$("#uploadForm").ajaxSubmit(option);
				} else {
					window.alert("Please choose an image file.");
				}
			}
		});
	} else {
		$inputImage.prop("disabled", true).parent().addClass("disabled");
	}
});

function rotateimgright() {
	$("#image").cropper("rotate", 90);
}

function rotateimgleft() {
	$("#image").cropper("rotate", -90);
}

// 经度
var lng = $("#companyLongitude").val();
// 纬度
var lat = $("#companyLatitude").val();
// 地址
var address = null;

// 创建Map实例
var map = new BMap.Map("allmap");
// 初始化地图,设置中心点坐标和地图级别
if (lng == null || lng == "") {
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	// 浏览器定位
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r) {
		if (this.getStatus() == BMAP_STATUS_SUCCESS) {
			map.panTo(r.point);
			// 强制居中
			map.panBy(375, 230);
		} else {
			alert('failed' + this.getStatus());
		}
	}, {
		enableHighAccuracy : true
	});
} else {
	map.centerAndZoom(new BMap.Point(lng, lat), 11);
	// 创建标注
	var marker = new BMap.Marker(new BMap.Point(lng, lat));
	// 将标注添加到地图中
	map.addOverlay(marker);
	// 跳动的动画
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	// 强制居中
	map.panBy(375, 230);
}

// 开启鼠标滚轮缩放
map.enableScrollWheelZoom(true);

// 点击获取地址
var geoc = new BMap.Geocoder();    

// 单击获取点击的经纬度
map.addEventListener("click", function(e) {
	lng = e.point.lng;
	lat = e.point.lat;
	// 清除覆盖物
	map.clearOverlays();
	// 创建标注
	var marker = new BMap.Marker(new BMap.Point(lng, lat));
	// 将标注添加到地图中
	map.addOverlay(marker);
	// 跳动的动画
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	// 获取地址
	var pt = e.point;
	geoc.getLocation(pt, function(rs){
		var addComp = rs.addressComponents;
		address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	}); 
});

// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
	// 靠左上角位置
	anchor : BMAP_ANCHOR_TOP_LEFT,
	// LARGE类型
	type : BMAP_NAVIGATION_CONTROL_LARGE,
	// 启用显示定位
	enableGeolocation : true
});
map.addControl(navigationControl);

var local = new BMap.LocalSearch(map, {
	renderOptions : {
		map : map
	}
});

// 查询地址
function selectMap() {
	local.search($("#selectAddress").val());
}
// 保存地址
function saveCompanyAddress() {
	if (address != null && lng != null && lat != null) {
		$("#companyAddress").val(address);
		$("#companyLongitude").val(lng);
		$("#companyLatitude").val(lat);
	}
	$("#baidu-map").click();
}
// 关闭地图
function closeMap() {
	$("#baidu-map").click();
}
