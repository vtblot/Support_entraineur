<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8" />

<%@ page import="java.util.ArrayList"%>

<%@ page import="com.ppe.jv.dao.EntraineurDAO"%>
<%@ page import="com.ppe.jv.dao.EquipeDAO"%>
<%@ page import="com.ppe.jv.dao.JoueurDAO"%>
<%@ page import="com.ppe.jv.classes.Entraineur"%>
<%@ page import="com.ppe.jv.classes.Equipe"%>
<%@ page import="com.ppe.jv.classes.Joueur"%>
<%
	Entraineur ent = (Entraineur) (session.getAttribute("user"));
	EquipeDAO dao2 = new EquipeDAO();
	JoueurDAO dao3 = new JoueurDAO();
	ArrayList<Equipe> listeEquipe = dao2.selectForTrainer(ent);
	Equipe e = dao2.select(1);
	Equipe e2 = dao2.select(2);
	
	Joueur[] poste = {new Joueur(),new Joueur(),new Joueur(),new Joueur(),new Joueur()};
%>

<title>Bienvenue <%=ent.getLogin()%>
</title>
<link type="text/css" rel="stylesheet" href="CSS/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="CSS/style.css" />


</head>




<body>

	<ul class="nav nav-tabs">
		<%
			for (int i = 0; i < listeEquipe.size(); i++) {
				if (i == 0) {
		%>
		<li id="equipe<%=listeEquipe.get(i).getId()%>" class="active"><a aria-expanded="true"
			href="#<%=listeEquipe.get(i).getCategorie()%>" data-toggle="tab"><%=listeEquipe.get(i).getCategorie()%></a></li>
		<%
			} else {
		%>
		<li id="equipe<%=listeEquipe.get(i).getId()%>" class=""><a aria-expanded="false"
			href="#<%=listeEquipe.get(i).getCategorie()%>" data-toggle="tab"><%=listeEquipe.get(i).getCategorie()%></a></li>
		<%
			}
			}
		%>
		<li class="">
			<form class="form-inline" method="POST" action="servletppe">
				<table>
					<tr>
						<td><div class="form-group">

								<input class="form-control" name="nomEquipe"
									placeholder="Nom de l'équipe" type="text"> <input
									class="form-control" name="idEntraineur" type="hidden"
									value="<%=ent.getId()%>">
							</div></td>
						<td>
							<button type="submit" class="btn btn-default">Ajouter
								une équipe</button>
						</td>
					</tr>
				</table>
			</form>
		</li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<%
			for (int i = 0; i < listeEquipe.size(); i++) {
		%>
		<div class="tab-pane fade <%if (i == 0) {%>active in<%}%>"
			id="<%=listeEquipe.get(i).getCategorie()%>">
			<section class="col-md-6">

				<a class="btn btn-default"
					href="FormJoueur.jsp?equipe=<%=listeEquipe.get(i).getId()%>">Ajouter
					un joueur</a>

				<button class="btn btn-default droite">Supprimer l'équipe</button>


				<table id="table" class="table table-striped table-hover"
					style="width: 100%;">

					<tbody>

						<%
							ArrayList<Joueur> listeJoueur = dao3.selectForTeam(listeEquipe.get(i));
								for (int j = 0; j < listeJoueur.size(); j++) {
						%>
					
						<tr id="<%=listeJoueur.get(j).getId()%>">
							<td style="width: 10%;"><%=listeJoueur.get(j).getPoste()%></td>
							<td style="width: 20%;"><%=listeJoueur.get(j).getPrenom() + " " + listeJoueur.get(j).getNom()%>
								</td>
							<td><input class="dispo" type="button"style="border: 1px solid black; background-color:
							<% if(listeJoueur.get(j).getDispo()==1){ %>#00FF00<% }
							   else {%>#FF0000 <%} %> ; border-radius:100%; height:20px; width:20px" /></td>
							<td>
							<div class="btn-toolbar">
									<div class="btn-group">
										<%
											for (int num = 1; num <= 5; num++) {
										%>

										<a href="#" class="a<%=num%> btn btn-default
										<%if (listeJoueur.get(j).getPoste() == num) 
										{
											poste[num-1] = listeJoueur.get(j);
											%> active<%
										}
										else if(listeJoueur.get(j).getDispo()==0){
											%> disabled<%
										}%>"><%=num%></a>


										<%
											}
										%>
									</div>
								</div></td>

						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</section>

			<% for(int ii=0;ii<5;ii++){ 
			 %>
			<div id="p<%=ii+1%>"><%=poste[ii].getPrenom() + " " +poste[ii].getNom()%></div>
			<%
			}%>


			<img id="basketf1" src="/SupportEntraineur/images/basketfield.png"
				alt="" />

		</div>
		<%
			}
		%>

	</div>



	<script src="JS/jquery.js"></script>
	<script src="JS/bootstrap.js"></script>
	<script src="JS/bootstrap2.js"></script>
	<script src="JS/update.js"></script>




</body>

</html>






