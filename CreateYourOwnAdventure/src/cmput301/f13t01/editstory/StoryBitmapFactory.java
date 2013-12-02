package cmput301.f13t01.editstory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

public class StoryBitmapFactory {
	public static final int MAX_SIZE = 512;
	public static final int DEFAULT_SIZE = 256;
	public static final int MIN_SIZE = 128;

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
