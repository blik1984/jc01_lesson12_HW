package com.edu.training.js.calcSimpleNumber;

import java.util.Scanner;

/**
 * Описание: Напишите программу на языке Java, которая определяет, является ли
 * заданное число простым. Если число простое, программа должна найти и вывести
 * сумму всех простых чисел, меньших заданного числа.
 */

public class CalculatorSimpleNumbers {

	public static void main(String[] args) {

		int number;
		long summ = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Введите число >>");

		while (!sc.hasNextInt()) {
			System.out.print("Ввод неверный. Повторите ввод >>");
			sc.nextLine();
		}

		number = sc.nextInt();
				if (number < 2) {
			System.out.println("Число " + number + " не является простым");
			return;
		}
		if (checkSipleNumber(number)) {
			System.out.println("Число " + number + " является простым");
			summ = summSimpleNumberBeforeThis(number);
			System.out.println("Сумма всех простых чисел, меньших " + number + ", равна " + summ + ".");
		} else {
			System.out.println("Число " + number + " не является простым");
		}

	}

	public static boolean checkSipleNumber(int number) {
		int area = (int) Math.sqrt(number);
		for (int i = 2; i <= area; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * используем решето Эратосфена для нахождения всех простых чисел в диапазоне от
	 * 2 до заданного. Работает для всех чисел входящих в int. Необходимо
	 * принудительно увеличивать объём оперативной памяти для heap. На объёме 4 ГБ
	 * для виртуальной машины и 2 ГБ на запуск - всё отрабатывает за несколько минут
	 * для максимального int. Сверялся(нескольконебольших кусочков) по списку
	 * простых чисел здесь https://all-num.com/ru/prime/0-999999/0-4999.html
	 */
	public static long summSimpleNumberBeforeThis(int number) {
		long summ = 0;
		boolean[] array = new boolean[number - 2]; // сдвиг на 2 позиции нужен т.к. виртуальная машина наотрез
													// отказывается создавать массив нужного размера, а первые 2 числа к
													// простым точно не относятся. Без сдвига код выглядит сильно лучше.

		int area = (int) Math.sqrt(array.length + 2);
		for (int i = 0; i + 2 <= area; i++) {
			if (array[i] == false && checkSipleNumber(i + 2)) {
				for (int j = i * 2 + 2; j < array.length && (array.length - j - (i + 2)) > 0; j += (i + 2)) {
					if (j < array.length && j > 0) {
						array[j] = true;
					}
				}
			}
		}
//		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == false) {
//				if (count < 100) {			//используется для вывода простых чисел и сверки со списком
//					System.out.println(num);
//					count++;
//				}
				summ += i + 2;
			}
		}
		return summ;
	}
}
