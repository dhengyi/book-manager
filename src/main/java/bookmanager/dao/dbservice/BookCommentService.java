package bookmanager.dao.dbservice;

import bookmanager.model.po.BookCommentPO;

/**
 * Created by dela on 11/23/17.
 */
public interface BookCommentService {

    // 向评论表中插入一条数据
    void save(BookCommentPO bookComment);
}
