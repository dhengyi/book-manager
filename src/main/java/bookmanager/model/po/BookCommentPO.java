package bookmanager.model.po;


/**
 * Created by dela on 11/22/17.
 */

// 评论表
public class BookCommentPO {
    private int pkId;                   // 无意义主键
    private int bookInfoPkId;           // 书籍信息表的主键id
    private int userId;                 // 用户表id
    private String content;             // 评论
    private String commentDateTime;     // 评论时间

    public BookCommentPO() { }

    public BookCommentPO(int bookInfoPkId, int userId, String content, String commentDateTime) {
        this.bookInfoPkId = bookInfoPkId;
        this.userId = userId;
        this.content = content;
        this.commentDateTime = commentDateTime;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(String commentDateTime) {
        this.commentDateTime = commentDateTime;
    }
}
