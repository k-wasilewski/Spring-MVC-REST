package org.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private MemoryBookService memoryBookService;

    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }
    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/list")
    public List<Book> getList() {
        return this.memoryBookService.getList();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") long id) {
        for (Book b : this.memoryBookService.getList()) {
            if (b.getId()==id) return b;
        }
        return null;
    }

    //curl -d "isbn=324234&title=Potop&author=Sienkiewicz&publisher=Znak&type=fiction" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8085/books/add
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8080")
    public String addBook(@RequestParam String isbn, @RequestParam String title,
                          @RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
        int id = this.memoryBookService.getList().size();
        Book b = new Book(id, isbn, title, author, publisher, type);
        this.memoryBookService.addBook(b);
        return "redirect:/list";
    }

    //curl -v -X PUT 'localhost:8085/books/edit2?isbn=232&title=Potop&author=Sienkiewicz&publisher=Znak&type=fiction'
    @PutMapping(value = "/edit{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public String editBook(@PathVariable("id") int id, @RequestParam String isbn, @RequestParam String title,
                           @RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
        Book bo = new Book(id, isbn, title, author, publisher, type);
        System.out.println("new book at controller: "+bo);
        this.memoryBookService.editBook(id, bo);
        return "redirect:/list";
    }

    //curl -v -X DELETE 'localhost:8085/books/del2'
    @DeleteMapping("/del{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public String addBook(@PathVariable("id") long id) {
        this.memoryBookService.delBook(id);
        return "redirect:/list";
    }
}
