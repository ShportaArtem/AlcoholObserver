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
            <c:when test="${sessionScope.verificationForEmployee != null }">
<div class="row">
    <div class="col-lg">
    <div class="container mt-sm-5 " style="width: 90%;">
    <div class="jumbotron jumbotron-liquid pt-3" style="height: 250px;" >
        <h1 class="display-4 text-danger font-weight-bold">WARNING</h1>
        <p class="lead">Write your explanation about failed verification.</p>
        <form id="login_form" action="/AlcoholObserver/Controller" method="post">
            <input type="hidden" name="command" value="insertDescriptionForVerification"/>
            <div class="form-group">
                <input class="form-control" type="text" placeholder="Explanation" name="descriptionForVerification" required="required">
            </div>
            <div class="form-group">
               <button type="submit" class="btn btn-success mb-2"><fmt:message key="button.confirm" /></button>
            </div>
        </form>
    </div>
      </div>
          </div>
          </div>
<div class="row"><hr></div>
</c:when>
</c:choose>
<div class="row">
    <div class="col-lg">

        <div class="container mt-sm-5 " style="width: 70%;">
        		<div class="jumbotron jumbotron-liquid pt-3" style="height: 684px;">
        		<h1 class="display-4">My Profile</h1>
                    <p><div class="alert alert-light"><h5>Login: ${sessionScope.user.getLogin()}</h5></div></p>

                    <p><div class="alert alert-light"><h5>Name: ${sessionScope.user.getName()}</h5></div></p>

                    <p><div class="alert alert-light"><h5>SurName: ${sessionScope.user.getSurname()}</h5></div></p>

                    <p><div class="alert alert-light"><h5>Email: ${sessionScope.employeeRightNow.getEmail()}</h5></div></p>
                    <p><div class="alert alert-light"><h5>Phone: ${sessionScope.employeeRightNow.getPhone()}</h5></div></p>
                    <p><div class="alert alert-light"><h5>Address: ${sessionScope.employeeRightNow.getAddress()}</h5></div></p>
                    <p><div class="alert alert-light"><h5>Count Of Violation: ${sessionScope.employeeRightNow.getCountOfViolation()}</h5></div></p>
                    <p><div class="alert alert-light"><h5>Fine: ${sessionScope.employeeRightNow.isFine()}</h5></div></p>
        		</div>
        	</div>
</div>
</div>
<div class="row"><hr></div>
</div>
	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>

</html>