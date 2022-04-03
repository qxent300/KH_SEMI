package com.kh.mvc.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.community.model.service.UserRecipeService;
import com.kh.mvc.community.model.vo.UserRecipe;
import com.kh.mvc.community.model.vo.UserRecipeManual;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/community/userRecipeDelete")
public class UserRecipeDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserRecipeService userRecipeService = new UserRecipeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String writer = req.getParameter("writer");
			
			if (loginMember != null && writer.equals(loginMember.getId()) == true) {
				UserRecipe userRecipe = userRecipeService.findUserRecipeByNo(Integer.parseInt(req.getParameter("hrno")));
				int result = userRecipeService.deleteUserRecipe(Integer.parseInt(req.getParameter("hrno")));
				
				if (result > 0) {
					deleteFile(userRecipe, writer);	// 첨부파일 삭제
					req.setAttribute("msg", "게시글 삭제 성공");
				} else {
					req.setAttribute("msg", "게시글 삭제 실패...!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "게시글 삭제를 실패하였습니다.");
		}
		
		req.setAttribute("location", "/community/userRecipeList");
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

	private void deleteFile(UserRecipe userRecipe, String writer) {
		try {
			String path = getServletContext().getRealPath("/resources/upload/userrecipe/" + writer);
			File deleteImage = new File(path, userRecipe.getATT_FILE_NO_MK());
			deleteImage.delete();
			
			List<UserRecipeManual> list = userRecipe.getRecipeMaual();
			
			for (UserRecipeManual userRecipeManual : list) {
				File deleteImg = new File(path, userRecipeManual.getManualImg());
				deleteImg.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
