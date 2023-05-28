<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="body-title">
    <a href="/news/goToNewsList">newses >> </a> news_list
</div>

<form action="/news/deleteNews" method="post">
    <c:forEach var="news" items="${requestScope.news}">
        <div class="single-news-wrapper">
            <div class="single-news-header-wrapper">
                <div class="news-title">
                    <c:out value="${news.title}"/>
                </div>
                <div class="news-date">
                    <c:out value="${news.publicationDate}"/>
                </div>
                <div class="news-content">
                    <c:out value="${news.brief}"/>
                </div>
                <div class="news-link-to-wrapper">
                    <div class="link-position">
                        <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
                            <c:url var="edit_news" value="/news/goToEditNews">
                                <c:param name="id" value="${news.idNews}"/>
                            </c:url>
                            <a href="${edit_news}">edit </a>
                        </c:if>
                        <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor' || sessionScope.role eq 'user'}">
                            <c:url var="view_news" value="/news/goToViewNews">
                                <c:param name="id" value="${news.idNews}"/>
                            </c:url>
                            <a href="${view_news}">view </a>
                        </c:if>
                        <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
                            <input type="checkbox" id="idNews" name="idNews" value="${news.idNews}">
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
    <div class="single-news-wrapper">
        <div class="link-position">
            <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
                <input type="submit" name="delete" value="delete"/>
            </c:if>
        </div>
    </div>

    <div class="no-news">
        <c:if test="${requestScope.news eq null}">
            No news.
        </c:if>
    </div>
</form>