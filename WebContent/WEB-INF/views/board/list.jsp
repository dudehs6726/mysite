<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="${pageContext.servletContext.contextPath }/search_form" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="hidden" name="a" value="list">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<!--${count-status.index } -->
							<td>${vo.rownum-status.index }</td>
							<c:choose>
								<c:when test="${vo.depth > 0}">
									<td style="padding-left:${20*vo.depth }px; text-align: left;"><img src="/mysite2/assets/images/reply.png"/>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }&page=${vo.page }">${vo.title }</a></td>
								</c:when>
								<c:otherwise>
									<td style="text-align: left;"><a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }&page=${vo.page }">${vo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.writeDate}</td>
							
							<td>
								<c:if test="${vo.userNo == authuser.no }">
									<a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }&fileName=${vo.fileName}" class="del">삭제</a>
								</c:if>
							</td>
							
						</tr>
					</c:forEach>
					<!-- 				
					<tr>
						<td>3</td>
						<td><a href="">세 번째 글입니다.</a></td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-10-11 12:04:20</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td><a href="">두 번째 글입니다.</a></td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-10-02 12:04:12</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td style="padding-left:${15*vo.depth }px"><img src="/mysite2/assets/images/reply.png"/> <a href="">첫 번째 글입니다.</a></td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-09-25 07:24:32</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					 -->
				</table>
				
				<!-- pager 추가 -->
				<!-- 
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:forEach items="${list }" var="vo" varStatus="status">
							<li><a href="">1</a></li>
							<li class="selected">2</li>
							<li><a href="">3</a></li>
							<li>4</li>
							<li>5</li>
						</c:forEach>
						<li><a href="">▶</a></li>
					</ul>
				</div>	
				 -->			
				 	
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${vo.page > 5 && !empty kew }">
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${vo.blockStartNum - 1}&kwd=${kwd }">◀</a></li>
							</c:when>
							<c:when test="${vo.page > 5 }">
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${vo.blockStartNum - 1}">◀</a></li>
							</c:when>
						</c:choose>
						<c:forEach var="i" begin="${ vo.blockStartNum }" end="${vo.blockLastNum }">
							<c:choose>
								<c:when test="${i > vo.lastPageNum }">
									<li>${i }</li>
								</c:when>
								<c:when test="${i == vo.page }">
									<li class="selected">${i }</li>
								</c:when>
								<c:when test="${ !empty kwd }">
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${ i }&kwd=${kwd }">${i }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${ i }">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${vo.lastPageNum > vo.blockLastNum && !empty kwd }">
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${ vo.blockLastNum + 1 }&kwd=${kwd }">▶</a></li>
							</c:when>
							<c:when test="${vo.lastPageNum > vo.blockLastNum }">
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${ vo.blockLastNum + 1 }">▶</a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>
				
				<c:if test="${!empty authuser }">
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board?a=write&page=${vo.page }" id="new-book">글쓰기</a>
					</div>
				</c:if>				
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url ="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>