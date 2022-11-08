package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteSearchRegistaServlet")
public class ExecuteSearchRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RegistaService registaService;

	public ExecuteSearchRegistaServlet() {
		this.registaService = MyServiceFactory.getRegistaServiceInstance();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String nickNameParam = request.getParameter("nickName");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");

		Sesso sessoParsed = StringUtils.isNotBlank(sessoParam) ? Sesso.valueOf(sessoParam) : null;
		Regista example = new Regista(nomeParam, cognomeParam, nickNameParam,
				UtilityForm.parseDateArrivoFromString(dataDiNascitaParam), sessoParsed);

		try {
			request.setAttribute("registi_list_attribute", registaService.findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("regista/list.jsp").forward(request, response);
	}

}
