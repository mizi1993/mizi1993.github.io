package com.mimi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mimi.entity.Category;

public class CategoryDao {
	private DataSource dataSource;
	public CategoryDao(){
		Context ctx;
		try {
			ctx=new InitialContext();
			dataSource=(DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 根据分类id查询分类
	 * @param id分类id
	 * @return
	 */
	public Category findById(Integer id){
		if(null==id){
			return null;
		}
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {
			conn=getDataSource().getConnection();
			String sql="select * from category where id=?";
			ptmt=conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs=ptmt.executeQuery();
			if(rs.next()){
				Category cat=new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				return cat;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ptmt!=null){
					ptmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取所有分类
	 * @return
	 */
	public List<Category> findAll(){
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		List<Category> cates=new ArrayList<Category>();
		try {
			conn=getDataSource().getConnection();
			String sql="select * from category";
			ptmt=conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			while(rs.next()){
				Category cat=new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cates.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ptmt!=null){
					ptmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cates;
	}
	
	/**
	 * 将分类对象持久化到数据库中。根据分类id是否为null，来判断是保存还是更新。
	 * @param cat
	 */
	public void makePersistence(Category cat){
		if(null!=cat.getId()){
			update(cat);
		}else{
			save(cat);
		}
	}
	
	/**
	 * 保存分类对象
	 * @param cat
	 */
	public void save(Category cat){
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {
			conn=getDataSource().getConnection();
			String sql="insert into category(name)values(?)";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1,cat.getName());
			ptmt.executeUpdate();
			rs=ptmt.executeQuery("select last_insert_id()");
			if(rs.next()){
				cat.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ptmt!=null){
					ptmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 更新分类对象
	 * @param
	 */
	public void update(Category cat){
		Connection conn=null;
		PreparedStatement ptmt=null;
		try {
			conn=getDataSource().getConnection();
			String sql="update category set name=? where id=?";
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1,cat.getName());
			ptmt.setInt(2, cat.getId());
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ptmt!=null){
					ptmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 删除分类对象.
	 * @param id
	 */
	public void delete(Integer id){
		Connection conn=null;
		PreparedStatement ptmt=null;
		try {
			conn=getDataSource().getConnection();
			String sql="delete from category where id=?";
			ptmt=conn.prepareStatement(sql);
			ptmt.setInt(1,id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ptmt!=null){
					ptmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
