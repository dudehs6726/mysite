package com.douzon.mysite.action.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;
import com.douzon.mysite.vo.UserVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* 접근제어(보안) */
		UserVo authUser = null;
		HttpSession session = request.getSession();
		
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		if(request.getMethod().toString().equals("GET"))//title == null
		{
			//글쓰기 화면으로 이동
			WebUtils.forward(request, response, "WEB-INF/views/board/write.jsp");
			return;
		}
		else if(request.getMethod().toString().equals("POST"))
		{
			String saveDir = request.getServletContext().getRealPath("file");
			
			String path = saveDir;
			File dir = new File(path);
			if(!dir.exists()) {
				dir.mkdir();
			}
			
			int maxSize = 1024*1024*100;
			String encType = "utf-8";

			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());

			BoardVo vo = new BoardVo();
			vo.setTitle(multipartRequest.getParameter("title"));
			vo.setContents(multipartRequest.getParameter("content"));
			vo.setFileName(multipartRequest.getFilesystemName("file"));
			vo.setOriFileName(multipartRequest.getOriginalFileName("file"));
			vo.setUserNo(authUser.getNo());
			
			new BoardDao().insert(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list&page=1");
		}
		
	}

}
