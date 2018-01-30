package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookRelationLabelPO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookRelationLabelServiceImpl implements BookRelationLabelService {
    private JdbcOperations jdbcOperations;

    private final static String SAVE = "INSERT INTO book_relation_label (book_info_pk_id, book_label_pk_id) " +
            "VALUES (?, ?)";

    @Inject
    public BookRelationLabelServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void save(BookRelationLabelPO bookRelationLabel) {
        jdbcOperations.update(SAVE, bookRelationLabel.getBookInfoPkId(), bookRelationLabel.getBookLabelPkId());
    }
}