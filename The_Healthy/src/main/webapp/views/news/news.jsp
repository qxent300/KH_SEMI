<%@page import="com.kh.mvc.news.model.vo.News"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
    
<%
	List<News> list = (List<News>)request.getAttribute("list");
%>

<!-- ========== MAIN CONTENT ========== -->
    <main id="content" role="main">
        <!-- CTA -->
	    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/news_background.png);">
	        <div class="container content-space-2 content-space-lg-3"></div>
	    </div>
	    <!-- End CTA -->
		
		<br><br><br>
		<h2 style="text-align: center; color: #cde99c;">오늘의 푸드 뉴스</h2>
		<br><br>
		<table style="border:none;" align="center">
			<%for(int i = 0; i < 3; i++) { %>
			<tr style="border:none;">
				<%for(int j = 0; j < 3; j++) { %>
                	<%
                		if((i * 3 + j) < list.size()) { 
							News news = list.get(i * 3 + j);                			
                	%>
						<td style="border:none;">
							<!-- Card -->
							<div class="card" style="max-width: 20rem;">
							  <div class="card-body">
							    <h3 class="card-title"><%=news.getTitle()%></h3>
							    <p class="card-text"><%=news.getDescription()%></p>
							    <a class="card-link" style="color:#66B62F;" target="_blank" href="<%=news.getOriginallink()%>">더보기</a>
							  </div>
							</div>
							<!-- End Card -->
						</td>
					<% } %>
				<% } %>
			</tr>
			<% } %>
		</table>
		
		
		
    </main>
    <!-- ========== END MAIN CONTENT ========== -->
    
	<!-- CSS table -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/assets/css/table.css">

<%@include file="../common/footer.jsp" %>
</body>
</html>