package it.unipr.informatica.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unipr.informatica.database.DatabaseManager;
import it.unipr.informatica.modello.Book;


@SuppressWarnings("serial")
@WebServlet("/get_all_books")
public class GetAllBooks extends HttpServlet {
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
	
			List<Book> allBooks = databaseManager.getAllBooks();
			
			session.setAttribute("allBooks", allBooks);
			
			req.getRequestDispatcher("get_all_books.jsp").forward(req, resp);
		} catch (Throwable throwable) {
			throwable.printStackTrace();

			req.getRequestDispatcher("error.html").forward(req, resp);
		}
	}
}
