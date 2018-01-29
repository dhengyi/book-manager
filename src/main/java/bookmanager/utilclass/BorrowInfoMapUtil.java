package bookmanager.utilclass;

import bookmanager.model.vo.BorrowInfoVO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dela on 1/19/18.
 */

//key为一个BorrowInfoVO(包括借书者名字, 借的书名字, 借书时间)
//value为一个String(书的所属者)
public class BorrowInfoMapUtil {
    public static Map<BorrowInfoVO, String> getBorrowInfo(
            List<BorrowInfoVO> borrowInfoVOList, List<String> ownerList) {
//        List<String> borrowInfoList = new ArrayList<String>();
//        JSONObject borrowInfoJson = new JSONObject();
        Map<BorrowInfoVO, String> borrowInfoMap = new TreeMap<BorrowInfoVO, String>();

        for (int i = 0; i < borrowInfoVOList.size(); i++) {
//            borrowInfoList.add(borrowInfoVOList.get(i).getBorrow_date());
//            borrowInfoList.add(getUONInfoString(borrowInfoVOList.get(i), ownerList.get(i)));
//            borrowInfoJson.put("time" + i, borrowInfoVOList.get(i).getBorrow_date());
//            borrowInfoJson.put("message" + i, getUONInfoString(borrowInfoVOList.get(i), ownerList.get(i)));
            String message = getUONInfoString(borrowInfoVOList.get(i), ownerList.get(i));

            System.out.println("message:" + message);

            borrowInfoMap.put(borrowInfoVOList.get(i), message);
        }

        return borrowInfoMap;
    }

    private static String getUONInfoString(BorrowInfoVO borrowInfoVO, String owner) {
        return borrowInfoVO.getName() + "从" + owner + "借阅了《" + borrowInfoVO.getUgk_name() + "》";
    }
}
