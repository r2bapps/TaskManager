package r2b.apps.test.taskmanager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;
import r2b.apps.lib.taskmanager.BaseAsyncTask;

public class GoogleSearchAsyncTask extends BaseAsyncTask {
	
	private long threadId;
	private Updatable listener;
	
	public interface Updatable {
		public void update(String txt);
	};
	
	public GoogleSearchAsyncTask(Updatable listener) {
		super();	
		this.listener = listener;
	}

	public final long getThreadId() {
		return threadId;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		
		threadId = Thread.currentThread().getId();
		
		InputStream in = null;
		HttpURLConnection urlConnection = null;
		
		try {
			URL url = new URL("http://www.google.es/?gfe_rd=cr&ei=ddKhU5fEG4_A8gevsoGoAQ&gws_rd=ssl#q=r2b+apps+github");
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream());
			readStream(in);						
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
			if(urlConnection != null) {
				urlConnection.disconnect();	
			}
			
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return null;
	}
	
	private void readStream(InputStream in) {
		
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = r.readLine()) != null) {
			    
				if(listener == null) {
					break;
				}
				else {
					listener.update(line);
					Log.i("GET", "Reading..." + line);
				}

			}
			
			Log.i("GET", "End read");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
