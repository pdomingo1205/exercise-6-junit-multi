package com.srv;

import java.util.stream.*;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalTime;
import com.horse.Horse;



public class HorseRace{

	private Stream<Horse> horseStream;
	private List<Horse> horseList;
	private static Integer field;
	HorseService horseService = new HorseService();
	ForkJoinPool forkJoinPool;


	public HorseRace(List<Horse> horseArray, Integer newField){
		horseList = horseArray.stream().filter(Horse::isHealthy).collect(Collectors.toList());
		field = newField;
	}

	public boolean canStart(){
		return (horseList.size() >= 2 ? true: false);
	}

	public void start() {
			forkJoinPool = new ForkJoinPool(horseList.size());
			startGallop(10, "Barn");
			System.out.println("\n \t -----START----- \n");
			startGallop(field, "Finish");

			horseList.stream().forEach(horse -> {
				System.out.println(horseService.printBreakDown(horse));
				}
			);
	}

	private void startGallop(Integer distance, String destination){
		try{
			horseStream = horseList.parallelStream();
			forkJoinPool.submit( () ->
				horseStream.forEach(horse -> {
				System.out.println("\n" + Thread.currentThread().getName() + " " + LocalTime.now() + "\n");
				horseService.runToGoal(horse, distance, destination);
			})).get();
		}
		catch(InterruptedException | ExecutionException ie){
			System.out.println(ie.getMessage());
		}
	}




}
