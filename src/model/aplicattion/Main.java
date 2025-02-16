package model.aplicattion;

import model.entities.Department;

public class Main {
	public static void main(String args[]) {
		Department department = new Department(12, "Nome");
		System.out.println(department);
	}
}
