package bookmanager.model.po;

import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

/**
 * Created by dela on 11/22/17.
 */

//书籍标签表
@Table(name = "book_label")
public class BookLabelPO {
    @Column(name = "pk_id")
    private int pkId;                   //标签id(有意义主键)(这几张表里唯一一个有意义的主键id)

    @Column(name = "name")
    private String name;                //书籍标签(唯一索引)

    @Column(name = "parent_id")
    private int parentId;               //当前标签的父标签id

    public BookLabelPO() {
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

    public void setName(String ukName) {
        this.name = ukName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "BookLabelPO{" +
                "pkId=" + pkId +
                ", ukName='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}