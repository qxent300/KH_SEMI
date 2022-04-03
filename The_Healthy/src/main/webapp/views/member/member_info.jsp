<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>

<%
	Member member = (Member) request.getAttribute("member");
%>

	<style>
        .nav-vertical.nav-tabs .nav-link.active {
            border-color: #cde99c;
        }
        .nav-tabs .nav-link.active {
            color: #cde99c;
        }
    </style>

	<main id="content" role="main">
        <!-- CTA -->
        <div class="bg-img-center" style="background-color: #e6fad7;">
            <div class="container content-space-2 content-space-lg-3"></div>
        </div>
        <!-- End CTA -->

        <div class="container content-space-1">
            <div class="row">
                <!-- Col -->
                <div class="col-lg-3">
                    <!-- Navbar -->
                    <div class="navbar-expand-lg navbar-light">
                        <div id="sidebarNav" class="collapse navbar-collapse navbar-vertical">
                            <!-- Card -->
                            <div class="card flex-grow-1 mb-5">
                                <div class="card-body">
                                    <!-- Avatar -->
                                    <div class="d-none d-lg-block text-center mb-5">
                                        <div class="avatar avatar-xxl avatar-circle mb-3">
                                            <img class="avatar-img" src="<%=request.getContextPath() %>/resources/image/personal_icon.jpg" alt="회원 이미지">
                                        </div>
                                        <h4 class="card-title mb-0"><%=member.getName() %></h4>
                                        <p class="card-text small"><%=member.getEmail() %></p>
                                    </div>
                                    <!-- End Avatar -->

                                    <!-- Nav -->
                                    <span class="text-cap">회원 정보</span>

                                    <!-- List -->
                                    <ul class="nav nav-sm nav-tabs nav-vertical mb-4">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="<%=request.getContextPath() %>/member/info">
                                                <i class="bi-person-badge nav-icon"></i> 회원 정보 수정
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link " href="#" data-bs-toggle="modal" data-bs-target="#memberDeleteModal">
                                                <i class="bi-person-x nav-icon"></i> 회원 탈퇴
                                            </a>
                                        </li>
                                    </ul>
                                    <!-- End List -->
                                </div>
                            </div>
                            <!-- End Card -->
                        </div>
                    </div>
                    <!-- End Navbar -->
                </div>
                <!-- End Col -->
                
                <!-- Modal -->
				<div class="modal fade" id="memberDeleteModal" tabindex="-1" role="dialog" aria-labelledby="memberDeleteModal" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">회원 탈퇴</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <form action="<%=request.getContextPath() %>/member/delete" method="get">
					      <div class="modal-body">
					      	<label class="form-label" for="memberDeletePwd">회원 탈퇴를 원하시면 비밀번호를 입력해주세요.</label>
				          	<input type="password" name="userPwd" id="memberDeletePwd" class="form-control"> 
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-white" data-bs-dismiss="modal">취소</button>
					        <button type="submit" class="btn" style="background-color: #cde99c;">확인</button>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
				<!-- End Modal -->

                <!-- Col -->
                <div class="col-lg-9">
                    <!-- Card -->
                    <div class="card">
                        <div class="card-header border-bottom">
                            <h4 class="card-header-title" style="color: #cde99c;">회원 정보 수정</h4>
                        </div>

                        <!-- Body -->
                        <div class="card-body">
                            <form action="<%=request.getContextPath() %>/member/update" method="post">
                                <!-- Form -->
                                <div class="row mb-4">
                                    <label for="userId" class="col-sm-3 col-form-label form-label">아이디</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="userId" id="userId" value="<%=member.getId() %>" readonly>
                                    </div>
                                </div>
                                <!-- End Form -->

                                <!-- Form -->
                                <div class="row mb-4">
                                    <label for="userName" class="col-sm-3 col-form-label form-label">이름</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="userName" id="userName" value="<%=member.getName() %>" readonly>
                                    </div>
                                </div>
                                <!-- End Form -->

                                <!-- Form -->
                                <div class="row mb-4">
                                    <label for="userEmail" class="col-sm-3 col-form-label form-label">이메일</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="userEmail" id="userEmail" value="<%=member.getEmail() %>" >
                                    </div>
                                </div>
                                <!-- End Form -->

                                <!-- Form -->
                                <div class="row mb-4">
                                    <label for="userAddress" class="col-sm-3 col-form-label form-label">주소</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="userAddress" id="userAddress" value="<%=member.getAddress() %>" >
                                    </div>
                                </div>
                                <!-- End Form -->

                                <div class="row mb-4">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-9 text-end">
                                        <button type="submit" class="btn" style="background-color: #cde99c;">수정하기</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- End Body -->
                    </div>
                    <!-- End Card -->
                </div>
                <!-- End Col -->
            </div>
        </div>        
    </main>

<%@include file="../common/footer.jsp" %>

</body>
</html>