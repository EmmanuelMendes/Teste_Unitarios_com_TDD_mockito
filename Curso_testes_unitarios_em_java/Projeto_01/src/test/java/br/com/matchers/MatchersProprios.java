package br.com.matchers;

import java.util.Calendar;
import java.util.Date;

public class MatchersProprios {
	public static diaDaSemanaMatchers caiEm(Integer DiaDaSemana) {
		return new diaDaSemanaMatchers(DiaDaSemana);
		
	}
	public static diaDaSemanaMatchers caiNumaSegunda() {
		return new diaDaSemanaMatchers(Calendar.MONDAY);
	}
	public static dataDiferenteDiasMatchers ehHojeComDiferencaDias(Integer diferencaDia) {
		return new dataDiferenteDiasMatchers(diferencaDia);
	}
	public static dataDiferenteDiasMatchers ehHoje() {
		return new dataDiferenteDiasMatchers(0);
	}


}
