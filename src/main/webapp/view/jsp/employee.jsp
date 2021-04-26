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

        <div class="container mt-sm-5 " style="width: 70%;">
        		<div class="jumbotron jumbotron-liquid pt-3" style="height: 684px;">
        			<form id="login_form" action="/AlcoholObserver/Controller" method="post">
        				<input type="hidden" name="command" value="updateEmployee" />
        				<div class="form-group">
        					<label for="login" style="color: #083A63;"><fmt:message key="login.login" /></label> <input type="text"
        						name="loginUser" class="form-control" id="exampleInputEmail1"
        						aria-describedby="emailHelp" pattern="\b\w+\b" value="${sessionScope.userEmployeeNow.getLogin()}"/>
        				</div>

        				<div class="form-group">
        					<label for="name" style="color: #083A63;"><fmt:message key="user.name" /></label> <input type="text"
        						name="nameUser" class="form-control" id="exampleInputEmail1"
        						aria-describedby="nameHelp" pattern="\b\w+\b" value="${sessionScope.userEmployeeNow.getName()}"/>
        				</div>
        				<div class="form-group">
        					<label for="surname" style="color: #083A63;"><fmt:message key="user.surname" /></label>
        					<input type="text"
        						name="surnameUser" class="form-control" id="exampleInputEmail1"
        						aria-describedby="surnameHelp" value="${sessionScope.userEmployeeNow.getSurname()}"/>
        				</div>
        				 <div class="form-group">
        				 <label for="email" style="color: #083A63;"><fmt:message key="user.email" /></label>
                                    <input type="email" id = "email" name = "emailUser" class="form-control" placeholder="Email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" value="${sessionScope.employeeRightNow.getEmail()}">

                        </div>
                        <div class="form-group">
        					<label for="surname" style="color: #083A63;"><fmt:message key="driver.phone" /></label>
        					<input type="text"
        						name="phone" class="form-control" pattern="^[0-9\-\+]{9,15}$" required="required"
        						aria-describedby="surnameHelp" value="${sessionScope.employeeRightNow.getPhone()}"/>
        				</div>
        				<div class="form-group">
                            <label for="surname" style="color: #083A63;"><fmt:message key="user.address" /></label>
                            <input type="text"
                                name="address" class="form-control" required="required"
                                aria-describedby="surnameHelp" value="${sessionScope.employeeRightNow.getAddress()}"/>
                        </div>
                        <div class="form-group">
                            <label for="surname" style="color: #083A63;"><fmt:message key="user.countOfViolation" /></label>
                            <input type="text"
                                name="address" class="form-control"
                                aria-describedby="surnameHelp" value="${sessionScope.employeeRightNow.getCountOfViolation()}" readonly/>
                        </div>
                        <div class="form-group">
        				<button type="submit" class="btn btn-outline-success" data-toggle="tooltip bg-dark" data-placement="bottom"	title="Registration new account">Update</button>
        			    <a href="/AlcoholObserver/Controller?command=deleteEmployee&employeeId=${sessionScope.employeeRightNow.getUserId()}" class="btn btn-outline-warning " type="button">Delete</a>
        			    <a href="/AlcoholObserver/Controller?command=resetViolation&employeeId=${sessionScope.employeeRightNow.getUserId()}" class="btn btn-outline-info " type="button">Reset counters of violations </a>
        			    </div>
        			</form>
        		</div>
        	</div>
</div>
</div>
<div class="row"><hr></div>
<div class="row">
    <div class="col-lg-9">
    <div class="container mt-sm-5 " style="width: 90%;">
    <div class="jumbotron jumbotron-liquid pt-3" style="height: 100px;" >
      <h1 class="display-4">Verifications</h1>
      </div>
      </div>
    <table class="table table-bordered table-dark table-striped ">
                <thead class="thead-light">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Check</th>
                  <th scope="col">Date</th>
                  <th scope="col">Description</th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="verification" items="${sessionScope.verificationsForEmployee}">
                    <tr>
                      <th scope="row">${verification.getId()}</th>
                      <td>${verification.isCheck()}</td>
                      <td>${verification.getDate()}</td>
                      <td>${verification.getDescription()}</td>
                    </tr>
                    </c:forEach>
                </tbody>

            </table>
          </div>
          </div>
          <div class="row"><hr></div>
</div>
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>