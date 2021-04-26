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
        <table class="table table-bordered table-dark table-striped ">
                    <thead class="thead-light">
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col"><fmt:message key="user.name"/></th>
                      <th scope="col"><fmt:message key="user.surname"/></th>
                      <th scope="col"><fmt:message key="user.login"/></th>
                    </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="user" items="${sessionScope.allUsers}">
                        <tr>
                          <th scope="row">${user.getId()}</th>
                          <td>${user.getName()}</td>
                          <td>${user.getSurname()}</td>
                          <td>${user.getLogin()}</td>
                        </tr>
                        </c:forEach>
                    </tbody>

                </table>
              </div>
                <div class="col-sm">
                    <div class="btn-group">
                        <div class="dropdown">
                            <button type="button" class="btn btn btn-outline-info dropdown-toggle" data-toggle="dropdown">
                            <fmt:message key="button.delete"/>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right">
                                <c:forEach var="user" items="${sessionScope.allUsers}">
                                    <a class="dropdown-item" href="/AlcoholObserver/Controller?command=deleteUser&userId=${user.getId()}">${user.getId()}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
</div>

</div>
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>