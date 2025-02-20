package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\r\n"
				+ "FROM seller INNER JOIN department\r\n"
				+ "ON seller.DepartmentId = department.Id\r\n"
				+ "WHERE seller.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()){
				
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				return seller;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
		Seller seller = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), department);
		return seller;
	}
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
			Department department = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
			return department;

	}
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
