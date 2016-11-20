package com.ztace.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.beetl.ext.fn.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztace.message.entity.Message;
import com.ztace.message.service.MessageService;
import com.ztace.message.web.Ajax;
import com.ztace.message.web.AjaxReturn;
import com.ztace.message.web.AjaxTableReturn;

/**
 * 消息控制层
 * 
 * @author admin
 *
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * 新增消息
	 * 
	 * @return
	 */
	@RequestMapping("add")
	public String add() {
		return "/message/back/add";
	}

	/**
	 * 保存消息信息
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReturn save(Message message) {
		System.out.println("进入");
		AjaxReturn result = Ajax.fail().setMsg("操作失败");
		if (null != message) {
			result.setOk(messageService.save(message) > 0).setMsg("新增成功");
		}

		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="listUser",method=RequestMethod.POST)
	public AjaxReturn listUser(){
		AjaxReturn result = Ajax.fail().setMsg("操作失败");
		List<Message> messages=messageService.queryMesAndUser();
		for (Message message : messages) {
			System.err.println(message.getUser().getUsername());
		}
		if(messages.size()>0){
			result.setOk(true).setMsg("查询成功");
		}
		return result;
	}

	/**
	 * 消息显示页面
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public String main() {
		return "/message/back/list";
	}
	
	
	

	/**
	 * 修改消息页面 
	 * @param mid
	 * @param model
	 * @return
	 */
	@RequestMapping("/editpage")
	public String edit(@RequestParam(value="id") int mid,Model model){
		Message message=messageService.findMessageById(mid);
		model.addAttribute("message", message);
		return "/message/back/edit";
	}
	
	
	/**
	 * 更新消息信息
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/usave",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturn updatesave(Message message){
		AjaxReturn result=Ajax.fail().setMsg("操作失败");
		if(messageService.modify(message)>0){
			result.setOk(true).setMsg("更新成功");
		}
		return result;
	}
	
	/**
	 * 删除单条消息信息
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturn delete(@RequestParam(value="mid") int mid){
		AjaxReturn result=Ajax.fail().setMsg("操作失败");
		if(messageService.delete(mid)>0){
			result.setOk(true).setMsg("删除成功");
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param mids
	 * @return
	 */
	@RequestMapping(value="deletebatch",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturn delteBatch(@RequestParam(value="mids") int[] mids){
		AjaxReturn result=Ajax.fail().setMsg("操作失败");
		if(mids.length>0){
			
			if(messageService.deleteBatch(mids)>0){
				result.setOk(true).setMsg("删除成功");
			}
		}
		return result;
	}
	
	/**
	 * 消息导入界面
	 * @return
	 */
	@RequestMapping("/importpage")
	public String importpage(){
		return "/message/back/import";
	}
	
	/**
	 * 导入数据
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/importmes" ,method=RequestMethod.POST)
	@ResponseBody
	public AjaxReturn importMes(@RequestParam(value="mesfile") MultipartFile multipartFile,HttpServletRequest request) throws IOException{
		AjaxReturn result=Ajax.fail().setMsg("操作失败");
		Message message=null;
		List<Message> messagesList=new ArrayList<Message>();
		//获取页面传递来的文件
		POIFSFileSystem fs=new POIFSFileSystem(multipartFile.getInputStream());
		//POI解析xls文件的工具类 ，得到文档对象
		HSSFWorkbook workbook=new HSSFWorkbook(fs);;
		//得到第一个表单sheet
		HSSFSheet sheet=workbook.getSheetAt(0);
	
		System.out.println("最大行"+sheet.getLastRowNum());
		if(null!=sheet){
			for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
				//解析行
				HSSFRow hssfRow=sheet.getRow(rowNum);
				message=new Message();
				//解析第一列
				//0代表消息指令 command
				HSSFCell command=hssfRow.getCell(0);
				if(null!=command){
					//将第一列的值传入到message中
					message.setCommand(command.getStringCellValue());
				}
				//1代表消息描述
				HSSFCell description=hssfRow.getCell(1);
				if(description!=null){
					message.setDescription(description.getStringCellValue());
				}
				//2代表消息内容
				HSSFCell content=hssfRow.getCell(2);
				if(content!=null){
					message.setContent(content.getStringCellValue());
				}
				
				//将此次循环的对象放入lis中
				messagesList.add(message);
			}
			System.out.println("结果"+messagesList);
		}
		//向数据库中的导入数据
		if(messageService.insertBatch(messagesList)>0){
			result.setOk(true).setMsg("导入成功");
		}
		return result;
	}
	
	/**
	 * 日历显示
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：cal
	 * 创建人：chenxu 
	 * 时间：2016年5月28日-下午10:01:01 
	 * 手机:
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/cal")
	public String cal(){
		return "/message/back/cal";
	}
	
	@RequestMapping("/info")
	public String info(){
		return "/message/back/info";
	}
	
	@RequestMapping(value = "calendarEvents")
	@ResponseBody
	public JSONArray calendarEvents(){
	   
	    String str="[{title:\"All\",start:\"2016-05-01\"},{title:\"HOIL\",start:\"2016-05-12\"}]";
	    String text = "[{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"},{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}]";
	    JSONArray json = JSON.parseArray(str);
	    
	    return json;
	}

	/**
	 * 查询所有信息
	 * 
	 * @param pagesize
	 * @param start
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findall")
	public AjaxTableReturn findAllMessages(@RequestParam(value = "limit", defaultValue = "10") int pagesize,
			@RequestParam(value = "offset", defaultValue = "0") int start) {
		start = start > 0 ? start / pagesize + 1 : 1;
		PageHelper.startPage(start, pagesize);
		List<Message> messages = messageService.findAllMessages();
		PageInfo<Message> page = new PageInfo<Message>(messages);
		return Ajax.tableDataOk(page.getTotal(), page.getList());
	}
}
