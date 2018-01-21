package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;
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
public class BookRelationLabelServiceImpl implements BookRelationLabelService {
    private JdbcOperations jdbcOperations;
    private final static String INSERT_NEW_BOOK_RELATION_LABEL = "INSERT INTO book_relation_label (book_info_pk_id, book_label_pk_id) " +
            "VALUES (?, ?)";
    private final static String QUERY_BOOKID_BY_LABELID = "SELECT book_info_pk_id FROM book_relation_label WHERE label_tree_pk_id = ? " +
            "ORDER BY book_info_pk_id DESC LIMIT ?, ?";
    private final static String QUERY_BOOKCOUNT_UNDER_LABEL_BY_LABELID = "SELECT COUNT(book_info_pk_id) " +
            "FROM book_relation_label WHERE book_label_pk_id = ?";

    @Inject
    public BookRelationLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    // 该数据库操作方法还没有实现
    public List<BookInfoPO> getListBookInfoByLabelId(int labelId) {
        return null;
    }

    @Override
    // 该数据库操作方法还没有实现
    public void save(BookRelationLabelPO bookRelationLabel) {
        jdbcOperations.update(INSERT_NEW_BOOK_RELATION_LABEL, bookRelationLabel.getBookInfoPkId(), bookRelationLabel.getBookLabelPkId());
    }

    @Override
    public int getBookCountUnderLabel(int labelId) {
        return jdbcOperations.queryForObject(QUERY_BOOKCOUNT_UNDER_LABEL_BY_LABELID, int.class, labelId);
    }

    private final static class BookIdRowMapper implements RowMapper<Integer> {
        public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return resultSet.getInt(1);
        }
    }
}
