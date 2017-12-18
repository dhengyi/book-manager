package bookmanager.model.po;

/**
 * Created by dela on 11/22/17.
 */

//书籍标签表
public class BookLabelPO {
    private int pkId;       // 标签id(有意义主键)(这几张表里唯一一个有意义的主键id)
    private String name;    // 书籍标签
    private int parentId;   // 当前标签的父标签id

    public BookLabelPO(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public BookLabelPO(int pkId, String name, int parentId) {
        this.pkId = pkId;
        this.name = name;
        this.parentId = parentId;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setUkName(String ukName) {
        this.name = ukName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
