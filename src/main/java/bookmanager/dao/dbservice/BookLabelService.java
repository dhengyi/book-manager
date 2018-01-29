package bookmanager.dao.dbservice;

import bookmanager.model.po.BookLabelPO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookLabelService {
    // 通过parentId得到此标签的所有信息
    List<BookLabelPO> getBookLabelByParentId(int parentId);

    // 通过标签名得到标签的id
    int getPkIdByName(String name);




    
    List<BookLabelPO> getParentLabelsByParentId(int parentId);

    List<BookLabelPO> getChildrenLabelsByParentId(int parentId);

    void insertNewLabel(BookLabelPO label);

    String getNameByPkId(int labelId);
}
