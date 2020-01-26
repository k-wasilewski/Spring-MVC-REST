package org.example;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemoryBookService {
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {return list;}

    public void setList(List<Book> list) {this.list = list;}

    public Book getBook(long id) {
        for (Book b : this.list) {
            if (b.getId()==id) return b;
        }
        return null;
    }

    public void addBook(Book b) {
        this.list.add(b);
    }

    public void editBook(long id, Book b) {
        Book toRemove=null;

        for (Book book : this.list) {
            if (book.getId() == id) {
                System.out.println("book found at service: "+book);
                toRemove=book;
            }
        }
        if (toRemove!=null) {
            this.list.remove(toRemove);
            this.list.add(b);
        }
    }

    public void delBook(long id) {
        Book toDel=null;

        for (Book b : this.list) {
            if (b.getId()==id) toDel=b;
        }
        if (toDel!=null) this.list.remove(toDel);
    }

}
