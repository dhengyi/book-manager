package bookmanager.dao.dbservice;

import bookmanager.model.po.BorrowInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface BorrowInfoService {
    // 根据bookId查询有多少条借阅记录
    int getBorrowCountByBookId(int bookInfoPkId);

    // 根据uid查询有多少条借阅记录
    int getBorrowCountByUid(int uid);

    // 根据uid和书籍的pkId删除这本书在借阅表中的信息
    void deleteBorrowInfoByBookIdAndUid(int bookId, int uid);

    // 向借阅表中插入一条信息
    void save(BorrowInfoPO borrowInfo);

    // 根据bookId和uid判断用户是否借阅过此书
    int checkBookIsBorrow(int bookId, int uid);
}
