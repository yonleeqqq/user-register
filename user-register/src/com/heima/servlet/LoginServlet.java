package com.heima.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.heima.entity.User;
import com.heima.service.UserService;
import com.heima.utils.CheckUtils;

public class LoginServlet extends HttpServlet {
	private UserService us = new UserService();

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//put params to new User()
				User user = new User();
				try {
					BeanUtils.populate(user, request.getParameterMap());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//check
				Map<String,String> errors = CheckUtils.checkUser(user);
				if(errors.size()>0){
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
				//apply Service to save
				try {
					us.login(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("/login.jsp").forward(request,response);
					return;
				}
				//sendRedirect on the result
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/ListServlet");

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
