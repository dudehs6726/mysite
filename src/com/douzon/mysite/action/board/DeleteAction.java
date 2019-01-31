package com.douzon.mysite.action.board;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;
import com.douzon.mysite.vo.UserVo;

public class DeleteAction implements Action {

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

		String no = request.getParameter("no");
		Long userNo = authUser.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setNo(Long.valueOf(no));
		vo.setUserNo(userNo);
		
		if(new BoardDao().getDeleteCheck(vo)) {
			showMessageDialog(null, "답글이 존재하여 삭제 할 수 없습니다.");
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list&page=1");
			return;
		}
		
		//파일 삭제
		String fileName = request.getParameter("fileName");
		String savePath = request.getServletContext().getRealPath("file");
		String sFilePath = savePath + "\\" + fileName;
		
		File f = new File(sFilePath);
			
		if(f.exists()) {
			f.delete();
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		new BoardDao().delete(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list&page=1");
	}

}
