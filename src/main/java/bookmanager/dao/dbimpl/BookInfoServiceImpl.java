package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookLabelPO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookInfoServiceImpl implements BookInfoService {
    private JdbcOperations jdbcOperations;
    private final static String INSERT_BOOK_INFO = "INSERT INTO book_info (ugk_name, author, ugk_uid, amount, upload_date, book_picture, describ)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String QUERY_Book_Info_PKID_BY_UGK_NAME_AND_UGK_UID = "SELECT pk_id FROM book_info " +
            "WHERE ugk_name = ? AND ugk_uid = ?";
    private final static String QUERY_BOOK_INFO_BY_UGK_NAME_AND_UGK_UID = "SELECT * FROM book_info " +
            "WHERE ugk_name = ? AND ugk_uid = ?";

    @Inject
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    // 该数据库操作方法还没有实现
    public void save(BookInfoPO bookInfoPO) {
        jdbcOperations.update(INSERT_BOOK_INFO, bookInfoPO.getUgkName(), bookInfoPO.getAuthor(),
                bookInfoPO.getUgkUid(), bookInfoPO.getAmount(), bookInfoPO.getUploadDate(), bookInfoPO.getBookPicture(), bookInfoPO.getDescrib());
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

    @Override
    public int getBookIDByBookNameAndUID(String bookName, int uid) {
        try {
            return jdbcOperations.queryForObject(QUERY_Book_Info_PKID_BY_UGK_NAME_AND_UGK_UID,
                    int.class, bookName, uid);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public BookInfoPO getBookInfoByBookNameAndUID(String bookName, int uid) {
        try {
            return jdbcOperations.queryForObject(QUERY_BOOK_INFO_BY_UGK_NAME_AND_UGK_UID, new BookInfoRowMapper(),
                    bookName, uid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    private final static class BookInfoRowMapper implements RowMapper<BookInfoPO> {
        public BookInfoPO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new BookInfoPO(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getDate(6).toString(),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
    }
}
