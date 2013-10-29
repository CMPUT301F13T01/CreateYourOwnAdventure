/**
 * 
 */
package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jesse Huard
 * 
 */
public class InfoFragment extends android.support.v4.app.Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for fragment information
		return inflater.inflate(R.layout.fragment_info, container, false);
	}
}
