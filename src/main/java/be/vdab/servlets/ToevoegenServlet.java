package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.Artikelgroep;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;
import be.vdab.util.StringUtils;

@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	private final transient ArtikelService artikelService = new ArtikelService();
	private final transient ArtikelgroepService artikelgroepService = new ArtikelgroepService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelgroepService.findall());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if(!Artikel.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		String aankoopprijsString = request.getParameter("aankoopprijs");
		BigDecimal aankoopprijs = null;
		if(StringUtils.isBigDecimal(aankoopprijsString)) {
			aankoopprijs = new BigDecimal(aankoopprijsString);
			if(!Artikel.isAankoopprijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "tik een getal groter dan 0.01");
			}
		} else {
			fouten.put("aankoopprijs", "tik een getal groter dan 0.01");
		}
		String verkoopprijsString = request.getParameter("verkoopprijs");
		BigDecimal verkoopprijs = null;
		if(StringUtils.isBigDecimal(verkoopprijsString)) {
			verkoopprijs = new BigDecimal(verkoopprijsString);
			if(!Artikel.isVerkoopprijsValid(verkoopprijs, aankoopprijs)) {
				fouten.put("verkoopprijs", "tik een getal groter of gelijk aan aankoopprijs");
			}
		} else {
			fouten.put("verkoopprijs", "tik een cijfer");
		}
		String soort = request.getParameter("soort");
		int houdbaarheid = 0;
		int garantie = 0;
		if(soort.equals("Food")) {
			String houdbaarheidString = request.getParameter("houdbaarheid");
			if(StringUtils.isInt(houdbaarheidString)) {
				houdbaarheid = Integer.parseInt(houdbaarheidString);
				if(!FoodArtikel.isHoudbaarheidValid(houdbaarheid)) {
					fouten.put("houdbaarheid", "tik een cijfer groter of gelijk aan 1");
				}
			} else {
				fouten.put("houdbaarheid", "tik een cijfer groter of gelijk aan 1");
			}
		} else if(soort.equals("Non-Food")) {
			String garantieString = request.getParameter("garantie");
			if(StringUtils.isInt(garantieString)) {
				garantie = Integer.parseInt(garantieString);
				if(!NonFoodArtikel.isGarantieValid(garantie)) {
					fouten.put("garantie", "tik een cijfer groter of gelijk aan 1");
				}
			} else {
				fouten.put("garantie", "tik een cijfer groter of gelijk aan 1");
			}
		} else {
			fouten.put("soort", "Ongeldige keuze soort");
		}
		String artikelgroepId = request.getParameter("artikelgroepid");
		if(artikelgroepId == null) {
			fouten.put("artikelgroepid", "verplicht");
		}
		if(fouten.isEmpty()) {
			Artikel artikel;
			Artikelgroep artikelgroep
				= artikelgroepService.read(Long.parseLong(artikelgroepId)).get();
			if(soort.equals("Food")) {
				artikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid, artikelgroep);
			} else {
				artikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, garantie, artikelgroep);
			}
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), artikel.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
