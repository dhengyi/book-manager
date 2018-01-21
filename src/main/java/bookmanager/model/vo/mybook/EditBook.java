package bookmanager.model.vo.mybook;

import bookmanager.model.po.BookInfoPO;

/**
 * Created by dongmengyuan on 18-1-6.
 */


// 一个方法只能返回一种类型的对象，书籍信息和书籍分类在两张表里，所以需要封装起来使用
public class EditBook {
    private BookInfoPO bookInfoPO;
    private String parentBookClass;
    private String childBookClass;

    public BookInfoPO getBookInfoPO() {
        return bookInfoPO;
    }

    public void setBookInfoPO(BookInfoPO bookInfoPO) {
        this.bookInfoPO = bookInfoPO;
    }

    public String getParentBookClass() {
        return parentBookClass;
    }

    public void setParentBookClass(String parentBookClass) {
        this.parentBookClass = parentBookClass;
    }

    public String getChildBookClass() {
        return childBookClass;
    }

    public void setChildBookClass(String childBookClass) {
        this.childBookClass = childBookClass;
    }
}
