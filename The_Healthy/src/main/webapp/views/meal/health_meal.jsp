<%@page import="java.util.Arrays"%>
<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.mvc.recipe.model.vo.HealthRecipe"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../common/header.jsp" %>
    
<%
	List<HealthRecipe> list = (List<HealthRecipe>) request.getAttribute("list");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String category = (String) request.getAttribute("category");
	String search = (String) request.getAttribute("search");
	
	String totalInfoEng = (String) session.getAttribute("totalInfoEng");
	String totalInfoCar = (String) session.getAttribute("totalInfoCar");
	String totalInfoPro = (String) session.getAttribute("totalInfoPro");
	String totalInfoFat = (String) session.getAttribute("totalInfoFat");
	String totalInfoNa = (String) session.getAttribute("totalInfoNa");
	
	String isArray = (String) session.getAttribute("isArray");
	
	String rcpNm = "";
	String[] rcpNmArr = null;
	
	if(isArray != null) {
		if(isArray.equals("Y")) {
			rcpNmArr = (String[]) session.getAttribute("rcpNm");		
		}else if(isArray.equals("N")) {
			rcpNm = (String) session.getAttribute("rcpNm");		
		}
	}
	
%> 
    
<style>
    input[type=radio] {
        display: none;
    }
</style>

    <main id="content" role="main">
        <!-- CTA -->
        <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Health_recipe_background.png);">
            <div class="container content-space-2 content-space-lg-3"></div>
        </div>
        <!-- End CTA -->

		<br><br>

        <h2 style="text-align: center; color: #cde99c;">건강 식단 구성</h2>

        <!-- Card Grid -->
        <div class="container mt-5">
            <!-- Nav Scroller -->
            <form action="<%=request.getContextPath() %>/health/meal" method="get">
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


            <div class="container">
                <div class="row">
                    <div class="col-lg-8 mb-9 mb-lg-0">
                    <%for(int i = 0; i < 3; i++) { %>
                    	<div class="row">
                    	<%for(int j = 0; j < 3; j++) { %>
                    		<%if((i * 3 + j) < list.size()) { %>
                    		<div class="col-sm-6 col-lg-4 mb-3 mb-lg-0">
                                <!-- Card -->
                                <div class="card card-transition h-80">
                                    <img class="card-img-top" src="<%=list.get(i * 3 + j).getATT_FILE_NO_MK() %>" alt="<%=list.get(i * 3 + j).getRCP_NM() %>">
                                    <div class="card-body text-center">
                                    	<span id="infoEng" class="visually-hidden"><%=list.get(i * 3 + j).getINFO_ENG() %></span>
                                    	<span id="infoCar" class="visually-hidden"><%=list.get(i * 3 + j).getINFO_CAR() %></span>
                                    	<span id="infoPro" class="visually-hidden"><%=list.get(i * 3 + j).getINFO_PRO() %></span>
                                    	<span id="infoFat" class="visually-hidden"><%=list.get(i * 3 + j).getINFO_FAT() %></span>
                                    	<span id="infoNa" class="visually-hidden"><%=list.get(i * 3 + j).getINFO_NA() %></span>
                                        <h5 class="card-text lh-base"><%=list.get(i * 3 + j).getRCP_NM() %></h5>
                                        <button type="button" id="add" class="addBtn btn" style="background-color: #cde99c;">추가</button>
                                    </div>
                                </div>
                                <!-- End Card -->
                            </div>
                            <!-- End Col -->
                            <%} %>
                    	<%} %>
                    	</div>
                    	<br>
                    <%} %>
                    </div>

                    <div class="col-lg-4">
                        <!-- Sticky Block -->
                        <div id="stickyBlockStartPointEg2" >
                            <div class="js-sticky-block"
                                data-hs-sticky-block-options='{
                                "parentSelector": "#stickyBlockStartPointEg2",
                                "breakpoint": "lg",
                                "startPoint": "#stickyBlockStartPointEg2",
                                "endPoint": "#stickyBlockEndPointEg2",
                                "stickyOffsetTop": 75,
                                "stickyOffsetBottom": 0
                                }'>
                                <!-- Card -->
                                <div class="card card-bordered">
                                    <div class="card-body">
                                        <div class="d-flex align-items-center mb-4">
                                            <div class="flex-grow-1 ms-3">
                                            <h4 class="card-title" style="color: #cde99c;">계산 결과</h4>
                                            </div>
                                        </div>
                                        <hr class="my-3">
                                        <table class="table">
                                            <tbody>
                                                <tr>
	                                                <th scope="row">열량(kcal)</th>
	                                                <td id="totalInfoEng"><%=totalInfoEng == null ? 0 : totalInfoEng %></td>
                                                </tr>
                                                <tr>
	                                                <th scope="row">탄수화물(g)</th>
	                                                <td id="totalInfoCar"><%=totalInfoCar == null ? 0 : totalInfoCar %></td>
                                                </tr>
                                                <tr>
	                                                <th scope="row">단백질(g)</th>
	                                                <td id="totalInfoPro"><%=totalInfoPro == null ? 0 : totalInfoPro %></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">지방(g)</th>
                                                    <td id="totalInfoFat"><%=totalInfoFat == null ? 0 : totalInfoFat %></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">나트륨(mg)</th>
                                                    <td id="totalInfoNa"><%=totalInfoNa == null ? 0 : totalInfoNa %></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <hr class="my-3">
                                        <div class="d-flex align-items-center mb-4">
                                            <div class="flex-grow-1 ms-3">
                                            	<h4 class="card-title" style="color: #cde99c;">식단 목록</h4>
                                            </div>
                                        </div>
                                        <div class="result text-center">
                                        <%if(isArray != null) { %>
                                        	<%if(isArray.equals("Y")) { %>
	                                        	<%for(String s : rcpNmArr) { %>
	                                        		<div>
	                                        			<span id="removeRcpNm"><%=s %></span> <button type='button' class='removeBtn btn btn-sm' style="background-color: #cde99c;">제거</button>
	                                        			<br><br>
	                                        		</div>
	                                        	<%} %>
	                                        <%} else if(isArray.equals("N")) { %>
	                                        	<%if(rcpNm != null) { %>
                                        		<div>
	                                        		<span id="removeRcpNm"><%=rcpNm %></span> <button type='button' class='removeBtn btn btn-sm' style="background-color: #cde99c;">제거</button>
	                                        		<br><br>
	                                        	</div>
	                                        	<%} %>
	                                        <%} %>
                                        <%} %>
                                        </div>
                                        <div class="d-grid">
                                            <button type="button" class="btn" onclick="reset()" style="background-color: #cde99c;">초기화</button>
                                        </div>
                                    </div>
                                </div>
                                <!-- End Card -->
                            </div>
                        </div>
                        <!-- End Sticky Block -->
                    </div>
                    <!-- End Col -->
                </div>
                <!-- End Row --> 
                
                <div id="tempdiv" class="visually-hidden">
                	<span id="tempInfoEng"></span>
                	<span id="tempInfoCar"></span>
                	<span id="tempInfoPro"></span>
                	<span id="tempInfoFat"></span>
                	<span id="tempInfoNa"></span>
                </div>      
            </div>

            <!-- Sticky Block End Point -->
            <div id="stickyBlockEndPointEg2"></div>

            <hr class="my-6">

            <!-- Pagination -->
	        <nav aria-label="Page navigation example">
	            <ul class="pagination justify-content-center pagination-lg">
	            
	            	<%if(pageInfo.getCurrentPage() != 1) { %>
		            	<%if(pageInfo.getCurrentPage() > 10) { %>
		            	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/health/meal?category=<%=category %>&page=1&search=<%=search %>" aria-label="Start">
		                        <span aria-hidden="true">«</span>
		                        <span class="visually-hidden">Start</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/health/meal?category=<%=category %>&page=<%=pageInfo.getCurrentPage() - 1 %>&search=<%=search %>" aria-label="Previous">
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
		               	<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/health/meal?category=<%=category %>&page=<%=i %>&search=<%=search %>"><%=i %></a></li>
	                	<%} %>                
	                <%} %>
	                
	                <%if(pageInfo.getCurrentPage() != pageInfo.getMaxPage()) { %>
	                	<%if(pageInfo.getMaxPage() > 10) { %>
	                	<li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/health/meal?category=<%=category %>&page=<%=pageInfo.getCurrentPage() + 1 %>&search=<%=search %>" aria-label="Next">
		                        <span aria-hidden="true">›</span>
		                        <span class="visually-hidden">Next</span>
		                    </a>
		                </li>
		                <li class="page-item">
		                    <a class="page-link" href="<%=request.getContextPath() %>/health/meal?category=<%=category %>&page=<%=pageInfo.getMaxPage() %>&search=<%=search %>" aria-label="End">
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
    
        var btn = document.getElementsByClassName('addBtn');
        
        for(var i = 0; i < btn.length; i++) {
            btn[i].addEventListener('click', addMeal);
        }

        function addMeal() {
        	var divRcpNm = $(this).parent('div').children('.card-text').text();				// 추가 버튼 클릭시 해당 레시피 이름을 저장

        	var totalInfoEng = Math.round(parseFloat($('#totalInfoEng').text()));
            var infoEng = Math.round(parseFloat($(this).siblings('#infoEng').text()));
            totalInfoEng = totalInfoEng + infoEng;
            $('#totalInfoEng').text(totalInfoEng);
            
            var totalInfoCar = Math.round(parseFloat($('#totalInfoCar').text()));
            var infoCar = Math.round(parseFloat($(this).siblings('#infoCar').text()));
            totalInfoCar = totalInfoCar + infoCar;
            $('#totalInfoCar').text(totalInfoCar);
            
            var totalInfoPro = Math.round(parseFloat($('#totalInfoPro').text()));
            var infoPro = Math.round(parseFloat($(this).siblings('#infoPro').text()));
            totalInfoPro = totalInfoPro + infoPro;
            $('#totalInfoPro').text(totalInfoPro);
            
            var totalInfoFat = Math.round(parseFloat($('#totalInfoFat').text()));
            var infoFat = Math.round(parseFloat($(this).siblings('#infoFat').text()));
            totalInfoFat = totalInfoFat + infoFat;
            $('#totalInfoFat').text(totalInfoFat);
            
            var totalInfoNa = Math.round(parseFloat($('#totalInfoNa').text()));
            var infoNa = Math.round(parseFloat($(this).siblings('#infoNa').text()));
            totalInfoNa = totalInfoNa + infoNa;
            $('#totalInfoNa').text(totalInfoNa);											// 각 영양성분 정보를 가져와서 계산(반올림)한 후 계산 결과에 입력

        	$.ajax({																		// 현재 계산 결과와 식단 목록을 session에 저장
                type: "get",
                url: "<%=request.getContextPath() %>/health/meal",
                data: {
                	totalInfoEng: totalInfoEng,
                	totalInfoCar: totalInfoCar,
                	totalInfoPro: totalInfoPro,
                	totalInfoFat: totalInfoFat,
                	totalInfoNa: totalInfoNa,
                	rcpNm: divRcpNm,
                	remove: "N"
                }
            });
        	
        	var removeBtn = " <button type='button' class='removeBtn btn btn-sm' style='background-color: #cde99c;'>제거</button><br><br>"
            $('.result').append('<div>' + divRcpNm + removeBtn + '</div>');							// 추가 버튼 클릭한 레시피 이름과 옆에 제거 버튼을 묶어서 식단 목록에 저장
        }

        $('.result').on('click', '.removeBtn', function(){
            var removeRcpNm = $(this).siblings('#removeRcpNm').text();								// 이미 존재하는 식단 목록에서 제거하는 경우 
            
            if (removeRcpNm == "") {																// 현재 페이지에서 추가한 메뉴를 식단 목록에서 제거하는 경우
            	removeRcpNm = $(this).parent('div').text().substr(0, $(this).parent('div').text().length - 3);
			}
        	
			var infoEng;
			var infoCar;
			var infoPro;
			var infoFat;
			var infoNa;
			var data = "";

			$('h5').each(function(){		// 현재 페이지에 제거버튼 클릭한 메뉴가 있는 경우
				if ($(this).text() == removeRcpNm) {
					infoEng = Math.round(parseFloat($(this).siblings('span#infoEng').text()));
					infoCar = Math.round(parseFloat($(this).siblings('span#infoCar').text()));
					infoPro = Math.round(parseFloat($(this).siblings('span#infoPro').text()));
					infoFat = Math.round(parseFloat($(this).siblings('span#infoFat').text()));
					infoNa = Math.round(parseFloat($(this).siblings('span#infoNa').text()));
					data = "Y";
					
					return false;
				}
			});
			
			if (data != "Y") {				// 현재 페이지에 제거버튼 클릭한 메뉴가 없는 경우
				$.ajax({
	                type: "get",
	                url: "<%=request.getContextPath() %>/health/meal",
	                data: {
	                	removeRcpNm: removeRcpNm
	                },
	                async: false,
	                dataType: "json",
	                success: (obj) => {
	                	$('#tempdiv').children('#tempInfoEng').html(obj.infoEng);
	                	$('#tempdiv').children('#tempInfoCar').html(obj.infoCar);
	                	$('#tempdiv').children('#tempInfoPro').html(obj.infoPro);
	                	$('#tempdiv').children('#tempInfoFat').html(obj.infoFat);
	                	$('#tempdiv').children('#tempInfoNa').html(obj.infoNa);
	                }
	            });
				
				infoEng = Math.round(parseFloat($('#tempdiv').children('#tempInfoEng').text()));
				infoCar = Math.round(parseFloat($('#tempdiv').children('#tempInfoCar').text()));
				infoPro = Math.round(parseFloat($('#tempdiv').children('#tempInfoPro').text()));
				infoFat = Math.round(parseFloat($('#tempdiv').children('#tempInfoFat').text()));
				infoNa = Math.round(parseFloat($('#tempdiv').children('#tempInfoNa').text()));
			}
			
			var totalInfoEng = Math.round(parseFloat($('#totalInfoEng').text()));
            totalInfoEng = totalInfoEng - infoEng;
            $('#totalInfoEng').text(totalInfoEng);
            
            var totalInfoCar = Math.round(parseFloat($('#totalInfoCar').text()));
            totalInfoCar = totalInfoCar - infoCar;
            $('#totalInfoCar').text(totalInfoCar);
            
            var totalInfoPro = Math.round(parseFloat($('#totalInfoPro').text()));
            totalInfoPro = totalInfoPro - infoPro;
            $('#totalInfoPro').text(totalInfoPro);
            
            var totalInfoFat = Math.round(parseFloat($('#totalInfoFat').text()));
            totalInfoFat = totalInfoFat - infoFat;
            $('#totalInfoFat').text(totalInfoFat);
            
            var totalInfoNa = Math.round(parseFloat($('#totalInfoNa').text()));
            totalInfoNa = totalInfoNa - infoNa;
            $('#totalInfoNa').text(totalInfoNa);

        	$.ajax({
                type: "get",
                url: "<%=request.getContextPath() %>/health/meal",
                data: {
                	totalInfoEng: totalInfoEng,
                	totalInfoCar: totalInfoCar,
                	totalInfoPro: totalInfoPro,
                	totalInfoFat: totalInfoFat,
                	totalInfoNa: totalInfoNa,
                	rcpNm: removeRcpNm,
                	remove: "Y"
                }
            });
        	
            $(this).parent().remove();
        });
        
        function reset() {
        	$('#totalInfoEng').text(0);
        	$('#totalInfoCar').text(0);
        	$('#totalInfoPro').text(0);
        	$('#totalInfoFat').text(0);
        	$('#totalInfoNa').text(0);
            $('.result').empty();
            
            rcpNm = "";
            var empty = "empty";
            
            $.ajax({
                type: "get",
                url: "<%=request.getContextPath() %>/health/meal",
                data: {
                	empty: empty
                }
            });
        }

        (function() {
            // INITIALIZATION OF STICKY BLOCKS
            // =======================================================
            new HSStickyBlock('.js-sticky-block', {
                targetSelector: document.getElementById('header').classList.contains('navbar-fixed') ? '#header' : null
            })
        })()
        
    </script>
</body>
</html>