package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookCommentService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookCommentPO;
import bookmanager.model.po.PagePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookCommentServiceImpl implements BookCommentService {
    private JdbcOperations jdbcOperations;

    private final static String ADD_CONTENT_BY_USERANDBOOK = "INSERT INTO book_comment(book_info_pk_id, cs_user_id, content, comment_datetime) VALUES (?, ?, ?, ?)";

    private final static String GET_CONTENT_BY_BOOKID = "SELECT * FROM book_comment WHERE book_info_pk_id = ? ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BOOK_COMMENT_COUNT_BY_BOOKID = "SELECT COUNT(*) FROM book_comment WHERE book_info_pk_id = ?";

    @Autowired
    public BookCommentServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void submitContent(BookCommentPO bookCommentPO) {
        jdbcOperations.update(ADD_CONTENT_BY_USERANDBOOK,bookCommentPO.getBookInfoPkId(),bookCommentPO.getCsUserId(),bookCommentPO.getContent(),bookCommentPO.getCommentDatetime());
    }

    @Override
    public List<BookCommentPO> getContent(int bookInfoPkId, PagePO pagePO) {
        return jdbcOperations.query(GET_CONTENT_BY_BOOKID, JdbcRowMapper.newInstance(BookCommentPO.class), bookInfoPkId, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    @Override
    public int getBookCommentCountByBookId(int bookInfoPkId) {
        return jdbcOperations.queryForObject(GET_BOOK_COMMENT_COUNT_BY_BOOKID, int.class, bookInfoPkId);
    }
}
