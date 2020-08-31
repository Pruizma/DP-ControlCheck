<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="entrepreneus.application.list.label.ticker" path="ticker" width="25%" />
	<acme:list-column code="entrepreneus.application.list.label.moment" path="moment" width="25%" />
	<acme:list-column code="entrepreneus.application.list.label.statement" path="statement" width="25%" />
	<acme:list-column code="entrepreneus.application.list.label.moneyOffer" path="moneyOffer" width="25%" />
	
</acme:list>
