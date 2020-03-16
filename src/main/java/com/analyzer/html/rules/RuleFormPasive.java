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
 * Regla formActive: No se permite el uso de la forma pasiva
 * 
 * @author Raúl
 *
 */
public class RuleFormPasive extends CommonRules {

	private static Logger logger = Logger.getLogger(RuleFormPasive.class);
	private static final String DEFINITIONRULE = "rules.RuleFormPasive";

	/**
	 * Validación de la regla
	 * 
	 * @param fSlide con las características de la diapositiva
	 * @return Regla tras la evaluación
	 * @throws IOException
	 */
	public Rule validateFormPasive(Slide fSlide) throws IOException {

		logger.info("Consulta regla forma pasiva...");

		CreateConnectionAPIPaula capi=new CreateConnectionAPIPaula();
		List<Sentence> ListSentences = fSlide.getListSentences();
		for(int i=0; i<ListSentences.size();i++) {
			if(!ListSentences.get(i).getContent().endsWith("."))
				ListSentences.get(i).setContent(ListSentences.get(i).getContent().concat("."));
			System.out.println("frase: " +ListSentences.get(i).getContent());
		}
		String sentences="";//Aqui se van a meter las frases como String ya que para la API hay que pasar así el parámetro
		for(int i=0; i<ListSentences.size();i++) {
			sentences=sentences+ListSentences.get(i).getContent();
		}
		

		//Una vez que tenemos las frases en el String llamamos a la API
		System.out.println("frases: "+sentences);
		String resultado = capi.connectRule20(sentences);
		System.out.println("res api: "+resultado);

		if(resultado.equalsIgnoreCase("Se ha producido un error en el proceso de validación")) {
			logger.error("La regla no está definida o no está activada en el fichero properties");
			return null;
		}else {
			//El resultado de la API es un objeto de formato Rule
			Gson g = new Gson();
			Rule reglaADevolver = g.fromJson(resultado, Rule.class);
			System.out.println("devolver: "+reglaADevolver);
			reglaADevolver.setId(20);
			return reglaADevolver;
		}

	}
}


