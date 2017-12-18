package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.model.po.BookLabelPO;
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
public class BookLabelServiceImpl implements BookLabelService {
    JdbcOperations jdbcOperations;
    private final static String QUERY_PARENT_LABELS_BY_PARENT_ID = "SELECT * FROM book_label " +
            "WHERE parent_id = ?";
    private final static String QUERY_CHILDREN_LABELS_BY_PARENT_ID = "SELECT * FROM book_label " +
            "WHERE parent_id <> ?";
    private final static String QUERY_CHILDREN_PKID_BY_PARENT_NAME = "SELECT pk_id FROM book_label" +
            "WHERE name = ?";
    private final static String INSERT_NEW_LABEL = "INSERT INTO book_label (name, parent_id)" +
            "VALUES (?, ?)";

    @Inject
    public BookLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<BookLabelPO> getParentLabelsByParentId(int parentId) {
        return jdbcOperations.query(QUERY_PARENT_LABELS_BY_PARENT_ID,
                new LabelRowMapper(), parentId);
    }

    @Override
    public List<BookLabelPO> getChildrenLabelsNyByParentId(int parentId) {
        return jdbcOperations.query(QUERY_CHILDREN_LABELS_BY_PARENT_ID,
                new LabelRowMapper(), parentId);
    }

    @Override
    public int getParentLabelIdByParentLabelName(String parentLabelName) {
        return  jdbcOperations.queryForObject(QUERY_CHILDREN_PKID_BY_PARENT_NAME,
                int.class, parentLabelName);
    }

    @Override
    public void insertNewLabel(BookLabelPO label) {
        jdbcOperations.update(INSERT_NEW_LABEL, label.getName(), label.getParentId());
    }

    private final static class LabelRowMapper implements RowMapper<BookLabelPO> {
        public BookLabelPO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new BookLabelPO(
                    resultSet.getInt("pk_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("parent_id")
            );
        }
    }
}
