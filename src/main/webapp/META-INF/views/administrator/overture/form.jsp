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
	<acme:form-textbox code="authenticated.overture.form.label.title" path="title"/>
	<acme:form-moment readonly="true" code="authenticated.overture.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.overture.form.label.description" path="description"/>
	<acme:form-money code="authenticated.overture.form.label.minMoney" path="minMoney"/>
	<acme:form-money code="authenticated.overture.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="authenticated.overture.form.label.email" path="email"/>
	
			
	<acme:form-submit test="${command == 'show'}"
		code = "administrator.overture.form.button.update"
		action = "/administrator/overture/update"/>
	<acme:form-submit test="${command == 'show'}"
		code = "administrator.overture.form.button.delete"
		action = "/administrator/overture/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code = "administrator.overture.form.button.create"
		action = "/administrator/overture/create"/>
	<acme:form-submit test="${command == 'update'}"
		code = "administrator.overture.form.button.update"
		action = "/administrator/overture/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code = "administrator.overture.form.button.delete"
		action = "/administrator/overture/delete"/>
  		
	
  	<acme:form-return code="authenticated.overture.form.button.return"/>
</acme:form>
