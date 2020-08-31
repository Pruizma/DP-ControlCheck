<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="investor.investment.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="investor.investment.list.label.title" path="title" width="20%"/>	
	<acme:list-column code="investor.investment.list.label.round" path="round" width="10%"/>	
	<acme:list-column code="investor.investment.list.label.description" path="description" width="50%"/>	
</acme:list>


