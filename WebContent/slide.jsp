<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@import url(assets/css/FirstView.css);
</style>
</head>
<body>
<div class="section">
	<input type="radio" name="slide" id="slide01" checked>
	<input type="radio" name="slide" id="slide02">
	<input type="radio" name="slide" id="slide03">
	<div class="slidewrap">
		
		<ul class="slidelist">
			<!-- �����̵� ���� -->
			<li class="slideitem">
				<a>
					<div class="textbox">
						<h3>���θ� ����Ʈ</h3>
						<p></p>
					</div>
					<img src="./img/��л���.jpeg">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
						<h3>�Խ��� ����</h3>
						<p></p>
					</div>
					<img src="./img/�Խ��ǻ���.png">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
						<h3></h3>
						<p></p>
					</div>
					<img src="./img/��л���.jpeg">
				</a>
			</li class="slideitem">

			<!-- ��,�� �����̵� ��ư -->
			<div class="slide-control">
				<div>
					<label for="slide03" class="left"></label>
					<label for="slide02" class="right"></label>
				</div>
				<div>
					<label for="slide01" class="left"></label>
					<label for="slide03" class="right"></label>
				</div>
				<div>
					<label for="slide02" class="left"></label>
					<label for="slide01" class="right"></label>
				</div>
			</div>

		</ul>
		<!-- ����¡ -->
		<ul class="slide-pagelist">
			<li><label for="slide01"></label></li>
			<li><label for="slide02"></label></li>
			<li><label for="slide03"></label></li>
		</ul>
	</div>

	
</div>
</body>
</html>