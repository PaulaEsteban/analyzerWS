package com.analyzer.html.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CreateConnectionAPIPaula {
	private String  output;
	//En esta clase está hecho el código necesario para poder llamar a la api alojada en la URL 
	//https://analizadorlecturafacil.herokuapp.com/
	//En está se comprueban las reglas 20 y 21, forma pasiva y sujeto respectivamente

	public String connectRule20(String text) throws IOException {
		//Remplazamos los simbolos que dan problemas a la hora de llamar a la api, la decisión de estos símbolos es arbitraria.
		text=text.replace(" ", "%20");
		text=text.replace("á", "001a");
		text=text.replace("é", "001e");
		text=text.replace("í", "001i");
		text=text.replace("ó", "001o");
		text=text.replace("ú", "001u");
		text=text.replace("Á", "001A");
		text=text.replace("É", "001E");
		text=text.replace("Í", "001I");
		text=text.replace("Ó", "001O");
		text=text.replace("Ú", "001U");
		text=text.replace("ü", "011u");
		text=text.replace("Ü", "011U");
		text=text.replace("ñ", "001n");
		text=text.replace("Ñ", "001N");
		text=text.replace("¡", "001!");
		text=text.replace("¿", "001?");

		String api="https://analizadorlecturafacil.herokuapp.com";
		String path="/analyzer/checkRules/1";
		String readLine = null;
		String response="";
		HttpsURLConnection connection = null;
		URL url = new URL(api + path + "?texto="+text);
		System.out.println("url: "+ url);
		connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");


		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			//response = new StringBuffer();
			while ((readLine = in .readLine()) != null) {
				response=response+readLine;
			} in .close();

			//System.out.println(response.toString());
		} else {
			response=""+responseCode;
		}
		return response;
	}
	public String connectRule21(String text) throws IOException {
		//Remplazamos los simbolos que dan problemas a la hora de llamar a la api, la decisión de estos símbolos es arbitraria.
		text=text.replace(" ", "%20");
		text=text.replace("á", "001a");
		text=text.replace("é", "001e");
		text=text.replace("í", "001i");
		text=text.replace("ó", "001o");
		text=text.replace("ú", "001u");
		text=text.replace("Á", "001A");
		text=text.replace("É", "001E");
		text=text.replace("Í", "001I");
		text=text.replace("Ó", "001O");
		text=text.replace("Ú", "001U");
		text=text.replace("ü", "001u");
		text=text.replace("Ü", "001U");
		text=text.replace("ñ", "001n");
		text=text.replace("Ñ", "001N");
		text=text.replace("¡", "001!");
		text=text.replace("¿", "001?");

		String api="https://analizadorlecturafacil.herokuapp.com";
		String path="/analyzer/checkRules/2";
		String readLine = null;
		String response="";
		HttpsURLConnection connection = null;
		URL url = new URL(api + path + "?texto="+text);
		System.out.println("url: "+url);
		connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");


		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			//response = new StringBuffer();
			while ((readLine = in .readLine()) != null) {
				response=response+readLine;
			} in .close();

			//System.out.println(response.toString());
		} else {
			response=""+responseCode;
		}
		return response;
	}

//		public static void main(String[] args) throws IOException {
//			CreateConnectionAPIPaula e=new CreateConnectionAPIPaula();
//			System.out.println(e.connectRule21("¡Esto es un ejemplo!. Él fue llevado al hospital. No ha venido hoy."));
//		}
}
