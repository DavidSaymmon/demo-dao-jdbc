package applicattion;

import java.sql.Connection;
import java.util.Date;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {
	public static void main(String args[]) {
		Department department = new Department(12, "Nome");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}
	
}
