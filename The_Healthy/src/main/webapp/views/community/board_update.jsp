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
        <br>
        <!-- Content -->
        <div class="container">
            <div class="card card-lg">

                
                <!-- Card -->
                <div class="card card-bordered shadow-none">
                    <div class="card-body">
                        <!-- Form -->
                        <form method="post" action="<%= request.getContextPath()%>/community/boardupdate" enctype="multipart/form-data">
                            <!-- Form -->
                          
                            <div class="row mb-3">
                                <div class="col-sm-9">
                                    	<input type="hidden" name="boardNo" value="<%= board.getNo() %>"/>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="title" class="col-sm-3 col-form-label">글 제목</label>
                                <div class="col-sm-9">
                                 <textarea class="form-control form-control-lg" name="title" id="title" required="required" style="height: 50px; resize: none;"><%=board.getTitle() %></textarea>
                                </div>
                            </div>
                            <!-- End Form -->
                
                            <!-- Form -->
                            <div class="row mb-3">
                                <label for="writer" class="col-sm-3 col-form-label form-label">작성자</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control form-control-lg" name="writer" id="writer" value="<%= board.getWriterId() %>"readonly/> 
                                </div>
                            </div>
                            <!-- End Form -->

                            <!-- Form -->
                            <div class="row mb-3">
                                <label for="uploadFile" class=" col-sm-3 js-file-attach col-form-label form-label"
                                    data-hs-file-attach-options='{
                                    "textTarget": "[for=\"customFile\"]"
                                    }'>첨부파일</label>
                                <div class="col-sm-9">
                                    <input class="form-control" name="upfile" type="file" id="upfile" src="<%= request.getContextPath() %>/resources/upload/board/<%=board.getOriginalFileName() %>">
                                    
                                </div>
                            </div>
                            <!-- End Form -->

                            <!-- Form-->
                            <div class="row mb-3">
                                <label for="content" class="col-sm-3 col-form-label form-label">내용</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control form-control-lg" name="content" id="content" required="required" style="height: 200px; resize: none;"><%=board.getContent() %></textarea>
                                </div>
                            </div>
                            <!-- End Form-->
                
                            <div class="text-center mt-5">
                                <button type="submit" class="btn btn-lg" style="background-color: #cde99c;">수정하기</button>
                                <button type="button" class="btn btn-lg" style="background-color: #cde99c;"><a href="<%=request.getContextPath()%>/community/boardlist">취소하기</a></button>
                            </div>
                        </form>
                        <!-- End Form -->
                    </div>
                </div>
                <!-- End Card -->
            </div>
        </div>
        <br>
    </main>


<%@include file="../common/footer.jsp" %>

    <!-- JS Plugins Init. -->
    <script>
        (function() {
        // INITIALIZATION OF FILE ATTACH
        // =======================================================
        new HSFileAttach('.js-file-attach')
        })()
    </script>
</body>
</html>