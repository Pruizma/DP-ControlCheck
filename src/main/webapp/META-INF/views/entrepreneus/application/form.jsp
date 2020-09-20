<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${command != 'update' }">
	<acme:form readonly="true">
		<acme:form-textbox code="entrepreneus.application.form.label.ticker" path="ticker" />
		<acme:form-moment code="entrepreneus.application.form.label.moment" path="moment" />
		<acme:form-textbox code="entrepreneus.application.form.label.statement" path="statement" />
		<jstl:if test="${not empty justification}">
		<acme:form-textbox code="entrepreneus.application.form.label.justification" path="justification" />
		</jstl:if>
		<acme:form-money code="entrepreneus.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-money code="entrepreneus.application.form.label.investmentTicker" path="investmentTicker" />
		<acme:form-money code="entrepreneus.application.form.label.investorName" path="investorName" />
		
		<jstl:if test="${passProct == false}">
		<acme:form-textbox code="entrepreneus.offer.form.label.titleOffer" path="titleOffer" />
		<jstl:if test="${linkOffer != ''}">
		<acme:form-url code="entrepreneus.offer.form.label.linkOffer" path="linkOffer" />
		</jstl:if>
		</jstl:if>
		
		<jstl:if test="${passProct != false && aux != true}">
		<acme:form-submit test="${offer == true }" code="entrepreneus.application.form.button.offer" action="/entrepreneus/offer/show?id=${offerid}" method= "get"  />
		</jstl:if>
		
		<jstl:if test="${passProct != false && aux == true}">
		<acme:form-textbox code="entrepreneus.offer.form.label.titleOffer" path="titleOffer" />
		<jstl:if test="${linkOffer != ''}">
		<acme:form-url code="entrepreneus.offer.form.label.linkOffer" path="linkOffer" />
		</jstl:if>
		</jstl:if>
		
		
		<jstl:if test="${statement == 'PENDING'}">
		<acme:form-submit code="entrepreneus.application.form.button.update" action = "/entrepreneus/application/update?id=${id}" method= "get" />
		</jstl:if>
		<acme:form-return code="entrepreneus.application.form.button.return" />
	</acme:form>
</jstl:if>
<jstl:if test="${command == 'update' }">
	<acme:form>
		<acme:form-textbox code="entrepreneus.application.form.label.statement" path="statement" />
		<acme:form-textbox code="entrepreneus.application.form.label.justification" path="justification" />
		<acme:form-submit code="entrepreneus.application.form.button.update" action = "/entrepreneus/application/update?id=${id}"/>
		<acme:form-return code="entrepreneus.application.form.button.return" />
	</acme:form>
</jstl:if>