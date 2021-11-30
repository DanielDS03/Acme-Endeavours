<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId" />

	<acme:form-textbox code="officer.task.label.title" path="title" />
	<acme:form-textarea code="officer.task.label.description"
		path="description" />
	<acme:form-workload code="officer.task.label.workload" path="workload" />
	<acme:form-moment code="officer.task.start" path="start" />
	<acme:form-moment code="officer.task.end" path="end" />
	<acme:form-url code="officer.task.label.link" path="link" />
	<acme:form-submit test="${command == 'create'}" code="officer.task.form.button.create" action="/officer/task/create"/>
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.task.button.delete" action="/officer/task/delete"/>		
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.task.button.update" action="/officer/task/update"/>	
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="officer.task.button.publish" action="/officer/task/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.task.button.delete" action="/officer/task/delete"/>		
	<acme:form-submit test="${command == 'update'}" code="officer.task.button.update" action="/officer/task/update"/>	
	<acme:form-submit test="${command == 'publish'}" code="officer.task.form.button.publish" action="/officer/task/publish"/>
	<acme:form-return code="officer.task.button.return" />


</acme:form>

