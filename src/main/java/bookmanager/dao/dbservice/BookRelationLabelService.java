package bookmanager.dao.dbservice;

import bookmanager.model.po.BookRelationLabelPO;

/**
 * Created by dela on 11/23/17.
 */
public interface BookRelationLabelService {
    // 在某标签下添加一本书籍信息
    void save(BookRelationLabelPO bookRelationLabel);
}
