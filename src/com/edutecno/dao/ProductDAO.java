package com.edutecno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutecno.entity.Product;
import com.edutecno.factorymethod.DBFactory;
import com.edutecno.factorymethod.IDBAdapter;

public class ProductDAO {
	
	private IDBAdapter dbAdapter;
	
	public ProductDAO() {
		dbAdapter = DBFactory.getDefaultDBAdapter();
	}
	
	public List<Product> findAllProducts(){
		Connection connection = dbAdapter.getConnection();
		
		List<Product> productList = new ArrayList<Product>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * from Productos");
			ResultSet results =  stmt.executeQuery();
			while(results.next()) {
				productList.add(new Product(results.getLong(1), results.getString(2), results.getDouble(3)));
			}
			return productList;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean saveProduct(Product product) {
		
		Connection connection = dbAdapter.getConnection();
		try {
				PreparedStatement stmt = connection.prepareStatement("INSERT INTO productos (idProductos,productName,productPrice) VALUES(?,?,?)");
				stmt.setLong(1, product.getIdProductos());
				stmt.setString(2, product.getProductName());
				stmt.setDouble(3, product.getProductPrice());
				stmt.executeUpdate();
				return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
