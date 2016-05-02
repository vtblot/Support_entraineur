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
		<div id="box2">
			<form class="form-horizontal" method="POST" action="servletppe">
				<fieldset>
					<legend>Informations</legend>
					<div class="form-group">
						<label class="col-lg-2 control-label">Nom</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputNom" placeholder="Nom"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">Prenom</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputPrenom"
								placeholder="Prenom" type="text">

						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-2 control-label">Date de naissance</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputDate"
								placeholder="aaaa-mm-jj" type="date">

						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">E-mail</label>
						<div class="col-lg-10">
							<input class="form-control" name="inputMail"
								placeholder="ex: dupont@gmail.com" type="email">
						</div>
						<input class="form-control" name="inputEquipe" type="hidden"
							value="<%=request.getParameter("equipe") %>">

						<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
								<button class="btn btn-default" onclick="window.location='WebContent/index.jsp'">Annuler</button>
								<button type="submit" class="btn btn-primary">Valider</button>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>