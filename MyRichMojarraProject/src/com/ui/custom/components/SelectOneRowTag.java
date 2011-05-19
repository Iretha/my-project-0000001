package com.ui.custom.components;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

/**
 * Клас за новия таг <t:selectOneRow>
 */
public class SelectOneRowTag extends ParamTag {

	private String groupName;

	// private String value;

	/**
	 * default constructor
	 */
	public SelectOneRowTag() {
		// default constructor
	}

	/**
	 * @see javax.faces.webapp.UIComponentTagBase#getComponentType()
	 */
	@Override
	public String getComponentType() {
		return SelectOneRow.COMPONENT_TYPE;
	}

	/**
	 * @return groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * @see javax.faces.webapp.UIComponentTagBase#getRendererType()
	 */
	@Override
	public String getRendererType() {
		return null;
	}

	/**
	 * @see javax.faces.webapp.UIComponentELTag#release()
	 */
	@Override
	public void release() {
		super.release();
		this.groupName = null;
	}

	/**
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @see com.acsior.ui.components.ParamTag#setProperties(javax.faces.component.UIComponent)
	 */
	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		UIInput singleInputRowSelect = (UIInput) component;
		singleInputRowSelect.getAttributes().put("groupName", this.groupName);
	}
}
