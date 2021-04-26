<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<%@ include file="/view/jspf/head.jspf"%>

<body class="bg-secondary">
<%@ include file="/view/jspf/header4.jspf"%>
	<form action="changeLocale.jsp" method="post">
		<fmt:message key="settings_jsp.label.set_locale" />:
		<select name="locale">
			<c:forEach items="${applicationScope.locales}" var="locale">
				<c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
				<option value="${locale.key}" ${selected}>${locale.value}</option>
			</c:forEach>
		</select>
		<input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
		
	</form>
	<a class="btn btn-primary mb-2" href="view/jsp/main.jsp"><fmt:message key="settings_jsp.link.back_to_main_page"></fmt:message></a>
</body>
</html>