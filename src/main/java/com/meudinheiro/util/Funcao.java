package com.meudinheiro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Funcao {
	
	public static String localDateToString(LocalDate data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formato.format(data);
	}
	
	public static LocalDate strToLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return  LocalDate.parse(data, formatter);
	}
	
	public static String getMes(Integer mes) {
		Map<Integer, String> mapa = new HashMap<Integer, String>();
		mapa.put(1, "Janeiro");
		mapa.put(2, "Fevereiro");
		mapa.put(3, "Março");
		mapa.put(4, "Abril");
		mapa.put(5, "Maio");
		mapa.put(6, "Junho");
		mapa.put(7, "Julho");
		mapa.put(8, "Agosto");
		mapa.put(9, "Setembro");
		mapa.put(10, "Outubro");
		mapa.put(11, "Novembro");
		mapa.put(12, "Dezembro");
		return mapa.get(mes);
	}
}
