<%@page import="com.kh.mvc.community.model.vo.UserRecipeManual"%>
<%@page import="com.kh.mvc.community.model.vo.UserRecipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
    
<%
	UserRecipe userRecipe = (UserRecipe) request.getAttribute("userRecipe");
	String writer = (String) request.getAttribute("writer");
	List<UserRecipeManual> list = userRecipe.getRecipeMaual();
%>

    <!-- CTA -->
    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Health_recipe_background.png);">
        <div class="container content-space-2 content-space-lg-3"></div>
    </div>
    <!-- End CTA -->

    <br><br>

    <h2 style="text-align: center; color: #cde99c;">나만의 레시피 상세 정보</h2>
    
	<br><br>
	
    <!-- Gallery -->
    <div class="container mb-5">
        <div class="rounded-2 overflow-hidden">
        <%if(loginMember != null && loginMember.getId().equals(writer) == true) { %>
        	<div class="row mb-2 text-end">
        		<div class="col">
		        	<button class="btn" style="background-color: #cde99c" onclick="location.href='<%=request.getContextPath() %>/community/userRecipeDelete?hrno=<%=userRecipe.getHR_NO() %>&writer=<%=writer %>'">삭제하기</button>
        		</div>
        	</div>
        <%} %>
            <div class="row gx-2">
                <div class="col-md-4" style="text-align: center;">
                    <!-- Gallery -->
                    <img class="img-fluid w-75 rounded-2" src="<%=request.getContextPath() %>/resources/upload/userrecipe/<%=writer %>/<%=userRecipe.getATT_FILE_NO_MK() %>" alt="<%=userRecipe.getRCP_NM() %>">
                    <!-- End Gallery -->
                </div>
                <!-- End Col -->

                <div class="col-md-8 d-none d-md-inline-block">
                    <div class="mb-4">
                        <span class="h2"><%=userRecipe.getRCP_NM() %> </span><span class="h4">(<%=writer %> 님의 레시피)</span>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <dl class="row">
                                <div class="col-mb-2">
                                    <h3 style="color: #cde99c;">재료 정보</h3>
                                </div>
                                
                                <dd class="col-15"><%=userRecipe.getRCP_PARTS_DTLS() %></dd>
                            </dl>
                            
                        </div>
                        <!-- End Col -->
                    </div>
                    <!-- End Row -->
                </div>
                <!-- End Col -->

            </div>
        <!-- End Row -->
        </div>
    </div>
    <!-- End Gallery -->
    
    <div class="container border-top border-5">
    <%for(UserRecipeManual manual : list) { %>
    	<div class="row justify-content-lg-between align-items-md-center">
            <div class="col-md-6 col-lg-8 content-space-1 content-space-md-2 text-center">
                <p class="mb-4 h3"><span class="h1" style="color: #cde99c;"><%=manual.getSeq() %> </span><%=manual.getManual() %></p>
            </div>
            <div class="col-md-4" style="text-align: center;">
                <img src="<%=request.getContextPath() %>/resources/upload/userrecipe/<%=writer %>/<%=manual.getManualImg() %>" alt="<%=userRecipe.getRCP_NM() %><%=manual.getSeq() %>" class="w-75 rounded-2">
            </div>
        </div>
    <%} %>
    </div>

<%@include file="../common/footer.jsp" %>

</body>
</html>