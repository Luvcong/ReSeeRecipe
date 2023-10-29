package com.kh.semi.board.recipe.controller.RecipeControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.kh.semi.board.recipe.model.service.RecipeService;
import com.kh.semi.board.recipe.model.service.RecipeServiceImpl;
import com.kh.semi.board.recipe.model.service.UnRecipeService;
import com.kh.semi.board.recipe.model.vo.CookSteps;
import com.kh.semi.board.recipe.model.vo.Ingredient;
import com.kh.semi.board.recipe.model.vo.Recipe;
import com.kh.semi.board.recipe.model.vo.RecipeCategory;
import com.kh.semi.board.recipe.model.vo.RecipePic;
import com.kh.semi.board.recipe.model.vo.RecipeTag;
import com.kh.semi.board.recipe.model.vo.Reply;
import com.kh.semi.board.recipe.model.vo.UnRecipe;
import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.common.SendError;
import com.kh.semi.common.model.vo.PageInfo;
import com.kh.semi.common.template.Pagination;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.tag.model.service.TagService;
import com.kh.semi.tag.model.vo.Tag;
import com.oreilly.servlet.MultipartRequest;

public class RecipeController {
	
	

	/**
	 * 레시피 서블릿(RecipeServlet)에 예상하지 못한 매핑값으로 요청이 들어왔을 때 디폴트 에러메세지와 함께 에러페이지로 포워딩
	 * @return 디폴트 에러메세지
	 */
	public String errorDefault(HttpServletRequest request, HttpServletResponse response) {
		return new SendError().sendError(request, "??? 알 수 없는 요청입니다 ???");
	}
	
	
	/**
	 * 카테고리 목록을 조회해 반환
	 * @return : 레시피 카테고리 목록이 담긴 어레이리스트 ArrayList<RecipeCategory>
	 */
	public String selectRecipeCategoryList(HttpServletRequest request, HttpServletResponse response) {
		
		String viewPath = "";
		ArrayList<RecipeCategory> cList = new RecipeServiceImpl().selectRecipeCategoryList();
		
		if(!cList.isEmpty()) {
			request.setAttribute("cList", cList);
			viewPath = "/views/board/recipe_frag/recipeCategoryBar.jsp";
		} else {
			viewPath =  new SendError().sendError(request, "카테고리 조회에 실패했습니다");
		}
		return viewPath;
	}
	
	
	/**
	 * 레시피 메인 보기 기능, 페이지네이션 처리 된 레시피목록을 최신순(레시피 PK번호순)으로 조회한 후<br>
	 * 목록과 PageInfo객체를 RecipeMainView로 포워딩함
	 * @param request : 요청 온 페이지 번호(페이지 바의 현재 페이지)
	 * @return :
	 * > ArrayList<Recipe> rList : 페이지네이션 처리되어 조회된 레시피 글 정보를 Recipe객체로 만든 후 ArrayList에 담음<br>
	 * 	 Recipe필드 :  recipeNo, recipeTitle, recipeCount, titleImg, memNickName, htCount<br>
	 * > PageInfo pi : 페이지네이션 처리를 위한 정보가 담긴 PageInfo객체<br>
	 * 	 PageInfo필드 : listCount(현재 게시글 총 개수), currentPage(요청온 페이지 번호),<br>
	 * 	 pageLimit(한 페이지에 보일 페이징 바의 최대 개수), boardLimit(한 페이지에 보일 게시글 최대 개수)<br>
	 */
	public String selectRecipeList(HttpServletRequest request, HttpServletResponse response) { // 최신순
		// 기본변수
		String viewPath = "";
		RecipeService recipeService = new RecipeServiceImpl();
		// ---------------- 페이지네이션 ----------------
		// pi값 계산은 여기서 (PagiInfo객체 화면단에 넘겨야하기때문에)
		int listCount = recipeService.selectRecipeListCount();
		int currentPage = request.getParameter("currentPage") != null ?
						  Integer.parseInt(request.getParameter("currentPage"))
						  : 1;
		int pageLimit = 10; // 페이징 바 개수
		int boardLimit = 9; // 한 페이지에 보일 게시글 수
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		ArrayList<Recipe> recipeList = recipeService.selectRecipeList(pi);
		
		// 결과에 따라 화면 선택
		if(!recipeList.isEmpty()) {
			request.setAttribute("pi", pi);
			request.setAttribute("recipeList", recipeList);
			viewPath = "/views/board/recipe/recipeMainView.jsp";
		} else {
			viewPath = new SendError().sendError(request, "조회된 게시글이 없습니다");
		}
		return viewPath;
	}
	
	
	
	/**
	 * 레시피 글작성 요청을 받은 후 Session에 로그인한 멤버가 존재한다면<br>
	 * 해당 유저의 임시저장 글 조회 후 정보와 함께<br>
	 * 레시피를 작성할 수 있는 폼 화면으로 포워딩해주는 기능<br>
	 */
	public String recipeEnrollForm(HttpServletRequest request, HttpServletResponse response) {
		
		String viewPath = "";
		//@@@@@@@@@@@@편의를 위해 잠시 null
		System.out.println("레시피 컨트롤러 recipeEnrollForm 편의상 번호 3해둠");
		// 임시저장글 받아오기 (여기까지 온 loginmember도 null체크필요)
		ArrayList<UnRecipe> unReList = new UnRecipeService().selectUnRecipeList(3/*loginMember.getMemNo()*/);
		
		request.setAttribute("unReList", unReList);
		
		viewPath = "/views/board/recipe/recipeEnrollFormView.jsp";
		return viewPath;
	}
	
	
	/**
	 * 레시피 글 상세조회를 하는 기능
	 * @param request
	 * @param response
	 * @return
	 */
	public String recipeDetail(HttpServletRequest request, HttpServletResponse response) {
		String viewPath = "";
		RecipeService recipeService = new RecipeServiceImpl();
		
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Recipe recipe = recipeService.selectRecipeSingle(recipeNo);
		ArrayList<RecipePic> reciepPicList = recipeService.selectRecipePicSingle(recipeNo);
		ArrayList<Ingredient> ingredientList = recipeService.selectIngredientSingle(recipeNo);
		ArrayList<CookSteps> cookStepsList = recipeService.selectCookStepsSingle(recipeNo);
		ArrayList<RecipeTag> recipeTagList = recipeService.selectRecipeTagSingle(recipeNo);
		
		if( !(reciepPicList.isEmpty()
			||ingredientList.isEmpty()
			||cookStepsList.isEmpty())) {
			HashMap<String, Object> recipeDetailMap = new HashMap();
			recipeDetailMap.put("recipe", recipe);
			recipeDetailMap.put("reciepPicList", reciepPicList);
			recipeDetailMap.put("ingredientList", ingredientList);
			recipeDetailMap.put("cookStepsList", cookStepsList);
			if(!recipeTagList.isEmpty()) {
				recipeDetailMap.put("recipeTagList", recipeTagList);
			}
			request.setAttribute("recipeDetailMap", recipeDetailMap);
			viewPath = "/views/board/recipe/recipeDetailView.jsp";
		} else {
			viewPath = new SendError().sendError(request, "게시글 상세 조회에 실패했습니다");
		}
		return viewPath;
	}
	
	
	/**
	 * 레시피 글을 작성하는 기능
	 * @param request :
	 * memNo : 로그인 멤버의 정보
	 * 
	 * @param response
	 * @return
	 * @throws IOException, FileUploadException 
	 */
	public String insertRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		
		// 인코딩은 Servlet에서 완료
		
		// 기본변수 초기화
		String viewPath = "";
		
		// Session의 Attribute영역에 "loginMember"키값이 있을 시 insert가능
		/* 여기서의 null체크의 경우 enrollForm에 스크립틀릿 사용 조건걸었기때문에 사실 필요하지는 않을듯하지만 중간에 에러때문에 걸어둠
		 * 넘어오는 값 빈문자열 체크나 타입 체크의 경우 하지 않았음 / if조건에 해당하지 않는 경우 모두 sendError */
		if(null != request.getSession().getAttribute("loginMember")) {
			
			// multipartContent가 있는지 체크 => 체크 후 서버올리기
			if(ServletFileUpload.isMultipartContent(request)) {
				// 1) Multipart객체 생성 시 => 서버에 파일 올라감
				// 1_1. 전송용량 제한 (10Mbytes)
				int maxSize = 1024 * 1024 * 10;
				
				// 1_2. 파일 저장할 경로 getServletContext.getRealPath() => 경로 매핑해줌
				String fileSavePath = request.getServletContext().getRealPath("/resources/recipe_upfiles/recipe_pics/");
				
				// 1_3. MultipartRequest객체 생성 하면서 파일 이름 새로 생성
				MultipartRequest multiRequest = new MultipartRequest
												(request,
												fileSavePath,
												maxSize,
												"UTF-8",
												new MyFileRenamePolicy());
				
			
				
				// 2) multiRequest로부터 값 뽑기 + 가공 => MultipartRequest객체의 getParameter()이용
				
				
				// 가공2_1. Recipe세팅 (loginMember의 memNo는 멤버테이블 PK)
				/* 위의 조건문을 통과했으므로 멤버는 로그인한 멤버임
				 * 레시피 제목과 카테고리 번호는 값 한개씩만 있기때문에 화면에서 넘겨받은 값을 단일 객체로 가공) 
				 * short서킷 연산을 하기때문에 or연산자(||) 이용 시 모든 값이 False일때만 끝까지 비교함
				 * 	=> 그러므로 해당 결과 반전 시 모든 값이 True일 때만 끝까지 연산이 일어나는것을 의미하게 됨
				 * 	=> &&를 사용할 때 보다 적은 비교를 할 확률이 높아진다 */
				int memNo = ((Member)(request.getSession().getAttribute("loginMember"))).getMemNo();
				Recipe recipe = new Recipe();
				
				// ↓ 이 항목들이 모두 데이터가 있다면 Recipe객체의 필드 초기화
				if( !(multiRequest.getParameter("recipeTitle") == null /* getParameter메소드 값이 없을 때 반환이 null이기 때문에 null비교 */
				   || multiRequest.getParameter("recipeCategoryNo") == null)) {
					recipe.setRecipeTitle(multiRequest.getParameter("recipeTitle"));
					recipe.setRecipeWriterNo(memNo); /* 레시피의 writerNo = memNo */
					recipe.setRecipeCategoryNo(Integer.parseInt(multiRequest.getParameter("recipeCategoryNo")));
				}
				
				
				// 가공2_2. ArrayList<RecipePic> 세팅 (RecipePic 7개까지 (인덱스 0 ~ 6) recipePicLev은 썸네일이 0번, 재료란 사진이 1 ~ 6번)
				ArrayList<RecipePic> recipePicList = new ArrayList();

				for(int i = 0; i < 7; i++) {
					String recipeNameOriginKey = "recipeNameOrigin" + i;
					// ↓ 이 항목들이 모두 데이터가 있다면 RecipePic객체 생성 + 필드 초기화 후 ArrayList에 추가
					if( !(multiRequest.getOriginalFileName(recipeNameOriginKey) == null
						|| multiRequest.getFilesystemName(recipeNameOriginKey) == null)) {
						RecipePic recipePic = new RecipePic();
						recipePic.setRecipePicNameOrigin(multiRequest.getOriginalFileName(recipeNameOriginKey));
						recipePic.setRecipePicNameUpload(multiRequest.getFilesystemName(recipeNameOriginKey));
						recipePic.setRecipePicPath("/resources/recipe_upfiles/recipe_pics/");
						recipePic.setRecipePicLev(i); /* 0번은 썸네일, 1 ~ 6은 요리 과정 사진 */
						recipePicList.add(recipePic);
						System.out.println("RecipeController 사진 반복 " + i + "번째");
					} else {
						break;
					}
				}
				
				
				// 가공2_3. ArrayList<Ingredient>세팅 (ingredient와 ingredientAmount에 값이 존재한다면)
				ArrayList<Ingredient> ingredientList = new ArrayList();
				
				for(int i = 0; i < 30; i++) {
					String ingredientKey = "ingredient" + i;
					String ingredientAmount = "ingredientAmount" + i;
					// ↓ 이 항목들이 모두 데이터가 있다면 Ingredient객체 생성 + 필드 초기화 후 ArrayList에 추가
					if( !(multiRequest.getParameter(ingredientKey) == null
						|| multiRequest.getParameter(ingredientAmount) == null)) {
						Ingredient ingredient = new Ingredient();
						ingredient.setIngredient(multiRequest.getParameter(ingredientKey));
						ingredient.setIngredientAmount(multiRequest.getParameter(ingredientAmount));
						ingredientList.add(ingredient);
					} else {
						break;
					}
					System.out.println("RecipeController 재료 반복 " + i + "번째");
				}
				
				
				// 가공2_4. ArrayList<CookSteps> (CookSteps 6개(인덱스 0 ~ 5), cookStepsTitle, cookStepsContent에 값이 존재한다면 )
				ArrayList<CookSteps> cookStepsList = new ArrayList();
				
				for(int i = 1; i <= 6; i++) {
					String csTitleKey = "cookStepsTitle" + i;
					String csContentKey = "cookStepsContent" + i;
					// ↓ 이 항목들이 모두 데이터가 있다면 CookSteps객체 생성 + 필드 초기화 후 ArrayList에 추가
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
				
				
				// 가공2_5. tagNO세팅, 여러개 있을 수도 있고 없을 수도 있음 */
				ArrayList<Integer> tagNoList = new ArrayList();
				for(int i = 0; i < 5; i++) {
					String tagNoKey = "tagNo" + i;
					// ↓ 이 항목들이 모두 데이터가 있다면 값 뽑아 ArrayList<Integer>에 추가
					if(multiRequest.getParameter(tagNoKey) != null) {
						int tagNo = Integer.parseInt(multiRequest.getParameter(tagNoKey));
						tagNoList.add(tagNo);
					} else {
						break;
					}
				}

				
				
				// 3) Service에 넘길 값 가공 => map에 담기
				HashMap<String, Object> insertRecipeMap = new HashMap();
				insertRecipeMap.put("recipe", recipe);
				insertRecipeMap.put("recipePicList", recipePicList);
				insertRecipeMap.put("ingredientList", ingredientList);
				insertRecipeMap.put("cookStepsList", cookStepsList);
				insertRecipeMap.put("tagNoList", tagNoList);
				
				
				
				// Recipecontroller호출
				int result = new RecipeServiceImpl().insertRecipe(insertRecipeMap);
				
				if(result > 0) {
					request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
					viewPath = "recipe/selectRecipeList.re";
				} else {
					viewPath = new SendError().sendError(request, "게시글 작성 실패"); // 게시글 작성 실패 시
				}
			}
		} else {
			viewPath = new SendError().sendError(request, "로그인이 필요한 서비스입니다"); // 로그인 하지 않을 시
		}
		return viewPath;
	}
	
	
	
	/* ***************************** AJAX 요청 처리 ***************************** */
	
	/**
	 * ajax요청을 받아 해시태그의 정보(번호, 이름, 날짜)를 조회해 반환
	 * @param response : 해시태그 정보가 담긴 ArrayList를 Gson객체로 변환해 응답<br>
	 * > Tag의 필드 : tagNo, tagName, tagDate
	 */
	public ArrayList<Tag> ajaxSelectTag(HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		
		// 해시태그 정보 조회
		ArrayList<Tag> tagList = new TagService().selectALlTagname();
		return tagList;
	}
	
	
	/**
	 * 댓글을 수정해주는 기능<br>
	 * 수정에 성공할 시 int형 숫자 1, 실패 시 0 반환
	 * @param request
	 * @param response
	 */
	public int ajaxModifyRecipeReply(HttpServletRequest request, HttpServletResponse response) {
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Reply reply = new Reply();
		reply.setReplyNo(replyNo);
		reply.setRecipeNo(recipeNo);
		//@@@@@@@@@@@@@@@@@@@@@@@@@
		
		int result = 0;
		
		return result;
		
	}
	
	
	/**
	 * 댓글의 상태를 'N'으로 바꾸는 기능(삭제요청)<br>
	 * 삭제요청에 성공할 시 int형 숫자 1, 실패 시 0 반환
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void ajaxDeleteRecipeReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Reply reply = new Reply();
		reply.setReplyNo(replyNo);
		reply.setRecipeNo(recipeNo);
		
		int result = new RecipeServiceImpl().deleteReqReplySingle(reply);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}
	
	
	/**
	 * 특정 번호 레시피(PK)에 달린 댓글 리스트를 조회하는 기능<br>
	 * (페이징처리 나중에)
	 * @param request : 레시피 PK
	 * @param response : 리플목록이 담긴 ArrayList
	 */
	public void ajaxSelectRecipeReplyList(HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		
		// 변수세팅 + 값뽑기
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		// Service요청
		ArrayList<Reply> replyList = new RecipeServiceImpl().selectReplyListSingle(recipeNo);
		
		// 형식+인코딩 지정 & 응답 Gson
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(replyList, response.getWriter());
	}
	
	
	/**
	 * 특정 번호 레시피(PK)에 댓글을 입력하는 기능<br>
	 * @param request
	 * @param response
	 */
	public void ajaxInsertRecipeReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String replyContent = (String)request.getParameter("replyContent");
		int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
		int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
		
		Reply reply = new Reply();
		reply.setReplyContent(replyContent);
		reply.setReplyWriterNo(memNo);
		reply.setRecipeNo(recipeNo);
		
		int result = new RecipeServiceImpl().insertReply(reply);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}
	
	
	
	
	
}//class.end