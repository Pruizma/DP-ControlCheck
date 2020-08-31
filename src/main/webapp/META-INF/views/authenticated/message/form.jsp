<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command != 'create'}">
	<acme:form-hidden path="discussionForumId" />
	<acme:form-textbox code="authenticated.message.form.label.messageTitle" path="messageTitle"/>
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="authenticated.message.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	<jstl:if test="${command == 'create' }">
	<acme:form-checkbox code="authenticated.message.form.label.checkbox" path="sure"/>
	</jstl:if>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.message.form.button.create" action="create" />

	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>