package com.ztace.vote.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.ztace.vote.entity.VoteInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 读取文件工具类
 * 
 * ReadFileUtil 创建人:chenxu 时间：2016年11月23日-下午2:33:49
 * 
 * @version 1.0.0
 *
 */
public class ReadFileUtil {

	public String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	
	public static void main(String[] args) {
		String JsonContext = new ReadFileUtil().ReadFile("/Users/cx/Downloads/fans-openid.json");
		JSONObject jsonObject=JSONObject.fromObject(JsonContext);
		JSONObject jsonObject2=JSONObject.fromObject(jsonObject.getString("data"));
		JSONArray jsonArray=JSONArray.fromObject(jsonObject2.getString("openid"));
		List<VoteInfo> voteInfos=new ArrayList<>();
		int size=jsonArray.size();
		System.err.println(size);
		for(int i=0;i<size;i++)
		{
			VoteInfo voteInfo=new VoteInfo();
			voteInfo.setOpenid(jsonArray.get(i).toString());
			voteInfo.setIsfollow(1);
			voteInfos.add(voteInfo);
			}
		System.err.println(voteInfos);
	}

}
