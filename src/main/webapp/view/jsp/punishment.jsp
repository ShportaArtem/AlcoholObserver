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
    <c:choose>
        <c:when test="${sessionScope.companyNow != null }">
        <div class="login-form">
        <form id="login_form" action="/AlcoholObserver/Controller" method="post">
        <input type="hidden" name="command" value="updatePunishment" />
        <label><h2>Punishment for ${sessionScope.companyNow.getName()}</h2></label>
            <div class="container mt-lg-6 " style="width: 100%;">
            		<div class="jumbotron jumbotron-liquid pt-3" style="height: 200px;">
        <div class="form-group">
              <label for="exampleFormControlInput1">The boundary value of drunken arrivals</label>
             <input class="form-control" type="number" min="1" value="${sessionScope.punishmentNow.getBorderValue()}" step="1" placeholder="Border number" name="borderValue" required="required">
            </div>

             <div class="form-group">
                  <label for="exampleFormControlInput1">Description of punishment</label>
                 <input class="form-control" type="text" value="${sessionScope.punishmentNow.getDescription()}" name="descriptionPunishment" required="required">
                </div>
          </div></div>
        <div class="form-group">
           <button type="submit" class="btn btn-success mb-2"><fmt:message key="button.update" /></button>
        </div>
        </form>
        </c:when>
    <c:otherwise>
                 <div class="jumbotron jumbotron-fluid">
                     <div class="container-fluid">
                         <h1 class="display-1"><fmt:message key="employees.noCompanySelected"/></h1>
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