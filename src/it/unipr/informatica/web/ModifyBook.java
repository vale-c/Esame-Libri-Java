package it.unipr.informatica.web;

/*
 * it.unipr.informatica.example6.web.ModifyStudent
 *
 * (c) 2019 Federico Bergenti. All rights reserved.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.database.DatabaseManager;

@SuppressWarnings("serial")
@WebServlet("/modify_book")
public class ModifyBook extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			DatabaseManager databaseManager = (DatabaseManager)session.getAttribute("DatabaseManager");
			
			if(databaseManager == null) {
				databaseManager = new DatabaseManager();
				
				session.setAttribute("DatabaseManager", databaseManager);
			}

			String idString = req.getParameter("id");

			int id = Integer.parseInt(idString);
			
			String author = req.getParameter("author");
			
			String title = req.getParameter("title");
			
			String npString = req.getParameter("pages");
			
			int numOfPages = Integer.parseInt(npString);
			
			databaseManager.modifyBook(id, author, title, numOfPages);
			
			
			req.getRequestDispatcher("get_all_books").forward(req, resp);			
		} catch (Throwable throwable) {
			throwable.printStackTrace();

			req.getRequestDispatcher("error.html").forward(req, resp);
		}
	}
}
