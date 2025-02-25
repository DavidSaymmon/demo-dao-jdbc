package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetInternalMethods;

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
	public void insert(Seller seller)  {
		PreparedStatement st = null;
		try {
			 st = conn.prepareStatement("INSERT INTO seller\r\n"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)\r\n"
					+ "VALUES\r\n"
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			int affectedRows = st.executeUpdate();
			System.out.println("Affected Rows "+ affectedRows);
		
				if(affectedRows>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unnexpected Error! no rows affected");
			}		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
		}
				
		
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					+ "ORDER BY Name");
			rs = ps.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()){

				Department dep = map.get(rs.getInt("DepartmentId"));
			
				if(dep==null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentID"), dep);
				}
						
				Seller obj = instantiateSeller(rs, dep);
				
				sellers.add(obj);
			}
			return sellers;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					+ "WHERE DepartmentId = ?\r\n"
					+ "ORDER BY Name");
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()){
				
				Department dep = map.get(rs.getInt("DepartmentId"));

				if(dep==null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentID"), dep);
				}
				Seller obj = instantiateSeller(rs, dep);
				
				sellers.add(obj);
			}
			return sellers;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}


}
