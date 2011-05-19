package com.ui.custom.components;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Клас за новия таг <t:selectOneRow>
 * 
 */
public class SelectOneRow extends UIInput {

	/**
	 * тип на компонента
	 */
	public static final String COMPONENT_FAMILY = "com.ui.custom.components.SelectOneRow";

	/**
	 * тип на компонента
	 */
	public static final String COMPONENT_TYPE = "com.ui.custom.components.SelectOneRow";

	private String groupName;

	/**
	 * selectRow
	 */
	public SelectOneRow() {
		setRendererType(null);

	}

	/**
	 * @see javax.faces.component.UIInput#decode(javax.faces.context.FacesContext)
	 */
	@Override
	public void decode(FacesContext context) {

		if (!isRendered()) {
			return;
		}

		@SuppressWarnings("rawtypes")
		Map requestMap = context.getExternalContext().getRequestParameterMap();
		String postedValue;

		if (requestMap.containsKey(getGroupName())) {
			postedValue = (String) requestMap.get(getGroupName());
			String clientId = getClientId(context);
			if (clientId.equals(postedValue)) {

				String[] postedValueArray = postedValue.split(":");
				String rowIndex = postedValueArray[postedValueArray.length - 2];

				Integer newValue = Integer.valueOf(rowIndex);
				// the value to go in conversion&validation
				setSubmittedValue(newValue);
				setValid(true);
			}
		}
	}

	/**
	 * @see javax.faces.component.UIComponentBase#encodeBegin(javax.faces.context.FacesContext)
	 */
	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		if (!isRendered()) {
			return;
		}

		ResponseWriter writer = context.getResponseWriter();

		writer.write("<input class=\"selectOneRadio\" type=\"radio\" name=\"");
		writer.write(getGroupName());
		writer.write("\"");

		writer.write(" id=\"");
		String clientId = getClientId(context);
		writer.write(clientId);
		writer.write("\"");

		writer.write(" value=\"");
		writer.write(clientId);
		writer.write("\"");

		if (isRowSelected(this)) {
			writer.write(" checked");
		}

		writer.write(" \\>");

	}

	/**
	 * @see javax.faces.component.UIInput#getFamily()
	 */
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * @return groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * @see javax.faces.component.UIInput#restoreState(javax.faces.context.FacesContext,
	 *      java.lang.Object)
	 */
	@Override
	public void restoreState(FacesContext context, Object state) {

		Object[] values = (Object[]) state;
		super.restoreState(context, values[0]);
		this.groupName = (String) values[1];

	}

	/**
	 * @see javax.faces.component.UIInput#saveState(javax.faces.context.FacesContext)
	 */
	@Override
	public Object saveState(FacesContext context) {
		Object[] values = new Object[2];
		values[0] = super.saveState(context);
		values[1] = this.groupName;
		return values;
	}

	/**
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param uicomponent
	 * @return
	 */
	protected UIData findUIData(UIComponent uicomponent) {
		if (uicomponent == null) {
			return null;
		}
		if (uicomponent instanceof UIData) {
			return (UIData) uicomponent;
		}
		return findUIData(uicomponent.getParent());
	}

	private int getCurrentRowIndex() {
		UIData uidata = findUIData(this);
		if (uidata == null) {
			return -1;
		}
		return uidata.getRowIndex();
	}

	private boolean isRowSelected(UIComponent component) {
		UIInput input = (UIInput) component;
		Object value = input.getValue();

		int currentRowIndex = getCurrentRowIndex();

		return value != null && currentRowIndex == ((Integer) value).intValue();

	}

}
