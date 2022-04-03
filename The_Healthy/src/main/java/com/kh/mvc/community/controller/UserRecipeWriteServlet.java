package com.kh.mvc.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyFileRenamePolicy;
import com.kh.mvc.community.model.service.UserRecipeService;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.community.model.vo.UserRecipeManual;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/community/userRecipeWrite")
public class UserRecipeWriteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
	private UserRecipeService userRecipeService = new UserRecipeService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			
			if (loginMember != null) {
				req.getRequestDispatcher("/views/community/userRecipe_write.jsp").forward(req, resp);
				
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
		req.setAttribute("location", "/community/userRecipeList");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member loginMember = (Member) req.getSession().getAttribute("loginMember");
			UserRecipe userRecipe = new UserRecipe();
			List<UserRecipeManual> manualList = new ArrayList<UserRecipeManual>();
			
			String path = getServletContext().getRealPath("/resources/upload/userrecipe/" + loginMember.getId());
			
			File dir = new File(path);
			
			if (dir.exists() == false) {
				dir.mkdirs();
			}
			
			int maxSize = 104857600;
			String encoding = "UTF-8";
			
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());

			if (loginMember.getId().equals(mr.getParameter("writer")) == false) {
				sendCommonPage(req, resp, "잘못된 접근입니다.(Account Mismatch)", "/");
				
				return;
			}
			
			if (mr.getFilesystemName("representPicture") == null) {
				sendCommonPage(req, resp, "대표 이미지를 업로드하세요.", "/community/userRecipeList");
				
				return;
			}
			
			int count = Integer.parseInt(mr.getParameter("count"));
			
			for (int i = 1; i <= count; i++) {
				if (mr.getFilesystemName("cook" + i) == null) {
					sendCommonPage(req, resp, "조리 사진을 업로드하세요.", "/community/userRecipeList");
					
					return;
				}
			}
			
			userRecipe.setRCP_NM(mr.getParameter("userRecipeTitle"));
			userRecipe.setUSER_NO(memberService.findMemberById(mr.getParameter("writer")).getNo());
			userRecipe.setATT_FILE_NO_MK(mr.getFilesystemName("representPicture"));
			userRecipe.setRCP_PARTS_DTLS(mr.getParameter("userRecipeIngredients"));
			userRecipe.setRCP_PAT2(mr.getParameter("rcp_pat2"));
			userRecipe.setRCP_WAY2(mr.getParameter("rcp_way2"));
			
			int insertResult = userRecipeService.insertUserRecipe(userRecipe);
			
			if (insertResult < 1) {
				sendCommonPage(req, resp, "게시글 저장에 실패하였습니다.", "/community/userRecipeList");
				
				return;
			}
			
			int userRecipeNo = userRecipeService.findUserRecipeNoByTitle(mr.getParameter("userRecipeTitle"));

			for (int i = 1; i <= count; i++) {
				UserRecipeManual userRecipeManual = new UserRecipeManual(userRecipeNo);
				userRecipeManual.setHR_NO(userRecipeNo);
				userRecipeManual.setSeq(i);
				userRecipeManual.setManualImg(mr.getFilesystemName("cook" + i));
				userRecipeManual.setManual(mr.getParameter("content" + i));

				manualList.add(userRecipeManual);
			}
			
			int insertManualResult = userRecipeService.insertUserRecipeManual(manualList);
			
			if (insertManualResult < 1) {
				sendCommonPage(req, resp, "게시글 저장에 실패하였습니다.", "/community/userRecipeList");
				
				return;
			}
			
			sendCommonPage(req, resp, "게시글이 등록되었습니다.", "/community/userRecipeList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendCommonPage(HttpServletRequest req, HttpServletResponse resp, String msg, String location) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("location", location);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}
	
}
