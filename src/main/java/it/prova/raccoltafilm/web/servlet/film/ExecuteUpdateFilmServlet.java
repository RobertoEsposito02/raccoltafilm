package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.FilmService;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteUpdateFilmServlet")
public class ExecuteUpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FilmService filmService;
	private RegistaService registaService;
	
    public ExecuteUpdateFilmServlet() {
        this.filmService = MyServiceFactory.getFilmServiceInstance();
        this.registaService = MyServiceFactory.getRegistaServiceInstance();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String registaIdParam = request.getParameter("regista.id");
		String idFilm = request.getParameter("idFilm");
		String idRegista = request.getParameter("regista.id");
		
		Film filmInstance = UtilityForm.createFilmFromParams(titoloParam, genereParam, minutiDurataParam,
				dataPubblicazioneParam, registaIdParam);
		
		System.out.println(idFilm + " " + idRegista);
		
		filmInstance.setId(Long.parseLong(idFilm));
		try {
			filmInstance.setRegista(registaService.caricaSingoloElemento(Long.parseLong(idRegista)));
		} catch (Exception e1) {
			e1.printStackTrace();
			request.setAttribute("update_film_attr", filmInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}
		
		if(!UtilityForm.validateFilmBean(filmInstance)) {
			request.setAttribute("update_film_attr", filmInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}
		
		try {
			filmService.aggiorna(filmInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
