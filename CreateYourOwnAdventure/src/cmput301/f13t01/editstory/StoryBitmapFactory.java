/*
The StoryBitmapFactory class is used to output Bitmap objects for the UI

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
package cmput301.f13t01.editstory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

/**
 * The StoryBitmapFactory class is used to output Bitmap objects for the UI
 * 
 * @author Jesse Huard
 * 
 */
public class StoryBitmapFactory {
	public static final int MAX_SIZE = 512;
	public static final int DEFAULT_SIZE = 256;
	public static final int MIN_SIZE = 128;

	/**
	 * Constructor.
	 * 
	 * @param selectedImage the Uri of the image
	 * @param reqWidth the width of the bitmap
	 * @param reqHeight the height of the bitmap
	 * @param context the Context of the activity
	 * @return the Bitmap decoded from the given URI
	 * @throws FileNotFoundException
	 */
	public static Bitmap decodeUri(Uri selectedImage, int reqWidth,
			int reqHeight, Context context) throws FileNotFoundException {

		Log.d("ImageSaveDebug", "selected: " + selectedImage.toString());
		Log.d("ImageSaveDebug",
				"Size: " + (new File(selectedImage.getPath())).length());

		// First decode image size
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		InputStream is = context.getContentResolver().openInputStream(
				selectedImage);
		BitmapFactory.decodeStream(is, null, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(selectedImage), null, options);
	}

	/**
	 * Decodes and scales a given image
	 * 
	 * @param selectedImage the Uri of the image
	 * @param context the Context of the activity
	 * @param scale the scale to set the image to
	 * @return the scaled Bitmap
	 * @throws FileNotFoundException
	 */
	public static Bitmap decodeUriToScale(Uri selectedImage, Context context,
			Integer scale) throws FileNotFoundException {
		Bitmap b = StoryBitmapFactory.decodeUri(selectedImage, DEFAULT_SIZE,
				DEFAULT_SIZE, context);

		Log.d("ImageDebug",
				"b dimenstions: " + b.getWidth() + " " + b.getHeight());

		float size = (float) (scale / 100.0);

		Bitmap scaled = Bitmap.createScaledBitmap(b,
				(int) (b.getWidth() * size), (int) (b.getHeight() * size),
				false);

		return scaled;
	}

	/**
	 * Determines how to scale an image with given height and width
	 * 
	 * @param options options for the Factory
	 * @param reqWidth requested width
	 * @param reqHeight requested height
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
}
