package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookCommentService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookCommentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TreeSet;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookCommentServiceImpl implements BookCommentService {
    private JdbcOperations jdbcOperations;

    private final static String ADD_CONTENT_BY_USERANDBOOK = "INSERT INTO book_comment(book_info_pk_id, cs_user_id, content, comment_datetime) VALUES (?,?,?,?)";
    private final static String GET_CONTENT_BY_BOOK = "SELECT * FROM book_comment WHERE book_info_pk_id = ?";

    @Autowired
    public BookCommentServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void submitContent(BookCommentPO bookCommentPO) {
        jdbcOperations.update(ADD_CONTENT_BY_USERANDBOOK,bookCommentPO.getBookInfoPkId(),bookCommentPO.getCsUserId(),bookCommentPO.getContent(),bookCommentPO.getCommentDatetime());
    }

    public List<BookCommentPO> getContent(Integer bookInfoPkId) {
        return jdbcOperations.query(GET_CONTENT_BY_BOOK, JdbcRowMapper.newInstance(BookCommentPO.class),bookInfoPkId);
    }
}
