<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="body-title">
    <a href="/news/goToNewsList">News >> </a> Edit News
</div>

<script type="text/javascript">

    <%@include file="/script/validation.js"%>

</script>

<form:form action="/news/editNews" method="post" modelAttribute="news">
    <div class="add-table-margin">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">News Title</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:input type="text" id="title" path="title"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">News Date</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:input type="date" id="publicationDate" path="publicationDate" />
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Brief</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:textarea id="brief" path="brief"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Content</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:textarea id="content" path="content"/>
                    </div>
                </td>
            </tr>
        </table>
    </div>


    <div class="first-view-button">
        <input type="hidden" name="id" value="${requestScope.news.idNews}"/>
        <input type="submit" value="Save"/>
    </div>
</form:form>

<div class="second-view-button">
    <form action="/news/goToViewNews" method="post">
        <input type="hidden" name="id" value="${requestScope.news.idNews}"/>
        <input type="submit" value="Cancel"/>
    </form>
</div>