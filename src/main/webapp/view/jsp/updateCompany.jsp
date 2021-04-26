<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<header>
        <div class="navbar navbar-expand-lg navbar-dark bg-dark ">
          <div class="container">
            <a class="navbar-brand" href="#">AlcoholObserver</a>
          </div>
        </div>
      </header>
<div class="container-fluid">
<div class="row"><hr></div>
<div class="row">
<div class="col-lg-6">
<div class="login-form">
<form id="login_form" action="/AlcoholObserver/Controller" method="post">
<input type="hidden" name="command" value="updateCompany" />
<label><h2>Company</h2></label>
<div class="container mt-lg-6 " style="width: 100%;">
		<div class="jumbotron jumbotron-liquid pt-3" style="height: 200px;">
<div class="form-group">
    <label for="exampleFormControlInput1">Name</label>
       <input class="form-control" type="text" placeholder="${sessionScope.companyUpdate.getName()}" name="name" pattern="\b\w+\b" required="required">
  </div>

  <div class="form-group">
      <label for="exampleFormControlInput1">Description</label>
     <input class="form-control" type="text" placeholder="${sessionScope.companyUpdate.getDescription()}" name="descriptionCompany" required="required">
    </div>
    </div>
    </div>

<div class="form-group">
   <button type="submit" class="btn btn-success mb-2"><fmt:message key="button.confirm" /></button>
   <button type="button" class="btn btn-primary mb-2" onclick="javascript:history.back()"><fmt:message key="button.back" /></button>
</div>
</form>
</div>
</div>
</div>
<div class="row"><hr></div>
</div>
<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>