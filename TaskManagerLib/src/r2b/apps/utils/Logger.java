/*
 * Logger
 * 
 * 0.1
 * 
 * 2014/10/18
 * 
 * (The MIT License)
 * 
 * Copyright (c) R2B Apps <r2b.apps@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * 'Software'), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

package r2b.apps.utils;

import android.util.Log;

/**
 * Android log wrapper. Useful to add several implementations about log
 * management like send to a service, print in a file,...
 */
public final class Logger {

	/**
	 * Send a INFO log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static final void i(String tag, String msg) {
		if (Cons.SHOW_LOGS) {
			Log.i(tag, msg);
		}
	}

	/**
	 * Send a INFO log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static final void e(String tag, String msg) {
		if (Cons.SHOW_LOGS) {
			Log.e(tag, msg);
		}
	}

	/**
	 * Send a ERROR log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log.
	 */
	public static final void e(String tag, String msg, Throwable tr) {
		if (Cons.SHOW_LOGS) {
			Log.e(tag, msg, tr);
		}
	}
	
	/**
	 * Send a INFO log message for performance notice.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param method
	 *            Used to identify the method of a log performance message.
	 * @param time
	 *            The performance time in milliseconds you would like logged.
	 */
	public static final void performance(String tag, String method, long time) {
		if (Cons.SHOW_LOGS) {
			Log.i("Performance:" + tag, method + ": " + String.valueOf(time) + " ms");
		}
	}	

}
