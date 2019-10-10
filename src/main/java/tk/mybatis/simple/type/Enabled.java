package tk.mybatis.simple.type;

public enum Enabled {
	tow(2),//测试
	enabled(1), //启用
	disabled(0);//禁用
	
	private final int value;

	private Enabled(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
