package com.kh.semi.common.template;

import com.kh.semi.common.model.vo.PageInfo;

public class Pagination {

	
	/**
	 * 페이지네이션을 위해 계산을 해주는 메소드
	 * @param listCount   : 총 게시글 개수 합산
	 * @param currentPage : 현재 사용자가 요청한 페이지
	 * @param pageLimit	  : 한 페이지에 보일 페이징바 최대 개수
	 * @param boardLimit  : 한 페이지에 보일 게시글 수
	 * @return : 7개의 필드가 모두 초기화된 PageInfo객체<br>
	 * 매개변수를 이용해 아래의 값들이 계산되고 필드가 초기화된다<br>
	 * maxPage 	  : 총 페이지 개수 합산<br>
	 * startPage  : 페이지 하단에 보여질 페이징 바의 시작 수<br>
	 * endPage 	  : 페이지 하단에 보여질 페이징 바의 끝 수<br>
	 */
	public static PageInfo getPageInfo(int listCount,
									   int currentPage,
									   int pageLimit,
									   int boardLimit) {
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		return new PageInfo(listCount, currentPage, pageLimit, boardLimit,
							maxPage, startPage, endPage);
	}
	
	
}
