package bookmanager.model.po;


import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 */

//书籍借阅关系表
@Table(name = "borrow_info")
public class BorrowInfoPO {
    @Column(name = "pk_id")
    private int pkId;                   //无意义主键

    @Column(name = "book_info_pk_id")
    private int bookInfoPkId;           //bookInfo这张表里面的主键

    @Column(name = "cs_user_id")
    private int csUserId;               //用户表里的id

    @Column(name = "borrow_date")
    private String borrowDate;          //借阅时间

    public BorrowInfoPO() { }

    public BorrowInfoPO(int bookInfoPkId, int csUserId, String borrowDate) {
        this.bookInfoPkId = bookInfoPkId;
        this.csUserId = csUserId;
        this.borrowDate = borrowDate;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public int getBookInfoPkId() {
        return bookInfoPkId;
    }

    public void setBookInfoPkId(int bookInfoPkId) {
        this.bookInfoPkId = bookInfoPkId;
    }

    public int getCsUserId() {
        return csUserId;
    }

    public void setCsUserId(int csUserId) {
        this.csUserId = csUserId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String toString() {
        return "BorrowInfoPO{" +
                "pkId=" + pkId +
                ", bookInfoPkId=" + bookInfoPkId +
                ", csUserId=" + csUserId +
                ", borrowDate='" + borrowDate + '\'' +
                '}';
    }
}