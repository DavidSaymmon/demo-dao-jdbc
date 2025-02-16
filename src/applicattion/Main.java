package applicattion;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {
	public static void main(String args[]) {
		Department department = new Department(12, "Nome");
		Seller seller = new Seller(10, "Joao", "Joao@outlook.com", new Date(), 2500.0, new Department());
		SellerDao sellerDao = DaoFactory.createSellerDao();
	}
	
}
