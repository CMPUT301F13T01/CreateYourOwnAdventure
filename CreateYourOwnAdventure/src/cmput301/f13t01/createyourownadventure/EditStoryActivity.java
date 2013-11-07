package cmput301.f13t01.createyourownadventure;

import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class EditStoryActivity extends FragmentActivity implements
		StoryFragmentListListener {
	private ReadStoryManager manager;
	private boolean isNew;
	private UUID storyId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		TextView firstPage = (TextView) findViewById(R.id.edit_first_page);

		Intent intent = getIntent();

		isNew = (boolean) intent.getBooleanExtra(
				getResources().getString(R.string.story_is_new), false);
		if (isNew == false) {
			storyId = (UUID) intent.getSerializableExtra(getResources()
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
			app.setNewStoryManager();
		}

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DialogFragment newFragment = (DialogFragment) new StoryFragmentListFragment();
		ft.add(R.id.edit_story_linear, newFragment);
		ft.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_story_actionbar, menu);
		return true;
	}

	@Override
	public void onStoryFragmentSelected(int fragmentId) {
		Intent intent = new Intent(this, EditFragmentInfoActivity.class);

		intent.putExtra(getResources().getString(R.string.fragment_is_new),
				false);
		intent.putExtra(getResources().getString(R.string.fragment_id),
				fragmentId);
		startActivity(intent);
	}

	@SuppressWarnings("unused")
	private void showFragmentSelection() {
		DialogFragment newFragment = new StoryFragmentListFragment();

		// window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		newFragment.show(getSupportFragmentManager(),
				getResources().getString(R.string.fragment_list));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			onSelectDelete();
			return true;
		case R.id.action_edit_add_fragment:
			Intent intent = new Intent(this, EditFragmentInfoActivity.class);
			intent.putExtra(getResources().getString(R.string.fragment_is_new),
					true);
			startActivity(intent);
			return true;
		case R.id.action_publish:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void onSelectCancel() {
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	private void onSelectDelete() {
		if (!isNew) {
			// manager.deleteStory(storyId);
		}
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.story_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
	public void onBackPressed() {
		//manager.saveStory();
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.story_save_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

}
