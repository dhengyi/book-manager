package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookInfoServiceImpl implements BookInfoService {
    private JdbcOperations jdbcOperations;

    private final static String GET_BOOK_COUNT = "SELECT COUNT(*) AS COUNT FROM book_info WHERE amount > 0";

    private final static String GET_BOOK_COUNT_BY_LABELID = "SELECT COUNT(*) AS COUNT FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0";

    private final static String GET_ONE_PAGE_BOOKINFO = "SELECT * FROM book_info WHERE amount > 0 ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BOOK_BY_LABELID_AND_PAGE = "SELECT * FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0 ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BOOK_BY_LABEL_AND_PAGE_TYPESCONTROLLER = "SELECT * FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id = ?) AND amount > 0 ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BOOK_COUNT_BY_LABEL_TYPESCONTROLLER = "SELECT COUNT(*) AS COUNT FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id = ?) AND amount > 0";

    private final static String GET_BOOK_BY_NAO_BY_PAGE = "SELECT * FROM book_info WHERE (" +
            "ugk_name LIKE ? OR author LIKE ?) UNION SELECT * FROM book_info WHERE " +
            "ugk_uid IN (SELECT uid FROM cs_user WHERE name LIKE ?) AND amount > 0 ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BOOK_COUNT_BY_UID = "SELECT COUNT(*) FROM book_info WHERE ugk_uid = ?";

    private final static String GET_UPLOAD_BOOK_BY_UID_AND_PAGE = "SELECT * FROM book_info WHERE ugk_uid = ? ORDER BY pk_id DESC LIMIT ?, ?";

    private final static String GET_BORROW_BOOK_BY_UID = "SELECT * FROM book_info WHERE pk_id IN (SELECT book_info_pk_id FROM borrow_info WHERE cs_user_uid = ?) LIMIT ?, ?";

    private final static String DELETE_BOOK_BY_BOOKINFOPO = "DELETE FROM book_info WHERE pk_id = ?";

    private final static String UPDATE_BOOK_COUNT_BY_BOOKID = "UPDATE book_info SET amount=amount+1 WHERE pk_id = ?";

    private final static String INSERT_BOOK_INFO = "INSERT INTO book_info (ugk_name, author, ugk_uid, amount, upload_date, book_picture, describ)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final static String QUERY_BOOK_INFO_PKID_BY_UGK_NAME_AND_UGK_UID = "SELECT pk_id FROM book_info " +
            "WHERE ugk_name = ? AND ugk_uid = ?";

    private final static String GET_BOOKINFO_BY_BOOKID = "SELECT * FROM book_info WHERE pk_id = ?";

    private final static String GET_BOOK_COUNT_BY_NAO = "SELECT COUNT(*) FROM (SELECT * FROM book_info WHERE (" +
            "ugk_name LIKE ? OR author LIKE ?) AND amount > 0 UNION SELECT * FROM book_info WHERE " +
            "ugk_uid IN (SELECT uid FROM cs_user WHERE name LIKE ?) AND amount > 0) AS temp";

    private final static String DEC_BOOK_COUNT_BY_BOOKID = "UPDATE book_info SET amount=amount-1 WHERE pk_id = ?";

    @Autowired
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Integer getBookCount() {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT, Integer.class);
    }

    @Override
    public Integer getBookCountByLabelId(int labelId) {
        if (labelId >= 13) {
            return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_LABEL_TYPESCONTROLLER, Integer.class, labelId);
        } else {
            return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_LABELID, Integer.class, labelId);
        }
    }

    @Override
    public List<BookInfoPO> getBookByPage(PagePO page) {
        return jdbcOperations.query(GET_ONE_PAGE_BOOKINFO,
                JdbcRowMapper.newInstance(BookInfoPO.class), page.getBeginIndex(), page.getEveryPage());
    }

    @Override
    public List<BookInfoPO> getBookByLabelIdAndPage(PagePO pagePO, int labelId) {
        if (labelId >= 13) {
            return jdbcOperations.query(GET_BOOK_BY_LABEL_AND_PAGE_TYPESCONTROLLER,
                    JdbcRowMapper.newInstance(BookInfoPO.class), labelId, pagePO.getBeginIndex(), pagePO.getEveryPage());
        } else {
            return jdbcOperations.query(GET_BOOK_BY_LABELID_AND_PAGE,
                    JdbcRowMapper.newInstance(BookInfoPO.class), labelId, pagePO.getBeginIndex(), pagePO.getEveryPage());
        }
    }

    @Override
    public Integer getBookCountByNAO(String keyWord) {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_NAO, Integer.class, keyWord, keyWord, keyWord);
    }

    @Override
    public List<BookInfoPO> getBookInfoByNAOAndPage(String keyWord, PagePO pagePO) {
        return jdbcOperations.query(GET_BOOK_BY_NAO_BY_PAGE,
                JdbcRowMapper.newInstance(BookInfoPO.class), keyWord, keyWord, keyWord, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    @Override
    public int getUploadBookCountByUid(int uid) {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_UID, int.class, uid);
    }

    @Override
    public List<BookInfoPO> getUploadBookByUidAndPage(int uid, PagePO pagePO) {
        return jdbcOperations.query(GET_UPLOAD_BOOK_BY_UID_AND_PAGE, JdbcRowMapper.newInstance(BookInfoPO.class), uid, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    @Override
    public List<BookInfoPO> getBorrowBookByUid(int uid, PagePO pagePO) {
        return jdbcOperations.query(GET_BORROW_BOOK_BY_UID, JdbcRowMapper.newInstance(BookInfoPO.class), uid, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    @Override
    public void deleteBookByBookId(int pkId) {
        jdbcOperations.update(DELETE_BOOK_BY_BOOKINFOPO, pkId);
    }

    @Override
    public BookInfoPO getBookInfoByBookId(int id) {
        return (BookInfoPO) jdbcOperations.queryForObject(GET_BOOKINFO_BY_BOOKID, JdbcRowMapper.newInstance(BookInfoPO.class), id);
    }

    @Override
    public void updateBookCountByBookId(int bookId) {
        jdbcOperations.update(UPDATE_BOOK_COUNT_BY_BOOKID, bookId);
    }

    @Override
    public boolean save(BookInfoPO bookInfoPO) {
        try {
            jdbcOperations.update(INSERT_BOOK_INFO, bookInfoPO.getUgkName(), bookInfoPO.getAuthor(),
                    bookInfoPO.getUgkUid(), bookInfoPO.getAmount(), bookInfoPO.getUploadDate(), bookInfoPO.getBookPicture(), bookInfoPO.getDescrib());
        } catch (DataIntegrityViolationException e) {
            return false;
        }

        return true;
    }

    @Override
    public int getBookIDByBookNameAndUID(String bookName, int uid) {
        try {
            return jdbcOperations.queryForObject(QUERY_BOOK_INFO_PKID_BY_UGK_NAME_AND_UGK_UID,
                    int.class, bookName, uid);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public void decBookCountByBookId(int bookId) {
        jdbcOperations.update(DEC_BOOK_COUNT_BY_BOOKID, bookId);
    }
}