package bookmanager.dao.dbservice;

import bookmanager.model.po.BookCommentPO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookCommentService {
    // 向评论表中插入一条数据
    void submitContent(BookCommentPO bookCommentPO);

    List<BookCommentPO> getContent(Integer bookInfoPkId);
}
