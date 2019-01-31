package org.javastack.consoleapp.manager;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.javastack.consoleapp.models.Book;
import org.javastack.consoleapp.models.Subject;

public class MenuDrivenApplication {

	Set<Subject> subjects = new TreeSet<Subject>();
	Set<Book> books = new TreeSet<Book>();
	MenuElements operation = new MenuElements();

	public static void main(String args[]) {

		MenuDrivenApplication menu = new MenuDrivenApplication();
		menu.showMenu();

	}

	public void showMenu() {

		String option = null;
		try (Scanner scanner = new Scanner(System.in)) {

			while (true) {

				String showOptions = "\n Select option from below menu (type A or B...)\n A => Add a Subject \n"
						+ "B =>Add a Book \n" + "C =>Delete a Subject \n" + "D =>Delete a book \n"
						+ "E =>Search for a book \n" + "F =>Search for a subject \n" + "G =>Exit";

				System.out.println(showOptions);

				if (scanner.hasNext()) {
					option = scanner.next();
				}
				System.out.println("Selected Option is:" + option);
				switch (option) {
				case "A":
					Subject subject = MenuElements.enterSubjectDetails();
					MenuElements.addSubject(subject);
					System.out.println("Subject added successfully");
					break;

				case "B":
					Book book = MenuElements.enterBookDetails();
					MenuElements.addBook(book);
					System.out.println("Book added successfully");
					break;

				case "C":
					String subjectTitle = MenuElements.showSubjectsAndGetSubjectId();
					MenuElements.deleteSubject(subjectTitle);
					System.out.println("the subject " + String.valueOf(subjectTitle) + " deleted");
					break;

				case "D":
					String bookTitle = MenuElements.showBooksAndGetBookId();
					MenuElements.deleteBook(bookTitle);
					System.out.println("the Book " + String.valueOf(bookTitle) + " deleted");
					break;

				case "E":

					System.out.println("Enter title of the book");
					if (scanner.hasNext()) {
						String searchedBookTitle = scanner.next();
						Optional<Book> bookSearched = MenuElements.searchBook(searchedBookTitle);
						if (bookSearched.isPresent()) {
							System.out.println("Book with title " + searchedBookTitle + " is available \n");
						} else {
							System.out.println("Book with title " + searchedBookTitle + " is not available \n ");
						}

					}
					break;
				case "F":
					System.out.println("Enter title of the Subject");
					if (scanner.hasNext()) {
						String searchedSubjectTitle = scanner.next();
						Optional<Subject> subjectSearched = MenuElements.searchSubject(searchedSubjectTitle);
						if (subjectSearched.isPresent()) {
							System.out.println("Subject with title " + searchedSubjectTitle + " is available \n");
						} else {
							System.out.println("Subject with title " + searchedSubjectTitle + " is not available \n");
						}

					}

					break;
				}

				if ("G".equals(option))
					break;

			}
		}
	}

}