package tim.com.libnetwork.view.slideList;

public class DataBean {
	
	public static final int TYPE_CHARACTER = 0;
	public static final int TYPE_DATA = 1;
	private int item_type;
	private String item_en;
	
	private String name;
	private String phone;
	
	
	public DataBean(String name, String phone, int type){
		CharacterParser parser = CharacterParser.getInstance();
		this.name = name;
		this.phone = phone;
		this.item_type = type;
		this.item_en = parser.getSelling(name).toUpperCase().trim();
		if(!item_en.matches("[A-Z]+")){
			item_en = "#"+item_en;
		}
	}
	
	public int getItem_type(){
		return item_type;
	}
	public void setItem_type(int item_type){
		this.item_type = item_type;
	}
	public String getItem_en(){
		return item_en;
	}
	public void setItem_en(String item_en){
		this.item_en = item_en;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}

}
