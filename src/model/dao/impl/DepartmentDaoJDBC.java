package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st=null;
		try {
			st=conn.prepareStatement(
					"INSERT INTO department "
					+"(Name) "
					+"VALUES " 
					+"(?) ",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected=st.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet rs=st.getGeneratedKeys();
				if(rs.next()) {
					int Id=rs.getInt(1);
					obj.setId(Id);	
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
							
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st=null;
		try {
			st=conn.prepareStatement(
					"UPDATE department " 
					+"SET Name = ? " 
					+"WHERE Id = ? ");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			int RowsAffected=st.executeUpdate();
			
			if(RowsAffected==0) {
				throw new DbException("No Department Updated!");
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st=null;
		try {
			st=conn.prepareStatement(
					"DELETE FROM department " 
					+"WHERE Id = ? ");
			
			st.setInt(1, id);
			int RowsAffected=st.executeUpdate();
			
			if(RowsAffected==0) {
				throw new DbException("No Department deleted!");
				}
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department FindById(Integer id) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=conn.prepareStatement(
					"SELECT * FROM department "
					+"WHERE Id = ? ");
			
			st.setInt(1, id);
			rs=st.executeQuery();
			
				if(rs.next()) {
					Department obj=InstantiateDepartment(rs);
					return obj;
				}
			return null;	
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
	}

	@Override
	public List<Department> FindyAll() {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=conn.prepareStatement("SELECT * FROM department ORDER BY Name");
			
			rs=st.executeQuery();
			
			List<Department> list=new ArrayList<>();
			
			while(rs.next()) {
				Department obj=InstantiateDepartment(rs);
				list.add(obj);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	private Department InstantiateDepartment(ResultSet rs) throws SQLException {
		Department dep=new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
