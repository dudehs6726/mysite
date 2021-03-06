package com.douzon.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.main.MainActionFactory;

//@WebServlet("")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		
		AbstractActionFactory af = new MainActionFactory();
		Action action = af.getAction(actionName);
		action.excute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	public void init() throws ServletException {
		//파라미터 내용
		String configPath = getServletConfig().getInitParameter("config");
		
		System.out.println("init() called : " + configPath);
	}
	
	

}
