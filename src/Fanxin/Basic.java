package Fanxin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Basic {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public static void main(String args[]) {
		Basic basic = new Basic();
		TimeerStamp t1 = new TimeerStamp(basic );
		System.out.println(t1.getStamp());
		List<Decorator> dec = new ArrayList<Decorator>();
		List<? super Decorator> ddd = dec;
	}
}
class Decorator extends Basic {
	protected Basic basic;

	public Decorator(Basic basic) {
		super();
		this.basic = basic;
	}

	public String getValue() {
		return basic.getValue();
	}

	public void setValue(String value) {
		basic.setValue(value);
	}
}

class TimeerStamp extends Decorator {
	private final long timms;

	public TimeerStamp(Basic basic) {
		super(basic);
		timms = new Date().getTime();
	}
	public long getStamp() {
		return timms;
	}
	
}

