<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head2.jspf"%>
<body class="bg-secondary">

	<%@ include file="/view/jspf/header3.jspf"%>
	<div class="container mt-sm-5 " style="width: 30%;">
		<div class="jumbotron jumbotron-liquid pt-3" style="height: 854px;">
			<form id="login_form" action="/AlcoholObserver/Controller" method="post">
				<input type="hidden" name="command" value="registerEmployee" />
				<div class="form-group">
					<label for="login" style="color: #083A63;">Login</label> <input type="text"
						name="loginUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" pattern="\b\w+\b"/> <small id="loginHelp"
						class="form-text text-muted" style="color: #083A63;">Enter your login.</small>
				</div>
				<div class="form-group">
                    <label for="exampleInputPassword1" style="color: #083A63;">Password</label> <input
                        type="password" name="passwordUser" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" class="form-control"
                        id="exampleInputPassword1" /> <small id="PasswordHelp"
                        class="form-text text-muted" style="color: #083A63;">Password must contain eight characters, at least one letter and one number.</small>
                </div>
				<div class="form-group">
					<label for="name" style="color: #083A63;">Name</label> <input type="text"
						name="nameUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="nameHelp" pattern="\b\w+\b"/> <small id="nameHelp"
						class="form-text text-muted" style="color: #083A63;">Enter your name.</small>
				</div>
				<div class="form-group">
					<label for="surname" style="color: #083A63;">SurName</label>
					<input type="text"
						name="surnameUser" class="form-control" id="exampleInputEmail1"
						aria-describedby="surnameHelp" /> <small id="surnameHelp"
						class="form-text text-muted" style="color: #083A63;">Enter your surname.</small>
				</div>
				 <div class="form-group">
				 <label for="email" style="color: #083A63;">Email</label>
                            <input type="email" id = "email" name = "emailUser" class="form-control" placeholder="Email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                     <small id="surnameHelp"
                    						class="form-text text-muted" style="color: #083A63;">Enter your email.</small>
                </div>
                <div class="form-group">
					<label for="surname" style="color: #083A63;">Phone</label>
					<input type="text"
						name="phone" class="form-control" pattern="^[0-9\-\+]{9,15}$" required="required"
						aria-describedby="surnameHelp" /> <small id="surnameHelp"
						class="form-text text-muted" style="color: #083A63;">Enter your phone.</small>
				</div>
				<div class="form-group">
                    <label for="surname" style="color: #083A63;">Address</label>
                    <input type="text"
                        name="address" class="form-control" required="required"
                        aria-describedby="surnameHelp" /> <small id="surnameHelp"
                        class="form-text text-muted" style="color: #083A63;">Enter your address.</small>
                </div>
				<button type="submit" class="btn btn-outline-success"
					data-toggle="tooltip bg-dark" data-placement="bottom"
					title="Registration new account" style="color: #F5EFE5; background-color: #27A8CF; border-color: #27A8CF;">Register</button>
			</form>
		</div>
	</div>

	<%@ include file="/view/jspf/footer.jspf"%>
	<%@ include file="/view/jspf/bodyScripts.jspf"%>

</body>

</html>