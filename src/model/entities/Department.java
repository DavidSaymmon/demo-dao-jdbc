package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer id;
	String nome;
	public Department(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Department(){
	
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}
	
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", nome=" + nome + "]";
	}
	public boolean equals(Department department) {
		if(department.getId()!=id) {
			return false;
		}
		return true;
	}
	
}
