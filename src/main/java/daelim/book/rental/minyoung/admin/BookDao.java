package daelim.book.rental.minyoung.admin;

import daelim.book.rental.minyoung.BookVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 📌 1. 도서 1건 조회 by PK(no)
    public BookVo selectBookByNo(int no) {
        String sql = "SELECT * FROM TB_BOOK WHERE no = ?";
        List<BookVo> result = jdbcTemplate.query(sql, new RowMapper<BookVo>() {
            @Override
            public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                BookVo book = new BookVo();
                book.setNo(rs.getInt("no"));
                book.setThumbnail(rs.getString("thumbnail"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishYear(rs.getString("publishYear"));
                book.setIsbn(rs.getString("isbn"));
                book.setCallNumber(rs.getString("callNumber"));
                book.setRentalAble(rs.getInt("rentalAble") == 1);
                book.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
                book.setModDate(rs.getTimestamp("modDate").toLocalDateTime());
                return book;
            }
        }, no);

        return result.isEmpty() ? null : result.get(0);
    }

    // 📌 2. 도서 정보 수정
    public int updateBook(BookVo book) {
        String sql = "UPDATE TB_BOOK SET " +
                "name = ?, author = ?, publisher = ?, publishYear = ?, " +
                "isbn = ?, callNumber = ?, rentalAble = ?, modDate = NOW() " +
                "WHERE no = ?";

        return jdbcTemplate.update(sql,
                book.getName(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishYear(),
                book.getIsbn(),
                book.getCallNumber(),
                book.isRentalAble() ? 1 : 0,
                book.getNo()
        );
    }

    public List<BookVo> selectAllBooks() {
        String sql = "SELECT * FROM TB_BOOK ORDER BY regDate DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BookVo book = new BookVo();
            book.setNo(rs.getInt("no"));
            book.setThumbnail(rs.getString("thumbnail"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishYear(rs.getString("publishYear"));
            book.setIsbn(rs.getString("isbn"));
            book.setCallNumber(rs.getString("callNumber"));
            book.setRentalAble(rs.getInt("rentalAble") == 1);
            book.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
            book.setModDate(rs.getTimestamp("modDate").toLocalDateTime());
            return book;
        });
    }

    public int insertbook(BookVo bookVo) {
        String sql = "INSERT INTO TB_BOOK (thumbnail, name, author, publisher, publishYear, isbn, callNumber, rentalAble, regDate, modDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        return jdbcTemplate.update(sql,
                bookVo.getThumbnail(),
                bookVo.getName(),
                bookVo.getAuthor(),
                bookVo.getPublisher(),
                bookVo.getPublishYear(),
                bookVo.getIsbn(),
                bookVo.getCallNumber(),
                bookVo.isRentalAble() ? 1 : 0
        );
    }

    public int deleteBook(int no) {
        String sql = "DELETE FROM TB_BOOK WHERE no = ?";
        return jdbcTemplate.update(sql, no);
    }

}
