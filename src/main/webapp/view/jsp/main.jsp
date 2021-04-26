<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
	<%@ include file="/view/jspf/header.jspf"%>
<c:choose>
    <c:when test="${sessionScope.userRole == '4' }">
	<br>
	<br>
          <a class="btn btn-success mb-2" href="/AlcoholObserver/Controller?command=openAddVerificationCommand">Add verification</a>
		</c:when>
	</c:choose>
	<c:choose>
        <c:when test="${sessionScope.userRole == '1' }">
    	<br>
    	<c:choose>
                <c:when test="${sessionScope.backupResult != null }">
                    <h1>${sessionScope.backupResult}</h1>
                </c:when>
                    	</c:choose>
    	<br>
              <a class="btn btn-success mb-2" href="/AlcoholObserver/Controller?command=doBackUp"><fmt:message key="button.doBackup"/></a>
    		</c:when>
    	</c:choose>
	
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>