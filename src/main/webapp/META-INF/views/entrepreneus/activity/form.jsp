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

	<acme:form-textbox code="entrepreneus.activity.form.label.title" path="title"/>

	<acme:form-moment code="entrepreneus.activity.form.label.start" path="start"/>
	
	<acme:form-moment code="entrepreneus.activity.form.label.end" path="end"/>
	<acme:form-textbox code="entrepreneus.activity.form.label.budget" path="budget"/>

<acme:form-submit test="${command == 'create'}" code = "entrepreneus.activity.form.button.create" action = "/entrepreneus/activity/create?id=${id}"/>
	<acme:form-submit test="${command == 'show' or command == 'update'}" code = "entrepreneus.activity.form.button.update" action = "/entrepreneus/activity/update?id=${id}"/>
	<acme:form-submit test="${command == 'show' or command == 'update'}" code = "entrepreneus.activity.form.button.delete" action = "/entrepreneus/activity/delete"/>
  	<acme:form-return code="entrepreneus.activity.form.button.return"/>
</acme:form>