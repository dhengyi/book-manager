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

    private final static String SAVE = "INSERT INTO book_relation_label (book_info_pk_id, book_label_pk_id) " +
            "VALUES (?, ?)";




    private final static String QUERY_BOOKCOUNT_UNDER_LABEL_BY_LABELID = "SELECT COUNT(book_info_pk_id) " +
            "FROM book_relation_label WHERE book_label_pk_id = ?";

    private final static String UPDATE_BOOKLABEL_BY_BOOKID = "UPDATE book_relation_label SET book_label_pk_id = ? WHERE book_info_pk_id = ?";

    @Inject
    public BookRelationLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void save(BookRelationLabelPO bookRelationLabel) {
        jdbcOperations.update(SAVE, bookRelationLabel.getBookInfoPkId(), bookRelationLabel.getBookLabelPkId());
    }






    @Override
    public int getBookCountUnderLabel(int labelId) {
        return jdbcOperations.queryForObject(QUERY_BOOKCOUNT_UNDER_LABEL_BY_LABELID, int.class, labelId);
    }

    @Override
    public void updateByBookId(BookRelationLabelPO bookRelationLabelPO) {
        jdbcOperations.update(UPDATE_BOOKLABEL_BY_BOOKID, bookRelationLabelPO.getBookLabelPkId(), bookRelationLabelPO.getBookInfoPkId());
    }

    private final static class BookIdRowMapper implements RowMapper<Integer> {
        public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return resultSet.getInt(1);
        }
    }
}
