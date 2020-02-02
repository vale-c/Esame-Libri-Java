/*
 * it.unipr.informatica.example6.web.GetStudent
 *
 * (c) 2019 Federico Bergenti. All rights reserved.
 */
package it.unipr.informatica.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.database.DatabaseManager;
import it.unipr.informatica.modello.Book;

@SuppressWarnings("serial")
@WebServlet("/get_book")
public class GetBook extends HttpServlet {
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

			String idString = req.getParameter("get_id");

			int id = Integer.parseInt(idString);
			
			Book book = databaseManager.getBook(id);
			
			session.setAttribute("modify_book", book);
			
			req.getRequestDispatcher("modify_book.jsp").forward(req, resp);
		} catch (Throwable throwable) {
			throwable.printStackTrace();

			req.getRequestDispatcher("error.html").forward(req, resp);
		}
	}
}
