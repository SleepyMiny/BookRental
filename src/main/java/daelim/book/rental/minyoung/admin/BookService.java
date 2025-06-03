package daelim.book.rental.minyoung.admin;

import daelim.book.rental.minyoung.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    // 도서 조회
    public BookVo getBookByNo(int no) {
        return bookDao.selectBookByNo(no);
    }

    // 도서 수정
    public int modifyBook(BookVo bookVo) {
        return bookDao.insertbook(bookVo);
    }
    public List<BookVo> getAllBooks() {
        return bookDao.selectAllBooks();
    }

    public void deleteBook(int no) {
        bookDao.deleteBook(no);
    }
}
