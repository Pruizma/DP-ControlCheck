<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.discussion-forum.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.discussion-forum.list.label.ticker" path="investment.ticker" width="20%"/>
	<acme:list-column code="authenticated.discussion-forum.list.label.username" path="aux" width="40%"/>
	
</acme:list>