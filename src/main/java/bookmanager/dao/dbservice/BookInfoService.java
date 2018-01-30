package bookmanager.dao.dbservice;

import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookInfoService {
    // 得到所有书籍总记录数
    Integer getBookCount();

    // 得到某一分类下的书籍总记录数
    Integer getBookCountByLabelId(int labelId);

    // 得到当前页的图书信息
    List<BookInfoPO> getBookByPage(PagePO page);

    // 得到某一分类下的当前页图书信息
    List<BookInfoPO> getBookByLabelIdAndPage(PagePO pagePO, int labelId);

    // 根据书名/作者/所属者进行模糊查询出书籍总量
    Integer getBookCountByNAO(String keyWord);

    // 根据书名/作者/所属者进行模糊查询出当前页书籍的信息
    List<BookInfoPO> getBookInfoByNAOAndPage(String keyWord, PagePO pagePO);

    // 根据uid拿到上传图书的总数
    int getUploadBookCountByUid(int uid);

    // 根据uid得到当前页上传的图书信息
    List<BookInfoPO> getUploadBookByUidAndPage(int uid, PagePO pagePO);

    // 根据uid得到某页借阅的图书
    List<BookInfoPO> getBorrowBookByUid(int uid, PagePO pagePO);

    // 根据bookId删除图书信息
    void deleteBookByBookId(int bookId);

    // 根据bookId查询一本书
    BookInfoPO getBookInfoByBookId(int bookId);

    // 向书籍信息表中插入一条数据
    boolean save(BookInfoPO bookInfo);

    // 通过bookId更新图书的数量
    void updateBookCountByBookId(int bookId);

    // 通过书籍名与uid查询书籍的id
    int getBookIDByBookNameAndUID(String bookName, int uid);

    // 通过bookId减少书籍的数量
    void decBookCountByBookId(int bookId);
}
