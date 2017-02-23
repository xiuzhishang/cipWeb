package com.chinapay.cip.test;


public class User {

	private String name;
	
	private int age;
	
	private String sex;
	
	private Double height;
	
	private Double weight;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	
	public User name(String name){
		this.name = name;
		return this;
	}
	
	public User age(int age){
		this.age = age;
		return this;
	}
	
	public User sex(String sex){
		this.sex = sex;
		return this;
	}
	
	public User height(Double height){
		this.height  = height;
		return this;
	}
	
	public User weight(Double weight){
		this.weight = weight;
		return this;
	}
	
	public static void main(String[] args) {
//		User user = new User().name("小明").age(12).sex("男").height(120d).weight(50.5D);
//		System.out.println(user.getName());
//		System.out.println(user.getAge());
//		System.out.println(user.getSex());
//		System.out.println(user.getHeight());
//		System.out.println(user.getWeight());
		
		User user = new UserBuilder().name("xiuzhi").sex("男").age(12).height(120d).weight(50.5D).build();
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.getSex());
		System.out.println(user.getHeight());
		System.out.println(user.getWeight());
	}
	
	public static class UserBuilder{
		
		private String name;
		
		private int age;
		
		private String sex;
		
		private Double height;
		
		private Double weight;
		

		public UserBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public UserBuilder age(int age){
			this.age = age;
			return this;
		}
		public UserBuilder sex(String sex){
			this.sex = sex;
			return this;
		}
		public UserBuilder height(Double height){
			this.height = height;
			return this;
		}
		
		public UserBuilder weight(Double weight){
			this.weight = weight;
			return this;
		}
		
		public User build(){
			return new User(this);
		}
		
	}
	public User(UserBuilder builder){
		this.name = builder.name;
		this.age = builder.age;
		this.height = builder.height;
		this.weight = builder.weight;
		this.sex = builder.sex;
	}
	
	public User(){}
}
