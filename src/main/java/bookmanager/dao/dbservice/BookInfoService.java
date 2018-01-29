package bookmanager.dao.dbservice;

import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookInfoService {
    // 得到某一级分类下的所有书
//    List<BookInfoPO> getBookInfoByBookLabelParentId(int bookParentId);

    // 得到书籍总记录数
    Integer getBookCount();

    // 得到某一分类下的图书的总本数
    Integer getBookCountByLabelId(int labelId);

    // 得到一页的图书信息
    List<BookInfoPO> getBookByPage(PagePO page);

    // 得到某一分类下的一页图书信息
    List<BookInfoPO> getBookByLabelIdAndPage(PagePO pagePO, int labelId);

    // 根据书名/作者/所属者进行模糊查询
    List<BookInfoPO> getBookInfoByNAOByPage(String keyWord, PagePO pagePO);

    // 根据书名/作者/所属者进行模糊查询出书籍总量
    Integer getBookCountByNAO(String keyWord);

    // 得到一页的图书信息的uid
    List<Integer> getBookInfoUidByPage(PagePO pagePO);

    // 根据书名/作者/所属者进行模糊查询
    List<BookInfoPO> getBookInfoByNAO(String keyWord);

    // 向书籍信息表中插入一条数据
    boolean save(BookInfoPO bookInfo);

    // 通过书籍名与UID查询书籍的ID
    int getBookIDByBookNameAndUID(String bookName, int uid);

    // 根据书的id查询一本书
    BookInfoPO getBookInfoById(int id);

    // 根据UID得到某页借阅的图书
    List<BookInfoPO> getBorrowBookByUid(int uid, PagePO pagePO);

    // 根据UID得到某页上传的图书
    List<BookInfoPO> getUploadBookByUid(int uid, PagePO pagePO);

    // 根据uid拿到上传图书的总数
    int getBookCountByUid(int uid);

    // 根据pk_id删除图书信息
    void deleteBook(int pkId);

    BookInfoPO getBookByPkId(int bookInfoPkId);

    String getParentBookLabel(int pk_id);

    String getChildBookLabel(int pk_id);

    void updateBook(BookInfoPO bookInfoPO);

    void updateBookCountByPkId(int pkId);
}
