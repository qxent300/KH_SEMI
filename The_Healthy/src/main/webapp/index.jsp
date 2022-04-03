<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="views/common/header.jsp" %>

<%
	Calendar calendar = Calendar.getInstance();
%>
	
	<main id="content" role="main">
        <div class="bg-img-start" style="height: 70px;"></div>
        
        <!-- Hero -->
        <div class="bg-img-start" style="background-color: #e6fad7;">
            <!-- Hero -->
            <div class="container content-space-1 contenspace-t-md-0">
                <div class="row justify-content-md-between align-items-md-center">
                    <div class="col-md-5 mb-7 mb-md-0">
                        <!-- Heading -->
                        <div class="mb-4">
                        <h1>나의 건강을 위한 레시피</h1>
                        <p>나에게 맞는 건강 레시피를 검색해보세요!</p>
                        </div>
                        <!-- End Heading -->
                
                        <form action="<%=request.getContextPath() %>/health/recipe" method="get">
                        <!-- Input Card -->
                        <div class="input-card border">
                            <div class="input-card-form">
	                            <label for="searchAppsForm" class="form-label visually-hidden">레시피 검색</label>
	                            <input type="text" name="search" class="form-control" id="searchAppsForm" placeholder="레시피 검색" aria-label="Search for apps">
                            </div>
                            <button type="submit" class="btn" style="background-color: #cde99c;">
                            	<i class="bi-search"></i>
                            </button>
                        </div>
                        <!-- End Input Card -->
                        </form>
                    </div>
                    <!-- End Col -->
            
                    <div class="col-md-5">
                        <img class="img-fluid" src="<%=request.getContextPath() %>/resources/image/cook.gif" alt="cook">
                    </div>
                    <!-- End Col -->
                </div>
                <!-- End Row -->
            </div>
            <!-- End Hero -->
        </div>
        <!-- End Hero -->


        <!-- Card Grid -->
        <div class="container content-space-2 content-space-lg-3">
            <div class="row gx-3 mb-5 mb-md-9">
                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-0">
                    <!-- Card -->
                    <a class="card card-transition h-100" href="<%=request.getContextPath() %>/health/meal">
                        <img class="card-img-top" src="<%=request.getContextPath() %>/resources/image/calc_icon.png" alt="calc">
                        <div class="card-body text-center">
                            <h5 class="card-text lh-base">식단 구성</h5>
                        </div>
                    </a>
                    <!-- End Card -->
                </div>
                <!-- End Col -->
        
                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-0">
                    <!-- Card -->
                    <a class="card card-transition h-100" href="<%=request.getContextPath() %>/eat/season">
                        <img class="card-img-top" src="<%=request.getContextPath() %>/resources/image/season_icon.png" alt="season">
                        <div class="card-body text-center">
                            <h5 class="card-text lh-base">제철 농산물</h5>
                        </div>
                    </a>
                    <!-- End Card -->
                </div>
                <!-- End Col -->
        
                <div class="col-sm-6 col-lg-3 mb-3 mb-sm-0">
                    <!-- Card -->
                    <a class="card card-transition h-100" href="<%=request.getContextPath() %>/eat/nutrition">
                        <img class="card-img-top" src="<%=request.getContextPath() %>/resources/image/nutrition_icon.png" alt="nutrition">
                        <div class="card-body text-center">
                            <h5 class="card-text lh-base">건강기능식품</h5>
                        </div>
                    </a>
                    <!-- End Card -->
                </div>
                <!-- End Col -->
        
                <div class="col-sm-6 col-lg-3">
                    <!-- Card -->
                    <a class="card card-transition h-100" href="<%=request.getContextPath() %>/community/boardlist">
                        <img class="card-img-top" src="<%=request.getContextPath() %>/resources/image/board_icon.png" alt="board">
                        <div class="card-body text-center">
                            <h5 class="card-text lh-base">자유게시판</h5>
                        </div>
                    </a>
                    <!-- End Card -->
                </div>
                <!-- End Col -->
            </div>
            <!-- End Row -->

            <br><br><br><br><br><br>

            <div class="mb-3">
                <!-- Heading -->
                <div class="w-md-75 w-lg-50 text-center mx-md-auto">
                    <h2>추천 레시피</h2>
                </div>
                <div class="text-end">
                    <button type="button" class="btn" style="background-color: #cde99c;" onclick="location.href='<%=request.getContextPath() %>/health/recipe'">더보기</button>
                </div>
                <!-- End Heading -->
            </div>
            
            <div id="recommendRecipe" class="row"></div>


            <br><br><br><br><br><br>

            <div class="mb-3">
                <!-- Heading -->
                <div class="w-md-75 w-lg-50 text-center mx-md-auto">
                    <h2>이달의 제철 농산물</h2>
                </div>
                <div class="text-end">
                    <button type="button" class="btn" style="background-color: #cde99c;" onclick="location.href='<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=calendar.get(Calendar.MONTH) + 1 %>월'">더보기</button>
                </div>
                <!-- End Heading -->
            </div>            
            
            <div id="recommendSeason" class="row"></div>
        </div>
        <!-- End Card Grid -->
    </main>

<%@include file="views/common/footer.jsp" %>

	<script>
		$(document).ready(function(){
			$.ajax({
				type: "get",
				url: "<%=request.getContextPath() %>/recommend/recipe",
				dataType: "json",
				success: (list) => {
					$.each(list, (i, obj) => {
						var img = "<a class='card card-sm card-stretched-vertical card-transition bg-img-start' href='" + "<%=request.getContextPath() %>" + "/health/recipe/view?hrno=" + obj.hrNo + "' style='background-image: url(" + obj.img + "); min-height: 15rem;'><div class='card-body'></div></a>";
						var title = "<div class='card-footer pt-0 text-center mt-3' style='padding: 0px 0px;'><span class='card-text lh-base h4'>" + obj.rcpNm + "</span></div>";

						$('#recommendRecipe').append("<div class='col'>" + img + title +"</div>")
					});
				}
			});
			
			$.ajax({
				type: "get",
				url: "<%=request.getContextPath() %>/recommend/season",
				dataType: "json",
				success: (list) => {
					$.each(list, (i, obj) => {
						var img = "<a class='card card-sm card-stretched-vertical card-transition bg-img-start' href='" + "<%=request.getContextPath() %>" + "/eat/season/view?hsno=" + obj.hsNo + "' style='background-image: url(" + obj.img + "); min-height: 15rem;'><div class='card-body'></div></a>";
						var title = "<div class='card-footer pt-0 text-center mt-3' style='padding: 0px 0px;'><span class='card-text lh-base h4'>" + obj.prdlstNm + "</span></div>";

						$('#recommendSeason').append("<div class='col'>" + img + title +"</div>")
					});
				}
			});
		});
	</script>