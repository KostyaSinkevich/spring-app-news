<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="menu-wrapper">
    <div class="menu-title-wrapper">
        <div class="menu-title">
            newses
        </div>
    </div>

    <div class="list-menu-invisible-wrapper">
        <div class="list-menu-wrapper" style="float: right;">
            <ul style="list-style-image: url(images/img.jpg); text-align: left;">
                <li style="padding-left: 15px;">

                    <a href="/news/goToNewsList">news_list</a><br/>
                </li>

                <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor'}">
                    <li style="padding-left: 15px;">

                        <a href="/news/goToAddNews">add_news</a>

                        <br/>

                    </li>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <!--  grey free space at the bottom of menu -->
    <div style="height: 25px;"></div>
</div>

