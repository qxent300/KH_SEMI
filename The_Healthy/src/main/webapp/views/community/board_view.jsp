<%@page import="com.kh.mvc.community.model.vo.Reply"%>
<%@page import="com.kh.mvc.community.model.vo.FreeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="../common/header.jsp" %>
<%
	FreeBoard board = (FreeBoard)request.getAttribute("board");
	
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

        <!-- Content -->
        <div class="container">

            <!-- Jobs Link -->
            <div class="mt-3 mb-1">
                <div class="row">
                    <div class="col" style="display: table;">
                        <div style="display: table-cell; vertical-align: middle;">
                            <a class="link" href="<%=request.getContextPath()%>/community/boardlist" style="color: #cde99c;">
                                <i class="bi-chevron-left small ms-1"></i> 목록으로
                            </a>
                        </div>
                    </div>
                    <%
						if(loginMember != null){
							if(loginMember.getId().equals(board.getWriterId())){
                    %>
                    <div class="col text-end mb-1">
                        <button type="button" class="btn btn-sm" style="background-color: #cde99c;"><a href="<%=request.getContextPath()%>/community/boardDelete?boardNo=<%= board.getNo()%>">삭제<a></button>
                        <button type="button" class="btn btn-sm" style="background-color: #cde99c;"><a href="<%=request.getContextPath()%>/community/boardupdate?boardNo=<%=board.getNo()%>">수정</a></button>
                    </div>
                    <%
                    	}
						}
                    %>
                  
                </div>
            </div>
            <!-- End Jobs Link -->

            <!-- Card -->
            <div class="card card-lg">
                <!-- Header -->
                <div class="card-header py-sm-3" style="background-color: #cde99c;">
                    <div class="row">
                        <div class="col h2"><%=board.getTitle() %></div>
                        <div class="col" style="display: table;">
                            <div class="text-end" style="display: table-cell; vertical-align: middle;"><%=board.getModifyDate() %></div>
                        </div>
                    </div>
                </div>
                <!-- End Header -->
            
                <!-- Card Body -->
                <div class="card-body" style="padding: 20px 40px 20px 40px;">
                    <div class="mb-7">
                        <h5 class="text-end text-muted"><%=board.getWriterId() %></h5>
                        <br>
                        <p>
						<%
						String name = board.getOriginalFileName();
						String reName = board.getRenamedFileName();
						if(name != null && name.length() > 0 ){
						%>
								<img src="<%= request.getContextPath() %>/resources/upload/board/<%=reName %>" />
						<% 	
						}
						%>  
						<br>
                        <%=board.getContent() %>
                        </p>
                    </div>
                </div>
                <!-- End Card Body -->
				
                <hr class="my-6">
                <form action="<%=request.getContextPath()%>/community/replySave" method="post" accept-charset="utf-8">
                <div class="row justify-content-lg-center">
                    <div class="col-lg-10">
                        <div class="input-group">
                        	<input type="hidden" name="boardNo" value="<%=board.getNo()%>" />
                            <textarea name="content" id="content" class="form-control" aria-label="With textarea" placeholder="로그인시 이용가능합니다." required ></textarea>
                            <button class="btn" style="background-color: #cde99c;">등록</button>
                        </div>
                    </div>
                </div>
                </form>

                <hr class="my-6">
				

                <!-- Comment -->
                <form action="<%=request.getContextPath()%>/community/replyDel" method="get">
              	  <div class="container">
                    <div class="row justify-content-lg-center">
                        <div class="col-lg-10">
                            <!-- Comment -->
                            <ul class="list-comment">
                                <!-- Item -->
                                <li class="list-comment-item">
                                    <!-- Media -->
            			 	   <%for(Reply reply : board.getReplies()) { %>
                				<%if(reply != null) {%>
                                    <div class="d-flex align-items-center mb-3">
                                        <div class="flex-grow-1 ms-3">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h6><%=reply.getWriterId() %></h6>
                                                <span ><%=reply.getCreateDate() %></span>
                                            </div>
	                                   			 <p><%= reply.getContent() %></p>
                                        </div>
                   			 				  	<%
									if(loginMember != null){
										if(loginMember.getId().equals(reply.getWriterId())){ 
             			   		    %>
             			   		    	<input type="hidden" name="replyNo" value="<%=reply.getNo()%>" />
	                                            <button class="btn" style="background-color: #cde99c;">삭제</button>
                    			   <%
                    				}
									}
              				   	   %>
                                    </div>
                           
                   				  <%} %>           
                   				  <%} %>    
                           
                                <!-- End Item -->
                            </ul>
                            <!-- End Comment -->
                        </div>
                        <!-- End Col -->
                    </div>
                    <!-- End Row -->
                    <br>
                </div>
                </form>
                <!-- End Comment -->
            </div>
            <!-- End Card -->
        </div>
        <!-- End Content -->
    </main>

    <br>

<%@include file="../common/footer.jsp" %>
<script type="text/javascript">

    
</script>
