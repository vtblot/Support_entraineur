/**
 * Recupere l'id de l'équipe courante
 */
function getIdEquipe()
{
	//on recupere le li qui est ouverte (possede la classe active)
	var li = $('.nav').find('.active');
	
	var id = li.attr('id').substring(6);
	
	return id;
}

$("a").click(function() {
	//pour tous les a (postes)
	
	//on enleve la classe active pour les colones (un poste est occupé par un seul joueur)
	$("." + $(this)[0].className.split(' ')[0]).removeClass("active");
	//on enleve la classe active pour les lignes (un joueur ne peut pas avoir plusieurs postes)
	$(this).parent().children().removeClass("active");
	//on ajoute la classe active au a sur lequel on a cliqué
	$(this).addClass("active");
	//on recupere l'id du <tr> (correspond à l'id du joueur)
	var id = $(this).closest('tr').attr('id');
	//on recupere le a qui à la classe active (correspond au poste du joueur)
	var poste = $(this)[0].className.split(' ')[0].substring(1)
	//on recupere l'id de l'équipe
	var idEquipe = getIdEquipe();

	$.ajax({
		url : 'servletppe' ,
		type : 'GET',
		dataType : 'json',

		data : {
			id : id,
			poste : poste,
			idEquipe : idEquipe
		},
		success: function(data) {},		
		error: function(data){}		
				
	});
});

$(".dispo").click(function() {
	//rond de couleur pourla disponibilité
	
	//on recupere la couleur du bouton
	var couleur = $(this).css("background-color");
	
	//on recupere l'id du <tr> (correspond à l'id du joueur)
	var id = $(this).closest('tr').attr('id');
	
	if (couleur == 'rgb(0, 255, 0)') {
		//le bouton est vert
		for (var i = 1; i < 6; i++) {
			$(this).closest('tr').find(".a"+i).addClass("disabled");
		}
		$(this).closest('tr').find(".active").removeClass("active");
		$(this).css("background-color", "#FF0000");
		
		var dispo = 0;
	} else {
		//le bouton est rouge
		for (var i = 1; i < 6; i++) {
			$(this).closest('tr').find(".a"+i).removeClass("disabled");
		}
		$(this).css("background-color", "#00FF00");
		
		var dispo = 1;
	}
	
	$.ajax({
		url : 'servletppe' ,
		type : 'GET',
		dataType : 'json',

		data : {
			id : id,
			dispo : dispo
		},
		success: function(data) {
			
		},error: function(data){
			
		}
	});

});


$(".droite").click(function() {
	// bouton de suppression d'équipe
	if(confirm("Voulez vous supprimer cette equipe?"))
	{
		//on supprime l'équipe
		
		
		var idEquipe = getIdEquipe();
		
		$.ajax({
			url : 'servletppe' ,
			type : 'GET',
			dataType : 'json',

			data : {
				idEquipe : idEquipe,
			},
			success: function(data) {
				
			},error: function(data){
				
			}
		});
		window.location.reload(true);
	}
});

