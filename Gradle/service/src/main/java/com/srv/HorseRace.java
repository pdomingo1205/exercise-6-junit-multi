package com.srv;

import java.util.stream.*;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalTime;
import com.horse.Horse;



public class HorseRace{

	private Stream<Horse> horseStream;
	private List<Horse> horseList;
	private static int field;


	public HorseRace(List<Horse> horseArray, int newField){
		horseList = horseArray.stream().filter(Horse::isHealthy).collect(Collectors.toList());
		field = newField;
	}

	public boolean canStart(){
		return (horseList.size() >= 2 ? true: false);
	}

	public void start() {
		HorseService horseService = new HorseService();

		try{

			horseStream = horseList.parallelStream();
			ForkJoinPool forkJoinPool = new ForkJoinPool(horseList.size());
			forkJoinPool.submit( () ->	horseStream.forEach(horse -> {
				horseService.runToGoal(horse, 10, "Barn");
			})).get();

			System.out.println("\n \t -----START----- \n");

			horseStream = horseList.parallelStream();
			forkJoinPool.submit( () ->
				horseStream.forEach(horse -> {
				System.out.println("\n" + Thread.currentThread().getName() + " " + LocalTime.now() + "\n");
				horseService.runToGoal(horse, field, "finish");
			})).get();

			horseList.stream().forEach(horse -> {
				horseService.printBreakDown(horse);
				}
			);


		}
		catch(InterruptedException | ExecutionException ie){
			System.out.println(ie.getMessage());
		}

	}




}
