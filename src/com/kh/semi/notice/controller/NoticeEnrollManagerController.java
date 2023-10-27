package com.kh.semi.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.common.MyFileRenamePolicy;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.notice.model.vo.NoticePic;
import com.kh.semi.tag.model.vo.Tag;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeEnrollManagerController
 */
@WebServlet("/hlnoticeEnroll.ma")
public class NoticeEnrollManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEnrollManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**공지사항 작성하기 서블릿
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 설정(POST)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기 multipart/form-data방식 => multipart
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			
			// 1_2. 전달될 파일을 저장할 서버의 폴더 경로 알아내기(String noticePicPath)
			
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/notice_upfiles/"); // notice_upfiles 폴더 안에 저장할 것이기 때문
		
			// 스텝 2) 서버에 업로드 작업(파일명 수정)
			MultipartRequest multiRequest = 
					new MultipartRequest(request, savePath, maxSize, "UTF-8",
										 new MyFileRenamePolicy());
		
			// ----- 파일업로드 작업 끝!
			
			// 2) 값 뽑기
			// 공지사항 제목, 공지사항 내용
			String noticeTitle = multiRequest.getParameter("HL_noticeTitle");
			String noticeCon = multiRequest.getParameter("HL_noticeContent");
			String noticeWriter = multiRequest.getParameter("adminNo");
			System.out.print(noticeTitle);
			// 3) VO객체로 가공 => 무조건 INSERTF
			Notice n = new Notice();
			n.setNoticeTitle(noticeTitle);
			n.setNoticeCon(noticeCon);
			n.setNoticeWriter(noticeWriter);
			
			// 두번째 INSERT => 선택적(첨부파일이 존재할 때만 INSERT)
			NoticePic np = null;
			
			System.out.println("multiRequest.getOriginalFileName(\"HL_noticeFile\") >>" + multiRequest.getOriginalFileName("HL_noticeFile"));
			// 첨부파일 유무 파악! // multirequest.getOriginalFileName("키값");  input type의 name속성값
			// 첨부파일이 존재한다면 "원본파일명" / 첨부파일이 존재하지 않는다면 null값을 반환
			if(multiRequest.getOriginalFileName("HL_noticeFile") != null) {
				
				// 첨부파일이 있디 -> VO 객체로 가공
				np = new NoticePic();
				
				np.setNoticePicNamgeOrigin(multiRequest.getOriginalFileName("HL_noticeFile"));
				
				// 파일경로 
				np.setNoticePicPath("resources/notice_upfiles");
				
				// 수정된 파일명
				np.setNoticePicNagmeChange(multiRequest.getFilesystemName("HL_noticeFile"));
			}
			
			System.out.println("np >>" + np);
			// 해시태그 입력할 수도 있고 안 할 수도 있음
			// 해시태그 값 뽑기
			String noticeTag = multiRequest.getParameter("tags");
			System.out.println(noticeTag);
			//ArrayList<Tag> list = new ArrayList();
			
			// 문자열에서 "value" 필드의 값 추출하여 리스트에 담기
	        //List<String> extractedValues = extractValues(noticeTag);
	        /*
	        // 추출된 값을 출력
	        for (String value : extractedValues) {
	            System.out.println("추출된 값: " + value);
	          
	        }
	        System.out.println(extractedValues);
			*/
			List<Tag> tagList = extractTags(noticeTag);
			System.out.println("tagList>>" + tagList);
			for (Tag tag : tagList) {
				System.out.println(tag);
			}
			if(tagList.isEmpty()) {
				System.out.println("tagList.isEmpty()" + tagList.isEmpty());
				tagList = null;
			}
			
			
			// 4) 서비스 요청
	        int result = new NoticeService().insertNotice(n, np, tagList);
	        
	        // 5) 응답 페이지 지정
	        if(result > 0) {
	        	request.getSession().setAttribute("alertMsg", "게시글 등록 성공"); // 포워딩으로 위임 시 NullPointException 발생
	        	response.sendRedirect(request.getContextPath() + "/hlnoticemanage.no?cnpage=1");
	        } else {
	        	
	        	// 공지사항 사진 첨부시 TB_NOTICE_PIC에 INSERT 실패시 이미 가지고 있는 파일을 삭제해야함
	        	// 공지사항 해시태그 작성시 TB_NOTICE_TAG에 INSERT 실패 시 
	        	if(np != null) {
	        		// delete() 호출
	        		new File(savePath + np.getNoticePicNagmeChange()).delete();
	        	}
	        	
	        	request.setAttribute("errorMsg", "공지사항 게시글 작성 실패");
	        	request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	        	
	        } 
	        
		}
		
	
		
	}
	
	/*
	public static List<String> extractValues(String noticeTag) {
        List<String> values = new ArrayList<>();

        // 정규식을 사용하여 "value" 필드의 값 추출
        Pattern pattern = Pattern.compile("\"value\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(noticeTag);

        while (matcher.find()) {
            values.add(matcher.group(1));
        }

        return values;
    }
	*/
	
	 public static List<Tag> extractTags(String noticeTag) {
	        List<Tag> tagList = new ArrayList<>();

	        int startIndex = noticeTag.indexOf("{\"value\":\"");
	        while (startIndex != -1) {
	            int endIndex = noticeTag.indexOf("\"}", startIndex);
	            if (endIndex != -1) {
	                String value = noticeTag.substring(startIndex + "{\"value\":\"".length(), endIndex);
	                tagList.add(new Tag(value));
	            }

	            startIndex = noticeTag.indexOf("{\"value\":\"", endIndex);
	        }

	        return tagList;
	    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
