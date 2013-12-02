/*
The StoryFragmentViewFactory class is used to generate the views for media within
a fragment.

     Copyright  �2013 Jesse Huard
    <Contact: jhuard@ualberta.ca>
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package cmput301.f13t01.createyourownadventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import android.R.dimen;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * The StoryFragmentViewFactory class is used to generate the views for media within
 * a fragment.
 * 
 * @author Jesse Huard
 * @version 1.0, 29/10/13
 * @see android.app.Application
 * 
 */

public class StoryFragmentViewFactory {
	private static final int horizontalPadding = cmput301.f13t01.createyourownadventure.R.dimen.activity_horizontal_margin;
	private static final int verticalPadding = cmput301.f13t01.createyourownadventure.R.dimen.activity_vertical_margin;

	// TODO: Make padding work correctly.
	@SuppressWarnings("rawtypes")
	public static void ConstructView(LinearLayout layout,
			ArrayList<Media> content, Context context, Boolean forEdit) {

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// Display the fragment
		for (Media media : content) {
			if (media.getClass().equals(Text.class)) {
				if (forEdit) {
					EditText edit = new EditText(context);
					edit.setTextColor(Color.BLACK);
					edit.setText(media.getContent().toString());
//					edit.setPadding(horizontalPadding, verticalPadding,
//							horizontalPadding, verticalPadding);
					layout.addView(edit, params);
				} else {
					TextView text = new TextView(context);
					text.setTextColor(Color.BLACK);
					text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
					text.setText((CharSequence) media.getContent());
//					text.setPadding(horizontalPadding, verticalPadding,
//							horizontalPadding, verticalPadding);
					layout.addView(text, params);
				}
			} else if (media.getClass().equals(Image.class)) {
				Image image = (Image) media;

				Uri imageUri = Uri.fromFile(new File(context.getFilesDir()
						.getAbsolutePath()
						+ "/"
						+ image.getType().toString()
						+ "/" + image.getContent()));
				addImage(imageUri, layout, context);
			} else if (media.getClass().equals(ImageUri.class)) {
				ImageUri image = (ImageUri) media;
				addImage(image.getContent(), layout, context);
			}
		}
	}

	public static void addImage(Uri image, LinearLayout layout, Context context) {
		try {

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			ImageView imageView = new ImageView(context);
			imageView.setVisibility(View.VISIBLE);
			imageView.setAdjustViewBounds(true);

			Log.d("oops", "Layout width: " + layout.getWidth() + " height: "
					+ layout.getHeight());

			Log.d("ImageDebug", "addImage path: " + image.getPath());

			Bitmap bitmap = StoryBitmapFactory.decodeUri(image, 256, 256, context);

			imageView.setImageBitmap(bitmap);
//			imageView.setPadding(horizontalPadding, verticalPadding,
//					horizontalPadding, verticalPadding);

			layout.addView(imageView, params);

		} catch (FileNotFoundException e) {
			Log.d("ImageDebug", "Couldn't find the file...");
			e.printStackTrace();
		}
	}

}
