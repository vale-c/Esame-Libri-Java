package it.unipr.informatica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.unipr.informatica.modello.Book;
import it.unipr.informatica.modelloimpl.BookImpl;


public class DatabaseManager {
	String databaseURL;
	
	public DatabaseManager() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("it.unipr.informatica.database.info.configuration");

			String databaseDriver = bundle.getString("database.driver");
			
			Class.forName(databaseDriver);
						
			databaseURL = bundle.getString("database.url");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	public Book addBook(String author, String title, int pages) throws SQLException {
	   try (
			 Connection connection = DriverManager.getConnection(databaseURL);
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO BOOKS(AUTHOR,TITLE,PAGES) VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			){
			 
			 statement.setString(1,author);
			 statement.setString(2,title);
			 statement.setInt(3,pages);
			 
			 statement.execute();	 
		 
		 try (
				ResultSet resultSet = statement.getGeneratedKeys();
			) {		
				resultSet.next();
				
				int id = resultSet.getInt(1);
						
					return new BookImpl(id, author, title, pages);
				} catch (SQLException exception) {
					throw exception;
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
				
				throw exception;
			}
		}

	public List<Book> getAllBooks() throws SQLException {
		List<Book> result = new ArrayList<Book>();
		
		try(
			Connection connection = DriverManager.getConnection(databaseURL);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from BOOKS order by ID");
		) {
			while(resultSet.next()) {
				int id = resultSet.getInt("ID");
				
				String author = resultSet.getString("author");
				
				String title = resultSet.getString("title");
				
				String numPages = resultSet.getString("pages");
				
				int pages = Integer.parseInt(numPages);
				
				Book book = new BookImpl(id, author, title, pages);
				
				result.add(book);		
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			
			throw exception;
		}
		
		return result;
	}
	
	public Book getBook(int id) throws SQLException {
		try(
			Connection connection = DriverManager.getConnection(databaseURL);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from BOOKS where ID = " + id);
		) {
			if(!resultSet.next())
				throw new SQLException("invalid id " + id);
			else {
				String author = resultSet.getString("AUTHOR");
				
				String title = resultSet.getString("TITLE");
				
				String numPages = resultSet.getString("PAGES");
				
				int pages = Integer.parseInt(numPages);
				
				Book book = new BookImpl(id, author, title, pages);

				return book;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			
			throw exception;
		}
	}
	
	public Book modifyBook(int id, String author, String title, int pages) throws SQLException {
		if(id <= 0)
			throw new IllegalArgumentException("invalid id");
		
		if(author == null || author.length() == 0 || author.length() > 250)
			throw new IllegalArgumentException("invalid author");

		if(title == null || title.length() == 0 || title.length() > 250)
			throw new IllegalArgumentException("invalid title");

		try(
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("update BOOKS set AUTHOR = ?, TITLE = ? where ID = ?");
		) {
			statement.setString(1, author);

			statement.setString(2, title);

			statement.setInt(3, id);

			statement.execute();

			return new BookImpl(id, author, title, pages);
		} catch (SQLException exception) {
			exception.printStackTrace();
			
			throw exception;
		}
	}
	
	public void deleteBook(int id) throws SQLException {
		try(
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("delete from BOOKS where ID = ?");
		) {
			statement.setInt(1, id);
			
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
			
			throw exception;
		}
	}

}