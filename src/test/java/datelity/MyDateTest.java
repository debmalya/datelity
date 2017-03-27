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
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * @author debmalyajash
 *
 */
public class MyDateTest {

	/**
	 * Test method for {@link datelity.MyDate#ageToday(int, java.time.Month, int)}.
	 */
	@Test
	public void testAgeToday() {
		MyDate myDate = new MyDate();
		String ageNow = myDate.ageToday(2016, Month.JUNE, 26);
		System.out.println(ageNow);
		
		LocalDate date = LocalDate.now();
		String text = date.format(MyDate.dateFormatter);
		System.out.println(text);
		
		LocalDateTime dateTime = LocalDateTime.now();
		text = dateTime.format(MyDate.dateTimeFomratter);
		System.out.println(text);
		text = dateTime.format(MyDate.dateFormatter);
		System.out.println(text);
		
//		dateTimeFomratter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
//		text = dateTime.format(dateTimeFomratter);
//		System.out.println(text);
		
		MyDate.dateTimeFomratter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		text = dateTime.format(MyDate.dateTimeFomratter);
		System.out.println(text);
		
		MyDate.dateTimeFomratter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		text = dateTime.format(MyDate.dateTimeFomratter);
		System.out.println(text);
		
		MyDate.dateTimeFomratter = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss");
		text = dateTime.format(MyDate.dateTimeFomratter);
		System.out.println(text);
		
		MyDate.dateTimeFomratter = DateTimeFormatter.ofPattern("ddMMMyyyy HH:mm:ss");
		text = dateTime.format(MyDate.dateTimeFomratter);
		System.out.println(text);
		
		LocalDate today = LocalDate.now();
		LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
	}

}
