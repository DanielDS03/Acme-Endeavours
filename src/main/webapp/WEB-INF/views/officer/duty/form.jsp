<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="dutyId" />

	<acme:form-textbox code="officer.duty.label.title" path="title" />
	<acme:form-textarea code="officer.duty.label.description"
		path="description" />
	<acme:form-workload code="officer.duty.label.workload" path="workload" />
	<acme:form-moment code="officer.duty.start" path="start" />
	<acme:form-moment code="officer.duty.end" path="end" />
	<acme:form-url code="officer.duty.label.link" path="link" />
	<acme:form-submit test="${command == 'create'}" code="officer.duty.form.button.create" action="/officer/duty/create"/>
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.duty.button.delete" action="/officer/duty/delete"/>		
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.duty.button.update" action="/officer/duty/update"/>	
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.duty.button.publish" action="/officer/duty/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.duty.button.delete" action="/officer/duty/delete"/>		
	<acme:form-submit test="${command == 'update'}" code="officer.duty.button.update" action="/officer/duty/update"/>	
	<acme:form-submit test="${command == 'publish'}" code="officer.duty.form.button.publish" action="/officer/duty/publish"/>
	<acme:form-return code="officer.duty.button.return" />


</acme:form>

