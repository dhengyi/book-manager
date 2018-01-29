package bookmanager.model.vo;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午8:26 17-12-3.
 * @Modified By:
 * @Description:
 */
public class UploadBook {
    @NotNull(message = "请填写书名")
    @Size(max=30, message="{name.size}")
    private String name;                // 书名

    private String author;              // 作者

    @NotNull(message = "请填写要捐赠的数量")
    @Min(value = 1, message = "至少要捐赠1本书哦")
    @Max(value = 5, message = "不需要捐赠这么多的同类书籍哦")
    private int amount;              // 数量

    @NotNull(message = "请选择图书的分类")
    private String types;               // 分类

    @Size(max=200, message="{describ.size}")
    private String describ;             // 书籍描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }
}
