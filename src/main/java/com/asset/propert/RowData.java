package com.asset.propert;

import java.util.Iterator;
import java.util.List;

import com.asset.property.RoomInfoProperty;
import com.voucher.manage.daoModel.RoomInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RowData<T> {
	ObservableList observableList;
	Object objects;
	Class<?> className;
 	
	public RowData(List<Object> objects,Class<?> className) {
		// TODO Auto-generated constructor stub
		observableList=FXCollections.observableArrayList();
		this.objects=objects;
		this.className=className;
	}
	
	public T get() {
		Iterator iterator=((List) objects).iterator();
		while (iterator.hasNext()) {
			observableList.add(new RowProperty<>(iterator.next(), className).get());
		}
		
		return (T) observableList;
	}
	
}
