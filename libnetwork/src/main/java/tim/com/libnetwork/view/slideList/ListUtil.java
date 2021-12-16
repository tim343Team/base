package tim.com.libnetwork.view.slideList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtil {
	
	public static  void sortList(List<DataBean> list){
		List<DataBean> _List = new ArrayList<DataBean>();
		Collections.sort(list, new PinyinComparator());
		DataBean dataBean = new DataBean(getFirstCharacter(list.get(0).getItem_en()), "",DataBean.TYPE_CHARACTER);
		String currentCharacter = getFirstCharacter(list.get(0).getItem_en());
		_List.add(dataBean);
		_List.add(list.get(0));
		for(int i=1;i<list.size();i++){
			if(getFirstCharacter(list.get(i).getItem_en()).compareTo(currentCharacter)!=0){
				currentCharacter = getFirstCharacter(list.get(i).getItem_en());
				dataBean = new DataBean(currentCharacter, "",DataBean.TYPE_CHARACTER);
				_List.add(dataBean);
			}
			_List.add(list.get(i));
		}
		list.clear();
		for(DataBean bean:_List){
			list.add(bean);
		}
	}
	
	public static String getFirstCharacter(String str){
		return str.substring(0, 1);
	}
	
	
}
