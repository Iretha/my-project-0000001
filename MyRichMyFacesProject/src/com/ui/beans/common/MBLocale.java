package com.ui.beans.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.ajax4jsf.component.UIActionParameter;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;

import com.ui.helpers.UIHelper;
import com.ui.utils.ManagedBeans;

public class MBLocale {

	List<Locale> supportedLocales;

	private String selectedLocaleAbbr;

	private HtmlPanelGrid localeGrid;

	public MBLocale() {
		super();
		initLocales();
		initLocaleGrid();
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

	public void initLocaleGrid() {
		UIHelper helper = new UIHelper();
		this.localeGrid = helper.genHPanelGrid("locale");
		HtmlAjaxCommandLink currLocaleLink = null;
		MethodExpression mExpr = null;
		UIActionParameter actionParam = null;
		String isoLngAbbrev = null;
		String displayName = null;
		if (!this.supportedLocales.isEmpty()) {
			this.localeGrid.setColumns(this.supportedLocales.size());
			mExpr = helper.getMethodExpression("changeLocale", getCurrentBean());
			for (Locale curr : this.supportedLocales) {
				isoLngAbbrev = curr.getISO3Language();
				displayName = curr.getDisplayLanguage();
				currLocaleLink = helper.getA4JCommandLInk(isoLngAbbrev, isoLngAbbrev, displayName,
						this.localeGrid, mExpr, null, null, null, "outerPanel");
				actionParam = helper.getUIActionParameter(isoLngAbbrev, isoLngAbbrev, isoLngAbbrev,
						"selectedLocaleAbbr", getCurrentBean(), currLocaleLink);
				currLocaleLink.getChildren().add(actionParam);
				this.localeGrid.getChildren().add(currLocaleLink);
			}
		}
	}

	public ManagedBeans getCurrentBean() {
		return ManagedBeans.locale;
	}

	public HtmlPanelGrid getLocaleGrid() {
		return localeGrid;
	}

	public void setLocaleGrid(HtmlPanelGrid localeGrid) {
		this.localeGrid = localeGrid;
	}

}
