package bookmanager.model.vo;

/**
 * Created by dela on 1/6/18.
 */
public class BorrowInfoVO implements Comparable<BorrowInfoVO>{
    private String borrow_date;
    private String name;
    private String ugk_name;
    private int pk_id;

    public BorrowInfoVO() { }

    public BorrowInfoVO(String borrow_date, String name, String ugk_name, int pk_id) {
        this.borrow_date = borrow_date;
        this.name = name;
        this.ugk_name = ugk_name;
        this.pk_id = pk_id;
    }

    public String getBorrow_date() {
        return borrow_date.substring(0, 19);
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUgk_name() {
        return ugk_name;
    }

    public void setUgk_name(String ugk_name) {
        this.ugk_name = ugk_name;
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }

    public int compareTo(BorrowInfoVO o) {
        return this.getPk_id() < o.getPk_id() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "BorrowInfoVO{" +
                "borrow_date='" + borrow_date + '\'' +
                ", name='" + name + '\'' +
                ", ugk_name='" + ugk_name + '\'' +
                ", pk_id=" + pk_id +
                '}';
    }
}
