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

<acme:form>
	<acme:form-textbox code="administrator.notice.form.label.headerImage" path="headerImage"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment readonly="true" code="administrator.notice.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="administrator.notice.form.label.body" path="body"/>
	<acme:form-textbox code="administrator.notice.form.label.link" path="link"/>
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="administrator.notice.form.label.confirm" path="confirm"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code = "administrator.notice.form.button.create" action = "/administrator/notice/create"/>
	<acme:form-return code="administrator.notice.form.button.return"/>
	
</acme:form>