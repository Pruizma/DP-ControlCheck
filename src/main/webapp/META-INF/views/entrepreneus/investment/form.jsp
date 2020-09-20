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
	<acme:form-errors path= "delete"/>
<jstl:if test="${finalMode == 'true' || command == 'delete' }">
	<acme:form-textbox code="entrepreneus.investment.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.entrepreneur" path="entrepreneurName" readonly="true"/>

	<acme:form-moment code="entrepreneus.investment.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.round" path="round" readonly="true"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.title" path="title" readonly="true"/>
	<acme:form-textarea code="entrepreneus.investment.form.label.description" path="description" readonly="true"/>
	<acme:form-money code="entrepreneus.investment.form.label.money" path="money" readonly="true"/>
	<acme:form-url code="entrepreneus.investment.form.label.url" path="url" readonly="true"/>
	<jstl:if test="${not empty quittel}">
	<acme:form-textarea code="entrepreneus.investment.form.label.quittel" path="quittel" readonly="true"/>
	</jstl:if>	
		<jstl:if test="${command != 'create'}">
			<acme:form-return code = "entrepreneus.accounting-record.form.button.list" action = "/entrepreneus/accounting-record/list?id=${id}"/>
			<acme:form-return code = "entrepreneus.activity.form.button.list" action = "/entrepreneus/activity/list-by-investment?id=${id}"/>
		</jstl:if>
	</jstl:if>
	<jstl:if test="${finalMode != 'true' && command != 'delete' }">
	<acme:form-textbox code="entrepreneus.investment.form.label.ticker" path="ticker"  placeholder="SEE-20-0000001"/>

	<jstl:if test="${command != 'create' }">
	<acme:form-textbox code="entrepreneus.investment.form.label.entrepreneur" path="entrepreneurName" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'show' || command == 'update' }">
	<acme:form-moment readonly="true" code="entrepreneus.investment.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneus.investment.form.label.round" path="round"/>
	<acme:form-textbox code="entrepreneus.investment.form.label.title" path="title"/>
	<acme:form-textarea code="entrepreneus.investment.form.label.description" path="description"/>
	<acme:form-money code="entrepreneus.investment.form.label.money" path="money"/>
	<acme:form-url code="entrepreneus.investment.form.label.url" path="url"/>
	<acme:form-textarea code="entrepreneus.investment.form.label.quittel" path="quittel"/>
		<jstl:if test="${command == 'show' || command == 'update' }">
			<acme:form-checkbox code="entrepreneus.investment.form.label.finalMode" path="finalMode"/>
			<acme:form-return code = "entrepreneus.accounting-record.form.button.list" action = "/entrepreneus/accounting-record/list?id=${id}"/>
			<acme:form-return code = "entrepreneus.activity.form.button.list" action = "/entrepreneus/activity/list-by-investment?id=${id}"/>
		</jstl:if>
		
		<acme:form-submit test="${command == 'show'}" code = "entrepreneus.activity.form.button.create" action = "/entrepreneus/activity/create?id=${id}" method="get"/>
		
	</jstl:if>
	<br><br>
	
	<acme:form-submit test="${command == 'show' && finalMode=='false'}"
		code = "entrepreneus.investment.form.button.update"
		action = "/entrepreneus/investment/update"/>
	<acme:form-submit test="${command == 'update' && finalMode=='false'}" 
		code="entrepreneus.investment.form.button.update" 
		action="/entrepreneus/investment/update"/>

	<acme:form-submit test="${command == 'show' }" code="entrepreneus.investment.form.button.delete"
		action="/entrepreneus/investment/delete" />
	<acme:form-submit test="${command == 'delete' }" code="entrepreneus.investment.form.button.delete"
		action="/entrepreneus/investment/delete" />
		
	<acme:form-submit test="${command == 'create'}"
		code = "entrepreneus.investment.form.button.create"
		action = "/entrepreneus/investment/create"/>
<%--
	<acme:form-submit test="${command == 'show'}" code = "entrepreneus.accounting-record.form.button.list" action = "/entrepreneus/accounting-record/list?id=${id}"  method="get"/>
	<acme:form-submit test="${command == 'show'}" code = "entrepreneus.activity.form.button.list" action = "/entrepreneus/activity/list-by-investment?id=${id}"  method="get"/>
	<acme:form-submit test="${command == 'show'}" code = "entrepreneus.activity.form.button.create" action = "/entrepreneus/activity/create?id=${id}" method="get"/>
   --%>
  <acme:form-return code="entrepreneus.investment.form.button.return"/>
</acme:form>
