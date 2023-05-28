<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${requestScope.presentation eq 'newsList' }">
    <c:import url="/WEB-INF/view/newsList.jsp"/>
</c:if>

<c:if test="${requestScope.presentation eq 'viewNews' }">
    <c:import url="/WEB-INF/view/viewNews.jsp"/>
</c:if>

<c:if test="${requestScope.presentation eq 'editNews' }">
    <c:import url="/WEB-INF/view/editNews.jsp"/>
</c:if>

<c:if test="${requestScope.presentation eq 'addNews' }">
    <c:import url="/WEB-INF/view/addNews.jsp"/>
</c:if>

<c:if test="${requestScope.presentation eq 'registration' }">
    <c:import url="/WEB-INF/view/registration.jsp"/>
</c:if>

<c:if test="${requestScope.presentation eq 'error' }">
    <c:import url="/WEB-INF/view/error.jsp"/>
</c:if>