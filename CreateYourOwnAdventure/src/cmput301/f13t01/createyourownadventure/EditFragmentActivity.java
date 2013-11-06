package cmput301.f13t01.createyourownadventure;

import java.io.Serializable;
import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class EditFragmentActivity extends FragmentActivity implements
		ActionBar.TabListener, ChoiceListListener {

	private StoryFragment storyFragment = new StoryFragment();
	private boolean isNew;
	private int fragmentId;
	private ReadStoryManager manager;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SpannableString string = new SpannableString("blah");
		SpannableString string2 = new SpannableString(
				"Your mother was a scallywag! Have at thee!");
		this.storyFragment.addContent(new Text(string));
		this.storyFragment.addContent(new Text(string2));

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		Intent intent = getIntent();

		isNew = (boolean) intent.getBooleanExtra(
				getResources().getString(R.string.fragment_is_new), false);
		if (isNew == false) {
			fragmentId = (int) intent.getIntExtra(
					getResources().getString(R.string.fragment_id), -1);
			this.storyFragment = manager.getFragment(fragmentId);
		} else {
			this.storyFragment = new StoryFragment();
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

		// Set to show the first tab
		mViewPager.setCurrentItem(0);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_actionbar, menu);
		return true;
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
		case R.id.action_edit_edit_choice:
			showChoiceSelection();
			return true;
		case R.id.action_edit_add_choice:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showChoiceSelection() {
		DialogFragment newFragment = new ChoiceListFragment();

		// window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		newFragment.show(getSupportFragmentManager(),
				getResources().getString(R.string.choice_list));
	}

	public void onChoiceSelected(Choice choice) {
		// TODO: Start the edit choice activity
		return;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			if (position == 0) {
				Fragment fragment = new InfoFragment();
				Bundle args = new Bundle();
				args.putSerializable(
						getResources().getString(R.string.story_fragment),
						(Serializable) storyFragment);
				fragment.setArguments(args);
				return fragment;
			} else if (position == 1) {
				Fragment fragment = new EditFragment();
				Bundle args = new Bundle();
				args.putSerializable(
						getResources().getString(R.string.story_fragment),
						(Serializable) storyFragment);
				fragment.setArguments(args);
				return fragment;
			}

			else if (position == 2) {
				Fragment fragment = new PreviewFragment();
				Bundle args = new Bundle();
				args.putSerializable(
						getResources().getString(R.string.story_fragment),
						(Serializable) storyFragment);
				fragment.setArguments(args);
				return fragment;
			}

			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.edit_section1).toUpperCase(l);
			case 1:
				return getString(R.string.edit_section2).toUpperCase(l);
			case 2:
				return getString(R.string.edit_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
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
			manager.removeFragment(fragmentId);
		}
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.story_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

}
