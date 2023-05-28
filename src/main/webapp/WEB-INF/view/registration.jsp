<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="wrapper">

    <div class="registration_form">

        <div class="title">
            <c:out value="Registration"/>
        </div>

        <form:form action="/news/doRegistration" method="post" modelAttribute="user">
            <div class="form_wrapper">
                <div class="input_grp">
                    <div class="input_wrap">
                        <label for="name"><c:out value="Name"/></label> <form:input type="text" id="name" path="userDetails.name" />
                    </div>
                    <div class="input_wrap">
                        <label for="surname"><c:out value="Surname"/></label> <form:input type="text" id="surname" path="userDetails.surname" />
                    </div>
                    <div class="input_wrap">
                        <label for="birthday"><c:out value="Birthday"/></label>
                        <form:input type="date" id="birthday" path="userDetails.birthday" />
                    </div>
                </div>
                <div class="input_wrap">
                    <label for="email"><c:out value="Email"/></label> <form:input type="text" id="email" path="userDetails.email" />
                </div>

                <div class="input_wrap">
                    <label for="login"><c:out value="Login"/></label> <form:input type="text" id="login" path="login" />
                </div>
                <div class="input_wrap">
                    <label for="password"><c:out value="Password"/></label> <form:input type="password" id="password" path="password" />
                </div>
                <div class="input_wrap">
                    <label for="repeatPassword"><c:out value="Repeat password"/></label>
                    <input type="password" id="repeatPassword" name="repeatPassword">
                </div>
                <div class="input_wrap">
                    <input type="submit" value="Confirm">
                </div>
            </div>
        </form:form>
    </div>
</div>
