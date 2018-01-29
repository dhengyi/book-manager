package bookmanager.model.vo;

import bookmanager.model.po.BookCommentPO;
import bookmanager.model.po.PagePO;

import java.util.Map;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午2:58 18-1-28.
 * @Modified By:
 * @Description:
 */
// TODO version 3.0 供 AJAX 使用，目前不用
public class CommentPageVO {
    private int ELPageValue;
    private int isOneOfNextFivePage;
    private int reachNextFivePage;
    private int returnPreFivePage;
    private int bookCommentCount;                                   // 书籍评论的总数
    private Map<BookCommentPO, String> bookCommentsMap;             // 书籍评论的信息
    private PagePO pagePO;                                          // 书籍分页的信息

    public CommentPageVO(int ELPageValue, int isOneOfNextFivePage, int reachNextFivePage,
                         int returnPreFivePage, int bookCommentCount, Map<BookCommentPO, String> bookCommentsMap, PagePO pagePO) {
        this.ELPageValue = ELPageValue;
        this.isOneOfNextFivePage = isOneOfNextFivePage;
        this.reachNextFivePage = reachNextFivePage;
        this.returnPreFivePage = returnPreFivePage;
        this.bookCommentCount = bookCommentCount;
        this.bookCommentsMap = bookCommentsMap;
        this.pagePO = pagePO;
    }

    public int getELPageValue() {
        return ELPageValue;
    }

    public void setELPageValue(int ELPageValue) {
        this.ELPageValue = ELPageValue;
    }

    public int getIsOneOfNextFivePage() {
        return isOneOfNextFivePage;
    }

    public void setIsOneOfNextFivePage(int isOneOfNextFivePage) {
        this.isOneOfNextFivePage = isOneOfNextFivePage;
    }

    public int getReachNextFivePage() {
        return reachNextFivePage;
    }

    public void setReachNextFivePage(int reachNextFivePage) {
        this.reachNextFivePage = reachNextFivePage;
    }

    public int getReturnPreFivePage() {
        return returnPreFivePage;
    }

    public void setReturnPreFivePage(int returnPreFivePage) {
        this.returnPreFivePage = returnPreFivePage;
    }

    public int getBookCommentCount() {
        return bookCommentCount;
    }

    public void setBookCommentCount(int bookCommentCount) {
        this.bookCommentCount = bookCommentCount;
    }

    public Map<BookCommentPO, String> getBookCommentsMap() {
        return bookCommentsMap;
    }

    public void setBookCommentsMap(Map<BookCommentPO, String> bookCommentsMap) {
        this.bookCommentsMap = bookCommentsMap;
    }

    public PagePO getPagePO() {
        return pagePO;
    }

    public void setPagePO(PagePO pagePO) {
        this.pagePO = pagePO;
    }

    @Override
    public String toString() {
        return "CommentPageVO{" +
                "ELPageValue=" + ELPageValue +
                ", isOneOfNextFivePage=" + isOneOfNextFivePage +
                ", reachNextFivePage=" + reachNextFivePage +
                ", returnPreFivePage=" + returnPreFivePage +
                ", bookCommentCount=" + bookCommentCount +
                ", bookCommentsMap=" + bookCommentsMap +
                ", pagePO=" + pagePO +
                '}';
    }
}