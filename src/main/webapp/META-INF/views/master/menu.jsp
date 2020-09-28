<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-david" action="https://www.amazon.es/" />
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-fragarroa" action="https://twitter.com/" />
			<acme:menu-suboption code="master.menu.anonymous.juapormon-favourite-link" action="http://www.youtube.com/" />
			<acme:menu-suboption code="master.menu.anonymous.gonzalo.favourite-link" action="https://www.twitter.com/" />
			<acme:menu-suboption code="master.menu.anonymous.luiparlop1-favoriteLink"
				action="https://www.reddit.com/r/rarepuppers/comments/bc1jqk/smiling_doggo/" />
			<acme:menu-suboption code="master.menu.anonymous.rmb.favourite-link" action="https://stackoverflow.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.cuevas-bulletin.list" action="/anonymous/cuevas-bulletin/list" />
			<acme:menu-suboption code="master.menu.cuevas-bulletin.create" action="/anonymous/cuevas-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.garcia-roales-bulletin-list" action="/anonymous/garcia-roales-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.garcia-roales-bulletin-create" action="/anonymous/garcia-roales-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.portero-montano-bulletin-list" action="/anonymous/portero-montano-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.portero-montano-bulletin-create"
				action="/anonymous/portero-montano-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.fernandezTorreBulletin.list" action="/anonymous/fernandez-torre-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.fernandezTorreBulletin.form" action="/anonymous/fernandez-torre-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.pardoLopezBulletin-list" action="/anonymous/pardo-lopez-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.pardoLopezBulletin-create" action="/anonymous/pardo-lopez-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous.rmb.list" action="/anonymous/ruiz-mateos-bulletin/list" />
			<acme:menu-suboption code="master.menu.anonymous.rmb.form" action="/anonymous/ruiz-mateos-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.toolRecord" action="/anonymous/tool-record/list" />
			<acme:menu-suboption code="master.menu.anonymous.notice-list" action="/anonymous/notice/list" />
			<acme:menu-suboption code="master.menu.anonymous.technology-records" action="/anonymous/technology-records/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.toolRecord" action="/authenticated/tool-record/list" />
			<acme:menu-suboption code="master.menu.authenticated.notice-list" action="/authenticated/notice/list" />
			<acme:menu-suboption code="master.menu.authenticated.technology-records" action="/authenticated/technology-records/list" />
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list" />
			<acme:menu-suboption code="master.menu.authenticated.overture.list" action="/authenticated/overture/list" />
			<acme:menu-suboption code="master.menu.authenticated.inquire-list" action="/authenticated/inquire/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.investment.list" action="/authenticated/investment/list" />
			<acme:menu-suboption code="master.menu.authenticated.dforum" action="/authenticated/discussion-forum/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.dforum.create" action="/authenticated/discussion-forum/create"/>		
		</acme:menu-option>



		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/list" />
			<acme:menu-suboption code="master.menu.administrator.notice" action="/administrator/notice/list" />
			<acme:menu-suboption code="master.menu.administrator.notice-create" action="/administrator/notice/create" />
			<acme:menu-suboption code="master.menu.administrator.challenge-link" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create" />
			<acme:menu-suboption code="master.menu.administrator.toolRecord.list" action="/administrator/tool-record/list" />
			<acme:menu-suboption code="master.menu.administrator.toolRecord.create" action="/administrator/tool-record/create" />
			<acme:menu-suboption code="master.menu.administrator.technology-records.edit" action="/administrator/technology-records/list" />
			<acme:menu-suboption code="master.menu.administrator.technology-records.create" action="/administrator/technology-records/create" />
			<acme:menu-suboption code="master.menu.administrator.overture" action="/administrator/overture/list" />
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create" />
			<acme:menu-suboption code="master.menu.administrator.inquire.list" action="/administrator/inquire/list" />
			<acme:menu-suboption code="master.menu.administrator.inquire.create" action="/administrator/inquire/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.customisationParameter" action="/administrator/customisation-parameter/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator.requests" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.bookkeeper-request.list" action="/administrator/bookkeeper-request/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.entrepreneus" access="hasRole('Entrepreneus')">
			<acme:menu-suboption code="master.menu.entrepreneus.create" action="/entrepreneus/investment/create" />
			<acme:menu-suboption code="master.menu.entrepreneus.investment.list" action="/entrepreneus/investment/list-mine" />
			<acme:menu-suboption code="master.menu.entrepreneus.application.listMine" action="/entrepreneus/application/list-mine" />
			<acme:menu-suboption code="master.menu.entrepreneus.application.listPending" action="/entrepreneus/application/list-pending" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.entrepreneus.application.listFecha" action="/entrepreneus/application/list-fecha" />
			<acme:menu-suboption code="master.menu.entrepreneus.application.listTicker" action="/entrepreneus/application/list-ticker" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application.listMine" action="/investor/application/list-mine" />
			<acme:menu-suboption code="master.menu.investor.application.create" action="/investor/investment/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investment.list-mine" action="/bookkeeper/investment/list-mine" />
			<acme:menu-suboption code="master.menu.bookkeeper.investment.list-not-mine" action="/bookkeeper/investment/list-not-mine" />
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.authenticated.create.entrepreneus" action="/authenticated/entrepreneus/create"
				access="!hasRole('Entrepreneus')" />
			<acme:menu-suboption code="master.menu.authenticated.update.entrepreneus" action="/authenticated/entrepreneus/update"
				access="hasRole('Entrepreneus')" />
			<acme:menu-suboption code="master.menu.authenticated.create.investor" action="/authenticated/investor/create"
				access="!hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.authenticated.update.investor" action="/authenticated/investor/update"
				access="hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.authenticated.create.bookkeeper-request" action="/authenticated/bookkeeper-request/create"
				access="!hasRole('Bookkeeper')" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>