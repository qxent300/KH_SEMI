<%@page import="com.kh.mvc.recipe.model.vo.RecipeManual"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.mvc.recipe.model.vo.HealthRecipe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
    
<%
	HealthRecipe healthRecipe = (HealthRecipe) request.getAttribute("healthRecipe");
	List<RecipeManual> list = healthRecipe.getRecipeMaual();
%>

    <!-- CTA -->
    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Health_recipe_background.png);">
        <div class="container content-space-2 content-space-lg-3"></div>
    </div>
    <!-- End CTA -->

    <br><br>

    <h2 style="text-align: center; color: #cde99c;">건강 레시피 상세 정보</h2>

    <br><br><br>
    
    <!-- Gallery -->
    <div class="container mb-5">
        <div class="rounded-2 overflow-hidden">
            <div class="row gx-2">
                <div class="col-md-4" style="text-align: center;">
                    <!-- Gallery -->
                    <img class="img-fluid w-75 rounded-2" src="<%=healthRecipe.getATT_FILE_NO_MK() %>" alt="<%=healthRecipe.getRCP_NM() %>">
                    <!-- End Gallery -->
                </div>
                <!-- End Col -->

                <div class="col-md-8 d-none d-md-inline-block">
                    <div class="mb-4">
                        <h2><%=healthRecipe.getRCP_NM() %></h2>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <div class="col-mb-2">
                                <h3 style="color: #cde99c;">영양 정보</h3>
                            </div>
                            <dl class="row">
                                <dt class="col-5">열량(kcal) :</dt>
                                <dd class="col-5"><%=healthRecipe.getINFO_ENG() %></dd>
                
                                <dt class="col-5">탄수화물(g) :</dt>
                                <dd class="col-5"><%=healthRecipe.getINFO_CAR() %></dd>

                                <dt class="col-5">단백질(g) :</dt>
                                <dd class="col-5"><%=healthRecipe.getINFO_PRO() %></dd>
                
                                <dt class="col-5">지방(g) :</dt>
                                <dd class="col-5"><%=healthRecipe.getINFO_FAT() %></dd>

                                <dt class="col-5">나트륨(g) :</dt>
                                <dd class="col-5"><%=healthRecipe.getINFO_NA() %></dd>
                            </dl>
                            <!-- End Row -->
                        </div>
                        <!-- End Col -->
 
                        <div class="col-md-5">
                            <dl class="row">
                                <div class="col-mb-2">
                                    <h3 style="color: #cde99c;">재료 정보</h3>
                                </div>
                                
                                <dd class="col-15"><%=healthRecipe.getRCP_PARTS_DTLS() %></dd>
                            </dl>
                            
                        </div>
                        <!-- End Col -->
                    </div>
                    <!-- End Row -->
                </div>
                <!-- End Col -->

            </div>
        <!-- End Row -->
        </div>
    </div>
    <!-- End Gallery -->
    
    <div class="container border-top border-5">
    <%for(RecipeManual manual : list) { %>
    	<div class="row justify-content-lg-between align-items-md-center">
            <div class="col-md-6 col-lg-8 content-space-1 content-space-md-2 text-center">
                <p class="mb-4 h3"><span class="h1" style="color: #cde99c;"><%=manual.getSeq() %> </span><%=manual.getManual() %></p>
            </div>
            <div class="col-md-4" style="text-align: center;">
                <img src="<%=manual.getManualImg() %>" alt="<%=healthRecipe.getRCP_NM() %><%=manual.getSeq() %>" class="w-75 rounded-2">
            </div>
        </div>
    <%} %>
    </div>

<%@include file="../common/footer.jsp" %>

</body>
</html>