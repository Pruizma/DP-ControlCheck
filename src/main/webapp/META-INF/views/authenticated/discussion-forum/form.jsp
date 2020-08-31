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

<acme:form >

	<acme:form-textbox code="authenticated.discussion-forum.form.label.title" path="title"/>
 	<acme:form-textbox code="authenticated.discussion-forum.form.label.ticker" path="investment.ticker"/>
 	<acme:form-textbox code="authenticated.discussion-forum.form.label.username" path="aux"/>
 	<jstl:if test="${command != 'create'}">
 	<acme:form-textbox code="authenticated.discussion-forum.form.label.leader" path="leader.userAccount.username"/>
 	</jstl:if>
 	<acme:form-hidden path="discussionForumId" />
 	
 	<acme:form-submit test="${command == 'show'}"
	code="authenticated.discussion-forum.form.button.update" 
	action="/authenticated/discussion-forum/update"/>
	<acme:form-submit test="${command == 'update'}"
	code="authenticated.discussion-forum.form.button.update" 
	action="/authenticated/discussion-forum/update"/>
	<acme:form-submit test="${command == 'show'}" 
	code="authenticated.discussion-forum.form.buttom.message" 
	action="/authenticated/message/list-mine?id=${id}"  method="get"/>
	<acme:form-submit test="${command == 'create'}"
	code="authenticated.discussion-forum.form.button.create" 
	action="/authenticated/discussion-forum/create"/>
	<acme:form-submit test="${command == 'show'}" code="authenticated.discussionForum.form.button.newmessage"
	action="/authenticated/message/create?discussionForumId=${id}" method="get"/>
	<acme:form-submit test="${command == 'show'}"
	code="authenticated.discussion-forum.form.button.delete" 
	action="/authenticated/discussion-forum/delete"/>		
	 
	<acme:form-return code="authenticated.discussion-forum.form.button.return"/>
</acme:form>
