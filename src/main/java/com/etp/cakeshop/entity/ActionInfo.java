package com.etp.cakeshop.entity;

import org.springframework.stereotype.Component;

/**
 * @author Swapnil this class is used to store the action id
 *
 */
@Component
public class ActionInfo {

	public String actionId;

	public ActionInfo() {
	}

	public ActionInfo(String actionId) {
		this.actionId = actionId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}
