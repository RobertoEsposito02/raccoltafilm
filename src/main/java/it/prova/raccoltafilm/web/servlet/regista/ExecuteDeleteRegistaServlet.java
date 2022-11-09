package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.exceptions.ElementNotFoundException;
import it.prova.raccoltafilm.exceptions.RegistaConFilmAssociatiException;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

@WebServlet("/admin/ExecuteDeleteRegistaServlet")
public class ExecuteDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RegistaService registaService;
	
    public ExecuteDeleteRegistaServlet() {
        this.registaService = MyServiceFactory.getRegistaServiceInstance();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRegista = request.getParameter("idRegista");
		
		if (!NumberUtils.isCreatable(idRegista)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		try {
			Regista regista = registaService.caricaSingoloElementoConFilms(Long.parseLong(idRegista));
			if(regista.getFilms().isEmpty())
				registaService.rimuovi(Long.parseLong(idRegista));
			
		} catch (RegistaConFilmAssociatiException e) {
			request.setAttribute("errorMessage", "Attenzio..ne si vuole eliminare un regista con dei film associati.");
			request.getRequestDispatcher("/ExecuteListRegistaServlet?operationResult=NOT_FOUND").forward(request, response);
			return;		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}
}
