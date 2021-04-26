<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html>
	<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
	<%@ include file="/view/jspf/header.jspf"%>
<div class="container-fluid">
<div class="row"><hr></div>
<c:choose>
            <c:when test="${sessionScope.companyNow != null }">
<div class="row">
    <div class="col-lg">
        <div class="btn-group">
                <a class="btn btn-success" href="/AlcoholObserver/Controller?command=openRegisterNewEmployee" data-toggle="tooltip bg-dark" data-placement="bottom" ><fmt:message key="button.registerEmployee" /></a>
        </div>
    </div>
</div>
</c:when>
</c:choose>
<div class="row"><hr></div>
<div class="row">
    <div class="col-lg-9">
        <c:choose>
            <c:when test="${sessionScope.companyNow == null }">
                <div class="jumbotron jumbotron-fluid">
                    <div class="container-fluid">
                        <h1 class="display-1"><fmt:message key="employees.noCompanySelected"/></h1>
                    </div>
                </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.employeesNow == null }">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container-fluid">
                                <h1 class="display-1"><fmt:message key="employees.noEmployeesFound"/></h1>
                            </div>
                        </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-bordered table-dark table-striped ">
                                    <thead class="thead-light">
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col"><fmt:message key="user.email"/></th>
                                      <th scope="col"><fmt:message key="user.address"/></th>
                                      <th scope="col"><fmt:message key="driver.phone"/></th>
                                      <th scope="col"><fmt:message key="user.countOfViolation"/></th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="employee" items="${sessionScope.employeesNow}">
                                        <tr>
                                          <th scope="row">${employee.getUserId()}</th>
                                          <td>${employee.getEmail()}</td>
                                          <td>${employee.getAddress()}</td>
                                          <td>${employee.getPhone()}</td>
                                          <td>${employee.getCountOfViolation()}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>

                                </table>
                              </div>
                                <div class="col-sm">
                        		    <div class="btn-group">
                                        <div class="dropdown">
                                            <button type="button" class="btn btn btn-outline-info dropdown-toggle" data-toggle="dropdown">
                                            <fmt:message key="employee.openEmployee"/>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <c:forEach var="employee" items="${sessionScope.employeesNow}">
                                                    <a class="dropdown-item" href="/AlcoholObserver/Controller?command=openEmployee&employeeId=${employee.getUserId()}">${employee.getUserId()}</a>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>


</div>

</div>
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>