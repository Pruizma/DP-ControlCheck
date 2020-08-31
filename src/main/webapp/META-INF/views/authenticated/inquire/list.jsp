<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.inquire.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.inquire.list.label.creation" path="creation" width="10%"/>
	<acme:list-column code="authenticated.inquire.list.label.deadline" path="deadline" width="10%"/>
	<acme:list-column code="authenticated.inquire.list.label.description" path="description" width="30%"/>
	<acme:list-column code="authenticated.inquire.list.label.moneyMin" path="moneyMin" width="10%"/>
	<acme:list-column code="authenticated.inquire.list.label.moneyMax" path="moneyMax" width="10%"/>
	<acme:list-column code="authenticated.inquire.list.label.email" path="email" width="10%"/>
</acme:list>