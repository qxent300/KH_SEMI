<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../common/header.jsp" %>

    <!-- CTA -->
    <div class="bg-img-center" style="background-image: url(<%=request.getContextPath() %>/resources/image/Health_recipe_background.png);">
        <div class="container content-space-2 content-space-lg-3"></div>
    </div>
    <!-- End CTA -->

    <br>

    <h1 style="text-align: center; color: #cde99c;">나만의 레시피 등록</h1>

    <br>

    <!-- Content -->
    <div class="container">
        <div class="card card-lg">
            <!-- Card -->
            <div class="card card-bordered shadow-none">
                <div class="card-body">
                    <!-- Form -->
                    <form id="userRecipeForm" method="post" action="<%=request.getContextPath() %>/community/userRecipeWrite" enctype="multipart/form-data">
                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="userRecipeTitle" class="col-sm-2 col-form-label">레시피 이름</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control form-control-lg" id="userRecipeTitle" name="userRecipeTitle">
                            </div>
                        </div>
                        <!-- End Form -->
            
                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="writer" class="col-sm-2 col-form-label form-label">작성자</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control form-control-lg" name="writer" id="writer" value="<%=loginMember.getId() %>" readonly>
                            </div>
                        </div>
                        <!-- End Form -->

                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="representPicture" class="col-sm-2 col-form-label">대표 사진</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="file" id="representPicture" name="representPicture">
                            </div>
                        </div>
                        <!-- End Form -->

                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="userRecipeIngredients" class="col-sm-2 col-form-label">재료</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control form-control-lg" id="userRecipeIngredients" name="userRecipeIngredients">
                            </div>
                        </div>
                        <!-- End Form -->

                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="kind" class="col-sm-2 col-form-label">요리 종류</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col">
                                        <label class="form-control" for="rice">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="rice" value="rice">
                                              <span class="form-check-label">밥</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="stew">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="stew" value="stew">
                                              <span class="form-check-label">국 &amp; 찌개</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="side">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="side" value="side">
                                              <span class="form-check-label">반찬</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="alacarte">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="alacarte" value="alacarte">
                                              <span class="form-check-label">일품</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="dessert">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="dessert" value="dessert">
                                              <span class="form-check-label">후식</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="kindetc">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_pat2" id="kindetc" value="kindetc">
                                              <span class="form-check-label">기타</span>
                                            </span>
                                          </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End Form -->

                        <!-- Form -->
                        <div class="row mb-3">
                            <label for="howto" class="col-sm-2 col-form-label">조리 방법</label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <div class="col">
                                        <label class="form-control" for="boil">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="boil" value="boil">
                                              <span class="form-check-label">끓이기</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="roast">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="roast" value="roast">
                                              <span class="form-check-label">굽기</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="fry">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="fry" value="fry">
                                              <span class="form-check-label">튀기기</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="steam">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="steam" value="steam">
                                              <span class="form-check-label">찌기</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="stir">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="stir" value="stir">
                                              <span class="form-check-label">볶기</span>
                                            </span>
                                          </label>
                                    </div>
                                    <div class="col">
                                        <label class="form-control" for="howtoetc">
                                            <span class="form-check">
                                              <input type="radio" class="form-check-input" name="rcp_way2" id="howtoetc" value="howtoetc">
                                              <span class="form-check-label">기타</span>
                                            </span>
                                          </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End Form -->
						
						<div id="manual">
	                        <!-- Form -->
	                        <div class="row mb-3">
	                            <label for="cook1" class="col-sm-2 js-file-attach col-form-label form-label"
	                                data-hs-file-attach-options='{
	                                "textTarget": "[for=\"customFile\"]"
	                                }'>조리사진 (1)</label>
	                            <div class="col-sm-10">
	                                <input class="form-control" type="file" id="cook1" name="cook1">
	                            </div>
	                        </div>
	                        <!-- End Form -->
	
	                        <!-- Form-->
	                        <div class="row mb-3">
	                            <label for="content1" class="col-sm-2 col-form-label form-label">조리방법 (1)</label>
	                            <div class="col-sm-10">
	                                <input type="text" class="form-control form-control-lg" id="content1" name="content1">
	                            </div>
	                        </div>
	                        <!-- End Form-->
						</div>
						
                        <button type="button" id="addFieldBtn" class="btn js-create-field form-link" onclick="addField()">
					      <i class="bi-plus-circle me-1"></i> 조리 방법 추가
					    </button>
					    
					    <input type="hidden" id="count" name="count">

                        <div class="text-center mt-5">
                            <button type="button" class="btn btn-lg" style="background-color: #cde99c;" onclick="userRecipeUpload()">등록하기</button>
                            <button type="button" class="btn btn-lg" style="background-color: #cde99c;" onclick="location.href='<%=request.getContextPath() %>/community/userRecipeList'">취소하기</button>
                        </div>
                    </form>
                    <!-- End Form -->
                </div>
            </div>
            <!-- End Card -->
        </div>
    </div>

<%@include file="../common/footer.jsp" %>

	<script type="text/javascript">
		var index = 2;
		
		function addField() {
			var uploadImg = "<div class='row mb-3'><label for='cook" + index + "' class='col-sm-2 js-file-attach col-form-label form-label'  data-hs-file-attach-options='{'textTarget': '[for=\'customFile\']'}'>조리사진 (" + index + ")</label><div class='col-sm-10'><input class='form-control' type='file' id='cook" + index + "' name='cook" + index + "'></div></div>";
			var uploadContent = "<div class='row mb-3'><label for='content" + index + "' class='col-sm-2 col-form-label form-label'>조리방법 (" + index + ")</label><div class='col-sm-10'><input type='text' class='form-control form-control-lg' id='content" + index + "' name='content" + index + "'></div></div>";

			$('#manual').append(uploadImg);
			$('#manual').append(uploadContent);
			index++;
		}
		
		function userRecipeUpload() {
			var form = document.getElementById('userRecipeForm');
			var count = document.getElementById('count');
			count.setAttribute('value', index - 1);
			
			var is_empty = false;
			$('#userRecipeForm').find('input[type!="hidden"]').each(function(){
			    if(!$(this).val()) {
			        is_empty = true;
			    }
			});
			 
			if(is_empty) {
			    alert('값을 전부 입력하시오');
			    
			    return;
			}
			
			form.submit();
		}
	</script>

</body>
</html>