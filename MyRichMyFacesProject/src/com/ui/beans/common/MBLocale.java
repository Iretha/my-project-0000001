package com.ui.beans.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class MBLocale {

	List<Locale> supportedLocales;

	private String selectedLocaleAbbr;

	public MBLocale() {
		super();
		initLocales();
	}

	private void initLocales() {
		Application app = FacesContext.getCurrentInstance().getApplication();
		Locale defaultLocale = app.getDefaultLocale();
		FacesContext.getCurrentInstance().getViewRoot().setLocale(defaultLocale); 
		this.selectedLocaleAbbr = defaultLocale.getISO3Language();
		this.supportedLocales = new ArrayList<Locale>();
		Iterator<Locale> supportedLocalesIt = app.getSupportedLocales();
		while (supportedLocalesIt.hasNext()) {
			this.supportedLocales.add(supportedLocalesIt.next());
		}
	}

	public void changeLocale() {
		for (Locale curr : this.supportedLocales) {
			if (curr.getISO3Language().equalsIgnoreCase(this.selectedLocaleAbbr)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale(curr);
				break;
			}
		}
	}

	public String getSelectedLocaleAbbr() {
		return selectedLocaleAbbr;
	}

	public void setSelectedLocaleAbbr(String selectedLocaleAbbr) {
		this.selectedLocaleAbbr = selectedLocaleAbbr;
	}

}
