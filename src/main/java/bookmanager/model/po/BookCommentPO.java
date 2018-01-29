package bookmanager.model.po;


import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 */

// 评论表
@Table(name = "book_comment")
public class BookCommentPO implements Comparable<BookCommentPO> {
    @Column(name = "pk_id")
    private int pkId;                       //无意义主键

    @Column(name = "book_info_pk_id")
    private int bookInfoPkId;               //书籍信息表的主键id

    @Column(name = "cs_user_id")
    private int csUserId;                   //用户表id

    @Column(name = "content")
    private String content;                 //评论

    @Column(name = "comment_datetime")
    private String commentDatetime;         //评论时间

    public BookCommentPO() {
    }

    public BookCommentPO(int bookInfoPkId, int csUserId, String content,
                         String commentDatetime) {
        this.bookInfoPkId = bookInfoPkId;
        this.csUserId = csUserId;
        this.content = content;
        this.commentDatetime = commentDatetime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDatetime() {
        return commentDatetime.substring(0, 19);
    }

    public void setCommentDatetime(String commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    @Override
    public int compareTo(BookCommentPO o) {
        return getPkId() < o.getPkId() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "BookCommentPO{" +
                "pkId=" + pkId +
                ", bookInfoPkId=" + bookInfoPkId +
                ", csUserId=" + csUserId +
                ", content='" + content + '\'' +
                ", commentDatetime='" + commentDatetime + '\'' +
                '}';
    }
}