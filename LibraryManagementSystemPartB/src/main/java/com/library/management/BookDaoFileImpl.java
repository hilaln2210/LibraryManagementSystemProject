package com.library.management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoFileImpl implements IBookDao {
    private final File file = new File("books.txt");

    @Override
    public void addBook(Book book) {
        int nextId = getNextBookId();
        book = new Book(nextId, book.getTitle(), book.getAuthor(), book.getYear());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getNextBookId() {
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id >= maxId) {
                    maxId = id + 1; // increment to the next ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    @Override
    public Book getBook(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    return new Book(id, parts[1], parts[2], Integer.parseInt(parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                books.add(new Book(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        List<Book> books = getAllBooks();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear());
                } else {
                    writer.write(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getYear());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        List<Book> books = getAllBooks();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Book b : books) {
                if (b.getId() != id) {
                    writer.write(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getYear());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
