package bookmanager.model.vo.label;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午10:59 17-11-23.
 * @Modified By:
 * @Description:
 */
public class AddLabelVO {
    private int uid;
    private String parentName;
    private String childName;

    public AddLabelVO(int uid, String parentName, String childName) {
        this.uid = uid;
        this.parentName = parentName;
        this.childName = childName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
