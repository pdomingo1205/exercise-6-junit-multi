package com.horse;

import java.util.*;

public class Horse{


	private ArrayList<Integer> distBreakdown = new ArrayList<Integer>();
	private String horseName;
	private String warCry;
	private boolean healthy;
	private Integer speed;
	private Integer distanceTravelled = 0;
	private Integer distRemain;


	public Horse(){

	}

	public ArrayList<Integer> getDistBreakDown(){
		return distBreakdown;
	}

	public String getName(){
		return horseName;
	}

	public String getWarCry(){
		return warCry;
	}

	public boolean isHealthy(){
		return healthy;
	}

	public Integer getSpeed(){
		return speed;
	}

	public Integer getDistRemain(){
		return distRemain;
	}

	public Integer getDistanceTravelled(){
		return distanceTravelled;
	}

	public void addDistBreakDown(Integer currSpeed){
		distBreakdown.add(currSpeed);
	}

	public void setName(String name){
		horseName = name;
	}

	public void setSpeed(Integer newSpeed){
		speed = newSpeed;
	}

	public void setDistanceTravelled(Integer newDist){
		distanceTravelled = newDist;
	}

	public void setDistRemain(Integer newRemain){
		distRemain = newRemain;
	}
	public void setHealth(boolean newHealth)
	{
		healthy = newHealth;
	}

	public void setWarCry(String newCry){
		warCry = newCry;
	}



}
