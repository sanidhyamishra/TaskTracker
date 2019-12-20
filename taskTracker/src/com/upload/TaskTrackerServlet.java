package com.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class TaskTrackerServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String mpp_file_dir= getServletContext().getInitParameter("mpp_file_dir");
		
		// Do some work.
		ReadExcelFile readExcelFile = new ReadExcelFile();
		JSONObject array = readExcelFile.getExcelDataAsJsonObject(mpp_file_dir);

		request.setAttribute("person", array);

		// Forward to to the JSP file.
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
