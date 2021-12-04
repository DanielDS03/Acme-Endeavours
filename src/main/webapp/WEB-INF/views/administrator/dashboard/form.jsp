<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<acme:form readonly="true">
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-public-duty"
		path="numberOfPublicDuty" />
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-private-duty"
		path="numberOfPrivateDuty" />
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-finish-duty"
		path="numberOfFinishDuty" />
	<acme:form-integer
		code="administrator.dashboard.form.label.number-of-not-finish-duty"
		path="numberOfNotFinishDuty" />
	<acme:form-integer
		code="administrator.dashboard.form.label.minimum-workload"
		path="minimumWorkload" />
	<acme:form-integer
		code="administrator.dashboard.form.label.maximum-workload"
		path="maximumWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.average-workload"
		path="averageWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.deviation-workload"
		path="deviationWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.average-execution-periods"
		path="averageExecutionPeriods" />
	<acme:form-double
		code="administrator.dashboard.form.label.deviation-execution-periods"
		path="deviationExecutionPeriods" />
<acme:form-double
		code="administrator.dashboard.form.label.minimum-execution-periods"
		path="minimumExecutionPeriods" />
<acme:form-double
		code="administrator.dashboard.form.label.maximum-execution-periods"
		path="maximumExecutionPeriods" />		
</acme:form>
