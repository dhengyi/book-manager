package bookmanager.dao.dbservice;

import bookmanager.model.po.ReturnInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface ReturnInfoService {
    // 添加归还信息
    void save(ReturnInfoPO returnInfoPO);
}
