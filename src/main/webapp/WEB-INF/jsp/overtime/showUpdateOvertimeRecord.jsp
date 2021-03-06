<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——修改加班记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#overtimeRecordForm").submit(function(){
			var workId = $("#workId");
			var name = $("#name");
			var startDate = $("#startDate");
			var endDate = $("#endDate");
			var startTime = $("#startTime");
			var endTime = $("#endTime");
			var overtimeLenth = $("#overtimeLenth");
			var overtimeReason = $("#overtimeReason");
			var msg = "";
			if ($.trim(startDate.val()) == ""){
				msg = "起始日期不能为空！";
				startDate.focus();
			}else if ($.trim(endDate.val()) == ""){
				msg = "结束日期不能为空！";
				endDate.focus();
			}else if ($.trim(startTime.val()) == ""){
				msg = "起始时间不能为空！";
				startTime.focus();
			}else if ($.trim(endTime.val()) == ""){
				msg = "结束时间不能为空！";
				endTime.focus();
			}else if ($.trim(overtimeLenth.val())==""){
				msg = "加班时长不能为空！";
				overtimeLenth.focus();
			}else if ($.trim(overtimeReason.val())==""){
				msg = "加班事由不能为空！";
				overtimeReason.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#overtimeRecordForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：延时加班  &gt; 修改加班记录</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/overtime/updateOvertimeRecord" id="overtimeRecordForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
    	 	<input type="hidden" name="projectId" value="${overtimeRecord.overtimeProject.id}">
			<input type="hidden" name="id" value="${overtimeRecord.id }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">工号：<input type="text" name="workId" id="workId" disabled="disabled" size="20" value="${overtimeRecord.workId }"/></td>
		    			<td class="font3 fftd">姓名：<input type="text" name="name" disabled="disabled" id="name" size="20" value="${overtimeRecord.name }"/></td>
		    			<td class="font3 fftd">时长：<input type="text" name="overtimeLenth" id="overtimeLenth" size="20" value="${overtimeRecord.overtimeLenth }"/></td>
		    			<td class="font3 fftd">事由：<input type="text" name="overtimeReason" id="overtimeReason" size="20" value="${overtimeRecord.overtimeReason }"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">起始日期：<input  name="startDate" id="startDate" size="20" value="${overtimeRecord.startDate }"/><font color="red">日期格式：yyyy-mm-dd</font></td>
		    			<td class="font3 fftd">结束日期：<input  name="endDate" id="endDate" size="20" value="${overtimeRecord.endDate }"/><font color="red">日期格式：yyyy-mm-dd</font></td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">起始时间：<input  name="startTime" id="startTime" size="20" value="${overtimeRecord.startTime }"/><font color="red">时间格式：hh:mm</font></td>
		    			<td class="font3 fftd">结束时间：<input  name="endTime" id="endTime" size="20" value="${overtimeRecord.endTime }"/><font color="red">时间格式：hh:mm</font></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>