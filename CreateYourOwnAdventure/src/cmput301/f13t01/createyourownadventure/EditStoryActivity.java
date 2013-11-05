package cmput301.f13t01.createyourownadventure;

import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.TextView;

public class EditStoryActivity extends FragmentActivity implements
		StoryFragmentListListener {
	private ReadStoryManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story);

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		getActionBar().setDisplayHomeAsUpEnabled(true);

		TextView firstPage = (TextView) findViewById(R.id.edit_first_page);

		Intent intent = getIntent();

		boolean isNew = (boolean) intent.getBooleanExtra(getResources()
				.getString(R.string.story_is_new), false);
		if (isNew == false) {
			UUID storyId = (UUID) intent.getSerializableExtra(getResources()
					.getString(R.string.story_id));
			app.setStoryManager(storyId);
			firstPage.setText(getResources().getString(R.string.first_page)
					+ manager.getFirstPage().getTitle());

		} else {
			firstPage
					.setText(getResources().getString(R.string.first_page)
							+ " "
							+ getResources().getString(
									R.string.first_page_empty));
		}

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DialogFragment newFragment = (DialogFragment) new StoryFragmentListFragment();
		ft.add(R.id.edit_story_linear, newFragment);
		ft.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_story, menu);
		return true;
	}

	@Override
	public void onStoryFragmentSelected(int fragmentId) {
		// TODO Auto-generated method stub

	}

}
