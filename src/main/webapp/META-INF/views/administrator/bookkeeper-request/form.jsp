<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox readonly="true" code="administrator.bookkeeper-request.form.label.firmName" path="firmName" />
	<acme:form-textbox readonly="true" code="administrator.bookkeeper-request.form.label.statement" path="statement" />
	
<acme:form-checkbox code="administrator.bookkeeper-request.form.label.accepted" path="accepted" />
	
	<acme:form-submit test="${command == 'show'}"
		code = "administrator.bookkeeper-request.form.button.update"
		action = "/administrator/bookkeeper-request/update"/>

	<acme:form-submit test="${command == 'update'}"
		code = "administrator.bookkeeper-request.form.button.update"
		action = "/administrator/bookkeeper-request/update"/>
  		
  	<acme:form-return code="administrator.bookkeeper-request.form.button.return"/>
</acme:form>