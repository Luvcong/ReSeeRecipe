package com.kh.semi.product.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.product.model.dao.ProductDao;
import com.kh.semi.product.model.vo.Option;
import com.kh.semi.product.model.vo.Product;
import com.kh.semi.product.model.vo.ProductPicture;

public class ProductService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Product> selectList(){
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Product> selectProductList(PageInfo pi, String cate){
		
		Connection conn = getConnection();
		ArrayList<Product> list = null;
		
		if(cate.equals("meat") || cate.equals("fish") || cate.equals("vegi") || cate.equals("sim")) {
			list = new ProductDao().selectCategoryProductList(conn, pi, cate);
		} else {
			list = new ProductDao().selectProductList(conn, pi, cate);
		}
		
		close(conn);
		
		return list;
	}
	
	public Product selectProduct(int pno) {
		
		Connection conn = getConnection();
		
		Product p = new ProductDao().selectProduct(conn, pno);
		
		close(conn);
		
		return p;
		
	}
	
	public ArrayList<ProductPicture> selectPicture(int pno) {
		
		Connection conn = getConnection();
		
		ArrayList<ProductPicture> list = new ProductDao().selectPicture(conn, pno);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Product> selectMainList(String cate){
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectMainList(conn, cate);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Option> selectOption(int pno){
		
		Connection conn = getConnection();
		
		ArrayList<Option> list2 = new ProductDao().selectOption(conn, pno);
		
		close(conn);
		
		return list2;
	}
	
	public ArrayList<Product> sortSelectProductList(PageInfo pi, int category, String sort){
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().sortSelectProductList(conn, pi, category, sort);
		
		close(conn);
		
		return list;
	}
	
	public Option oSelectOption(int ono){
		
		Connection conn = getConnection();
		
		Option o = new ProductDao().oSelectOption(conn, ono);
		
		close(conn);
		
		return o;
	}
	
	public ProductPicture selectProductPicture(int ppno) {
		
		Connection conn = getConnection();
		
		ProductPicture pp = new ProductDao().selectProductPicture(conn, ppno);
		
		close(conn);
		
		return pp;
		
	}
	
	public int orderInsert(HashMap<String, Object> order) { // 일단 주문이 무조건 1개, 하나만 들어온다고 가정
		
		Connection conn = getConnection();
		
		ProductDao pd = new ProductDao();
		
		int result = pd.orderInsert(conn, order);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(result);
		
		return result;
		/*
		int orderNo = pd.orderInsert(conn, pno, price); // insert 후 pk가져옴
		
		if(orderNo > 0) { // insert 잘 됐을때
			
			int result1 = pd.deliveryInsert(conn, orderNo, mno, order); // 배송지정보 insert
			int odNo = pd.orderDetailInsert(conn, pno, orderNo); // 주문상세 insert 후 pk가져옴
			
			if(odNo > 0 && result1 > 0) { // 배송지정보 주문상세 둘다 insert됐을때
				commit(conn);
				if(ono != 0) { // 옵션번호값이 null이 아닐때
					int result2 = pd.orderOptionInsert(conn, ono, odNo); // 주문옵션 insert
					if(result2 > 0) { // 커밋
						commit(conn);
					} else { // 롤백
						rollback(conn);
						// 앞 컬럼 세개를 삭제?
					}
				}
			} else { // 둘중 하나라도 insert 안됐을때
				rollback(conn);
				// order테이블의 컬럼을 삭제해야하는가?
			}
		} else { // 주문 insert 잘 안됐을때
			rollback(conn);
		}*/
		
	}
	
	
	
	
	

}
