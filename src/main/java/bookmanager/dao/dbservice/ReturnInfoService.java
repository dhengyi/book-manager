package bookmanager.dao.dbservice;

import bookmanager.model.po.ReturnInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface ReturnInfoService {
    //向归还表中插入一条信息
    void save(ReturnInfoPO returnInfo);

    void returnBookByUserAndPkId(ReturnInfoPO returnInfoPO);
}
