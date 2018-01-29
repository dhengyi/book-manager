package bookmanager.dao.dbservice;

import bookmanager.model.po.ReturnInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface ReturnInfoService {
    // 根据uid和PkId归还图书
    void returnBookByUserAndPkId(ReturnInfoPO returnInfoPO);
}
