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

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author After reading from
 *         http://codereview.stackexchange.com/questions/63520/executing-a-task-
 *         at-a-particular-time-in-the-morning-using-scheduledexecutorserv?
 *         newreg=2d8b0ee462f045bf96cc71927f49a7cb
 *
 */
public class MyDailyTask implements Runnable {

	private final ScheduledExecutorService service;
	private final Runnable task;
	private final int hour;
	private final int min;
	private final int sec;

	private final AtomicBoolean active = new AtomicBoolean(false);
	private final AtomicBoolean scheduled = new AtomicBoolean(false);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// since we are running, we are no longer scheduled...
		// There are two possible states scheduled or running...
		scheduled.set(false);

		// we may have been disabled after we were enabled ...
		// you can't cancel the schedule, but you can ignore the task...
		if (!active.get()) {
			return;
		}

		// we were active, and we run the task, and force the reschedule.
		try {
			task.run();
		} finally {
			reSchedule();
		}

	}

	public void enable() {
		if (!active.getAndSet(true)) {
			// was not enabled:
			reSchedule();
		}
	}

	public void disable() {
		active.getAndSet(false);
	}

	/**
	 * 
	 * @param service
	 * @param task
	 * @param hour
	 */
	public MyDailyTask(final ScheduledExecutorService service, final Runnable task, final int hour) {
		this(service, task, hour, 0, 0);
	}

	public MyDailyTask(final ScheduledExecutorService service, final Runnable task, final int hour, final int min,
			final int sec) {
		this.service = service;
		this.task = task;
		this.hour = hour;
		this.min = min;
		this.sec = sec;

	}

	private void reSchedule() {
		if (!scheduled.getAndSet(true)) {
			Calendar calendar = Calendar.getInstance();
			long now = calendar.getTimeInMillis();
			calendar.set(Calendar.HOUR, hour);
			calendar.set(Calendar.MINUTE, min);
			calendar.set(Calendar.SECOND, sec);
			calendar.set(Calendar.MILLISECOND, 0);
			while (calendar.getTimeInMillis() < now) {
				// move forward 1 day
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date date = new Date();
				date.setTime(calendar.getTimeInMillis());
				System.out.println("Rescheduled at :" + date);
			}

			// schedule for the next run
			service.schedule(this, calendar.getTimeInMillis() - now, TimeUnit.MILLISECONDS);
		}

	}

}
