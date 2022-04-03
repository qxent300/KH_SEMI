<%@page import="com.kh.mvc.season.model.vo.HealthSeason"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@include file="../common/header.jsp" %>

    
<%
	List<HealthSeason> list = (List<HealthSeason>) request.getAttribute("list");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	String M_DISTCTNS = (String)request.getAttribute("M_DISTCTNS");
%>    



	<style>
		input[type=radio] {
		     display: none;
		}
    </style>
	<!-- NAV -->
	    <!-- CTA -->
	    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Season_background.jpg);">
	        <div class="container content-space-2 content-space-lg-3"></div>
	    </div>
	    <!-- End CTA -->
	
	    <br><br>
    <!-- End NAV -->

	<!-- Contents -->
	    <h2 style="text-align: center; color: #cde99c;">제철 농산물</h2>
	
	    <!-- Card Grid -->
	    <div class="container content-space-b-2 content-space-b-lg-3 mt-5">
	        <!-- Nav Scroller -->
	        <form action="<%=request.getContextPath() %>/health/recipe" method="get">
		        <div class="js-nav-scroller hs-nav-scroller-horizontal mb-7">
		            <!-- Nav -->
		            <div class="js-filter-options nav nav-segment nav-pills d-flex mx-auto" style="max-width: 57rem;">
		                <div class="nav-item nav-link active">
		                    월별
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_1" value="전체" checked>
							<label for="M_DISTCTNS_1">전체</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_2" value="1월">
							<label for="M_DISTCTNS_2">1월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_3" value="2월">
							<label for="M_DISTCTNS_3">2월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_4" value="3월">
							<label for="M_DISTCTNS_4">3월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_5" value="4월">
							<label for="M_DISTCTNS_5">4월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_6" value="5월">
							<label for="M_DISTCTNS_6">5월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_7" value="6월">
							<label for="M_DISTCTNS_7">6월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_8" value="7월">
							<label for="M_DISTCTNS_8">7월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_9" value="8월">
							<label for="M_DISTCTNS_9">8월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_10" value="9월">
							<label for="M_DISTCTNS_10">9월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_11" value="10월">
							<label for="M_DISTCTNS_11">10월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_12" value="11월">
							<label for="M_DISTCTNS_12">11월</label>
		                </div>
		                <div class="nav-item nav-link">
		                    <input type="radio" name="M_DISTCTNS" id="M_DISTCTNS_13" value="12월">
							<label for="M_DISTCTNS_13">12월</label>
		                </div>
		            </div>
		            <!-- End Nav -->
		            <br>
				</div>
			</form>
	        <!-- End Nav Scroller -->
	    
	        <%if(list.isEmpty() == true) { %>
	        	<br><br><br><br>
	        	<div class="text-center">결과가 존재하지 않습니다.</div>
	        <%} else { %>
	        	<div class="js-shuffle row row-cols-1 row-cols-sm-2 row-cols-md-3">
	        	<%for(HealthSeason healthSeason : list) { %>
		        	<div class="js-shuffle-item col mb-5">
			            <!-- Card -->
			            <a class="card card-flush card-transition" href="<%=request.getContextPath() %>/eat/season/view?hsno=<%=healthSeason.getHS_NO() %>">
				            <img class="card-img-top" src="<%=healthSeason.getIMG_URL() %>" alt="<%=healthSeason.getPRDLST_NM() %>">
				            <div class="card-body">
				                <h3 class="card-title text-center"><%=healthSeason.getPRDLST_NM() %></h3>
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
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=M_DISTCTNS%>&page=1" aria-label="Start">
		                        <span aria-hidden="true">«</span>
		                        <span class="visually-hidden">Start</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=M_DISTCTNS%>&page=<%=pageInfo.getCurrentPage() - 1 %>" aria-label="Previous">
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
		               	<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=M_DISTCTNS%>&page=<%=i %>"><%=i %></a></li>
	                	<%} %>                
	                <%} %>
	                
	                <%if(pageInfo.getCurrentPage() != pageInfo.getMaxPage()) { %>
	                	<%if(pageInfo.getMaxPage() > 10) { %>
	                	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=M_DISTCTNS%>&page=<%=pageInfo.getCurrentPage() + 1 %>" aria-label="Next">
		                        <span aria-hidden="true">›</span>
		                        <span class="visually-hidden">Next</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/season?M_DISTCTNS=<%=M_DISTCTNS%>&page=<%=pageInfo.getMaxPage() %>" aria-label="End">
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
    <!-- End Contents -->
    
    
    <script src="https://code.jquery.com/jquery-latest.js"></script>

    <script>
    	$(document).ready(function(){
			var radioList = $('input[name="M_DISTCTNS"]');
			
			for(var i = 0; i < radioList.length; i++) {
				radioList[i].parentElement.classList.remove('active');
				
                if ('<%=M_DISTCTNS %>' == radioList[i].value) {
                	radioList[i].parentElement.classList.add('active');
                	radioList[i].checked = true;
				}
            }
		});

    	$('input[name="M_DISTCTNS"]').click(function() {
    		location.href="<%= request.getContextPath() %>/eat/season?M_DISTCTNS=" +  $(this).val();
    	});
        
    </script>


<%@include file="../common/footer.jsp" %>
</body>
</html>