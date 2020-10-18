package br.com.unipe.aula.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.unipe.aula.web.model.Moeda;

@Controller
public class CambioController {


	@RequestMapping(value = "/cambio", method = RequestMethod.POST)
	public ModelAndView mandaForm(@ModelAttribute Moeda m) {
		ModelAndView v = new ModelAndView("formulario");
		v.addObject("mensagem", "$ " + m.getValorDolares() +
				" dólares corresponde a R$ " + m.getValorReais() + " reais");
		return v;
	}

	@RequestMapping(value = "/cambio", method = RequestMethod.GET)
	public ModelAndView exibeForm(Model model) {
		model.addAttribute("moeda", new Moeda());
		return new ModelAndView("formulario");
	}

}
