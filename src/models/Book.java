package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	protected String title;
	protected String author;
	protected String genre;
	
	
	public Book(String title, String author, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


	public StringProperty titleProperty() {
		// TODO Auto-generated method stub
		return new SimpleStringProperty(title);
	}
	
	public StringProperty authorProperty() {
		// TODO Auto-generated method stub
		return new SimpleStringProperty(author);
	}
	
	public StringProperty genreProperty() {
		// TODO Auto-generated method stub
		return new SimpleStringProperty(genre);
	}
	
}
