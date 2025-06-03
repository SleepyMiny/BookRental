package daelim.book.rental.minyoung.admin;

import daelim.book.rental.minyoung.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 📌 도서 수정 폼 조회
    @GetMapping("/modifyBookForm")
    public String modifyBookForm(Model model) {
        model.addAttribute("book", new BookVo());
        return "admin/book/modify_book_form"; // JSP 경로
    }

    // 📌 도서 수정 처리
    @PostMapping("/modifyBook")
    public String modifyBook(@ModelAttribute BookVo bookVo) {
        bookService.modifyBook(bookVo);
        return "redirect:/admin/book/getAllBooks"; // ✅ 정리된 URL로 이동
    }

    @GetMapping("/getAllBooks")
    public String getAllBooks(Model model) {
        List<BookVo> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);
        return "admin/book/book_list"; // => /WEB-INF/views/admin/book/bookList.jsp
    }

    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam("no") int no, Model model) {
        BookVo book = bookService.getBookByNo(no);
        model.addAttribute("book", book);
        return "admin/book/book_detail";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("no") int no) {
        bookService.deleteBook(no);
        return "redirect:/admin/book/getAllBooks";
    }

}
