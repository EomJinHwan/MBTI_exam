<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>


<body>
	<!-- 질문 영역 전체를 form으로 감싸 사용 -->
	<form id="mbtiForm" action="/mbti/mbtiList.do" method="post">

		<div id="List" align="center">
			<!-- 질문List들 반복문 사용으로 전체 받아오기-->
			<c:forEach items="${questionList}" var="list" varStatus="status">
				<div class="question">
					${list.mbtiComment} <br>
					<!-- 각 질문마다 라디오버튼 생성-->
					<c:forEach var="value" begin="1" end="7" step="1">
						<label style="display: inline-flex; flex-direction: column;">
							<!-- 라디오 버튼마다 value 설정 1부터 7까지-->
							 <!-- 각 라디오마다 0부터 index를 넣어 이름을 구분-->
							<input type=radio value="${value}" name="answers[${status.index}]">
							<c:choose>
								<c:when test="${value == 1}">매우 동의</c:when>
								<c:when test="${value == 7}">매우 비동의</c:when>
							</c:choose>
						</label>
					</c:forEach>
					<br>
				</div>
			</c:forEach>
		</div>
		
		<div align="center">

			<button type="button" id="previous">이전</button>
			<button type="button" id="complete">완료</button>
			<button type="button" id="next">다음</button>

		</div>
	</form>
</body>
</html>