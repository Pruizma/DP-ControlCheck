<%--
- list.jsp
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

<acme:form readonly="true">
<acme:form-double code="administrator.dashboard.investments" path="investmentsWithoutQuittel"/>
<acme:form-double code="administrator.dashboard.applications1" path="applicationsWithLink"/>
<acme:form-double code="administrator.dashboard.applications2" path="applicationsWithPasswordProtected"/>
<acme:message code="mensaje.informativo"/>
<acme:form-double code="administrator.dashboard.application" path="application"/>
<acme:form-double code="administrator.dashboard.investment" path="investment"/>
<acme:form-double code="administrator.dashboard.investments1" path="investmentsWithoutQuittel2"/>
<acme:form-double code="administrator.dashboard.applications11" path="applicationsWithLink2"/>
<acme:form-double code="administrator.dashboard.applications22" path="applicationsWithPasswordProtected2"/>

</acme:form>



	    	
	 
