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