package com.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getParameter("status"));

		String status = request.getParameter("status");

		String mpp_file_dir = getServletContext().getInitParameter("mpp_file_dir");
		try {
		JSONObject array;
		// Do some work.
		ReadExcelFile readExcelFile = new ReadExcelFile();
		if (status.equalsIgnoreCase("completed!")) {
			array = readExcelFile.getCompletedTaskAsJsonObject(mpp_file_dir);
		}
		else if (status.equalsIgnoreCase("In Progress!")) {			
			array = readExcelFile.getInProgressTaskAsJsonObject(mpp_file_dir);
		}
		else if (status.equalsIgnoreCase("Running Late!")) {
			array = readExcelFile.getRunningLateAsJsonObject(mpp_file_dir);
		}
		else if (status.equalsIgnoreCase("Not Started!")) {
			array = readExcelFile.getNotStartedTaskAsJsonObject(mpp_file_dir);
		}else{
			array = readExcelFile.getExcelDataAsJsonObject(mpp_file_dir);
		}
		
		PrintWriter out;
		
			out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(array.get("Sheet1"));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
