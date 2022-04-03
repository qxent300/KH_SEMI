<%@page import="com.kh.mvc.season.model.vo.HealthSeason"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file="../common/header.jsp" %>


<%
	HealthSeason hs = (HealthSeason) request.getAttribute("hs");
%>


	<!-- NAV -->
		<!-- CTA -->
		<div class="bg-img-center"
			style="background-image: url(<%=request.getContextPath()%>/resources/image/Season_background.jpg);">
			<div class="container content-space-2 content-space-lg-3"></div>
		</div>
		<!-- End CTA -->
	
		<br><br>
	<!-- End NAV -->


	<!-- Contents -->
	<h2 style="text-align: center; color: #cde99c;">제철 농산물 상세 정보</h2>
	<br> <br> <br>

	<style>
		#resultTBL th, #resultTBL td {
			border-top: 1px solid #cde99c;
			border-bottom: 1px solid #cde99c;
		}
		#resultTBL th {
			width: 170px; 
			text-align: center;
			font-weight: bold;
			background-color:#F3FAE6;
		}
	</style>

	<div class="text-dark">
		<h2 style="text-align: center;"><%=hs.getPRDLST_NM()%></h2>
		<br>

		<div style="margin: 0 auto; text-align:center; width:35%;">
			<!-- Gallery -->
			<img class="img-fluid w-75 rounded-2" src="<%=hs.getIMG_URL()%>"
				alt="<%=hs.getPRDLST_NM()%>">
			<!-- End Gallery -->
		</div>

		<br> <br> <br>

		<div class="w-50" style="margin: 0 auto;">
			<table cellpadding="30px" id="resultTBL">
				<tr>
					<th>월별</th>
					<td><%=hs.getM_DISTCTNS()%></td>
				</tr>
				<tr>
					<% if (hs.getMTC_NM() != null) { %>
					<th>주요산지</th>
					<td><%=hs.getMTC_NM()%></td>
					<% } %>
				</tr>
				<tr>
					<th>효능</th>
					<td>
						<%
						String[] effectArray = hs.getEFFECT().split("-");
						for (int i = 1; i < effectArray.length; i++) {
						%> 
						- <%=effectArray[i]%> <br> 
						<% } %>
					</td>
				</tr>
				<tr>
					<% if (hs.getPURCHASE_MTH() != null) { %>
					<th>구입요령</th>
					<td><%=hs.getPURCHASE_MTH()%></td>
					<% } %>
				</tr>
				<tr>
					<th>조리법</th>
					<td><%=hs.getCOOK_MTH()%></td>
				</tr>
				<tr>
					<th>손질과<br>보관법</th>
					<td><%=hs.getTRT_MTH()%></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- End Contents -->


	<br> <br> <br> <br>


	<script src="https://code.jquery.com/jquery-latest.js"></script>


<%@include file="../common/footer.jsp" %>
</body>
</html>