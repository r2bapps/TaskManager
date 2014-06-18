package r2b.apps.test.taskmanager;

import r2b.apps.lib.taskmanager.TaskManager;
import r2b.apps.test.taskmanager.GoogleSearchAsyncTask.Updatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Runner extends FragmentActivity implements Updatable {

	private static TaskManager taskManager;
	private static GoogleSearchAsyncTask task;
	private static TextView txt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_runner);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		taskManager = new TaskManager();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.frg_runner, container,false);
			txt = (TextView) rootView.findViewById(R.id.txt);
			Button btn = (Button) rootView.findViewById(R.id.btn);
			btn.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					try {
						taskManager.execute(task);
					}
					catch(Exception e) {
						Log.e("Runner", e.toString());
					}
				}
			});
			return rootView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			task = new GoogleSearchAsyncTask((Updatable) getActivity());			
		}
		
	}

	@Override
	protected void onPause() {
		if(taskManager != null) {
			taskManager.shutdown(false);
		}
		super.onPause();
	}
	
	public void update(final String text) {
		runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				txt.append(text);				
			}
		});		
	}
	
}
