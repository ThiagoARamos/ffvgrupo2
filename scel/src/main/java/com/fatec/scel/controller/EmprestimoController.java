package com.fatec.scel.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.servico.EmprestimoServico;

@Controller
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
	Logger logger = LogManager.getLogger(EmprestimoController.class);
	@Autowired
	EmprestimoServico emprestimoServico;

	@GetMapping("/registrar")
	public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
		logger.info("==============> chamada do menu para classe controller");
		ModelAndView mv = new ModelAndView("registrarEmprestimo");
		mv.addObject("emprestimo", emprestimo);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
		logger.info("=================> chamada da pagina registrar emprestimo para controller");
		String mensagem = "";
		ModelAndView modelAndView = new ModelAndView("registrarEmprestimo");
		mensagem = emprestimoServico.save(emprestimo);
		modelAndView.addObject("message", mensagem);
		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		emprestimoServico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoServico.findAll());
		return modelAndView;
	}

	@GetMapping("/consultar")
	public ModelAndView retornaFormDeConsultaTodosEmprestimos() {
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoServico.findAll());
		return modelAndView;
	}
}
