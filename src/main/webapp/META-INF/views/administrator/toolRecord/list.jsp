<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.toolRecord.list.label.title" path="title" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.activitySector" path="activitySector" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.inventorName" path="inventorName" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.description" path="description" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.webSite" path="webSite" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.email" path="email" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.openSource" path="openSource" width="12.5%"/>
	<acme:list-column code="administrator.toolRecord.list.label.stars" path="stars" width="12.5%"/>
</acme:list>