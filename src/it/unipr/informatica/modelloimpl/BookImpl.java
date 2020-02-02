package it.unipr.informatica.modelloimpl;

import it.unipr.informatica.modello.Book;

public class BookImpl implements Book {	
	private int id;
	private String author;
	private String title;
	private int numOfPages;

	public BookImpl(int id, String author, String title, int numOfPages) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.numOfPages = numOfPages;
	}
	  
	public int getId() {
		return id;
	}	
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getNumOfPages() {
		return numOfPages;
	}
}
