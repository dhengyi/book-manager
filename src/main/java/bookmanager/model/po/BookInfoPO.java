package bookmanager.model.po;


import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 *
 * @Description: 此PO已经操作了Comparable接口，在BookUserMapUtil中使用TreeMap进行存储，
 * 因此在数据库查询中不用对查询出来的数据进行排序
 */

// 书籍信息表
@Table(name = "book_info")
public class BookInfoPO implements Comparable<BookInfoPO> {
    @Column(name = "pk_id")
    private int pkId;                   // 无意义主键
    @Column(name = "ugk_name")
    private String ugkName;             // 书名(组合索引)
    @Column(name = "author")
    private String author;              // 作者
    @Column(name = "ugk_uid")
    private int ugkUid;                 // 所有者v (即用户表里的id)(组合索引)
    @Column(name = "amount")
    private int amount;                 // 数量
    @Column(name = "upload_date")
    private String uploadDate;          // 上传时间
    @Column(name = "book_picture")
    private String bookPicture;         // 书籍照片
    @Column(name = "describ")
    private String describ;             // 书籍描述

    public BookInfoPO() { }

    public BookInfoPO(int pkId, String ugkName, String author, int ugkUid, int amount, String uploadDate, String bookPicture, String describ) {
        this.pkId = pkId;
        this.ugkName = ugkName;
        this.author = author;
        this.ugkUid = ugkUid;
        this.amount = amount;
        this.uploadDate = uploadDate;
        this.bookPicture = bookPicture;
        this.describ = describ;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public String getUgkName() {
        return ugkName;
    }

    public void setUgkName(String ugkName) {
        this.ugkName = ugkName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUgkUid() {
        return ugkUid;
    }

    public void setUgkUid(int ugkUid) {
        this.ugkUid = ugkUid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUploadDate() {
        return uploadDate.substring(0, 19);
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public int compareTo(BookInfoPO o) {
        return this.getPkId() < o.getPkId() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "BookInfoPO{" +
                "pkId=" + pkId +
                ", ugkName='" + ugkName + '\'' +
                ", author='" + author + '\'' +
                ", ugkUid=" + ugkUid +
                ", amount=" + amount +
                ", uploadDate='" + uploadDate + '\'' +
                ", bookPicture='" + bookPicture + '\'' +
                ", describ='" + describ + '\'' +
                '}';
    }
}