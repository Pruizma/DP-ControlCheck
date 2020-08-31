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

	<acme:form-textbox code="authenticated.banner.form.label.name" path="name"/>
	<acme:form-url  code="authenticated.banner.form.label.picture" path="picture"/>
	
	<acme:message code="a.b.credit.card"/>
	
	<acme:form-textbox code="authenticated.banner.form.label.creditCard.number" path="number"/>
	<acme:form-textbox code="authenticated.banner.form.label.creditCard.holder" path="holder"/>
	<acme:form-textbox code="authenticated.banner.form.label.creditCard.brand" path="brand"/>
	<acme:form-textbox code="authenticated.banner.form.label.creditCard.cvv" path="cvv"/>
	<acme:form-textbox code="authenticated.banner.form.label.creditCard.expirationDate" path="expirationDate"/>
	
  	<acme:form-return code="authenticated.activity.form.button.return"/>
</acme:form>