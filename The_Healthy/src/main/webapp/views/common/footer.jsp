<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- ========== FOOTER ========== -->
    <footer class="container text-center content-space-1">
        <!-- Logo -->
        <a class="d-inline-flex align-items-center mb-2" href="<%=request.getContextPath() %>" aria-label="Front">
        	<img class="brand" src="<%=request.getContextPath() %>/resources/image/LOGO.svg" alt="Logo">
        </a>
        <!-- End Logo -->
    
        <p class="small text-muted mb-0">2021 © Htmlstream. All rights reserved.</p>
    </footer>
    <!-- ========== END FOOTER ========== -->



    <!-- JS Global Compulsory -->
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    
    <!-- JS Implementing Plugins -->
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-mega-menu/dist/hs-mega-menu.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-nav-scroller/dist/hs-nav-scroller.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/fslightbox/index.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-sticky-block/dist/hs-sticky-block.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/leaflet/dist/leaflet.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-header/dist/hs-header.min.js "></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-show-animation/dist/hs-show-animation.min.js "></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-go-to/dist/hs-go-to.min.js "></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-file-attach/dist/hs-file-attach.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/vendor/hs-toggle-password/dist/js/hs-toggle-password.js"></script>

    <!-- JS Front -->
    <script src="<%=request.getContextPath() %>/resources/front-v4.1/assets/js/theme.min.js"></script>
    
    <!-- JS Plugins Init. -->
	<script>
		function login() {
			var form = document.getElementById('loginForm');
			var currentUrl = document.getElementById('currentUrl');
			currentUrl.setAttribute('value', document.location.href);
			
			form.submit();			
		}
		
		function logout() {
			var currentUrl = document.location.href;
			currentUrl = currentUrl.replace(/&/g,"%26");
			location.replace("<%=request.getContextPath() %>/member/logout?currentUrl=" + currentUrl);
		}
		
		function enroll(){
			var form = document.getElementById('enrollForm');
			var currentUrl = document.getElementById('currentEnrollUrl'); 
			var pwd = document.getElementById('enrollPassword').value; 
		    var cpwd = document.getElementById('enrollConfirmPassword').value; 
			currentUrl.setAttribute('value', document.location.href);

			if (pwd != cpwd) {  //비밀번호 확인 
				  alert("비밀번호가 서로 일치하지 않습니다!"); 

				  return false; 
			}  
			
			form.submit();	
		}
		
		function doubleCheckId() {
			
			var enrollID = document.getElementById('enrollID').value;
			
			if (enrollID == "") {
				alert("아이디를 입력하세요...!");
				return;
			}
			
			$.ajax({
                type: "get",
                url: "<%=request.getContextPath() %>/member/doubleCheckId",
                data: {
                	enrollID: enrollID
                },
                success: (result) => {
					alert(result);
					if (result == "이미 사용중인 아이디입니다...") {
						$('#enrollID').val("");
					}
                }
            });
		}
		
		(function() {
			// INITIALIZATION OF BOOTSTRAP VALIDATION
			// =======================================================
			HSBsValidation.init('.js-validate', {
			  onSubmit: data => {
			    data.event.preventDefault()
			    alert('Submited')
			  }
			})
			
			
			// INITIALIZATION OF TOGGLE PASSWORD
			// =======================================================
			new HSTogglePassword('.js-toggle-password')
		})()
	</script>