package bookmanager.dao.dbservice;

import bookmanager.model.po.BookCommentPO;
import bookmanager.model.po.PagePO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookCommentService {
    // 向评论表中插入一条数据
    void submitContent(BookCommentPO bookCommentPO);

    // 通过图书id得到此图书下的评论总数
    int getBookCommentCountByBookId(int bookInfoPkId);

    // 获取此书下当前页的评论
    List<BookCommentPO> getContent(int bookInfoPkId, PagePO pagePO);
}
