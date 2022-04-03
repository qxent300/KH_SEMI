<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.mvc.health_nutrition.model.vo.Health_Nutrition"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
    
<%
	List<Health_Nutrition> list = (List<Health_Nutrition>)request.getAttribute("list");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	String[] numberArray = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
%>

<!-- ========== MAIN CONTENT ========== -->
    <main id="content" role="main">
        <!-- 상단 건강식품 관련 이미지 -->
        <!-- CTA -->
	    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/nutrition_background2.png);">
	        <div class="container content-space-2 content-space-lg-3"></div>
	    </div>
	    <!-- End CTA -->
        <!-- 상단 건강식품 관련 이미지 끝 -->

        <!-- 건강기능식품 -->
        <div class="container content-space-1 content-space-b-lg-3">
            <div class="w-lg-100 mx-lg-auto">
                <div class="d-grid gap-10">
                    <div class="d-grid gap-3">
                        <h1 style="text-align: center; color: #cde99c;">건강기능식품</h1><br>
                        <!-- Accordion -->
                        <div class="accordion accordion-btn-icon-start" id="accordionFAQBasics">
                            <!-- 건강기능식품 내용-->
                            <!-- 첫 번째 줄 -->
                            <% for(int i = 0; i < 3; i++) {%>
	                            <div class="row mb-3">
	                            	<% 
	                            		for(int j = 0; j < 3; j++) {
	                            			try{
	                            				if(list.get((i*3)+j) != null) { 
	                            					Health_Nutrition hn = list.get((i*3)+j);
	                            					String numberStr = numberArray[(i*3)+j];
	                            	%>
		                                <div class="modal-lg col-sm-4 col-lg-4">
		                                    <div class="accordion-header outline" id="btn-icon-start-heading<%= numberStr%>">
		                                        <a class="accordion-button collapsed" role="button" data-bs-toggle="collapse" data-bs-target="#btn-icon-start-collapse<%= numberStr%>" aria-expanded="false" aria-controls="btn-icon-start-collapse<%= numberStr%>">
		                                            <span class="ps-2"><%= hn.getPRDCT_NM() %></span>
		                                        </a>
		                                    </div>
		                                    <div id="btn-icon-start-collapse<%= numberStr%>" class="accordion-collapse collapse" aria-labelledby="btn-icon-start-heading<%= numberStr%>" data-bs-parent="#accordionBtnIconStartExample">
		                                        <div class="accordion-body outline border-top-0">
		                                            <table>
		                                                <thead>
		                                                	<% if(hn.getIFTKN_ATNT_MATR_CN() != null) { %>
		                                                    <tr>
		                                                        <th class="title"><span>섭취시주의사항</span></th>
		                                                        <th class="content"><span><%= hn.getIFTKN_ATNT_MATR_CN() %></span></th>
		                                                    </tr>
		                                                    <% } %>
		                                                </thead>
		                                                <tbody>
		                                                	<% if(hn.getPRIMARY_FNCLTY() != null) { %>
		                                                    <tr>
		                                                        <td class="title"><span>주된기능성</span></td>
		                                                        <td class="content"><span><%= hn.getPRIMARY_FNCLTY() %></span></td>
		                                                    </tr>
		                                                    <% } %>
		                                                    <% if(hn.getDAY_INTK_LOWLIMIT() != null) { %>
		                                                    <tr>
		                                                        <td class="title"><span>일일섭취량 하한</span></td>
		                                                        <td class="content"><span><%= hn.getDAY_INTK_LOWLIMIT() %></span></td>
		                                                    </tr>
		                                                    <% } %>
		                                                    <% if(hn.getDAY_INTK_HIGHLIMIT() != null) { %>
		                                                    <tr>
		                                                        <td class="title"><span>일일섭취량 상한</span></td>
		                                                        <td class="content"><span><%= hn.getDAY_INTK_HIGHLIMIT() %></span></td>
		                                                    </tr>
		                                                    <% } %>
		                                                    <% if(hn.getINTK_UNIT() != null) { %>
		                                                    <tr>
		                                                        <td class="title"><span>단위</span></td>
		                                                        <td class="content"><span><%= hn.getINTK_UNIT() %></span></td>
		                                                    </tr>
		                                                    <% } %>
		                                                    <% if(hn.getSKLL_IX_IRDNT_RAWMTRL() != null) { %>
		                                                    <tr>
		                                                        <td class="title"><span>성분명</span></td>
		                                                        <td class="content"><span><%= hn.getSKLL_IX_IRDNT_RAWMTRL() %></span></td>
		                                                    </tr>
		                                                    <% } %>
		                                                </tbody>
		                                            </table>
		                                        </div>
		                                    </div>
		                                </div>
	                                <% 
	                                		}
	                            		} catch (Exception e) {} %>
	                                <% } %>
	                                
	                            </div>
                            <% } %>
                            </div>
                            <!-- 첫 번째 줄 끝 -->
                            <!-- End Accordion Item -->
                            
                            <!-- End Accordion Item -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- 건강기능식품 끝 -->
	        <!-- Pagination -->
	        <nav aria-label="Page navigation example">
	            <ul class="pagination justify-content-center pagination-lg">
	            
	            	<%if(pageInfo.getCurrentPage() != 1) { %>
		            	<%if(pageInfo.getCurrentPage() > 10) { %>
		            	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/nutrition?page=1" aria-label="Start">
		                        <span aria-hidden="true">«</span>
		                        <span class="visually-hidden">Start</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/nutrition?page=<%=pageInfo.getCurrentPage() - 1 %>" aria-label="Previous">
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
		               	<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/eat/nutrition?page=<%=i %>"><%=i %></a></li>
	                	<%} %>                
	                <%} %>
	                
	                <%if(pageInfo.getCurrentPage() != pageInfo.getMaxPage()) { %>
	                	<%if(pageInfo.getMaxPage() > 10) { %>
	                	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/nutrition?page=<%=pageInfo.getCurrentPage() + 1 %>" aria-label="Next">
		                        <span aria-hidden="true">›</span>
		                        <span class="visually-hidden">Next</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/eat/nutrition?page=<%=pageInfo.getMaxPage() %>" aria-label="End">
		                        <span aria-hidden="true">»</span>
		                        <span class="visually-hidden">End</span>
		                    </a>
		                </li>
	                	<%} %>
	                <%} %>
	                
	            </ul>
	        </nav>
	        <!-- End Pagination -->
    </main>
    <!-- ========== END MAIN CONTENT ========== -->
    
    <!-- ========== END FOOTER ========== -->


	<!-- CSS table -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/front-v4.1/assets/css/table.css">

<%@include file="../common/footer.jsp" %>
</body>
</html>