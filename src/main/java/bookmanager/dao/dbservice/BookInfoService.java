package bookmanager.dao.dbservice;

import bookmanager.model.po.BookInfoPO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookInfoService {

    // 向书籍信息表中插入一条数据
    void save(BookInfoPO bookInfo);

    // 通过书籍id进行查询
    BookInfoPO getBookInfoByBookId(int bookId);

    // 通过关键字进行书名|作者|所属者模糊查询
    List<BookInfoPO> getListBookInfoByNAO(String keywords);

    // 修改一本书籍的信息
    void updateBookInfo(BookInfoPO bookInfo);

    // 通过书籍名与UID查询书籍的ID
    int getBookIDByBookNameAndUID(String bookName, int uid);

    // 通过书籍名与UID查询整本书的信息
    BookInfoPO getBookInfoByBookNameAndUID(String bookName, int uid);
}
