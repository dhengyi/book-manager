package bookmanager.dao.dbservice;

import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookRelationLabelService {
    // 通过标签Id获取标签下的所有书籍信息
    List<BookInfoPO> getListBookInfoByLabelId(int labelId);

    // 在某标签下添加一本书籍信息
    void save(BookRelationLabelPO bookRelationLabel);

    int getBookCountUnderLabel(int labelId);

    // 通过图书id更新图书的分类id
    void updateByBookId(BookRelationLabelPO bookRelationLabelPO);
}
