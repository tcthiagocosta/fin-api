package com.meudinheiro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcao {
	
	public static String localDateToString(LocalDate data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formato.format(data);
	}
	
	public static LocalDate strToLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return  LocalDate.parse(data, formatter);
	}
}
