<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<style>
    .cc-img {
        margin: 0 auto;
    }
</style>
<body class="bg-secondary">
    <header>
        <!-- Navigation -->
        <div class="navbar navbar-expand-lg navbar-dark bg-dark ">
          <div class="container">
            <a class="navbar-brand" href="#">AlcoholObserver</a>
          </div>
        </div>
      </header>
<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3" style="height: 458px;">
<div class="login-form">
    <form action="/AlcoholObserver/Controller" method="post" class>
        <input type="hidden" name="command" value="buySlot"/>
        <h2 class="text-center">Buy new slot</h2>
        <div class="form-group">
        <label>CARD NUMBER</label>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Valid Card Number" required="required" pattern="\d{16}">
                <span class="input-group-addon"><span class="fa fa-credit-card"></span></span>
            </div>
        </div>
        <div class="form-group">
        <label><span class="hidden-xs">EXPIRATION DATE</span></label>
                                            <input type="text" class="form-control" placeholder="MM\YY" required="required" pattern="\d{2}\\\d{2}">
        </div>
        <div class="form-group">
                <label>CV CODE</label>
                <input type="text" class="form-control" placeholder="CVC" required="required" pattern="\d{3}"/>
                </div>
        <div class="form-group">
            <label>CARD OWNER</label>
            <input type="text" class="form-control" placeholder="Card Owner Names" required="required" pattern="\b\w+\b"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Buy</button>
        </div>
    </form>
    </div>
    	</div>
</div>
<footer class="py-2 bg-dark fixed-bottom">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; GameStore 2021</p>
    </div>
  </footer>
</body>
</html>