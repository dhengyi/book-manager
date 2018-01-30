package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookLabelPO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookLabelServiceImpl implements BookLabelService {
    private JdbcOperations jdbcOperations;

    private static final String GET_BOOKLABEL_BY_PARENTID = "SELECT * FROM book_label WHERE parent_id = ?";

    private static final String GET_NAME_BY_PKID = "SELECT name FROM book_label WHERE pk_id = ?";

    private final static String QUERY_CHILDREN_LABELS_BY_PARENT_ID = "SELECT * FROM book_label " +
            "WHERE parent_id <> ?";

    private final static String QUERY_PK_ID_BY_NAME = "SELECT pk_id FROM book_label " +
            "WHERE name = ?";

    @Inject
    public BookLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<BookLabelPO> getBookLabelByParentId(int parentId) {
        return jdbcOperations.query(GET_BOOKLABEL_BY_PARENTID, JdbcRowMapper.newInstance(BookLabelPO.class), parentId);
    }

    @Override
    public String getNameByPkId(int labelId) {
        return jdbcOperations.queryForObject(GET_NAME_BY_PKID, String.class, labelId);
    }

    @Override
    public List<BookLabelPO> getChildrenLabelsByParentId(int parentId) {
        return jdbcOperations.query(QUERY_CHILDREN_LABELS_BY_PARENT_ID,
                JdbcRowMapper.newInstance(BookLabelPO.class), parentId);
    }

    @Override
    public int getPkIdByName(String parentLabelName) {
        return  jdbcOperations.queryForObject(QUERY_PK_ID_BY_NAME,
                int.class, parentLabelName);
    }
}
