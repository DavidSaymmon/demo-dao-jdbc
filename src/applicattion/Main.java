package applicattion;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {
	public static void main(String args[]) {
		Department department = new Department(2, "Nome");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("===Teste 1 FindById===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println("===Teste 2 FindByDepartment===");
		List<Seller> sellers = sellerDao.findByDepartment(department);
		for(Seller obj : sellers) {
			System.out.println(obj);
		}
		System.out.println("===Teste 3 FindAll===");
		List<Seller> sellers2 = sellerDao.findAll();
		for(Seller obj : sellers2) {
			System.out.println(obj);
		}
	}
	
}
