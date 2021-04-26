<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html>
	<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
	<%@ include file="/view/jspf/header.jspf"%>
<div class="container-fluid">
<div class="row"><hr></div>
<div class="row">
    <div class="col-lg">
        <div class="btn-group">
                <a class="btn btn-success" href="/AlcoholObserver/Controller?command=openBuyNewSlot" data-toggle="tooltip bg-dark" data-placement="bottom" ><fmt:message key="company.buyNewSlot"/></a>
            <c:choose>
                <c:when test="${sessionScope.companiesNow != null }">
                    <div class="dropdown">
                        <button type="button" class="btn btn btn-info dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="company.selectCompany"/>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <c:forEach var="company" items="${sessionScope.companiesNow}">
                                <a class="dropdown-item" href="/AlcoholObserver/Controller?command=selectCurrentCompany&companyId=${company.getId()}">${company.getId()}</a>
                            </c:forEach>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
<div class="row"><hr></div>
<div class="row">
    <div class="col-lg-9">
        <c:choose>
            <c:when test="${sessionScope.companiesNow != null }">
            <table class="table table-bordered table-dark table-striped ">
            <thead class="thead-light">
            <tr>
              <th scope="col">#</th>
              <th scope="col"><fmt:message key="user.name"/></th>
              <th scope="col"><fmt:message key="company.description"/></th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="company" items="${sessionScope.companiesNow}">
                <tr>
                  <th scope="row">${company.getId()}</th>
                  <td>${company.getName()}</td>
                  <td>${company.getDescription()}</td>
                </tr>
                </c:forEach>
            </tbody>

        </table>
      </div>
        <div class="col-sm">
		    <div class="btn-group">
                <div class="dropdown">
                    <button type="button" class="btn btn btn-outline-info dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="button.update" />
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <c:forEach var="company" items="${sessionScope.companiesNow}">
                            <a class="dropdown-item" href="/AlcoholObserver/Controller?command=openUpdateCompany&companyId=${company.getId()}">${company.getId()}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        </c:when>
        <c:otherwise>
            <div class="jumbotron jumbotron-fluid">
                <div class="container-fluid">
                    <h1 class="display-1"><fmt:message key="company.noCompaniesFound"/></h1>
                </div>
            </div>
          </div>
        </c:otherwise>
        </c:choose>


</div>

</div>
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>