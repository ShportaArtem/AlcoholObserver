<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header.jspf"%>

	<div class="container-fluid">
<div class="row"><hr></div>
<div class="row">
<div class="col-lg-6">
<form id="login_form" action="Controller" method="post">
<input type="hidden" name="command" value="addVerification" />
	 <div class="form-group">
        <label for="exampleFormControlSelect1">Verified ?</label>
        <select class="form-control" id="exampleFormControlSelect1" name="check">
          <option>Yes</option>
          <option>No</option>
        </select>
      </div>
      <div class="form-group">
              <label for="exampleFormControlSelect1">Employee</label>
              <select class="form-control" id="exampleFormControlSelect1" name="employeeId">
                <c:forEach var="employee" items="${sessionScope.allEmployees}">
                <option value="${employee.getId()}">${employee.getName()}</option>
                </c:forEach>
              </select>
            </div>
  
<div class="form-group">
   <button type="submit" class="btn btn-success mb-2"><fmt:message key="button.add" /></button>
</div>
</form>
</div>
</div>
</div>


<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>