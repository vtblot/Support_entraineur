package com.ppe.jv.servlet;

import java.io.IOException;

/*import javax.servlet.RequestDispatcher;*/
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppe.jv.classes.Entraineur;
import com.ppe.jv.dao.EntraineurDAO;
import com.ppe.jv.classes.Joueur;
import com.ppe.jv.dao.JoueurDAO;
import com.ppe.jv.classes.Equipe;
import com.ppe.jv.dao.EquipeDAO;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servletppe")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getParameter("inputLogin") != null) {
			// formulaire de connexion
			System.out.println("servletppe access");
			String email = request.getParameter("inputLogin");
			String pwd = request.getParameter("inputPassword");

			if ((email.trim().equals("")) || (pwd.trim().equals(""))) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "login/mdp vide");
				return;
			}

			EntraineurDAO aa = new EntraineurDAO();
			Entraineur a = aa.auth(email, pwd);
			System.out.println(a);

			if (a == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "login/mdp incorrect");
				System.out.println("Recommence");
				return;
			}

			session.setAttribute("user", a);
			
		} else if (request.getParameter("inputEquipe") != null) {
			// formulaire ajout joueur
			String nom = request.getParameter("inputNom");
			String prenom = request.getParameter("inputPrenom");
			String date = request.getParameter("inputDate");
			String mail = request.getParameter("inputMail");
			String equipe = request.getParameter("inputEquipe");

			JoueurDAO ab = new JoueurDAO();
			Joueur j = new Joueur();
			j.setNom(nom);
			j.setPrenom(prenom);
			j.setEmail(mail);
			j.setAnneeNaissance(date);
			j.setId_equi(Integer.parseInt(equipe));
			j.setSurclas(false);

			ab.insert(j);
		} else if(request.getParameter("nomEquipe") != null){
			//formulaire ajout equipe
			String idEnt = request.getParameter("idEntraineur");
			String nomEquipe = request.getParameter("nomEquipe");
			
			EquipeDAO abc = new EquipeDAO();
			Equipe e = new Equipe();
			e.setCategorie(nomEquipe);
			e.setId_entraineur(Integer.parseInt(idEnt));
			
			abc.insert(e);

		} 

		
		
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("poste") != null){
			//mise à jour poste joueur
			
			int id = Integer.parseInt(request.getParameter("id"));
			int poste = Integer.parseInt(request.getParameter("poste"));
			int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
			
			Joueur j = new Joueur();
			j.setId(id);
			j.setId_equi(idEquipe);
			j.setPoste(poste);
			
			System.out.println(j);
			
			JoueurDAO dao = new JoueurDAO();
			dao.updatePoste(j);			
		}
		else if(request.getParameter("dispo") != null){
			//mise à jour de la disponibilité du joueur
			
			int id = Integer.parseInt(request.getParameter("id"));
			int dispo = Integer.parseInt(request.getParameter("dispo"));
			
			Joueur j = new Joueur();
			j.setId(id);
			j.setDispo(dispo);
			j.setPoste(0);
			
			JoueurDAO dao = new JoueurDAO();
			
			System.out.println(j);
			dao.updateDispo(j);	
			dao.updatePoste(j);	
		}
		else if(request.getParameter("idEquipe") != null){
			//suppression d'une equipe
			
			int id = Integer.parseInt(request.getParameter("idEquipe"));
			
			Equipe e = new Equipe();
			e.setId(id);
			
			EquipeDAO dao = new EquipeDAO();
			
			dao.delete(e);			
		}
		
		
	}

}
