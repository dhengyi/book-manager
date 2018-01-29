package bookmanager.dao.dbservice;

import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BorrowInfoService {
    // 向借阅表中插入一条信息
    void save(BorrowInfoPO borrowInfo);

    // 查询有多少条借阅记录
    Integer getBorrowInfoCount();

    // 根据uid查询有多少条借阅记录
    int getBorrowCountByUid(int uid);

    // 根据图书的pk_id得查询有多少条借阅记录
    int getBorrowCountByBookId(int bookInfoPkId);

    // 得到一页(10条)的借阅表里的时间/借书者/书名
    List<BorrowInfoVO> getBorrowInfoVOByPage(PagePO pagePO);

    // 得到一页(10条)的借阅表里的书籍所属者
    List<String> getBorrowInfoOwnerByPage(PagePO pagePO);

    // 根据bookId和uid判断用户是否借阅过此书
    int checkBookIsBorrow(int bookId, int uid);

    // 根据uid和书籍的pkId删除这本书在借阅表中的信息
    void deleteBorrowInfo(int pkId, int uid);
}
