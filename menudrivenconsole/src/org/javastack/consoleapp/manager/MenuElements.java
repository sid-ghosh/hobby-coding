package org.javastack.consoleapp.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.javastack.consoleapp.models.Book;
import org.javastack.consoleapp.models.Subject;

public class MenuElements {

	public static Subject enterSubjectDetails() {
		Subject subject = new Subject();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter subject Id");
		if (scanner.hasNextLong())
			subject.setSubjectId(scanner.nextLong());
		System.out.println("Enter subject Title");
		subject.setSubtitle(scanner.next());
		System.out.println("Enter duration in hours");
		subject.setDurationInHours(scanner.nextInt());
		Book book = enterBookDetails();
		addBook(book);
		System.out.println(subject);

		return subject;
	}

	public static Book enterBookDetails() {
		Book book = new Book();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Book Id");
		long bookId = scanner.nextLong();
		book.setBookId(bookId);
		System.out.println("Enter Book Title");
		book.setTitle(scanner.next());
		System.out.println("Enter Book price");
		book.setPrice(scanner.nextDouble());
		System.out.println("Enter Book Volume");
		book.setVolume(scanner.nextInt());
		System.out.println("Enter Book publish Date DD-mm-yyyy");
		book.setPublishDate(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

		return book;
	}

	public static void addBook(final Book book) {
		Set<Book> books = readBooksObjectFromFile();
		books.add(book);
		storeBooksObjectInFile(books);
	}

	public static void deleteBook(final String bookTitle) {
		Set<Book> books = readBooksObjectFromFile();
		Optional<Book> searchedBook = searchBook(bookTitle);
		if (searchedBook.isPresent())
			books.remove(searchedBook.get());
		storeBooksObjectInFile(books);
	}

	public static Optional<Book> searchBook(final String bookTitle) {
		Set<Book> books = readBooksObjectFromFile();
		return (books.stream().filter(book -> bookTitle.equalsIgnoreCase(book.getTitle())).findFirst());
	}

	public static Optional<Subject> searchSubject(final String subjectTitle) {
		Set<Subject> subjects = readSubjectsFromFile();
		return (subjects.stream().filter(s -> subjectTitle.equals(s.getSubtitle()))).findFirst();
	}

	public static void deleteSubject(final String subjectTitle) {
		Set<Subject> subjects = readSubjectsFromFile();
		Optional<Subject> subject = searchSubject(subjectTitle);
		if (subject.isPresent())
			removeAllBooksInSubject(subject.get());
		subjects.remove(subject.get());
		storeSubjectsInFile(subjects);
	}

	public static void addSubject(final Subject subject) {
		Set<Subject> subjects = readSubjectsFromFile();
		subjects.add(subject);
		addAllBooksInSubject(subject);
		storeSubjectsInFile(subjects);
	}

	public static void addAllBooksInSubject(final Subject subject) {
		Set<Book> books = readBooksObjectFromFile();
		books.addAll(subject.getBooks());
		storeBooksObjectInFile(books);
	}

	public static void removeAllBooksInSubject(final Subject subject) {
		Set<Book> books = readBooksObjectFromFile();
		books.remove(subject.getBooks());
		storeBooksObjectInFile(books);
	}

	public static String showSubjectsAndGetSubjectId() {
		Set<Subject> subjects = readSubjectsFromFile();
		subjects.stream().forEach(s -> System.out.println(s));
		System.out.println("Enter Subject Title to delete");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static String showBooksAndGetBookId() {
		Set<Book> books = readBooksObjectFromFile();
		books.stream().forEach(s -> System.out.println(s));
		System.out.println("Enter Book Title to delete");
		final Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static void storeBooksObjectInFile(final Set<Book> books) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Books.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(books);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in Books.ser \n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void storeSubjectsInFile(final Set<Subject> subjects) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Subjects.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(subjects);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in Subjects.ser \n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Set<Book> readBooksObjectFromFile() {
		Set<Book> books = new TreeSet<Book>();
		try {
			FileInputStream fileIn = new FileInputStream("Books.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			books = (Set<Book>) in.readObject();
			in.close();
			fileIn.close();
			System.out.printf("Deserialized data from Books.ser \n");

		} catch (IOException | ClassNotFoundException i) {
			i.printStackTrace();
		}
		return books;
	}

	@SuppressWarnings("unchecked")
	public static Set<Subject> readSubjectsFromFile() {
		Set<Subject> subjects = new TreeSet<Subject>();
		try {
			FileInputStream fileIn = new FileInputStream("Subjects.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			subjects = (Set<Subject>) in.readObject();
			in.close();
			fileIn.close();
			System.out.printf("Deserialized data from Subject.ser \n");

		} catch (IOException | ClassNotFoundException i) {
			i.printStackTrace();
		}
		return subjects;
	}
}
