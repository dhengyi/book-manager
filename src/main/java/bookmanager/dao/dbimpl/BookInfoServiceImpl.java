package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.model.po.BookInfoPO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookInfoServiceImpl implements BookInfoService {
    JdbcOperations jdbcOperations;
    private final static String INSERT_BOOK_INFO = "INSERT INTO book_info (ugk_name, author, ugk_uid, amount, upload_date, book_picture, describ)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Inject
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    // 该数据库操作方法还没有实现
    public void save(BookInfoPO bookInfoPO) {
        jdbcOperations.update(INSERT_BOOK_INFO, bookInfoPO.getUgkName(), bookInfoPO.getAuthor(),
                bookInfoPO.getUgkUid(), bookInfoPO.getAmount(), bookInfoPO.getDescrib());
    }

    // 该数据库操作方法还没有实现
    public BookInfoPO getBookInfoByBookId(int bookId) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public List<BookInfoPO> getListBookInfoByNAO(String keywords) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public void updateBookInfo(BookInfoPO bookInfo) {

    }
}
