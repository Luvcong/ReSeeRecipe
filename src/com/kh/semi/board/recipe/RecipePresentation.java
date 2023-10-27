package com.kh.semi.board.recipe;

import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.board.recipe.controller.RecipeControllers.RecipeController;
import com.kh.semi.board.recipe.model.dao.RecipeDao;
import com.kh.semi.board.recipe.model.service.RecipeService;
import com.kh.semi.board.recipe.model.vo.CookSteps;
import com.kh.semi.board.recipe.model.vo.Ingredient;
import com.kh.semi.board.recipe.model.vo.Recipe;
import com.kh.semi.board.recipe.model.vo.RecipePic;
import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.common.SendError;
import com.kh.semi.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

public class RecipePresentation {

	private Properties prop = new Properties();
	
	

	
	
	// RecipeServlet
	//***** [ 1 ]
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean flag = true;
		String viewPath = "";
		RecipeController rc = new RecipeController();
		
		request.setCharacterEncoding("UTF-8");	
		
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		
		switch(mapping) {
			case "selectRecipeCategoryList" : viewPath = rc.selectRecipeCategoryList(request, response); break;
			case "selectRecipeList"	: viewPath = rc.selectRecipeList(request, response); break;
			case "recipeDetail" 		: viewPath = rc.recipeDetail(request, response); break;
			case "recipeEnrollForm"	: viewPath = rc.recipeEnrollForm(request, response); break;
			case "insertRecipe" 		: viewPath = rc.insertRecipe(request, response);
								  	  flag = viewPath.contains("errorPage") ?  true : false; break;
			default : viewPath = rc.errorDefault(request, response); flag=false; break;
		}
		
		if(flag) { request.getRequestDispatcher(viewPath).forward(request, response); }
		else 	 { response.sendRedirect(viewPath); }
	}
	
	//*****

	
	
	
	
	// RecipeController
	//***** [ 2 ]
	
	public String insertRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		
		String viewPath = "";
		
		if(null != request.getSession().getAttribute("loginMember")) {
			
			if(ServletFileUpload.isMultipartContent(request)) {
				int maxSize = 1024 * 1024 * 10;
				String fileSavePath = request.getServletContext().getRealPath("/resources/recipe_upfiles/recipe_pics/");
				
				MultipartRequest multiRequest = new MultipartRequest
												(request,
												fileSavePath,
												maxSize,
												"UTF-8",
												new MyFileRenamePolicy());
				
				
				int memNo = ((Member)(request.getSession().getAttribute("loginMember"))).getMemNo();
				Recipe recipe = new Recipe();
				if( !(multiRequest.getParameter("recipeTitle") == null
				   || multiRequest.getParameter("recipeCategoryNo") == null)) {
					recipe.setRecipeTitle(multiRequest.getParameter("recipeTitle"));
					recipe.setRecipeWriterNo(memNo);
					recipe.setRecipeCategoryNo(Integer.parseInt(multiRequest.getParameter("recipeCategoryNo")));
				}
				
				//*****
				
				
				
				//***** [ 3 ]
				
				ArrayList<RecipePic> recipePicList = new ArrayList();
				
				for(int i = 0; i < 7; i++) {
					String recipeNameOriginKey = "recipeNameOrigin" + i;
		
					if( !(multiRequest.getOriginalFileName(recipeNameOriginKey) == null
						|| multiRequest.getFilesystemName(recipeNameOriginKey) == null)) {
						RecipePic recipePic = new RecipePic();
						recipePic.setRecipePicNameOrigin(multiRequest.getOriginalFileName(recipeNameOriginKey));
						recipePic.setRecipePicNameUpload(multiRequest.getFilesystemName(recipeNameOriginKey));
						recipePic.setRecipePicPath("/resources/recipe_upfiles/recipe_pics/");
						recipePic.setRecipePicLev(i);
						recipePicList.add(recipePic);
					} else {
						break;
					}
				}
				
				ArrayList<Ingredient> ingredientList = new ArrayList();
				
				for(int i = 0; i < 30; i++) {
					String ingredientKey = "ingredient" + i;
					String ingredientAmount = "ingredientAmount" + i;

					if( !(multiRequest.getParameter(ingredientKey) == null
						|| multiRequest.getParameter(ingredientAmount) == null)) {
						Ingredient ingredient = new Ingredient();
						ingredient.setIngredient(multiRequest.getParameter(ingredientKey));
						ingredient.setIngredientAmount(multiRequest.getParameter(ingredientAmount));
						ingredientList.add(ingredient);
					} else {
						break;
					}
				}
				
				//*****
				
				
				
				//***** [ 4 ]
				
				ArrayList<CookSteps> cookStepsList = new ArrayList();
				
				for(int i = 1; i <= 6; i++) {
					String csTitleKey = "cookStepsTitle" + i;
					String csContentKey = "cookStepsContent" + i;
					
					if( !(multiRequest.getParameter(csTitleKey) == null
						|| multiRequest.getParameter(csContentKey) == null)) {
						CookSteps cookSteps = new CookSteps();
						cookSteps.setCookStepsTitle(multiRequest.getParameter(csTitleKey));
						cookSteps.setCookStepsContent(multiRequest.getParameter(csContentKey));
						cookSteps.setCookStepsLev(i); /* 요리과정 순서 넘버에 띄워줄 값, 사용자에게 보여지는 값이므로 1로 받아 6까지 */
						cookStepsList.add(cookSteps);
					} else {
						break;
					}
				}
				
				
				ArrayList<Integer> tagNoList = new ArrayList();
				
				for(int i = 0; i < 5; i++) {
					String tagNoKey = "tagNo" + i;

					if(multiRequest.getParameter(tagNoKey) != null) {
						int tagNo = Integer.parseInt(multiRequest.getParameter(tagNoKey));
						tagNoList.add(tagNo);
					} else {
						break;
					}
				}
				
				//*****

				
				
				//***** [ 5 ]
				
				HashMap<String, Object> insertRecipeMap = new HashMap();
				insertRecipeMap.put("recipe", recipe);
				insertRecipeMap.put("recipePicList", recipePicList);
				insertRecipeMap.put("ingredientList", ingredientList);
				insertRecipeMap.put("cookStepsList", cookStepsList);
				insertRecipeMap.put("tagNoList", tagNoList);
				
				int result = new RecipeService().insertRecipe(insertRecipeMap);
				
				if(result > 0) {
					request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
					viewPath = "recipe/selectRecipeList.re";
				} else {
					viewPath = new SendError().sendError(request, "게시글 작성 실패");
				}
			}
		} else {
			viewPath = new SendError().sendError(request, "로그인이 필요한 서비스입니다");
		}
		return viewPath;
	}
	
	//*****
	
	
	
	
	
	// RecipeService
	//***** [ 6 ]
	
	public int insertRecipe(HashMap<String, Object> insertRecipeMap) {
		
		int recipeResult = 0;
		int tagResult = 0;
		int picResult = 0;
		int cookStepsResult = 0;
		int ingredientResult = 0;
		
		int returningResult = 0;
		
		RecipeDao rd = new RecipeDao();
		Connection conn = getConnection();
		Recipe recipe = (Recipe)insertRecipeMap.get("recipe");
		
		// 레시피(TB_RECIPE) INSERT먼저
		recipeResult = rd.insertRecipe(conn, recipe);
		
		if(recipeResult > 0) {
			ArrayList<RecipePic> recipePicList = (ArrayList<RecipePic>)insertRecipeMap.get("recipePicList"); // TB_RECIPE_PIC insert
			picResult = rd.insertRecipePic(conn, recipePicList);
			
			ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>)insertRecipeMap.get("ingredientList"); // TB_INGREDIENT insert
			ingredientResult = rd.insertIngredient(conn, ingredientList);
			
			ArrayList<CookSteps> cookStepsList = (ArrayList<CookSteps>)insertRecipeMap.get("cookStepsList"); // TB_COOK_STEPS insert
			cookStepsResult = rd.insertCookSteps(conn, cookStepsList);
			
			ArrayList<Integer> tagNoList = (ArrayList<Integer>)insertRecipeMap.get("tagNoList"); // TB_RECIPE_TAG insert
			tagResult = rd.insertRecipeTag(conn, tagNoList);
			
			// 커넥션 닫기 전 transaction처리
			if( !(recipeResult != 1
			   || picResult * ingredientResult * cookStepsResult * tagResult != 1)) {
				returningResult = 1;
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		return returningResult;
	}
	
	//*****
	
	
	
	
	
	// RecipeDao
	//***** [ 7 ]
	public int insertRecipe(Connection conn, Recipe recipe) {
		int result = 0;
		String sql = prop.getProperty("insertRecipe");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, recipe.getRecipeTitle());
			pstmt.setInt(2, recipe.getRecipeWriterNo());
			pstmt.setInt(3, recipe.getRecipeCategoryNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertRecipePic(Connection conn, ArrayList<RecipePic> recipePicList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertRecipePic");
		
		for(RecipePic recipePic : recipePicList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, recipePic.getRecipePicNameOrigin());
				pstmt.setString(2, recipePic.getRecipePicNameUpload());
				pstmt.setString(3, recipePic.getRecipePicPath());
				pstmt.setInt(4, recipePic.getRecipePicLev());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//*****
	
	
	//***** [ 8 ]
	public int insertIngredient(Connection conn, ArrayList<Ingredient> ingredientList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertIngredient");
		
		for(Ingredient ingredient : ingredientList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, ingredient.getIngredient());
				pstmt.setString(2, ingredient.getIngredientAmount());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	 
	public int insertCookSteps(Connection conn, ArrayList<CookSteps> cookStepsList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertCookSteps");
		
		for(CookSteps cookSteps : cookStepsList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, cookSteps.getCookStepsTitle());
				pstmt.setString(2, cookSteps.getCookStepsContent());
				pstmt.setInt(3, cookSteps.getCookStepsLev());
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//*****
	
	//***** [ 9 ]
	
	public int insertRecipeTag(Connection conn, ArrayList<Integer> tagNoList) {
		// 1로 초기화 후 executeUpdate결과를 곱함 => 하나라도 실패 시 0반환
		int result = 1;
		String sql = prop.getProperty("insertRecipeTag");
		
		for(int tagNo : tagNoList) {
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, tagNo);
				
				result *= pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//*****
	
	/* 캡쳐X ~ */
	protected void d(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = true;
		String viewPath = "";
		RecipeController rc = new RecipeController();
		request.setCharacterEncoding("UTF-8");	
		String uri = request.getRequestURI();
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		/* ~ 캡쳐X */
		
		
		//***** [ 10 - 1 ]

		switch(mapping) {
			// 생략 . . .
			case "insertRecipe" 		: viewPath = rc.insertRecipe(request, response);
								  	  flag = viewPath.contains("errorPage") ?  true : false; break;
		}
		
		if(flag) { request.getRequestDispatcher(viewPath).forward(request, response); }
		else 	 { response.sendRedirect(viewPath); }

		//*****
	}
	
	//***** [ 10 - 2 ]
	
	public class SendError {
		
		private String viewPathError = "/views/common/errorPage.jsp";
		
		public String sendError(HttpServletRequest request, String errorStr) {
			request.setAttribute("errorMsg", errorStr);
			return viewPathError;
		}
		
	}
	
	//*****
	
	
	
	
	
}
