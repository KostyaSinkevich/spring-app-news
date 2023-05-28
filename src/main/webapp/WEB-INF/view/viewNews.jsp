<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="body-title">
    <a href="/news/goToNewsList">News >> </a> View News
</div>

<div class="add-table-margin">
    <table class="news_text_format">
        <tr>
            <td class="space_around_title_text">News Title</td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${news.title}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">News Date</td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${news.publicationDate}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">Brief</td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${news.brief}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">Content</td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${news.content}"/>
                </div>
            </td>
        </tr>
    </table>
</div>

<c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
    <div class="first-view-button">
        <form action="/news/goToEditNews" method="post">
            <input type="hidden" name="id" value="${news.idNews}"/>
            <input type="submit" value="Edit"/>
        </form>
    </div>

    <div class="second-view-button">
        <form action="/news/deleteNews" method="post">
            <input type="hidden" id="idNews" name="idNews" value="${news.idNews}"/>
            <input type="submit" value="Delete"/>
        </form>
    </div>
</c:if>