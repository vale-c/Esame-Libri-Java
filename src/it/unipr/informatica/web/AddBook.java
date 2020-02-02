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
 * Servlet implementation class AddTranslation
 */
@SuppressWarnings("serial")
@WebServlet("/add_book")
public class AddBook extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			DatabaseManager databaseManager = (DatabaseManager)session.getAttribute("DatabaseManager");
			
			if(databaseManager == null) {
				databaseManager = new DatabaseManager();
				
				session.setAttribute("DatabaseManager", databaseManager);
			}

			String author = request.getParameter("author");
					
			String title = request.getParameter("title");
			
			String numPages = request.getParameter("pages");
			
			int pages = Integer.parseInt(numPages);
			
			databaseManager.addBook(author, title, pages);
			
			request.getRequestDispatcher("get_all_books").forward(request, response);			
		} catch (Throwable throwable) {
			throwable.printStackTrace();

			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}

}
