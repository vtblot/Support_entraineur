<!DOCTYPE html>
<html>
<head>
<title></title>



<script src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/puc.js"></script>
<script src="JS/bootstrap.js"></script>
<script src="JS/bootstrap2.js"></script>
<link type="text/css" rel="stylesheet" href="CSS/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="CSS/style.css" />










</head>
<body>


	<div id="filter">
		<div id="box">
			<form class="form-horizontal" method="POST" action="servletppe">
				<fieldset>
					<legend>Insérez vos identifiants</legend>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">Login</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputLogin" placeholder="Login"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-lg-2 control-label">Password</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputPassword"
								placeholder="Password" type="password">

						</div>
						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button type="reset" class="btn btn-default">Annuler</button>
								<button type="submit" class="btn btn-primary">Valider</button>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>


	<script>
		$("a").click(function() {
			$("." + $(this)[0].className.split(' ')[0]).removeClass("active");
			$(this).parent().children().removeClass("active");
			$(this).addClass("active");

			var id = $(this).closest('tr').attr('id');
			var poste = $(this)[0].className.split(' ')[0].substring(1);
		});
	</script>
</body>
</html>