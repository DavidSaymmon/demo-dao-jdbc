package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Department(){
	
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	public boolean equals(Department department) {
		if(department.getId()!=id) {
			return false;
		}
		return true;
	}
	
}
