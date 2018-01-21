package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final static String GET_BOOKINFO_BY_BOOKLABEL_PARENT_ID =
            "SELECT * FROM book_info where pk_id IN (" +
                    "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
                    "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0";
    private final static String GET_BOOK_COUNT = "SELECT COUNT(*) AS COUNT FROM book_info WHERE amount > 0";
    private final static String GET_ONE_PAGE_BOOKINFO = "SELECT * FROM book_info WHERE amount >0 LIMIT ? , ?";
    private final static String GET_ONE_PAGE_BOOKINFO_UID = "SELECT pk_id FROM book_info where amount > 0 LIMIT ?, ?";
    private final static String GET_BOOK_BY_LABEL_AND_PAGE = "SELECT * FROM book_info where pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0 LIMIT ?, ?";
    private final static String GET_BOOK_BY_LABEL_AND_PAGE_TYPESCONTROLLER = "SELECT * FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id = ?) AND amount > 0 ORDER BY pk_id DESC LIMIT ?, ?";
    private final static String GET_BOOK_COUNT_BY_LABEL = "SELECT COUNT(*) AS COUNT FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0";
    private final static String GET_BOOK_COUNT_BY_LABEL_TYPESCONTROLLER = "SELECT COUNT(*) AS COUNT FROM book_info WHERE pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id = ?) AND amount > 0";
    private final static String GET_BOOK_BY_NAO = "SELECT * FROM book_info WHERE (" +
            "ugk_name LIKE ? or author LIKE ?) UNION SELECT * FROM book_info WHERE " +
            "ugk_uid IN((SELECT uid FROM cs_user WHERE name LIKE ?))";
    private final static String INSERT_BOOK_INFO = "INSERT INTO book_info (ugk_name, author, ugk_uid, amount, upload_date, book_picture, describ)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String QUERY_BOOK_INFO_PKID_BY_UGK_NAME_AND_UGK_UID = "SELECT pk_id FROM book_info " +
            "WHERE ugk_name = ? AND ugk_uid = ?";
    private final static String GET_BOOKINFO_BY_ID = "select * from book_info where pk_id = ?";
    private final static String GET_BORROW_BOOK_BY_UID = "SELECT * FROM book_info WHERE pk_id IN (SELECT book_info_pk_id FROM borrow_info WHERE cs_user_uid = ?)";
    private final static String GET_UPLOAD_BOOK_BY_UID = "SELECT * FROM book_info WHERE ugk_uid = ?";
    private final static String GET_BOOK_COUNT_BY_UID = "SELECT COUNT(*) FROM book_info WHERE uid = ?";
    private final static String DELETE_BOOK_BY_BOOKINFOPO = "DELETE FROM book_info WHERE pk_id = ? AND ugk_uid = ?";
    private final static String GET_BOOK_BY_PKID = "SELECT * FROM book_info WHERE pk_id = ?";
    private final static String GET_PARENT_BOOK_CLASS_BY_PK_ID = "SELECT name FROM book_label WHERE pk_id IN (SELECT parent_id FROM book_label WHERE pk_id IN (SELECT label_tree_pk_id FROM book_relation_label WHERE book_info_pk_id = ?))";
    private final static String GET_CHILD_BOOK_CLASS_BY_PK_ID = "SELECT name FROM book_label WHERE pk_id IN (SELECT label_tree_pk_id FROM book_relation_label WHERE book_info_pk_id = ?)";
    private final static String UPDATE_BOOK_BY_ID = "UPDATE book_info SET ugk_name = ?,author = ?,amount = ?,describ = ? WHERE pk_id = ?";

    @Autowired
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    // 通过一级分类的ID查询该一级分类下的所有的书
    public List<BookInfoPO> getBookInfoByBookLabelParentId(int bookParentId) {
        return jdbcOperations.query(GET_BOOKINFO_BY_BOOKLABEL_PARENT_ID,
                JdbcRowMapper.newInstance(BookInfoPO.class), bookParentId);
    }

    public Integer getBookCount() {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT, Integer.class);
    }

    public List<BookInfoPO> getBookByPage(PagePO page) {
        return jdbcOperations.query(GET_ONE_PAGE_BOOKINFO,
                JdbcRowMapper.newInstance(BookInfoPO.class), page.getBeginIndex(), page.getEveryPage());
    }

    public List<Integer> getBookInfoUidByPage(PagePO pagePO) {
        return jdbcOperations.queryForList(GET_ONE_PAGE_BOOKINFO_UID,
                Integer.class, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    public List<BookInfoPO> getBookByLabelAndPage(PagePO pagePO, int labelid) {
        System.out.println("everypage: " + pagePO.getEveryPage());
        if (labelid >= 13) {
            return jdbcOperations.query(GET_BOOK_BY_LABEL_AND_PAGE_TYPESCONTROLLER,
                    JdbcRowMapper.newInstance(BookInfoPO.class), labelid, pagePO.getBeginIndex(), pagePO.getEveryPage());
        } else {
            return jdbcOperations.query(GET_BOOK_BY_LABEL_AND_PAGE,
                    JdbcRowMapper.newInstance(BookInfoPO.class), labelid, pagePO.getBeginIndex(), pagePO.getEveryPage());
        }
    }

    public Integer getBookCountByLabel(int labelId) {
        if (labelId >= 13) {
            return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_LABEL_TYPESCONTROLLER, Integer.class, labelId);
        } else {
            return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_LABEL, Integer.class, labelId);
        }
    }

    public List<BookInfoPO> getBookInfoByNAO(String keyWord) {
        return jdbcOperations.query(GET_BOOK_BY_NAO,
                JdbcRowMapper.newInstance(BookInfoPO.class), keyWord, keyWord);
    }

    public void save(BookInfoPO bookInfoPO) {
        jdbcOperations.update(INSERT_BOOK_INFO, bookInfoPO.getUgkName(), bookInfoPO.getAuthor(),
                bookInfoPO.getUgkUid(), bookInfoPO.getAmount(), bookInfoPO.getUploadDate(), bookInfoPO.getBookPicture(), bookInfoPO.getDescrib());
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
    public BookInfoPO getBookInfoById(int id) {
        return (BookInfoPO) jdbcOperations.queryForObject(GET_BOOKINFO_BY_ID, JdbcRowMapper.newInstance(BookInfoPO.class), id);
    }

    @Override
    public List<BookInfoPO> getBorrowBookByUid(int uid, PagePO pagePO) {
        return jdbcOperations.query(GET_BORROW_BOOK_BY_UID, JdbcRowMapper.newInstance(BookInfoPO.class), uid);
    }

    @Override
    public List<BookInfoPO> getUploadBookByUid(int uid, PagePO pagePO) {
        return jdbcOperations.query(GET_UPLOAD_BOOK_BY_UID, JdbcRowMapper.newInstance(BookInfoPO.class), uid);
    }

    @Override
    public int getBookCountByUid(int uid) {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_UID, int.class, uid);
    }

    @Override
    public void deleteBook(BookInfoPO bookInfoPO) {
        jdbcOperations.update(DELETE_BOOK_BY_BOOKINFOPO, bookInfoPO.getPkId(), bookInfoPO.getUgkUid());
    }

    @Override
    public BookInfoPO getBookByPkId(int bookInfoPkId) {
        List<BookInfoPO> query = jdbcOperations.query(GET_BOOK_BY_PKID, JdbcRowMapper.newInstance(BookInfoPO.class), bookInfoPkId);
        return query.get(0);
    }

    @Override
    public String getParentBookLabel(int pk_id) {
        return jdbcOperations.queryForObject(GET_PARENT_BOOK_CLASS_BY_PK_ID,String.class,pk_id);
    }

    @Override
    public String getChildBookLabel(int pk_id) {
        return jdbcOperations.queryForObject(GET_CHILD_BOOK_CLASS_BY_PK_ID,String.class,pk_id);
    }

    @Override
    public void updateBook(BookInfoPO bookInfoPO) {
        jdbcOperations.update(UPDATE_BOOK_BY_ID,bookInfoPO.getUgkName(),bookInfoPO.getAuthor(),bookInfoPO.getAmount(),bookInfoPO.getDescrib(),bookInfoPO.getPkId());
    }
}
