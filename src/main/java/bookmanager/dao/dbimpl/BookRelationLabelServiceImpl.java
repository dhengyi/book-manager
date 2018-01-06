package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookRelationLabelServiceImpl implements BookRelationLabelService {
    private JdbcOperations jdbcOperations;
    private final static String INSERT_NEW_BOOK_RELATION_LABEL = "INSERT INTO book_relation_label (book_info_pk_id, label_tree_pk_id) " +
            "VALUES (?, ?)";

    @Inject
    public BookRelationLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    // 该数据库操作方法还没有实现
    public List<BookInfoPO> getListBookInfoByLabelId(int labelId) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public void save(BookRelationLabelPO bookRelationLabel) {
        jdbcOperations.update(INSERT_NEW_BOOK_RELATION_LABEL, bookRelationLabel.getBookInfoPkId(), bookRelationLabel.getBookLabelPkId());
    }
}
