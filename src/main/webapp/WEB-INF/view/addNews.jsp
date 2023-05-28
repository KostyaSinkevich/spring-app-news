<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="body-title">
    <a href="/news/goToNewsList">News >> </a> Edit News
</div>

<form:form action="/news/addNews" method="post" modelAttribute="news">
    <div class="add-table-margin">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">News Title</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:input type="text" id="title" placeholder="Enter the title"  path="title"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">News Date</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:input type="date" id="publicationDate"  path="publicationDate"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Brief</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:textarea id="brief" placeholder="Enter the brief" path="brief" />
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Content</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <form:textarea id="content" placeholder="Enter the content"  path="content"/>
                    </div>
                </td>
            </tr>
        </table>
    </div>


    <div class="first-view-button">
        <input type="submit" value="Save">
    </div>
</form:form>

<div class="second-view-button">
    <form action="/news/goToNewsList" method="post">
        <input type="submit" value="Cancel"/>
    </form>
</div>