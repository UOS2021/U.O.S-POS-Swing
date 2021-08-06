package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Category{
	private String name;
	private ImageIcon img;
	
	private Category parent;
	private List<Category> siblings;
	private List<Category> childrens;
	
	
	public Category(String name, ImageIcon img) {
		this.name = name;
		this.img = img;
		
		siblings = new ArrayList<Category>();
		childrens = new ArrayList<Category>();
	}
	
	public String getName() {
		return name;
	}
	
	public Category getParent() {
		return parent;
	}
	
	public List<Category> getSiblings(){
		return parent.getChildrens();
	}
	
	public List<Category> getChildrens(){
		return childrens;
	}
	
	public List<Category> getDescendants(){
		return null;
	}
	
	public void addChild(Category category) {
		childrens.add(category);
		category.parent = this;
	}
	
	public void addChildren(List<Category> categories) {
		childrens.addAll(categories);
		for(Category category : categories) {
			category.parent = this;
		}
	}
	
	public String toString() {
		return name;
	}
}