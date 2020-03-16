package com.analyzer.html.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.analyzer.html.provider.CreateConnectionAPIPaula;
import com.analyzer.html.vo.Rule;
import com.analyzer.html.vo.Sentence;
import com.analyzer.html.vo.Slide;
import com.google.gson.Gson;

/**
 * Regla hasSubject: Es obligatorio el uso de sujeto
 * 
 * @author Raúl
 *
 */
public class RuleHasSubject extends CommonRules {

	private static Logger logger = Logger.getLogger(RuleHasSubject.class);
	private static final String DEFINITIONRULE = "rules.RuleHasSubject";

	/**
	 * Validación de la regla
	 * 
	 * @param fSlide con las características de la diapositiva
	 * @return Regla tras la evaluación
	 * @throws IOException
	 */
	public Rule validateHasSubject(Slide fSlide) throws IOException {//Hay que devolver un Rule

		logger.info("Consulta regla tiene sujeto...");
		CreateConnectionAPIPaula capi=new CreateConnectionAPIPaula();
		List<Sentence> ListSentences = fSlide.getListSentences();
		String sentences="";//Aqui se van a meter las frases como String ya que para la API hay que pasar así el parámetro
		for(int i=0; i<ListSentences.size();i++) {
			sentences=sentences+ListSentences.get(i).getContent();
		}
		System.out.println(sentences);
		//Una vez que tenemos las frases en el String llamamos a la API
		
		String resultado = capi.connectRule21(sentences);
		System.out.println(resultado);

		if(resultado.equalsIgnoreCase("Se ha producido un error en el proceso de validación")) {
			logger.error("La regla no está definida o no está activada en el fichero properties");
			return null;
		}else {
			System.out.println("Estamos en el else");

		//El resultado de la API es un objeto de formato Rule
		Gson g = new Gson();
		Rule reglaADevolver = g.fromJson(resultado, Rule.class);
		reglaADevolver.setId(21);
		return reglaADevolver;
		}
		
	}


}
