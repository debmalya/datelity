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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author debmalyajash
 *
 */
public class StoryOfFormatting {
	
	private static SimpleDateFormat sdf;

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		if (args.length > 1) {
			sdf = new SimpleDateFormat(args[0]);
			sdf.setLenient(false);
			Date parsedDate = sdf.parse(args[1]);
			System.out.println("Parsed Date :" + parsedDate);
			
		}else {
			System.err.println("Usage : StoryOfFormatting <format> <date>");
		}

	}

}
