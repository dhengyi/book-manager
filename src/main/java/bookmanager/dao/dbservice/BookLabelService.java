package bookmanager.dao.dbservice;

import bookmanager.model.po.BookLabelPO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookLabelService {

    List<BookLabelPO> getParentLabelsByParentId(int parentId);

    List<BookLabelPO> getChildrenLabelsNyByParentId(int parentId);

    void insertNewLabel(BookLabelPO label);

    int getPkIdByName(String name);
}
