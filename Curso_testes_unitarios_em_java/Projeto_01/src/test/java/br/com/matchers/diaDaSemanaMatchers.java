package br.com.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.com.Utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class diaDaSemanaMatchers extends TypeSafeMatcher<Date> {
	private Integer DiaDaSemana;
	
	public diaDaSemanaMatchers(Integer DiaDaSemana) {
		this.DiaDaSemana = DiaDaSemana;
		
	}
	
	public void describeTo(Description description) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.DAY_OF_WEEK, DiaDaSemana);
		String dataEstensao = calendario.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		description.appendText(dataEstensao);
	}

	@Override
	protected boolean matchesSafely(Date DiaEmQuestao) {
		// TODO Auto-generated method stub
		return DataUtils.verificarDiaSemana(DiaEmQuestao, DiaDaSemana);
	}
	

}
