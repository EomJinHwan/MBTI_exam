<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<script>
	$j(document).ready(function() {

		var questions = $j(".question");
		var pageSize = 5;
		var totalPages = questions.length / pageSize;
		var currentPage = 1;

		/* 페이징 처리 - 전체를 불러온 뒤 나눠서 화면에 보여줌 */
		function showPage(page) {

			var start = (page - 1) * pageSize;
			var end = start + pageSize;
			/* 질문들 전체를 숨김 */
			questions.hide();
			/* 페이지 별로 질문들을 5개씩 보여줌 */
			/* page=1일 때 slice(0, 5): 인덱스 0~4의 질문 표시 */
			questions.slice(start, end).show();

			/* page가 1일경우 "이전" 버튼 숨김 */
			if (page == 1) {
				$j("#previous").hide();
			} else {
				$j("#previous").show();
			}

			/* 현재 페이지와 토탈 페이지가 같으면 "다음" 버튼 없어지고 "완료" 버튼 생김 */
			if (page == totalPages) {
				$j("#next").hide();
				$j("#complete").show();
			} else {
				$j("#next").show();
				$j("#complete").hide();
			}
		}

		$j("#previous").on("click", function(){
			if(currentPage > 1) {
				currentPage--;
				showPage(currentPage);
			}
		});

		$j("#complete").on("click", function(){
			/* 전체 질문 갯수와 input태그의 라디오 타입의 체크된 수가 다르다면 = 체크 안된게 있는경우 */
			if ($j(".question").length != $j('input[type="radio"]:checked').length) {
				alert("모든 질문을 체크해주세요");
				return;
			}
			if (!confirm("제출하시겠습니까?")) {
    			return;
			}
			/* submit 폼 이벤트 - 폼 제출 시 */
			/* 화면 일부만 갱신하는 비동기 통신이 필요없어 form 제출 사용 */
			$j("#mbtiForm").submit();
		});

		$j("#next").on("click", function(){

			if (currentPage < totalPages) {
				currentPage++;
				showPage(currentPage);
			}
		});

		/* 처음 화면을 표시 */
		showPage(currentPage);
	});

</script>


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