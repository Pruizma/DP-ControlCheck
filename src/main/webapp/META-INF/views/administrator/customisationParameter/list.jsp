<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.customisationParameter.list.label.spamWords" path="spamWords" width="34%"/>
	<acme:list-column code="administrator.customisationParameter.list.label.spamThreshold" path="spamThreshold" width="33%"/>
	<acme:list-column code="administrator.customisationParameter.list.label.activitySectors" path="activitySectors" width="33%"/>
	</acme:list>