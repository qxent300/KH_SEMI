<%@page import="java.util.HashMap"%>
<%@page import="com.kh.mvc.community.model.vo.UserRecipe"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../common/header.jsp" %>
    
<%
	List<UserRecipe> list = (List<UserRecipe>) request.getAttribute("list");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String category = (String) request.getAttribute("category");
	String search = (String) request.getAttribute("search");
	HashMap<Integer, String> map = (HashMap<Integer, String>) request.getAttribute("map");
%>   
 
<style>
	input[type=radio] {
	     display: none;
	}
	.container {
		padding-bottom: 0px;
	}
</style>

    <!-- CTA -->
    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Health_recipe_background.png);">
        <div class="container content-space-2 content-space-lg-3"></div>
    </div>
    <!-- End CTA -->
    
    <br><br>

    <h2 style="text-align: center; color: #cde99c;">나만의 레시피</h2>

	<main id="content" role="main">
		<!-- Card Grid -->
	    <div class="container mt-5">
	        <!-- Nav Scroller -->
	        <form action="<%=request.getContextPath() %>/community/userRecipeList" method="get">
		        <div class="js-nav-scroller hs-nav-scroller-horizontal mb-7">
		            <!-- Nav -->
		            <div class="js-filter-options nav nav-segment nav-pills d-flex mx-auto" style="max-width: 40rem;">
		                <div class="nav-item nav-link active">
		                    요리 종류
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="kindall"><label for="kindall">전체</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="rice"><label for="rice">밥</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="stew"><label for="stew">국 &amp; 찌개</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="side"><label for="side">반찬</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="alacarte"><label for="alacarte">일품</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="dessert"><label for="dessert">후식</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="kindetc"><label for="kindetc">기타</label>
		                </div>
		            </div>
		            <br>
		            <div class="js-filter-options nav nav-segment nav-pills d-flex mx-auto" style="max-width: 40rem;">
		                <div class="nav-item nav-link active">
		                    조리 방법
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="howtoall"><label for="howtoall">전체</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="boil"><label for="boil">끓이기</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="roast"><label for="roast">굽기</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="fry"><label for="fry">튀기기</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="steam"><label for="steam">찌기</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="stir"><label for="stir">볶기</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="category" value="howtoetc"><label for="howtoetc">기타</label>
		                </div>
		            </div>
		            <!-- End Nav -->
		            <br>
					<div class="w-lg-75 mx-lg-auto">
						<!-- Input Card -->
						<div class="input-card">
							<div class="input-card-form">
								<div class="input-group input-group-merge">
									<label for="searchAnswersForm" class="form-label visually-hidden">레시피를 검색해보세요.</label>
									<span class="input-group-prepend input-group-text">
										<i class="bi-search"></i>
									</span>
									<input type="text" name="search" class="form-control" id="searchAnswersForm" placeholder="레시피를 검색해보세요." aria-label="Search for answers">
								</div>
							</div>
							<button type="submit" class="btn btn-icon" style="background-color: #cde99c;">
								<i class="bi-arrow-right"></i>
							</button>
						</div>
						<!-- End Input Card -->
					</div>
				</div>
			</form>
	        <!-- End Nav Scroller -->
	    
	    	<div class="text-end">
	    		<button class="btn" style="background-color: #cde99c;" onclick="location.href='<%=request.getContextPath() %>/community/userRecipeWrite'">글쓰기</button>
	    	</div>
	        <br>
	        <%if(list.isEmpty() == true) { %>
	        	<br><br><br><br>
	        	<div class="text-center">검색 결과가 존재하지 않습니다.</div>
	        <%} else { %>
	        	<div class="js-shuffle row row-cols-1 row-cols-sm-2 row-cols-md-3">
	        	<%for(UserRecipe userRecipe : list) { %>
		        	<div class="js-shuffle-item col mb-5">
			            <!-- Card -->
			            <a class="card card-flush card-transition" href="<%=request.getContextPath() %>/community/userRecipeView?hrno=<%=userRecipe.getHR_NO() %>&userno=<%=userRecipe.getUSER_NO() %>">
				            <img class="card-img-top" src="<%=request.getContextPath() %>/resources/upload/userrecipe/<%=map.get(userRecipe.getUSER_NO()) %>/<%=userRecipe.getATT_FILE_NO_MK() %>" alt="<%=userRecipe.getRCP_NM() %>">
				            <div class="card-body">
				                <h3 class="card-title text-center"><%=userRecipe.getRCP_NM() %></h3>
				            </div>
			            </a>
			        </div>
	        	<%} %>
	        	</div>
	        <%} %>
	        
	
	        <!-- Pagination -->
	        <nav aria-label="Page navigation example">
	            <ul class="pagination justify-content-center pagination-lg">
	            
	            	<%if(pageInfo.getCurrentPage() != 1) { %>
		            	<%if(pageInfo.getCurrentPage() > 10) { %>
		            	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/community/userRecipeList?category=<%=category %>&page=1&search=<%=search %>" aria-label="Start">
		                        <span aria-hidden="true">«</span>
		                        <span class="visually-hidden">Start</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/community/userRecipeList?category=<%=category %>&page=<%=pageInfo.getCurrentPage() - 1 %>&search=<%=search %>" aria-label="Previous">
		                        <span aria-hidden="true">‹</span>
		                        <span class="visually-hidden">Previous</span>
		                    </a>
		                </li>
		                <%} %>
	            	<%} %>
	                
	                <%for(int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); i++) { %>
	                	<%if(i == pageInfo.getCurrentPage()) { %>
	                	<li class="page-item"><a class="page-link text-primary" style="background-color: #f8fafd; cursor: default;"><%=i %></a></li>
	                	<%} else { %>
		               	<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/community/userRecipeList?category=<%=category %>&page=<%=i %>&search=<%=search %>"><%=i %></a></li>
	                	<%} %>                
	                <%} %>
	                
	                <%if(pageInfo.getCurrentPage() != pageInfo.getMaxPage()) { %>
	                	<%if(pageInfo.getMaxPage() > 10) { %>
	                	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/community/userRecipeList?category=<%=category %>&page=<%=pageInfo.getCurrentPage() + 1 %>&search=<%=search %>" aria-label="Next">
		                        <span aria-hidden="true">›</span>
		                        <span class="visually-hidden">Next</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/community/userRecipeList?category=<%=category %>&page=<%=pageInfo.getMaxPage() %>&search=<%=search %>" aria-label="End">
		                        <span aria-hidden="true">»</span>
		                        <span class="visually-hidden">End</span>
		                    </a>
		                </li>
	                	<%} %>
	                <%} %>
	                
	            </ul>
	        </nav>
	        <!-- End Pagination -->
	
	    </div>
	    <!-- End Card Grid -->
	</main>


<%@include file="../common/footer.jsp" %>

    <!-- JS Plugins Init. -->
    <script>
        $(document).ready(function(){
       		if ('<%=category %>' == "") {
       			$('input:radio[value=kindall]').parent('div').addClass('active');
       			$('input:radio[value=kindall]').prop('checked', true);
       			$('input:radio[value=howtoall]').parent('div').addClass('active');
       			$('input:radio[value=howtoall]').prop('checked', true);
			} else {
				var test = $('input:radio[name=category]');
				
				for(var i = 0; i < test.length; i++) {
					test[i].parentElement.classList.remove('active');
					
                    if ('<%=category %>' == test[i].value) {
                    	test[i].parentElement.classList.add('active');
                    	test[i].checked = true;
    				}
                }
			}
        	
            $('.nav-item').click(function() {
                $(this).children('input[type=radio]').prop('checked', true);
                $('input:radio[name=category]').parent('div').removeClass('active');
                $(this).addClass('active');
            });
        });
    </script>

</body>
</html>