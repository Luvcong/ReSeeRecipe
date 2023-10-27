package com.kh.semi.product.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.product.model.vo.Option;
import com.kh.semi.product.model.vo.Product;
import com.kh.semi.product.model.vo.ProductPicture;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao() {
		
		String fileName = ProductDao.class.getResource("/sql/product/product-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품의 총 개수 셀렉트해오는 메소드
	 * @return 개수
	 */
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	/**
	 * 상품리스트 페이징처리로 뿌려주는 메소드
	 * @return 페이징처리된 상품리스트
	 */
	public ArrayList<Product> selectProductList(Connection conn, PageInfo pi, String cate){
		
		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		switch(cate) {
		case "best": sql = prop.getProperty("selectList1"); break;
		case "good": sql = prop.getProperty("selectList2"); break;
		case "new": sql = prop.getProperty("selectList3"); break;
		}
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 카테고리별 상품리스트 페이징처리해서 뿌려주는 메소드
	 */
	public ArrayList<Product> selectCategoryProductList(Connection conn, PageInfo pi, String cate){
		
		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int num = 0;
		String sql = prop.getProperty("selectCategoryList");
		
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			switch(cate) {
			case "meat": num = 1; break;
			case "fish": num = 2; break;
			case "vegi": num = 3; break;
			case "sim": num = 4; break;
			}
			
			pstmt.setInt(1, num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 상품리스트가져오는 메소드
	 * @return 상품리스트
	 */
	public ArrayList<Product> selectList(Connection conn){
		
		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	/**
	 * 상품번호로 디테일하게 가져오는 메소드
	 * @return 상품정보
	 */
	public Product selectProduct(Connection conn, int pno) {
		
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductSubname(rset.getString("PRODUCT_SUBNAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductDetail(rset.getString("PRODUCT_DETAIL"));
				p.setDilivery(rset.getInt("PRODUCT_DILIVERY"));
				p.setOrigin(rset.getString("PRODUCT_ORIGIN"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}
	
	/**
	 * 상품번호로 사진 가져오는 메소드
	 * @return 상품정보의 사진
	 */
	public ArrayList<ProductPicture> selectPicture(Connection conn, int pno) {
		
		ArrayList<ProductPicture> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPicture");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductPicture pp = new ProductPicture();
				pp.setPictureNo(rset.getInt("PRODUCT_PICTURE_NO"));
				pp.setPictureOname(rset.getString("PRODUCT_PICTURE_ONAME"));
				pp.setPictureCname(rset.getString("PRODUCT_PICTURE_CNAME"));
				pp.setPicturePath(rset.getString("PRODUCT_PICTURE_PATH"));
				
				list.add(pp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 메인화면 상품 가져오는 메소드
	 * @param 정렬기준값
	 */
	public ArrayList<Product> selectMainList(Connection conn, String cate){
		
		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		switch(cate) {
		case "best": sql = prop.getProperty("selectMainList1"); break;
		case "good": sql = prop.getProperty("selectMainList2"); break;
		case "new": sql = prop.getProperty("selectMainList3"); break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 상품번호로 옵션 가져오는 메소드
	 */
	public ArrayList<Option> selectOption(Connection conn, int pno){
		
		ArrayList<Option> list2 = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOption");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Option o = new Option();
				o.setOptionName(rset.getString("OPTION_NAME"));
				o.setOptionNo(rset.getInt("OPTION_NO"));
				o.setOptionPrice(rset.getInt("OPTION_PRICE"));
				
				list2.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list2;
	}
	
	/**
	 * 상품 섞어주는 메소드
	 */
	public ArrayList<Product> sortSelectProductList(Connection conn, PageInfo pi, int category, String sort){
		
		ArrayList<Product> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		switch(sort) {
		case "good": sql = prop.getProperty("sortSelectProductList1"); break;
		case "price": sql = prop.getProperty("sortSelectProductList2"); break;
		case "star": sql = prop.getProperty("sortSelectProductList3"); break;
		}
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductScoreReviewAvg(rset.getDouble("PRODUCT_SCORE_AVG"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 옵션번호로 옵션가져오는 메소드
	 */
	public Option oSelectOption(Connection conn, int ono){
		
		Option o = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("oSelectOption");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				o = new Option();
				o.setOptionName(rset.getString("OPTION_NAME"));
				o.setOptionNo(rset.getInt("OPTION_NO"));
				o.setOptionPrice(rset.getInt("OPTION_PRICE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return o;
	}
	
	/**
	 * 사진번호로 사진조회
	 */
	public ProductPicture selectProductPicture(Connection conn, int ppno) {
		
		ProductPicture pp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductPicture");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ppno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pp = new ProductPicture();
				
				pp.setPictureNo(rset.getInt("PRODUCT_PICTURE_NO"));
				pp.setPictureOname(rset.getString("PRODUCT_PICTURE_ONAME"));
				pp.setPictureCname(rset.getString("PRODUCT_PICTURE_CNAME"));
				pp.setPicturePath(rset.getString("PRODUCT_PICTURE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pp;
	}
	
	/**
	 * 주문관련테이블 insert
	 */
	public int orderInsert(Connection conn, HashMap<String, Object> order) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deliveryInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (int)order.get("price"));
			pstmt.setInt(2, (int)order.get("mno"));
			
			pstmt.setString(3, (String)order.get("name"));
			pstmt.setString(4, (String)order.get("phone"));
			pstmt.setString(5, (String)order.get("email"));
			pstmt.setString(6, (String)order.get("address"));
			pstmt.setString(7, (String)order.get("request"));
			pstmt.setInt(8, (int)order.get("mno"));
			
			pstmt.setInt(9, (int)order.get("pno"));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * 주문테이블 insert
	public int orderInsert(Connection conn, int mno, int price) {
		
		int result = 0;
		int orderNo = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("orderInsert");
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, price);
			pstmt.setInt(2, mno);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				rset = pstmt.getGeneratedKeys();
				if(rset.next()) {
					System.out.println(rset.getString(1));
					orderNo = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return orderNo;
	}
	
	/**
	 * 주문번호로 배송지테이블 insert
	public int deliveryInsert(Connection conn, int orderNo, int mno, HashMap<String, String> order) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deliveryInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, order.get("name"));
			pstmt.setString(2, order.get("phone"));
			pstmt.setString(3, order.get("email"));
			pstmt.setString(4, order.get("address"));
			pstmt.setString(5, order.get("request"));
			pstmt.setInt(6, orderNo);
			pstmt.setInt(7, mno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * 주문번호로 주문상세테이블 insert
	public int orderDetailInsert(Connection conn, int pno, int orderNo) {
		
		int result = 0;
		int odNo = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("orderDetailInsert");
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, pno);
			pstmt.setInt(2, orderNo);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				rset = pstmt.getGeneratedKeys();
				if(rset.next()) {
					odNo = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return odNo;
	}
	
	/**
	 * 주문옵션테이블 insert
	public int orderOptionInsert(Connection conn, int ono, int odNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deliveryInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ono);
			pstmt.setInt(2, odNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	} */
	
	
	
	
	
	
	
	

}
