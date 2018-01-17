package com.android.provider.domain;

public class Person {
    private String id;
    private String name;
    private String telnumber;
    private String account;
	public Person(String id, String name, String telnumber,String account) {
		super();
		this.id = id;
		this.name = name;
		this.telnumber = telnumber;
		this.account = account;
	}
	public Person() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
    @Override
    public String toString() {
    	return "ĞÕÃû£º"+this.name+"    ºÅÂë£º"+this.telnumber+"   ½ğÇ®£º"+this.account;
    }
}
