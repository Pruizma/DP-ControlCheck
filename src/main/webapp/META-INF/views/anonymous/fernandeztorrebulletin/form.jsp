<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.fernandezTorreBulletin.form.label.author" path="author"/>
	<acme:form-textbox code="anonymous.fernandezTorreBulletin.form.label.address" path="address"/>
	
	<acme:form-submit code="anonymous.fernandezTorreBulletin.form.button.create" action="/anonymous/fernandez-torre-bulletin/create"/>
	<acme:form-return code="anonymous.fernandezTorreBulletin.form.button.return"/>
</acme:form>