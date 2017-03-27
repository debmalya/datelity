/**
 * Copyright 2015-2016 Debmalya Jash
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package datelity;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author debmalyajash
 *
 */
public class MyDate {
	/**
	 * 
	 */
	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

	public static DateTimeFormatter dateTimeFomratter = DateTimeFormatter.ISO_DATE_TIME;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Enter birth year :");
			int year = in.nextInt();
			int month = 0;
			Month enteredMonth = Month.JANUARY;

			System.out.println("Enter birth month:");
			month = in.nextInt();
			enteredMonth = Month.of(month);

			System.out.println("Enter birth day:");
			int day = in.nextInt();
			System.out.println("Today your age is " + ageToday(year, enteredMonth, day));

		}

	}

	/**
	 * 
	 * @return
	 */
	public static String ageToday(final int year, final Month month, final int day) {
		LocalDate now = LocalDate.now();
		LocalDate birthdate = LocalDate.of(year, month, day);
		Period p = Period.between(birthdate, now);
		StringBuilder sb = new StringBuilder();
		sb.append(p.getYears());
		sb.append(" years ");
		sb.append(p.getMonths());
		sb.append(" months ");
		sb.append(p.getDays());
		sb.append(" days ");
		return sb.toString();
	}
}
