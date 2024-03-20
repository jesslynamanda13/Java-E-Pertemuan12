package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import index.DatabaseConnection;
import index.DatabaseSingleton;
import models.Book;

public class BookController {
	private static DatabaseConnection db = DatabaseSingleton.getInstance();
	public static List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");

                Book book = new Book(title, author, genre);
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
	}
	
	public static boolean insertBook(Book book) {
		String query = "INSERT INTO books (title, author, genre) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean updateBook(String title, String author, String genre) {
		String query = "UPDATE books SET author = ?, genre = ? WHERE title = ?";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, author);
            statement.setString(2, genre);
            statement.setString(3, title);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static List<String> getAllTitles(){
		List<String> titles = new ArrayList<>();
        String query = "SELECT title FROM books";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                titles.add(title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titles;
	}
	
	public static boolean deleteBook(String title) {
		String query = "DELETE FROM books WHERE title = ?";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, title);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
}
