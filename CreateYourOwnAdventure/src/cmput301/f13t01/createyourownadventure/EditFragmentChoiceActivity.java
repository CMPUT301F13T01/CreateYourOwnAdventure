package cmput301.f13t01.createyourownadventure;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditFragmentChoiceActivity extends Activity implements
		StoryFragmentListListener {

	private Choice choice;
	private int sourceId;
	private boolean isNew;
	private ReadStoryManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment_choice);
		setupActionBar();
		
        final Button button = (Button) findViewById(R.id.edit_set_destination);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFragmentSelection();
            }
        });


		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		Intent intent = getIntent();

		sourceId = intent.getIntExtra(
				getResources().getString(R.string.fragment_id), -1);

		isNew = intent.getBooleanExtra(
				getResources().getString(R.string.choice_is_new), true);

		TextView sourceText = (TextView) findViewById(R.id.edit_choice_source);
		sourceText.setText("Source: "
				+ manager.getFragmentInfo(sourceId).getTitle());

		TextView destText = (TextView) findViewById(R.id.edit_choice_destination);

		if (isNew) {
			destText.setText("Destination: Not Set");
			
			choice = new Choice();
			choice.setSourceId(sourceId);
			
		} else {
			choice = (Choice) intent.getSerializableExtra(getResources()
					.getString(R.string.choice));

			destText.setText("Destination: "
					+ manager.getFragmentInfo(choice.getDestinationId())
							.getTitle());

			TextView flavourText = (TextView) findViewById(R.id.edit_choice_flavour);
			flavourText.setText(choice.getFlavourText());
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_fragment_choice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			onSelectDelete();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onSelectDelete() {
		// TODO Auto-generated method stub
		
	}

	private void onSelectCancel() {
		// TODO Auto-generated method stub
		
	}

	public void showFragmentSelection() {
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		android.app.Fragment prev = getFragmentManager().findFragmentByTag(
				"dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		
		DialogFragment newFragment = (DialogFragment) StoryFragmentListFragment
				.newInstance();
		newFragment.show(ft, "dialog");
	}

	@Override
	public void onStoryFragmentSelected(int fragmentId) {
		choice.setDestinationId(fragmentId);
		
		TextView destText = (TextView) findViewById(R.id.edit_choice_destination);
		
		destText.setText("Destination: "
				+ manager.getFragmentInfo(choice.getDestinationId())
						.getTitle());
	}

}
