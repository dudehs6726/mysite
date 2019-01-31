package com.douzon.mysite.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;

import sun.security.util.Length;

public class DownAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String fileName = request.getParameter("fileName");
		String savePath = request.getServletContext().getRealPath("file");
		
		
		//System.out.println("다운로드 위치: " + savePath);
		String sFilePath = savePath + "\\" + fileName;
		File oFile = new File(sFilePath);
		
		byte[] b = new byte[100 * 1024 * 1024];
		FileInputStream in = new FileInputStream(oFile);
		
		String sMimeType = request.getServletContext().getMimeType(sFilePath);
		//System.out.println("유형:" + sMimeType);
		
		if(sMimeType == null) {
			sMimeType = "application.octec-stream";
		}
		
		//다운로드 시작
		response.setContentType(sMimeType);
		String A = new String(fileName.getBytes("euc-kr"),"8859_1");
		String B = "utf-8";
		String sEncoding = URLEncoder.encode(A, B);
		
		String AA = "Content-Disposition";
		String BB = "attachment; filename="+sEncoding;
		response.setHeader(AA, BB);
		
		ServletOutputStream out2 = response.getOutputStream();
		
		int numRead= 0;
		
		while((numRead = in.read(b,0,b.length))!=-1) {
			out2.write(b,0,numRead);
		}
		out2.flush();
		out2.close();
		in.close();
	}

}
