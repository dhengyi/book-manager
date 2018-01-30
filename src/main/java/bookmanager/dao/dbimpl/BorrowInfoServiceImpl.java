package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.model.po.BorrowInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BorrowInfoServiceImpl implements BorrowInfoService {
    private JdbcOperations jdbcOperations;

    private final static String GET_BORROW_COUNT_BY_BOOK_ID = "SELECT COUNT(*) FROM borrow_info WHERE book_info_pk_id = ?";

    private final static String GET_BORROW_COUNT_BY_UID = "SELECT COUNT(*) FROM borrow_info WHERE cs_user_uid = ?";

    private static final String DELETE_BORROWINFO_BY_BOOKID_AND_UID = "DELETE FROM borrow_info WHERE book_info_pk_id = ? AND cs_user_uid = ?";

    private final static String SAVE = "INSERT INTO borrow_info(book_info_pk_id, cs_user_uid, borrow_date) VALUES(?, ?, ?)";

    private final static String CHECK_BOOK_IS_BORROW = "SELECT pk_id FROM borrow_info WHERE book_info_pk_id = ? AND cs_user_uid = ?";

    @Autowired
    public BorrowInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int getBorrowCountByBookId(int bookInfoPkId) {
        return jdbcOperations.queryForObject(GET_BORROW_COUNT_BY_BOOK_ID, Integer.class, bookInfoPkId);
    }

    @Override
    public int getBorrowCountByUid(int uid) {
        return jdbcOperations.queryForObject(GET_BORROW_COUNT_BY_UID, int.class, uid);
    }

    @Override
    public void deleteBorrowInfoByBookIdAndUid(int bookId, int uid) {
        jdbcOperations.update(DELETE_BORROWINFO_BY_BOOKID_AND_UID, bookId, uid);
    }

    @Override
    public void save(BorrowInfoPO borrowInfo) {
        jdbcOperations.update(SAVE, borrowInfo.getBookInfoPkId(), borrowInfo.getCsUserId(), borrowInfo.getBorrowDate());
    }

    @Override
    public int checkBookIsBorrow(int bookId, int uid) {
        try {
            return jdbcOperations.queryForObject(CHECK_BOOK_IS_BORROW, int.class, bookId, uid);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}