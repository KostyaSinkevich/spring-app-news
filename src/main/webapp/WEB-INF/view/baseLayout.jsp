<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/resources/script/validation.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/styles/newsStyle.css"/>" />
    <title>locale.linkname.headertitle <!-- <bean:message key="locale.linkname.headertitle"/>
        -->
    </title>

</head>
<body>
<div class="page">
    <div class="header">
        <c:import url="/WEB-INF/view/header.jsp"/>
    </div>

    <div class="base-layout-wrapper">
        <div class="menu">

            <c:if test="${(sessionScope.role eq 'guest')}">
                Welcome!!!!!
                <%-- <c:import url=""></c:import> --%>
            </c:if>
            <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor' || sessionScope.role eq 'user'}">
                <c:import url="/WEB-INF/view/menu.jsp"/>
            </c:if>
        </div>

        <div class="content">

            <c:import url="/WEB-INF/view/body.jsp"/>

        </div>
    </div>

    <div class="footer">

        <c:import url="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>