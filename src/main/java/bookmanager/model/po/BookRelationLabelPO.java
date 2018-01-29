package bookmanager.model.po;

import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 */

//书籍与标签关系表
@Table(name = "book_relation_label")
public class BookRelationLabelPO {
    @Column(name = "pk_id")
    private int pkId;           // 无意义主键

    @Column(name = "book_info_pk_id")
    private int bookInfoPkId;   // bookInfo这张表里的主键

    @Column(name = "book_label_pk_id")
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

    @Override
    public String toString() {
        return "BookRelationLabelPO{" +
                "pkId=" + pkId +
                ", bookInfoPkId=" + bookInfoPkId +
                ", bookLabelPkId=" + bookLabelPkId +
                '}';
    }
}
