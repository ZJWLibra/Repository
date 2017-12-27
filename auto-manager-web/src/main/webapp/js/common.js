/**
 * 初始化表格
 */
function pageInit(tableId, caption, url, colNames, colModel) {
	$.jgrid.defaults.styleUI = "Bootstrap";

	jQuery("#" + tableId).jqGrid({
		caption : caption,
		url : url,
		mtype : "post",
		datatype : "json",
		height : "auto",
		multiselect : true, // 定义是否出现多选框
		rownumbers : true, // 如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
		viewrecords : true, // 是否要显示总记录数
		autowidth : true, // 如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
		shrinkToFit : true, // 此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
		colNames : colNames,
		colModel : colModel,
		rowNum : 10,
		rowList : [ 10, 20, 30, 40, 50 ],
		pager : "#table_page",
	});
	
}