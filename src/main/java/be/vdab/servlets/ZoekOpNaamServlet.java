package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.ArtikelService;

@WebServlet("/artikels/zoekenopnaam.htm")
public class ZoekOpNaamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/zoekopnaam.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String naam = request.getParameter("naam");
		if (naam != null) {
			if (naam.trim().isEmpty()) {
				request.setAttribute("fouten",
						Collections.singletonMap("naam", "verplicht"));
			} else {
				request.setAttribute("artikels",
						artikelService.findByName(naam));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
