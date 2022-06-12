package br.com.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.Utils.DataUtils;

public class dataDiferenteDiasMatchers extends TypeSafeMatcher<Date> {
	
	public Integer diferencaDia;
	
	public dataDiferenteDiasMatchers(Integer diferencaDia) {
		this.diferencaDia = diferencaDia;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean matchesSafely(Date Data) {
		// TODO Auto-generated method stub
		return DataUtils.isMesmaData(Data, DataUtils.obterDataComDiferencaDias(diferencaDia));
	}

	

	

}
