$(function() {
	$("#up-img-touch").click(function() {
		$("#doc-modal-1").modal({
			width : '600px'
		});
	});
	
	'use strict';
	// 初始化
	var $image = $('#image');
	$image.cropper({
		aspectRatio : '1',
		autoCropArea : 0.8,
		preview : '.up-pre-after',
	});

	// 上传图片
	var $inputImage = $('#inputImage');
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
					$image.one('built.cropper', function() {
						// 当加载完成时撤消
						URL.revokeObjectURL(blobURL);
					}).cropper('reset').cropper('replace', blobURL);
					
					var option = {
						type : "POST",
						url : "carBrand/upload",
						dataType : "JSON",
						data : {
							"fileName" : "brandLogoFile"
						},
						success : function(data) {
							// 把json格式的字符串转换成json对象
							var jsonObj = $.parseJSON(data);
							// 数据库保存相对路径
							$("#brandLogo").val(jsonObj.relativePath);
						}
					};
				
					$("#uploadForm").ajaxSubmit(option);
				} else {
					window.alert('Please choose an image file.');
				}
			}
		});
	} else {
		$inputImage.prop('disabled', true).parent().addClass('disabled');
	}
});

function rotateimgright() {
	$("#image").cropper('rotate', 90);
}

function rotateimgleft() {
	$("#image").cropper('rotate', -90);
}
