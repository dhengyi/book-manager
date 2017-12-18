package bookmanager.model.vo.bookinfo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午8:26 17-12-3.
 * @Modified By:
 * @Description:
 */
public class UploadBook {
    @NotNull
    @Size(min=1, max=20, message="{name.size}")
    private String name;                // 书名

    @Size(max=20, message="{author.size}")
    private String author;              // 作者

    @NotNull
    private int amount;                 // 数量

    @Size(max=200, message="{name.size}")
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

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }
}
