<%@page import="com.kh.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- Required Meta Tags Always Come First -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>The Healthy</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/front-v4.1/favicon.ico">

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-mega-menu/dist/hs-mega-menu.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/vendor/swiper/swiper-bundle.min.css">

    <!-- CSS Front Template -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/assets/css/theme.min.css">
    
    <style>
    	.navbar .dropdown-menu::before {
    		background-color: #cde99c;
    		height: 10px;
    	}
    </style>
</head>
<body>
    <!-- ========== HEADER ========== -->
    <header id="header" class="navbar navbar-expand-lg navbar-end navbar-sticky-top navbar-light bg-white">
        <div class="container">
            <nav class="js-mega-menu navbar-nav-wrap">
                <!-- Default Logo -->
                <a class="navbar-brand" href="<%=request.getContextPath() %>" aria-label="Front">
                    <img class="navbar-brand-logo" src="<%=request.getContextPath() %>/resources/image/LOGO.svg" alt="Logo">
                </a>
                <!-- End Default Logo -->
        
                <!-- Toggler -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-default">
                        <i class="bi-list"></i>
                    </span>
                    <span class="navbar-toggler-toggled">
                        <i class="bi-x"></i>
                    </span>
                </button>
                <!-- End Toggler -->
        
                <!-- Collapse -->
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <!-- Company -->
                        <li class="hs-has-sub-menu nav-item">
                            <a id="companyMegaMenu" class="hs-mega-menu-invoker nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">?????????</a>
            
                            <!-- Mega Menu -->
                            <div class="hs-sub-menu dropdown-menu" aria-labelledby="companyMegaMenu" style="min-width: 14rem;">
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/health/recipe">?????? ?????????</a>
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/health/meal">?????? ?????? ??????</a>
                            </div>
                            <!-- End Mega Menu -->
                        </li>
                        <!-- End Company -->
            
                        <!-- Company -->
                        <li class="hs-has-sub-menu nav-item">
                            <a id="companyMegaMenu" class="hs-mega-menu-invoker nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">?????? ?????????</a>
            
                            <!-- Mega Menu -->
                            <div class="hs-sub-menu dropdown-menu" aria-labelledby="companyMegaMenu" style="min-width: 14rem;">
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/eat/season">?????? ?????????</a>
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/eat/nutrition">??????????????????</a>
                            </div>
                            <!-- End Mega Menu -->
                        </li>
                        <!-- End Company -->
            
                        <!-- Account -->
                        <li class="hs-has-sub-menu nav-item">
                            <a id="accountMegaMenu" class="hs-mega-menu-invoker nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">????????????</a>
            
                            <!-- Mega Menu -->
                            <div class="hs-sub-menu dropdown-menu" aria-labelledby="accountMegaMenu" style="min-width: 14rem;">
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/community/boardlist">???????????????</a>
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/community/userRecipeList">????????? ?????????</a>
                                <a class="dropdown-item" href="<%=request.getContextPath() %>/community/news">????????? ?????? ??????</a>
                            </div>
                            <!-- End Mega Menu -->
                        </li>
                        <!-- End Account -->
                        
                       <%if(loginMember != null) { %>
                       		<li class="nav-item">
                        		
                        	</li>
                        	<li class="nav-item">
                        		<%if(loginMember.getId().equals("admin")) { %>
                        			<span><%=loginMember.getId() %> ??? ???????????????.</span>
                        		<%} else { %>
                        			<span><a href="<%=request.getContextPath() %>/member/info"><%=loginMember.getId() %></a> ??? ???????????????.</span>
                        		<%} %>
                        	</li>
                        	
                        	<!-- Button -->
	                        <li class="nav-item">
	                            <a class="btn btn-transition" onclick="logout()" style="background-color: #cde99c;">????????????</a>
	                        </li>
	                        <!-- End Button -->
                        <%} else { %>
	                        <!-- Button -->
	                        <li class="nav-item">
	                            <a class="btn btn-transition" data-bs-toggle="modal" data-bs-target="#loginModalCenter" style="background-color: #CDE99C;">?????????</a>
	                        </li>
	                        <!-- End Button -->
                        <%} %>
                    </ul>
                </div>
                <!-- End Collapse -->
            </nav>
        </div>
    </header>
    <!-- ========== END HEADER ========== -->

    <!-- Modal -->
    <div id="loginModalCenter" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">
                        <!-- ?????????/????????????/???????????? ?????? -->
                        <div class="text-center">
                            <ul class="nav nav-segment nav-pills mb-7" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="loginTab" href="#login" data-bs-toggle="pill" data-bs-target="#login" role="tab" aria-controls="login" aria-selected="true">?????????</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="enrollTab" href="#enroll" data-bs-toggle="pill" data-bs-target="#enroll" role="tab" aria-controls="enroll" aria-selected="false">????????????</a>
                                </li>
                            </ul>
                        </div>
                    </h5>
                    <!-- ?????????/???????????? ?????? ??? -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Tab Content -->
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="loginTab">
                            <form id="loginForm" class="js-validate needs-validation" method="post" action="<%=request.getContextPath() %>/member/login">
                                <!-- ????????? ??? -->
                                <!-- ?????????(?????????) -->
                                <div class="mb-4">
                                    <label class="form-label" for="userID">?????????</label>
                                    <input type="text" class="form-control form-control-lg" name="userId" id="userID" placeholder="?????????" required>
                                    <span class="invalid-feedback">!???????????? ?????? ??????????????????.</span>
                                </div>
                                <!-- ?????????(?????????) ??? -->

                                <!-- ???????????? -->
                                <div class="mb-4">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <label class="form-label" for="userPassword">????????????</label>
                                    </div>
                                    <div class="input-group input-group-merge" data-hs-validation-validate-class="">
                                        <input type="password" class="js-toggle-password form-control form-control-lg" name="userPwd" id="userPassword" placeholder="????????????" aria-label="4+ characters required" required minlength="4" data-hs-toggle-password-options="{target :  #changePassTarget ,defaultClass :  bi-eye-slash ,showClass :  bi-eye ,classChangeTarget :  #changePassIcon}">
                                        <a id="changePassTarget" class="input-group-append input-group-text" href="javascript:;">
                                            <i id="changePassIcon" class="bi-eye-slash"></i>
                                        </a>
                                    </div>

                                    <span class="invalid-feedback">!??????????????? ?????? ??????????????????.</span>
                                </div>
                                <!-- ???????????? ??? -->
                                
                                <input type="hidden" id="currentUrl" name="currentUrl" value="">

                                <div class="d-grid mb-3">
                                    <button type="button" class="btn" onclick="login()" style="background-color: #cde99c;">?????????</button>
                                </div>
                            </form>
                        </div>
                        <!-- ????????? ??? ??? -->

                        <!-- ???????????? ??? -->
                        <div class="tab-pane fade" id="enroll" role="tabpanel" aria-labelledby="enrollTab">
                            <form id="enrollForm" class="js-validate needs-validation" method="post" action="<%=request.getContextPath()%>/member/enroll">
                                <!-- ?????????(????????????) -->
                                <div class="mb-3">
                                	<div class="row">
                                		<div class="col">
		                                    <label class="form-label" for="enrollID">?????????</label>
	                                	</div>
	                                	<div class="col text-end">
	                                		<span><a href="#" onclick="doubleCheckId()">????????????</a></span>
	                                	</div>
                                	</div>
                                    <input type="text" class="form-control form-control-lg" name="enrollID" id="enrollID" placeholder="?????????" required>
                                    <span class="invalid-feedback">!???????????? ?????? ??????????????????.</span>
                                </div>
                                <!-- ?????????(????????????) ??? -->

                                <!-- ???????????? -->
                                <div class="mb-3">
                                    <label class="form-label" for="enrollPassword">????????????</label>

                                    <div class="input-group input-group-merge" data-hs-validation-validate-class="">
                                        <input type="password" class="js-toggle-password form-control form-control-lg" name="enrollPassword" id="enrollPassword" placeholder="????????????" aria-label="4+ characters required" required data-hs-toggle-password-options="{
                        target: [.js-toggle-password-target-1, .js-toggle-password-target-2],
                        defaultClass: bi-eye-slash,
                        showClass: bi-eye,
                        classChangeTarget: .js-toggle-passowrd-show-icon-1
                    }">
                                        <a class="js-toggle-password-target-1 input-group-append input-group-text" href="javascript:;">
                                            <i class="js-toggle-passowrd-show-icon-1 bi-eye-slash"></i>
                                        </a>
                                    </div>

                                    <span class="invalid-feedback">!??????????????? ????????????.4??? ???????????? ??????????????????.</span>
                                </div>
                                <!-- ???????????? ??? -->

                                <!-- ???????????? ????????? -->
                                <div class="mb-3">
                                    <label class="form-label" for="enrollConfirmPassword">???????????? ?????????</label>

                                    <div class="input-group input-group-merge" data-hs-validation-validate-class="">
                                        <input type="password" class="js-toggle-password form-control form-control-lg" name="enrollConfirmPassword" id="enrollConfirmPassword" placeholder="????????????" aria-label="4+ characters required" required data-hs-validation-equal-field="#enrollPassword" data-hs-toggle-password-options="{
                        target: [.js-toggle-password-target-1, .js-toggle-password-target-2],
                        defaultClass: bi-eye-slash,
                        showClass: bi-eye,
                        classChangeTarget: .js-toggle-passowrd-show-icon-2
                    }">
                                        <a class="js-toggle-password-target-2 input-group-append input-group-text" href="javascript:;">
                                            <i class="js-toggle-passowrd-show-icon-2 bi-eye-slash"></i>
                                        </a>
                                    </div>
                                    <span class="invalid-feedback">!??????????????? ????????????.</span>
                                </div>
                                <!-- ???????????? ????????? ??? -->

                                <!-- ?????? -->
                                <div class="mb-3">
                                    <label class="form-label" for="enrollName">??????</label>
                                    <input type="text" class="form-control form-control-lg" name="enrollName" id="enrollName" placeholder="??????" required>
                                    <span class="invalid-feedback">!????????? ??????????????????.</span>
                                </div>
                                <!-- ?????? ??? -->

                                <!-- ????????? -->
                                <div class="mb-3">
                                    <label class="form-label" for="enrollEmail">?????????</label>
                                    <input type="email" class="form-control form-control-lg" name="enrollEmail" id="enrollEmail" placeholder="email@site.com" aria-label="email@site.com" required>
                                    <span class="invalid-feedback">!???????????? ??????????????????.</span>
                                </div>
                                <!-- ????????? ??? -->

                                <!-- ?????? -->
                                <div class="mb-3">
                                    <label class="form-label" for="enrollAddress">??????</label>
                                    <input type="text" class="form-control form-control-lg" name="enrollAddress" id="enrollAddress" placeholder="??????" required>
                                    <span class="invalid-feedback">!????????? ??????????????????.</span>
                                </div>
                                <!-- ?????? ??? -->
                                <input type="hidden" id="currentEnrollUrl" name="currentEnrollUrl" value="">
                                
                                <div class="d-grid mb-3">
                                    <button type="button" class="btn" onclick="enroll()" style="background-color: #cde99c;">????????????</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- ???????????? ??? -->
                    <!-- End Tab -->
                </div>
            </div>
        </div>
    </div>
    <!-- End Modal -->