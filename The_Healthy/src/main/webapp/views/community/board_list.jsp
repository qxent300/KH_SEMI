<%@page import="com.kh.mvc.community.model.service.FreeBoardService"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.mvc.community.model.vo.FreeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
<%
	List<FreeBoard> list = (List<FreeBoard>)request.getAttribute("list");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	FreeBoardService service = new FreeBoardService(); 
%>
	<style>
		a {
			color: black;
		}
	</style>

    <main id="content" role="main">
        <!-- CTA -->
        <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/freeboard_background.png);">
            <div class="container content-space-2 content-space-lg-3"></div>
        </div>
        <!-- End CTA -->

        <br><br>

        <h1 style="text-align: center; color: #cde99c;">자유게시판</h1>

        <div class="container content-space-1 content-space-b-lg-3">
            <div class="text-end mb-1">
                <button type="button" class="btn btn-sm" style="background-color: #cde99c;">
                	<a href="<%=request.getContextPath() %>/community/boardwrite">글쓰기</a>
                </button>
            </div>
            <!-- Table -->
            <table class="table table-hover table-text-center">
                <thead style="background-color: #cde99c;">
                <tr>
                    <th scope="col" style="width: 8%;">번호</th>
                    <th scope="col" style="width: 46%;">제목</th>
                    <th scope="col" style="width: 8%;">작성자</th>
                    <th scope="col" style="width: 16%;">작성일자</th>
                    <th scope="col" style="width: 8%;">조회수</th>
                </tr>
                </thead>
                <tbody>
                <%if(list == null || list.isEmpty()) {%>
                <tr>
                    <td colspan="3">조회된 게시글이 없습니다.</td>
                </tr>
                <%} else { %>
                	<%for(FreeBoard b : list){ %>
            	    <tr>
                    	<th><%=b.getNo() %></th>
                  	 	 <td>
                	   		 <%String path = request.getContextPath() +"/community/boardview?boardNo=" + b.getNo(); %>
							 <a href="<%=path%>"><%=b.getTitle() %> <%if(service.getReplyCount(b.getNo()) > 0){ %>
								<small style="color: #35d555; font-weight: bolder; font-size: 18px;"> [<%=(service.getReplyCount(b.getNo())) %>]</small>
								<%} %>					
							 </a>
                   		 </td>
                   		 <td><%=b.getWriterId() %></td>
                   		 <td><%=b.getCreateDate() %></td>
                    	<td><%=b.getReadCount() %></td>
           	   	  	</tr>
                   <%} %>
                    
                <%} %>
           
                </tbody>
            </table>
            <!-- End Table -->

            <!-- Pagination -->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center pagination-lg">
                    <li class="page-item">
                        <a class="page-link" href='<%=request.getContextPath()%>/community/boardlist?page=<%=pageInfo.getStartPage() %>' aria-label="Previous">
                            <span aria-hidden="true">«</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href='<%=request.getContextPath()%>/community/boardlist?page=<%=pageInfo.getPrvePage() %>' aria-label="Previous">
                            <span aria-hidden="true">‹</span>
                        </a>
                    </li>
						<%for(int i = pageInfo.getStartPage(); i<= pageInfo.getEndPage(); i++){ %>
							<%if(i == pageInfo.getCurrentPage()){ %>
                    		    <li class="page-item"><a class="page-link" style="background-color: #f8fafd; cursor: default;" ><%= i %></a></li>
                   		    <%}else{ %>
                   		    	 <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/community/boardlist?page=<%=i%>"><%= i %></a></li>
                     	  	<%} %>
						<%} %>
						
                    <li class="page-item">
                        <a class="page-link" href='<%=request.getContextPath()%>/community/boardlist?page=<%=pageInfo.getNextPage()%>' aria-label="Next">
                            <span aria-hidden="true">›</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href='<%=request.getContextPath()%>/community/boardlist?page=<%=pageInfo.getEndPage()%>' aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- End Pagination -->
        </div>
    </main>
    

<%@include file="../common/footer.jsp" %>

</body>
</html>