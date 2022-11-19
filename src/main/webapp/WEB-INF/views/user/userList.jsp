<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!--인기 게시글-->
<main class="userList">
	<div class="exploreContainer">

		<div class="popular-gallery">

            <c:forEach var="userDto" items="${userListDtos}">

                <div class="p-img-box">
                    <a href="/user/${userDto.id}"> <div>${userDto.username}</div>
                    </a>
                </div>
            </c:forEach>

		</div>

	</div>
</main>

<%@ include file="../layout/footer.jsp"%>