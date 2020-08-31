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
	<acme:form-textbox code="entrepreneus.investment.form.label.ticker" path="ticker"/>
	<acme:form-moment readonly="true" code="entrepreneus.investment.form.label.moment" path="moment"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.round" path="round"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.title" path="title"/>
	<acme:form-textarea code="entrepreneus.investment.form.label.description" path="description"/>
	<acme:form-money code="entrepreneus.investment.form.label.money" path="money"/>
	<acme:form-url code="entrepreneus.investment.form.label.url" path="url"/> 
	
	<acme:form-submit code="bookkeeper.investment.form.button.create-accounting-record" action="/bookkeeper/accounting-record/create?idInvestment=${id}" method="get"/>


	<acme:form-submit test="${command == 'create'}"
		code = "entrepreneus.investment.form.button.create"
		action = "/entrepreneus/investment/create"/>

	<acme:form-submit test="${command == 'show'}" code = "authenticated.accounting-record.form.button.list" action = "/bookkeeper/accounting-record/list?id=${id}"  method="get"/>
		
  	<acme:form-return code="entrepreneus.investment.form.button.return"/>
</acme:form>
