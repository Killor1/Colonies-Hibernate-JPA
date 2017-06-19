package Entities;


public class Guardian {
	
	private String dni;
	private String name;
	private String phone;
	private String address;
	private String mail;
	
	
	public Guardian () {
		
	}

	public Guardian (String dni, String name, String phone, String address, String mail) {
		this.dni=dni;
		this.name=name;
		this.phone=phone;
		this.address=address;
		this.mail=mail;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

	@Override
	public String toString() {
		return "Cuidador [dni=" + dni + ", name=" + name + ", tlf=" + phone + ", direccio=" + address + ", mail=" + mail
				+ "]";
	}	
}