package com.asset.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class HiddenAssetsProperty {
	private IntegerProperty id;
	private StringProperty asset_guid;
	private StringProperty hidden_guid;
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public StringProperty getAsset_guid() {
		return asset_guid;
	}
	public void setAsset_guid(StringProperty asset_guid) {
		this.asset_guid = asset_guid;
	}
	public StringProperty getHidden_guid() {
		return hidden_guid;
	}
	public void setHidden_guid(StringProperty hidden_guid) {
		this.hidden_guid = hidden_guid;
	}

}
