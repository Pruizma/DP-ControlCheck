<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.customisationParameter.form.label.spamWords" path="spamWords"/>
	<acme:form-textbox code="administrator.customisationParameter.form.label.spamThreshold" path="spamThreshold"/>
	<acme:form-textbox code="administrator.customisationParameter.form.label.activitySectors" path="activitySectors"/>
	<acme:form-submit test="${command == 'show'}" code = "administrator.customisationParameter.form.button.update" action = "/administrator/customisation-parameter/update"/>
	<acme:form-submit test="${command == 'update'}" code = "administrator.customisationParameter.form.button.update" action = "/administrator/customisation-parameter/update"/>
	<acme:form-return code="administrator.customisationParameter.form.button.return"/>
	
</acme:form>