package bookmanager.model.po;


/**
 * Created by dela on 11/22/17.
 */

// 书籍信息表
public class BookInfoPO {
    private int pkId;                   // 无意义主键
    private String ugkName;             // 书名(组合索引)
    private String author;              // 作者
    private int ugkUid;                 // 所有者(即用户表里的id)(组合索引)
    private int amount;                 // 数量
    private String uploadDate;          // 上传时间
    private String bookPicture;         // 书籍照片
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
        return uploadDate;
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
}
