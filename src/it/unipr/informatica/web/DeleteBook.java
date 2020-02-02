package it.unipr.informatica.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.database.DatabaseManager;

/**
 * Servlet implementation class DeleteBook
 */
@SuppressWarnings("serial")
@WebServlet("/delete_book")
public class DeleteBook extends HttpServlet {

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

			String idString = req.getParameter("delete_id");
			
			int id = Integer.parseInt(idString);
			
			databaseManager.deleteBook(id);
			
			req.getRequestDispatcher("get_all_books").forward(req, resp);			
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			
			req.getRequestDispatcher("error.html").forward(req, resp);
		}
	}

}
