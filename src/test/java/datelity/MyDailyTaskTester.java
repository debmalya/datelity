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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author debmalyajash
 *
 */
public class MyDailyTaskTester {

	private static Date date;

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		if (args.length > 1) {
			ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
			Calendar cal = Calendar.getInstance();
			date.setTime(cal.getTimeInMillis());
			System.out.println("Time now: " +  date);
			int hour = 0;
			int minute = 0;
			int sec = 0;
			try {
				hour = Integer.parseInt(args[0]);
			} catch(NumberFormatException pe) {
				System.err.println("Mentioned hour value '" + args[0] + "' is not numeric");
			}
			try {
				minute = Integer.parseInt(args[1]);
			} catch(NumberFormatException pe) {
				System.err.println("Mentioned minute value '" + args[0] + "' is not numeric");
			}
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
	        cal.set(Calendar.MILLISECOND, 0);
	        final Object lock = new Object();
	        Runnable task = new Runnable() {

	            @Override
	            public void run() {
	            	date.setTime(Calendar.getInstance().getTimeInMillis());
	                System.out.println("Executed at  " + date);
	                synchronized(lock) {
	                    lock.notifyAll();
	                }
	            }
	        };
	        
	        MyDailyTask daily = new MyDailyTask(service, task, hour, minute, sec);
	        daily.enable();

	        System.out.println("Waiting");
	        synchronized (lock) {
	            lock.wait();
	        }
	        System.out.println("I have done. What about you?");

		} else {
			System.out.println("Usage : MyDailyTaskTester <<hour>> <<minute>>");
		}

	}

}
