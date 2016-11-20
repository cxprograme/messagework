
/**
 * 批量删除
 * @param basePath
 */
function deleteBatch(basePath)
{
	$("#mainform").attr("action",basePath+"deleteBatch");
	$("#mainform").submit();
	}