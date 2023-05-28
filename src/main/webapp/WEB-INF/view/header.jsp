<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="wrapper">
    <div class="newstitle">
        News management
    </div>
    <div class="local-link">
        <div align="right">
            <a href="?lang=en"> en </a> &nbsp;&nbsp;
            <a href="?lang=ru"> ru </a> <br/> <br/>
        </div>

        <c:if test="${sessionScope.role eq 'guest'}">

            <div align="right">
                <form action="/news/doSignIn" method="post">
                    login: <input type="text" name="login" value=""/><br/>
                    password: <input type="password" name="password" value=""/><br/>

                    <c:if test="${not (sessionScope.authenticationError eq null)}">
                        <font color="red">
                            <c:out value="${sessionScope.authenticationError}"/>
                            <c:remove var="authenticationError"/>
                        </font>
                    </c:if>

                    <a href="/news/goToRegistrationPage">registration</a>
                    <input type="submit" value="sign_in"/><br/>
                </form>
            </div>

        </c:if>

        <c:if test="${sessionScope.role eq 'admin' || sessionScope.role eq 'editor' || sessionScope.role eq 'user'}">

            <div align="right">
                <form action="/news/doSignOut" method="post">
                    <input type="submit" value="sign_out"/><br/>
                </form>
            </div>
        </c:if>
    </div>

</div>
