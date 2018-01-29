package bookmanager.model.vo;

import bookmanager.model.po.BookInfoPO;

/**
 * Created by dongmengyuan on 18-1-6.
 */

public class EditBook {
    private BookInfoPO bookInfoPO;

    public BookInfoPO getBookInfoPO() {
        return bookInfoPO;
    }

    public void setBookInfoPO(BookInfoPO bookInfoPO) {
        this.bookInfoPO = bookInfoPO;
    }

    @Override
    public String toString() {
        return "EditBook{" +
                "bookInfoPO=" + bookInfoPO +
                '}';
    }
}
