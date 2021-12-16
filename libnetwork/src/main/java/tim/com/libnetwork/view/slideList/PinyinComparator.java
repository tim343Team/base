package tim.com.libnetwork.view.slideList;

import java.util.Comparator;


public class PinyinComparator implements Comparator<DataBean>{
	public int compare(DataBean o1, DataBean o2){
		if (o1.getItem_en().equals("@")
				|| o2.getItem_en().equals("#")){
			return -1;
		} else if (o1.getItem_en().equals("#")
				|| o2.getItem_en().equals("@")) {
			return 1;
		} else {
			return o1.getItem_en().compareTo(o2.getItem_en());
		}
	}

}
