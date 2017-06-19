package Entities;

import java.util.Date;

public class Child {
	private int idChild;
	private String name;
	private Date dob;
	private boolean specialMenu;
	private Guardian guardian;
 	
	public Child() {
		
	}

	public Child(int idChild, String name, Date dob, boolean specialMenu) {
		this.idChild = idChild;
		this.name = name;
		this.dob = dob;
		this.specialMenu = specialMenu;
	}

	public Child(int idChild, String name, Date dob, boolean specialMenu, Guardian guardian) {
		this.idChild = idChild;
		this.name = name;
		this.dob = dob;
		this.specialMenu = specialMenu;
		this.guardian = guardian;
	}
	
	public int getIdChild() {
		return idChild;
	}

	public void setIdChild(int idChild) {
		this.idChild = idChild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isSpecialMenu() {
		return specialMenu;
	}

	public void setSpecialMenu(boolean menu_especial) {
		this.specialMenu = menu_especial;
	}

	public Guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Guardian cuidador) {
		this.guardian = cuidador;
	}
	
	@Override
	public String toString() {
		return "Nen [idNen=" + idChild + ", nom=" + name + ", data_naix=" + dob + ", menu_especial=" + specialMenu
				+ ", cuidador=" + guardian + "]";
	}
	
}
