package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookRelationLabelServiceImpl implements BookRelationLabelService {

    // 该数据库操作方法还没有实现
    public List<BookInfoPO> getListBookInfoByLabelId(int labelId) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public void save(BookRelationLabelPO bookRelationLabel) {

    }
}
