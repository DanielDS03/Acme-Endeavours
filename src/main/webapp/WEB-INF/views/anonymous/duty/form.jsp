<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="dutyId"/>

		<acme:form-textbox code="anonymous.duty.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="anonymous.duty.label.description" path="description" readonly="true"/>
	<acme:form-textarea code="anonymous.duty.label.workload" path="workload" readonly="true"/>
	<acme:form-textarea code="anonymous.duty.start" path="start" readonly="true"/>
	<acme:form-textarea code="anonymous.duty.end" path="end" readonly="true"/>
	<acme:form-textarea code="anonymous.duty.label.link" path="link" readonly="true"/>			
		
	<acme:form-return code="anonymous.duty.button.return"/>	
</acme:form>

