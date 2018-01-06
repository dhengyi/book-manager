package bookmanager.model.po;

/**
 * Created by dela on 11/22/17.
 */

//书籍与标签关系表
public class BookRelationLabelPO {
    private int pkId;           // 无意义主键
    private int bookInfoPkId;   // bookInfo这张表里的主键
    private int bookLabelPkId;  // bookLable这张表里的主键

    public BookRelationLabelPO() { }

    public BookRelationLabelPO(int bookInfoPkId, int bookLabelPkId) {
        this.bookInfoPkId = bookInfoPkId;
        this.bookLabelPkId = bookLabelPkId;
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

    public int getBookLabelPkId() {
        return bookLabelPkId;
    }

    public void setBookLabelPkId(int bookLabelPkId) {
        this.bookLabelPkId = bookLabelPkId;
    }
}
