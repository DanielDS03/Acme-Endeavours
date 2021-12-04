<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="dutyId"/>

		<acme:form-textbox code="authenticated.duty.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="authenticated.duty.label.description" path="description" readonly="true"/>
	<acme:form-textarea code="authenticated.duty.label.workload" path="workload" readonly="true"/>
	<acme:form-textarea code="authenticated.duty.start" path="start" readonly="true"/>
	<acme:form-textarea code="authenticated.duty.label.end" path="end" readonly="true"/>	
	<acme:form-textarea code="authenticated.duty.label.link" path="link" readonly="true"/>			
		
	<acme:form-return code="authenticated.duty.button.return"/>	
</acme:form>

