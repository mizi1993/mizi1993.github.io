package com.mimi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mimi.model.User;

public class UserDao {
	private DataSource dataSource;

	public UserDao() {
		Context ctx;
		try {
			ctx=new InitialContext();
			dataSource=(DataSource) ctx.lookup("java:/comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public DataSource getDataSource() {
		return dataSource;
	}

    public User register(User user){
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	try {
			conn=getDataSource().getConnection();
			String sql="insert into reg_user(username,password,sex,email,pwd_question,pwd_answer,reg_date) values(?,?,?,?,?,?,?)";
			System.out.println(sql);
			pstmt=conn.prepareStatement(sql);
			int index=0;
			pstmt.setString(++index,user.getUsername());
			pstmt.setString(++index, user.getPassword());
			pstmt.setBoolean(++index,user.getSex());
			pstmt.setString(++index,user.getEmail());
			pstmt.setString(++index, user.getPwdQuestion());
			pstmt.setString(++index,user.getPwdAnswer());
			pstmt.setTimestamp(++index,new java.sql.Timestamp(user.getRegDate().getTime()));//设置注册时间。
			pstmt.execute();
			rs=pstmt.executeQuery("select last_insert_id()");//在多用户的并发访问中id可能不正确。
			if(rs.next()){
				user.setId(rs.getInt(1));
				
			}else{
				return null;
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return user;
    }
	
	
	
}
