package bookmanager.dao.dbservice;

import bookmanager.model.po.BorrowInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface BorrowInfoService {
    // 向借阅表中插入一条信息
    void save(BorrowInfoPO borrowInfo);

    // 根据uid拿到借阅图书的总数
    int getBorrowCountByUid(int uid);

    // 根据图书的pk_id得到本书的借阅次数
    int getBorrowCount(int bookInfoPkId);

    BorrowInfoPO checkBookIsBorrow(BorrowInfoPO borrowInfoPO);
}
