package com.ztace.vote.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片控制层
 * 
 * UploadController
 * 创建人:chenxu 
 * 时间：2016年11月20日-下午4:02:11 
 * @version 1.0.0
 *
 */

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping(value="/file",method=RequestMethod.POST)
	public String cxupload(@RequestParam("doc") MultipartFile file,HttpServletRequest request,
			HttpServletResponse response)throws IllegalStateException,IOException,JSONException{
		
		String oldname=file.getOriginalFilename();
		String ext=oldname.substring(oldname.lastIndexOf(".")+1);
		//设置日期路径
		String ymd=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//		String fpath="/Developer/web/resources/"+ymd;
		String fpath=uploadPath+ymd;
		//获取服务器的路径
		String dirPath=fpath;//request.getSession().getServletContext().getRealPath(fpath);
		//对图片重新命名
		String newName=UUID.randomUUID().toString()+"."+ext;
		//获得上传图片的具体路径
		File targetFile=new File(dirPath,newName);
		//获得父目录
		File pFile=new File(targetFile.getPath());
		//判断如果目录不存在，先创建
		if(!pFile.exists())pFile.mkdirs();
		//上传文件到目标文件---文件拷贝
		file.transferTo(targetFile);  //文件上传
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("name", oldname);		//文件的原始名称
		map.put("newName",newName);	//文件的新名称
		map.put("ext", ext);		//文件的后缀
		map.put("size", file.getSize());// 文件的真实大小
		map.put("url","/resources/"+ymd+"/"+newName);	//获取文件的具体的服务期目录
		
		return JSONUtil.serialize(map) ;
		
	}
}
