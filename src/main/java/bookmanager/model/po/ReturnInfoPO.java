package bookmanager.model.po;


import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 */

//归还时间表
@Table(name = "return_info")
public class ReturnInfoPO {
    @Column(name = "pk_id")
    private int pkId;                           //无意义主键

    @Column(name = "book_info_pk_id")
    private int bookInfoPkId;                   //bookInfo这张表里面的主键

    @Column(name = "cs_user_id")
    private int csUserId;                       //用户表里的id

    @Column(name = "return_date")
    private String returnDate;                  //归还日期

    public ReturnInfoPO() { }

    public ReturnInfoPO(int pkId, int bookInfoPkId, int csUserId, String returnDate) {
        this.pkId = pkId;
        this.bookInfoPkId = bookInfoPkId;
        this.csUserId = csUserId;
        this.returnDate = returnDate;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ReturnInfoPO{" +
                "pkId=" + pkId +
                ", bookInfoPkId=" + bookInfoPkId +
                ", csUserId=" + csUserId +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
