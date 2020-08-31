<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="investor.investment.form.label.ticker" path="ticker"/>
	<acme:form-moment code="investor.investment.form.label.moment" path="moment"/>
	<acme:form-textbox code="investor.investment.form.label.round" path="round"/>
	<acme:form-textbox code="investor.investment.form.label.title" path="title"/>
	<acme:form-textarea code="investor.investment.form.label.description" path="description"/>
	<acme:form-textbox code="investor.investment.form.label.money" path="money"/>
	<acme:form-textbox code="investor.investment.form.label.url" path="url"/>
	<acme:form-submit test="${command == 'show'}" code = "investor.application.form.button.create" action = "/investor/application/create?id=${id}" method="get"/>
  	<acme:form-return code="investor.investment.form.button.return"/>
</acme:form>
