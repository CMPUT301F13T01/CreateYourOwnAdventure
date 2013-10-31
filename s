[1mdiff --git a/.gitignore b/.gitignore[m
[1mindex 5556361..b9d6bd9 100644[m
[1m--- a/.gitignore[m
[1m+++ b/.gitignore[m
[36m@@ -5,9 +5,7 @@[m
 *.pydevproject[m
 .project[m
 .metadata[m
[31m-CreateYourOwnAdventure/.settings/[m
[31m-CreateYourOwnAdventure/bin/[m
[31m-CreateYourOwnAdventure/gen/[m
[32m+[m[32mbin/[m
 tmp/[m
 *.tmp[m
 *.bak[m
[36m@@ -214,4 +212,4 @@[m [mpip-log.txt[m
 *.mo[m
 [m
 #Mr Developer[m
[31m-.mr.developer.cfg[m
\ No newline at end of file[m
[32m+[m[32m.mr.developer.cfg[m
[1mdiff --git a/.goutputstream-6FLV3W b/.goutputstream-6FLV3W[m
[1mnew file mode 100644[m
[1mindex 0000000..7bfd358[m
[1m--- /dev/null[m
[1m+++ b/.goutputstream-6FLV3W[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m?ttps://github.com/CMPUT301F13T01 Project Wiki/T01ChooseYourOwnAdventure[m
[32m+[m
[32m+[m[32mSubject: CMPUT301 TEAM CMPUT301F13[m
[32m+[m
[32m+[m
[32m+[m
[1mdiff --git a/.goutputstream-UQKW3W b/.goutputstream-UQKW3W[m
[1mnew file mode 100644[m
[1mindex 0000000..7bfd358[m
[1m--- /dev/null[m
[1m+++ b/.goutputstream-UQKW3W[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m?ttps://github.com/CMPUT301F13T01 Project Wiki/T01ChooseYourOwnAdventure[m
[32m+[m
[32m+[m[32mSubject: CMPUT301 TEAM CMPUT301F13[m
[32m+[m
[32m+[m
[32m+[m
[1mdiff --git a/CreateYourOwnAdventure/.classpath b/CreateYourOwnAdventure/.classpath[m
[1mnew file mode 100644[m
[1mindex 0000000..7bc01d9[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/.classpath[m
[36m@@ -0,0 +1,9 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<classpath>[m
[32m+[m	[32m<classpathentry kind="src" path="src"/>[m
[32m+[m	[32m<classpathentry kind="src" path="gen"/>[m
[32m+[m	[32m<classpathentry kind="con" path="com.android.ide.eclipse.adt.ANDROID_FRAMEWORK"/>[m
[32m+[m	[32m<classpathentry exported="true" kind="con" path="com.android.ide.eclipse.adt.LIBRARIES"/>[m
[32m+[m	[32m<classpathentry exported="true" kind="con" path="com.android.ide.eclipse.adt.DEPENDENCIES"/>[m
[32m+[m	[32m<classpathentry kind="output" path="bin/classes"/>[m
[32m+[m[32m</classpath>[m
[1mdiff --git a/CreateYourOwnAdventure/.settings/org.eclipse.jdt.core.prefs b/CreateYourOwnAdventure/.settings/org.eclipse.jdt.core.prefs[m
[1mindex 8000cd6..b080d2d 100644[m
[1m--- a/CreateYourOwnAdventure/.settings/org.eclipse.jdt.core.prefs[m
[1m+++ b/CreateYourOwnAdventure/.settings/org.eclipse.jdt.core.prefs[m
[36m@@ -1,11 +1,4 @@[m
 eclipse.preferences.version=1[m
[31m-org.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled[m
 org.eclipse.jdt.core.compiler.codegen.targetPlatform=1.6[m
[31m-org.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve[m
 org.eclipse.jdt.core.compiler.compliance=1.6[m
[31m-org.eclipse.jdt.core.compiler.debug.lineNumber=generate[m
[31m-org.eclipse.jdt.core.compiler.debug.localVariable=generate[m
[31m-org.eclipse.jdt.core.compiler.debug.sourceFile=generate[m
[31m-org.eclipse.jdt.core.compiler.problem.assertIdentifier=error[m
[31m-org.eclipse.jdt.core.compiler.problem.enumIdentifier=error[m
 org.eclipse.jdt.core.compiler.source=1.6[m
[1mdiff --git a/CreateYourOwnAdventure/AndroidManifest.xml b/CreateYourOwnAdventure/AndroidManifest.xml[m
[1mindex e6c64f2..af82c9d 100644[m
[1m--- a/CreateYourOwnAdventure/AndroidManifest.xml[m
[1m+++ b/CreateYourOwnAdventure/AndroidManifest.xml[m
[36m@@ -12,7 +12,7 @@[m
         android:allowBackup="true"[m
         android:icon="@drawable/ic_launcher"[m
         android:label="@string/app_name"[m
[31m-        android:theme="@android:style/Theme.Holo.Light" android:name="GlobalManager">[m
[32m+[m[32m        android:theme="@android:style/Theme.Holo.Light" >[m
         <activity[m
             android:name="cmput301.f13t01.createyourownadventure.MainActivity"[m
             android:label="@string/app_name" >[m
[36m@@ -22,6 +22,7 @@[m
                 <category android:name="android.intent.category.LAUNCHER" />[m
             </intent-filter>[m
         </activity>[m
[32m+[m[32m        <activity android:name="ReadFragmentActivity"></activity>[m
     </application>[m
 [m
 </manifest>[m
[1mdiff --git a/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/BuildConfig.java b/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/BuildConfig.java[m
[1mnew file mode 100644[m
[1mindex 0000000..756e2f7[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/BuildConfig.java[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m/** Automatically generated file. DO NOT MODIFY */[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32mpublic final class BuildConfig {[m
[32m+[m[32m    public final static boolean DEBUG = true;[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/R.java b/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/R.java[m
[1mnew file mode 100644[m
[1mindex 0000000..7a93ac3[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/gen/cmput301/f13t01/createyourownadventure/R.java[m
[36m@@ -0,0 +1,93 @@[m
[32m+[m[32m/* AUTO-GENERATED FILE.  DO NOT MODIFY.[m
[32m+[m[32m *[m
[32m+[m[32m * This class was automatically generated by the[m
[32m+[m[32m * aapt tool from the resource data it found.  It[m
[32m+[m[32m * should not be modified by hand.[m
[32m+[m[32m */[m
[32m+[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32mpublic final class R {[m
[32m+[m[32m    public static final class attr {[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class dimen {[m
[32m+[m[32m        /**  Default screen margins, per the Android Design guidelines.[m[41m [m
[32m+[m
[32m+[m[32m         Customize dimensions originally defined in res/values/dimens.xml (such as[m
[32m+[m[32m         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.[m
[32m+[m[41m    [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int activity_horizontal_margin=0x7f040000;[m
[32m+[m[32m        public static final int activity_vertical_margin=0x7f040001;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class drawable {[m
[32m+[m[32m        public static final int browse_online_stories=0x7f020000;[m
[32m+[m[32m        public static final int create_new_story=0x7f020001;[m
[32m+[m[32m        public static final int ic_launcher=0x7f020002;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class id {[m
[32m+[m[32m        public static final int action_browse_online_stories=0x7f080004;[m
[32m+[m[32m        public static final int action_create_new_story=0x7f080005;[m
[32m+[m[32m        public static final int action_return_to_beginning=0x7f080006;[m
[32m+[m[32m        public static final int action_return_to_previous_page=0x7f080007;[m
[32m+[m[32m        public static final int action_settings=0x7f080008;[m
[32m+[m[32m        public static final int activity_main_layout=0x7f080001;[m
[32m+[m[32m        public static final int activity_main_story_list=0x7f080002;[m
[32m+[m[32m        public static final int row_text=0x7f080003;[m
[32m+[m[32m        public static final int rowtextview=0x7f080000;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class layout {[m
[32m+[m[32m        public static final int activity_story_list=0x7f030000;[m
[32m+[m[32m        public static final int list_choices=0x7f030001;[m
[32m+[m[32m        public static final int main_activity=0x7f030002;[m
[32m+[m[32m        public static final int main_textview_activity=0x7f030003;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class menu {[m
[32m+[m[32m        public static final int main_menu=0x7f070000;[m
[32m+[m[32m        public static final int read_menu=0x7f070001;[m
[32m+[m[32m        public static final int story_list=0x7f070002;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class string {[m
[32m+[m[32m        /**   main screen action bar items[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int action_browse_online_stories=0x7f050003;[m
[32m+[m[32m        public static final int action_create_new_story=0x7f050004;[m
[32m+[m[32m        public static final int action_return_to_beginning=0x7f050006;[m
[32m+[m[32m        /**   main screen action bar items[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int action_return_to_previous_page=0x7f050005;[m
[32m+[m[32m        /**   action bar items[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int action_settings=0x7f050002;[m
[32m+[m[32m        public static final int app_name=0x7f050000;[m
[32m+[m[32m        public static final int hello_world=0x7f050001;[m
[32m+[m[32m    }[m
[32m+[m[32m    public static final class style {[m
[32m+[m[32m        /**[m[41m [m
[32m+[m[32m        Base application theme, dependent on API level. This theme is replaced[m
[32m+[m[32m        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.[m
[32m+[m[41m    [m
[32m+[m
[32m+[m[32m            Theme customizations available in newer API levels can go in[m
[32m+[m[32m            res/values-vXX/styles.xml, while customizations related to[m
[32m+[m[32m            backward-compatibility can go here.[m
[32m+[m[41m        [m
[32m+[m
[32m+[m[32m        Base application theme for API 11+. This theme completely replaces[m
[32m+[m[32m        AppBaseTheme from res/values/styles.xml on API 11+ devices.[m
[32m+[m[41m    [m
[32m+[m[32m API 11 theme customizations can go here.[m[41m [m
[32m+[m
[32m+[m[32m        Base application theme for API 14+. This theme completely replaces[m
[32m+[m[32m        AppBaseTheme from BOTH res/values/styles.xml and[m
[32m+[m[32m        res/values-v11/styles.xml on API 14+ devices.[m
[32m+[m[41m    [m
[32m+[m[32m API 14 theme customizations can go here.[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int AppBaseTheme=0x7f060000;[m
[32m+[m[32m        /**  Application theme.[m[41m [m
[32m+[m[32m All customizations that are NOT specific to a particular API-level can go here.[m[41m [m
[32m+[m[32m         */[m
[32m+[m[32m        public static final int AppTheme=0x7f060001;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[1mdiff --git a/CreateYourOwnAdventure/res/layout/choice_list_item.xml b/CreateYourOwnAdventure/res/layout/choice_list_item.xml[m
[1mdeleted file mode 100644[m
[1mindex fe621e9..0000000[m
[1m--- a/CreateYourOwnAdventure/res/layout/choice_list_item.xml[m
[1m+++ /dev/null[m
[36m@@ -1,22 +0,0 @@[m
[31m-<?xml version="1.0" encoding="utf-8"?>[m
[31m-<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    android:layout_width="match_parent"[m
[31m-    android:layout_height="wrap_content"[m
[31m-    android:orientation="vertical" >[m
[31m-[m
[31m-    <TextView[m
[31m-        android:id="@+id/choice_list_direction"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:textSize="16sp"[m
[31m-        android:textStyle="bold" />[m
[31m-[m
[31m-    <TextView[m
[31m-        android:id="@+id/choice_list_flavour"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:singleLine="true"[m
[31m-        android:textSize="14sp"[m
[31m-        android:textStyle="normal" />[m
[31m-[m
[31m-</LinearLayout>[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/res/layout/list_choices.xml b/CreateYourOwnAdventure/res/layout/list_choices.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..baf4837[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/res/layout/list_choices.xml[m
[36m@@ -0,0 +1,7 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<TextView xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    android:id="@+id/rowtextview"[m
[32m+[m[32m    android:layout_width="fill_parent"[m
[32m+[m[32m    android:layout_height="fill_parent"[m
[32m+[m[32m    android:textSize="20sp"  >[m
[32m+[m[32m</TextView>[m
[1mdiff --git a/CreateYourOwnAdventure/res/layout/story_fragment_list_item.xml b/CreateYourOwnAdventure/res/layout/story_fragment_list_item.xml[m
[1mdeleted file mode 100644[m
[1mindex da0fd35..0000000[m
[1m--- a/CreateYourOwnAdventure/res/layout/story_fragment_list_item.xml[m
[1m+++ /dev/null[m
[36m@@ -1,22 +0,0 @@[m
[31m-<?xml version="1.0" encoding="utf-8"?>[m
[31m-<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    android:layout_width="match_parent"[m
[31m-    android:layout_height="wrap_content"[m
[31m-    android:orientation="vertical" >[m
[31m-[m
[31m-    <TextView[m
[31m-        android:id="@+id/fragment_list_title"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:textSize="16sp"[m
[31m-        android:textStyle="bold" />[m
[31m-[m
[31m-    <TextView[m
[31m-        android:id="@+id/fragment_list_description"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="wrap_content"[m
[31m-        android:singleLine="true"[m
[31m-        android:textSize="14sp"[m
[31m-        android:textStyle="normal" />[m
[31m-[m
[31m-</LinearLayout>[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/res/menu/read_menu.xml b/CreateYourOwnAdventure/res/menu/read_menu.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..98c1231[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/res/menu/read_menu.xml[m
[36m@@ -0,0 +1,14 @@[m
[32m+[m[32m<?xml version="1.0" encoding="utf-8"?>[m
[32m+[m[32m<menu xmlns:android="http://schemas.android.com/apk/res/android" >[m
[32m+[m[41m    [m
[32m+[m[32m    <!--  return to beginning of story -->[m
[32m+[m[32m    <item android:id="@+id/action_return_to_beginning"[m
[32m+[m[32m          android:title="@string/action_return_to_beginning"[m[41m [m
[32m+[m[32m          android:showAsAction="ifRoom" />[m[41m [m
[32m+[m[41m           [m
[32m+[m[32m    <!--  return to previous page according to history  -->[m
[32m+[m[32m    <item android:id="@+id/action_return_to_previous_page"[m[41m [m
[32m+[m[32m          android:title="@string/action_return_to_previous_page"[m[41m [m
[32m+[m[32m          android:showAsAction="ifRoom" />[m[41m  [m
[32m+[m
[32m+[m[32m</menu>[m
[1mdiff --git a/CreateYourOwnAdventure/res/values/strings.xml b/CreateYourOwnAdventure/res/values/strings.xml[m
[1mindex 9101e1d..84cadaa 100644[m
[1m--- a/CreateYourOwnAdventure/res/values/strings.xml[m
[1m+++ b/CreateYourOwnAdventure/res/values/strings.xml[m
[36m@@ -7,8 +7,9 @@[m
     <string name="action_settings">Settings</string>[m
     <!--  main screen action bar items -->[m
     <string name="action_browse_online_stories">Browse Online Stories</string>[m
[31m-    <string name="action_create_new_story">Create New Story</string>[m
[31m-    <string name="story_fragment">Story Fragment</string>[m
[31m-    <string name="story_id">Story ID</string>       [m
[31m-[m
[32m+[m[32m    <string name="action_create_new_story">Create New Story</string>[m[41m       [m
[32m+[m[32m    <!--  main screen action bar items -->[m
[32m+[m[32m    <string name="action_return_to_previous_page">Return to Previous Page</string>[m
[32m+[m[32m    <string name="action_return_to_beginning">Return to Beginning</string>[m[41m   [m
[32m+[m[41m    [m
 </resources>[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Choice.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Choice.java[m
[1mdeleted file mode 100644[m
[1mindex ea15f86..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Choice.java[m
[1m+++ /dev/null[m
[36m@@ -1,119 +0,0 @@[m
[31m-/*[m
[31m-Choice class for CreateYourOwnAdventure.[m
[31m-This is a simple object that contains a source ID of a[m
[31m-fragment, a destination ID of a fragment, and the flavour[m
[31m-text of the particular choice.[m
[31m-[m
[31m-     Copyright  ©2013 Reginald Miller[m
[31m-    <Contact: rmiller3@ualberta.ca>[m
[31m-    [m
[31m-    License GPLv3: GNU GPL Version 3[m
[31m-    <http://gnu.org/licenses/gpl.html>.[m
[31m-    [m
[31m-    This program is free software: you can redistribute it and/or modify[m
[31m-    it under the terms of the GNU General Public License as published by[m
[31m-    the Free Software Foundation, either version 3 of the License, or[m
[31m-    (at your option) any later version.[m
[31m-[m
[31m-    This program is distributed in the hope that it will be useful,[m
[31m-    but WITHOUT ANY WARRANTY; without even the implied warranty of[m
[31m-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the[m
[31m-    GNU General Public License for more details.[m
[31m-[m
[31m-    You should have received a copy of the GNU General Public License[m
[31m-    along with this program. If not, see <http://www.gnu.org/licenses/>.[m
[31m- */[m
[31m-[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import java.io.IOException;[m
[31m-import java.io.ObjectStreamException;[m
[31m-import java.io.Serializable;[m
[31m-[m
[31m-/**[m
[31m- * This is a simple object that will contain the information[m
[31m- * related to a particular choice, which will allow the[m
[31m- * transition from one fragment to another while reading.[m
[31m- * [m
[31m- * @author Reginald Miller, October 2013[m
[31m- *[m
[31m- */[m
[31m-[m
[31m-public class Choice implements Serializable {[m
[31m-[m
[31m-	private int sourceId;[m
[31m-	private int destinationId;[m
[31m-	private String flavourText;[m
[31m-	[m
[31m-	//No initializations[m
[31m-	public Choice() {[m
[31m-		[m
[31m-	}[m
[31m-	[m
[31m-	//Can choose to initialize[m
[31m-	public Choice(int sourceId, int destinationId, String flavourText) {[m
[31m-		this.sourceId = sourceId;[m
[31m-		this.destinationId = destinationId;[m
[31m-		this.flavourText = flavourText;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @return the fragment's source ID[m
[31m-	 */[m
[31m-	public int getSourceId() {[m
[31m-		return sourceId;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @param sourceId the fragment's source ID to set[m
[31m-	 */[m
[31m-	public void setSourceId(int sourceId) {[m
[31m-		this.sourceId = sourceId;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @return the fragment's destination ID[m
[31m-	 */[m
[31m-	public int getDestinationId() {[m
[31m-		return destinationId;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @param destinationId the fragment's destination ID to set[m
[31m-	 */[m
[31m-	public void setDestinationId(int destinationId) {[m
[31m-		this.destinationId = destinationId;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @return the flavour text of the choice[m
[31m-	 */[m
[31m-	public String getFlavourText() {[m
[31m-		return flavourText;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * @param flavourText the flavour text to set[m
[31m-	 */[m
[31m-	public void setFlavourText(String flavourText) {[m
[31m-		this.flavourText = flavourText;[m
[31m-	}[m
[31m-	[m
[31m-	private void writeObject(java.io.ObjectOutputStream out)[m
[31m-		     throws IOException {[m
[31m-		out.writeObject(sourceId);[m
[31m-		out.writeObject(destinationId);[m
[31m-		out.writeObject(flavourText);[m
[31m-	}[m
[31m-	private void readObject(java.io.ObjectInputStream in)[m
[31m-		     throws IOException, ClassNotFoundException {[m
[31m-		sourceId = (Integer) in.readObject();[m
[31m-		destinationId = (Integer) in.readObject();[m
[31m-		flavourText = (String) in.readObject();[m
[31m-	}[m
[31m-		 [m
[31m-	private void readObjectNoData() [m
[31m-		     throws ObjectStreamException {[m
[31m-	}[m
[31m-	[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListAdapter.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListAdapter.java[m
[1mdeleted file mode 100644[m
[1mindex abc55e3..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListAdapter.java[m
[1m+++ /dev/null[m
[36m@@ -1,78 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import android.app.Activity;[m
[31m-import android.content.Context;[m
[31m-import android.view.LayoutInflater;[m
[31m-import android.view.View;[m
[31m-import android.view.ViewGroup;[m
[31m-import android.widget.ArrayAdapter;[m
[31m-import android.widget.TextView;[m
[31m-[m
[31m-/**[m
[31m- * The ChoiceListAdapter class is the ArrayAdapter for the ChoiceList Fragment.[m
[31m- * It handles inflating the list's views by extracting the StoryFragment titles[m
[31m- * from the EditManager based on the StoryFragmentIds contained in the passed[m
[31m- * Choice. Activities using this fragment must implement the ChoiceListListener[m
[31m- * interface.[m
[31m- * [m
[31m- * @author Jesse Huard[m
[31m- * @version 1.0, 30/10/13[m
[31m- * [m
[31m- */[m
[31m-[m
[31m-public class ChoiceListAdapter extends ArrayAdapter<Choice> {[m
[31m-[m
[31m-	/**[m
[31m-	 * Holds the application context.[m
[31m-	 */[m
[31m-	private final Context context;[m
[31m-	private final Choice[] choices;[m
[31m-[m
[31m-	public ChoiceListAdapter(Context context, Choice[] choices) {[m
[31m-		super(context, R.layout.choice_list_item, choices);[m
[31m-		this.context = context;[m
[31m-		this.choices = choices;[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public View getView(int position, View convertView, ViewGroup parent) {[m
[31m-		LayoutInflater inflater = (LayoutInflater) context[m
[31m-				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);[m
[31m-[m
[31m-		Activity activity = (Activity) context;[m
[31m-		GlobalManager app = (GlobalManager) activity.getApplication();[m
[31m-		EditManager manager = app.getEditManager();[m
[31m-[m
[31m-		// Inflate the view[m
[31m-		View rowView = inflater.inflate(R.layout.choice_list_item, parent,[m
[31m-				false);[m
[31m-[m
[31m-		// Get the view's TextViews[m
[31m-		TextView direction = (TextView) rowView[m
[31m-				.findViewById(R.id.choice_list_direction);[m
[31m-		TextView flavour = (TextView) rowView[m
[31m-				.findViewById(R.id.choice_list_flavour);[m
[31m-[m
[31m-		String sourceTitle = manager.getFragmentInfo([m
[31m-				choices[position].getSourceId()).getTitle();[m
[31m-		String destTitle = manager.getFragmentInfo([m
[31m-				choices[position].getDestinationId()).getTitle();[m
[31m-[m
[31m-		// Copy the story fragment's information into the view[m
[31m-		direction.setText(sourceTitle + " to " + destTitle);[m
[31m-		flavour.setText(choices[position].getFlavourText());[m
[31m-[m
[31m-		return rowView;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Gets the Choice at the selected position.[m
[31m-	 * [m
[31m-	 * @param position[m
[31m-	 *            the position of the desired Choice.[m
[31m-	 * @return the selected Choice[m
[31m-	 */[m
[31m-	public Choice getChoiceAtPosition(int position) {[m
[31m-		return choices[position];[m
[31m-	}[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListFragment.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListFragment.java[m
[1mdeleted file mode 100644[m
[1mindex a916e97..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListFragment.java[m
[1m+++ /dev/null[m
[36m@@ -1,30 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import java.util.ArrayList;[m
[31m-[m
[31m-import android.app.ListFragment;[m
[31m-import android.os.Bundle;[m
[31m-import android.view.View;[m
[31m-import android.widget.ListView;[m
[31m-[m
[31m-public class ChoiceListFragment extends ListFragment {[m
[31m-	@Override[m
[31m-	public void onCreate(Bundle savedInstanceState) {[m
[31m-		super.onCreate(savedInstanceState);[m
[31m-[m
[31m-		GlobalManager app = (GlobalManager) getActivity().getApplication();[m
[31m-		ArrayList<Choice> choices = app.getEditManager().getChoiceList();[m
[31m-[m
[31m-		ChoiceListAdapter adapter = new ChoiceListAdapter(getActivity(),[m
[31m-				choices);[m
[31m-		setListAdapter(adapter);[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public void onListItemClick(ListView l, View v, int position, long id) {[m
[31m-		StoryFragmentListListener activity = (StoryFragmentListListener) getActivity();[m
[31m-		ChoiceListAdapter adapter = (ChoiceListAdapter) getListAdapter();[m
[31m-		Choice choice = adapter.getChoiceAtPosition(position)[m
[31m-		activity.onChoiceSelected(choice);[m
[31m-	}[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListListener.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListListener.java[m
[1mdeleted file mode 100644[m
[1mindex a991a5c..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceListListener.java[m
[1m+++ /dev/null[m
[36m@@ -1,16 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-/**[m
[31m- * The ChoiceListListener interface is an interface that must be[m
[31m- * implemented by any activity using a ChoiceListFragment. This interface[m
[31m- * is to ensure that the activity implements the onChoiceSelected callback[m
[31m- * function to correctly handle item selection in the ListFragment.[m
[31m- * [m
[31m- * @author Jesse Huard[m
[31m- * @version 1.0, 30/10/13[m
[31m- * [m
[31m- */[m
[31m-[m
[31m-public interface ChoiceListListener {[m
[31m-		void onChoiceSelected(Choice choice);[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceMap.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceMap.java[m
[1mindex ad6d93e..e1c11e2 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceMap.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ChoiceMap.java[m
[36m@@ -1,211 +1,26 @@[m
[31m-/*[m
[31m-ChoiceMap class for CreateYourOwnAdventure.[m
[31m-Allows for access to all choices linking fragments in a story[m
[31m-[m
[31m-     Copyright  ©2013 Reginald Miller[m
[31m-    <Contact: rmiller3@ualberta.ca>[m
[31m-    [m
[31m-    License GPLv3: GNU GPL Version 3[m
[31m-    <http://gnu.org/licenses/gpl.html>.[m
[31m-    [m
[31m-    This program is free software: you can redistribute it and/or modify[m
[31m-    it under the terms of the GNU General Public License as published by[m
[31m-    the Free Software Foundation, either version 3 of the License, or[m
[31m-    (at your option) any later version.[m
[31m-[m
[31m-    This program is distributed in the hope that it will be useful,[m
[31m-    but WITHOUT ANY WARRANTY; without even the implied warranty of[m
[31m-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the[m
[31m-    GNU General Public License for more details.[m
[31m-[m
[31m-    You should have received a copy of the GNU General Public License[m
[31m-    along with this program. If not, see <http://www.gnu.org/licenses/>.[m
[31m- */[m
[31m-[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.ObjectStreamException;[m
[31m-import java.io.Serializable;[m
 import java.util.ArrayList;[m
 import java.util.HashMap;[m
 [m
[31m-/**[m
[31m- * This class contains all methods associated with selecting[m
[31m- * adding, editing or removing the choice linkages for a given story.[m
[31m- * [m
[31m- * @author Reginald Miller, October 2013[m
[31m- *[m
[31m- */[m
[32m+[m[32mpublic class ChoiceMap {[m
 [m
[31m-public class ChoiceMap implements Serializable {[m
[31m-[m
[31m-	//Maps Fragment IDs to Fragment ArrayList, to be indexed[m
[31m-	private HashMap<Integer, ArrayList<Choice>> choiceMapping;[m
[31m-	[m
[31m-	public ChoiceMap() {[m
[31m-		this.choiceMapping = new HashMap<Integer, ArrayList<Choice>>();[m
[31m-	}[m
[32m+[m	[32mprivate HashMap<Fragment, ArrayList<Fragment>> choice_mapping;[m
 	[m
[31m-	/**[m
[31m-	 * This is called whenever a previously-unlinked choice selects a[m
[31m-	 * new destination.[m
[31m-	 * [m
[31m-	 * @param fragmentId   ID of fragment where choice is located[m
[31m-	 * @param choice   Choice object to add to the ChoiceMap[m
[31m-	 */[m
[31m-	public void addChoice(int fragmentId, Choice choice) {[m
[31m-		[m
[31m-		ArrayList<Choice> destinations = choiceMapping.get(fragmentId);[m
[31m-		[m
[31m-		if (destinations != null) {[m
[31m-			destinations.add(choice);[m
[31m-			choiceMapping.put(fragmentId, destinations);[m
[31m-		}[m
[31m-		//This occurs if no choices in the fragment exist yet[m
[31m-		else {[m
[31m-			ArrayList<Choice> destination = new ArrayList<Choice>();[m
[31m-			destination.add(choice);[m
[31m-			choiceMapping.put(fragmentId, destination);[m
[31m-		}[m
[32m+[m	[32mpublic void add_choice() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * Removes a choice from the ChoiceMap when author unlinks a choice[m
[31m-	 * [m
[31m-	 * @param fragmentId   ID of fragment where choice is located[m
[31m-	 * @param index   Index of the choice to remove[m
[31m-	 * @return   Returns true if choice existed, otherwise returns false[m
[31m-	 */[m
[31m-	public boolean deleteChoice(int fragmentId, int index) {[m
[31m-		[m
[31m-		ArrayList<Choice> destinations = choiceMapping.get(fragmentId);[m
[31m-		[m
[31m-		//Makes sure entry exists in HashMap and that the choice doesn't[m
[31m-		//index outside of length of destinations list[m
[31m-		if(destinations != null && index >= 0 && index < destinations.size()) {[m
[31m-			destinations.remove(index);[m
[31m-			choiceMapping.put(fragmentId, destinations);[m
[31m-			return true;[m
[31m-		}[m
[31m-		//If tried to remove a choice that doesn't exist, returns false[m
[31m-		else {[m
[31m-			return false;[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Switches the destination of an already-linked choice to a different[m
[31m-	 * destination fragment.[m
[31m-	 * [m
[31m-	 * @param fragmentId   ID of fragment where choice is located[m
[31m-	 * @param index   Index of choice to change[m
[31m-	 * @param choice   New choice to put in there[m
[31m-	 * @return   Returns true if choice already existed, false otherwise[m
[31m-	 */[m
[31m-	public boolean updateChoice(int fragmentId, int index, Choice choice) {[m
[31m-		[m
[31m-		ArrayList<Choice> destinations = choiceMapping.get(fragmentId);[m
[31m-		[m
[31m-		//Insures that the choice already exists, deletes old choice from ChoiceMap[m
[31m-		if(destinations != null && index > 0 && index < destinations.size()) {[m
[31m-			destinations.set(index, choice);[m
[31m-			choiceMapping.put(fragmentId, destinations);[m
[31m-			return true;[m
[31m-		}[m
[31m-		else {[m
[31m-			return false;[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Want this to be called when a fragment is deleted. If it doesn't[m
[31m-	 * exist, then there's nothing to remove. Also removes all choices[m
[31m-	 * currently linked to it.[m
[31m-	 * [m
[31m-	 * @param fragmentId   ID of fragment to remove from all choices[m
[31m-	 */[m
[31m-	public void cleanFragmentReferences(int fragmentId) {[m
[31m-		[m
[31m-		//Removes all choices the fragment is linked to[m
[31m-		if (choiceMapping.containsKey(fragmentId)) {[m
[31m-			choiceMapping.remove(fragmentId);[m
[31m-		}[m
[31m-		[m
[31m-		//Checks for all instances where the fragment was a [m
[31m-		//destination and removes them.[m
[31m-		for (int id : choiceMapping.keySet()) {[m
[31m-			ArrayList<Choice> choiceList = choiceMapping.get(id);[m
[31m-			int choiceIndex;[m
[31m-				[m
[31m-			//Make sure we don't remove entry while iterating[m
[31m-			//through it.[m
[31m-			//If multiple choices leading to same fragment, want[m
[31m-			//to remove all of them.[m
[31m-			do {[m
[31m-				choiceIndex = -1;[m
[31m-				for (Choice choice : choiceList) {[m
[31m-					if (choice.getDestinationId() == fragmentId) {[m
[31m-						choiceIndex = choiceList.indexOf(choice);[m
[31m-						break;[m
[31m-					}[m
[31m-				}[m
[31m-				[m
[31m-				//If choice found, it is removed[m
[31m-				if (choiceIndex != -1) {[m
[31m-					choiceList.remove(choiceIndex);[m
[31m-				}[m
[31m-			} while (choiceIndex != -1);[m
[31m-			[m
[31m-			choiceMapping.put(id, choiceList);[m
[31m-		}[m
[31m-		[m
[32m+[m	[32mpublic void remove_choice() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * Returns an ArrayList of map entries for all possible choices for the fragment.[m
[31m-	 * Entries are sorted by fragment ID.[m
[31m-	 * [m
[31m-	 * @param fragmentId   ID of fragment to fetch the choices for[m
[31m-	 * @return   Assorted ArrayList of int, String pairs associated with fragment ID and choice text[m
[31m-	 */[m
[31m-	public ArrayList<Choice> getChoices(int fragmentId) {[m
[31m-		[m
[31m-		ArrayList<Choice> destinations = choiceMapping.get(fragmentId);[m
[31m-		[m
[31m-		if (destinations == null) {[m
[31m-			return null;[m
[31m-		}[m
[31m-		else {[m
[31m-			return destinations;[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param out   ObjectOutputStream to write with[m
[31m-	 * @throws IOException[m
[31m-	 */[m
[31m-	private void writeObject(java.io.ObjectOutputStream out)[m
[31m-		     throws IOException {[m
[31m-		out.writeObject(choiceMapping);[m
[32m+[m	[32mpublic void edit_choice() {[m
[32m+[m		[32mreturn;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param in   ObjectInputStream to read with[m
[31m-	 * @throws IOException[m
[31m-	 * @throws ClassNotFoundException[m
[31m-	 */[m
[31m-	private void readObject(java.io.ObjectInputStream in)[m
[31m-		     throws IOException, ClassNotFoundException {[m
[31m-		choiceMapping = (HashMap<Integer, ArrayList<Choice>>) in.readObject();[m
[31m-	}[m
[31m-		 [m
[31m-	private void readObjectNoData() [m
[31m-		     throws ObjectStreamException {[m
[32m+[m	[32mpublic void clean_fragment_references() {[m
[32m+[m		[32mreturn;[m
 	}[m
 	[m
 }[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/FragmentListAdapter.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/FragmentListAdapter.java[m
[1mdeleted file mode 100644[m
[1mindex ba0bfd3..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/FragmentListAdapter.java[m
[1m+++ /dev/null[m
[36m@@ -1,72 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import android.content.Context;[m
[31m-import android.view.LayoutInflater;[m
[31m-import android.view.View;[m
[31m-import android.view.ViewGroup;[m
[31m-import android.widget.ArrayAdapter;[m
[31m-import android.widget.ImageView;[m
[31m-import android.widget.TextView;[m
[31m-[m
[31m-/**[m
[31m- * The FragmentListAdapter class is the ArrayAdapter for the FragmentList[m
[31m- * Fragment. It handles inflating the list's views by extracting the strings[m
[31m- * from the array FragmentInfo objects passed to its constructor. Activities[m
[31m- * using this fragment must implement the StoryFragmentListListener interface.[m
[31m- * [m
[31m- * @author Jesse Huard[m
[31m- * @version 1.0, 30/10/13[m
[31m- * [m
[31m- */[m
[31m-[m
[31m-public class FragmentListAdapter extends ArrayAdapter<FragmentInfo> {[m
[31m-[m
[31m-	/**[m
[31m-	 * Holds the application context.[m
[31m-	 */[m
[31m-	private final Context context;[m
[31m-[m
[31m-	/**[m
[31m-	 * The array of StoryFragment information displayed in the list.[m
[31m-	 */[m
[31m-	private final FragmentInfo[] info;[m
[31m-[m
[31m-	public FragmentListAdapter(Context context, FragmentInfo[] info) {[m
[31m-		super(context, R.layout.story_fragment_list_item, info);[m
[31m-		this.context = context;[m
[31m-		this.info = info;[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public View getView(int position, View convertView, ViewGroup parent) {[m
[31m-		LayoutInflater inflater = (LayoutInflater) context[m
[31m-				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);[m
[31m-[m
[31m-		// Inflate the view[m
[31m-		View rowView = inflater.inflate(R.layout.story_fragment_list_item,[m
[31m-				parent, false);[m
[31m-[m
[31m-		// Get the view's TextViews[m
[31m-		TextView title = (TextView) rowView[m
[31m-				.findViewById(R.id.fragment_list_title);[m
[31m-		TextView description = (TextView) rowView[m
[31m-				.findViewById(R.id.fragment_list_description);[m
[31m-[m
[31m-		// Copy the story fragment's information into the view[m
[31m-		title.setText(info[position].getTitle());[m
[31m-		description.setText(info[position].getDescription());[m
[31m-[m
[31m-		return rowView;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Gets the ID of the StoryFragment at the selected position.[m
[31m-	 * [m
[31m-	 * @param position[m
[31m-	 *            the position of the desired story fragment ID.[m
[31m-	 * @return the ID of the selected story fragment[m
[31m-	 */[m
[31m-	public int getIdAtPosition(int position) {[m
[31m-		return info[position].getFragmentId();[m
[31m-	}[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/GlobalManager.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/GlobalManager.java[m
[1mdeleted file mode 100644[m
[1mindex f371f95..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/GlobalManager.java[m
[1m+++ /dev/null[m
[36m@@ -1,107 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import android.app.Application;[m
[31m-[m
[31m-/**[m
[31m- * The GlobalManager class is a class used to hold global story access managers.[m
[31m- * These managers can be used to load stories that are then accessed across[m
[31m- * multiple activities without the need to pass the story data around from[m
[31m- * activity to activity. This class extends the default Android Application to[m
[31m- * provide global state persistence.[m
[31m- * [m
[31m- * [m
[31m- * @author Jesse Huard[m
[31m- * @version 1.0, 29/10/13[m
[31m- * @see android.app.Application[m
[31m- * [m
[31m- */[m
[31m-public class GlobalManager extends Application {[m
[31m-	/**[m
[31m-	 * The Application's ReadStoryManager.[m
[31m-	 * [m
[31m-	 * @see #getReadManager()[m
[31m-	 * @see #setReadManager()[m
[31m-	 */[m
[31m-	private static ReadStoryManager readManager;[m
[31m-[m
[31m-	/**[m
[31m-	 * The Application's EditStoryManager.[m
[31m-	 * [m
[31m-	 * @see #getEditManager()[m
[31m-	 * @see #setEditManager()[m
[31m-	 */[m
[31m-	private static EditStoryManager editManager;[m
[31m-[m
[31m-	/**[m
[31m-	 * The Application's LibraryManager.[m
[31m-	 * [m
[31m-	 * @see #getLibraryManager()[m
[31m-	 * @see #setLibraryManager()[m
[31m-	 */[m
[31m-	private static LibraryManager libraryManager;[m
[31m-[m
[31m-	/**[m
[31m-	 * Get the Application's LibraryManager.[m
[31m-	 * [m
[31m-	 * @return the Application's LibraryManager.[m
[31m-	 */[m
[31m-	public LibraryManager getLibraryManager() {[m
[31m-		return libraryManager;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Get the Application's ReadStoryManager.[m
[31m-	 * [m
[31m-	 * @return the Application's ReadStoryManager.[m
[31m-	 */[m
[31m-	public ReadStoryManager getReadManager() {[m
[31m-		return readManager;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Get the Application's EditStoryManager.[m
[31m-	 * [m
[31m-	 * @return the Application's EditStoryManager.[m
[31m-	 */[m
[31m-	public EditStoryManager getEditManager() {[m
[31m-		return editManager;[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Simultaneously set the Read and Edit Managers for the story described in[m
[31m-	 * the library by the ID argument. This is the preferred method for setting[m
[31m-	 * the managers for local reading.[m
[31m-	 * [m
[31m-	 * @param storyId[m
[31m-	 *            the ID of the story to be managed.[m
[31m-	 */[m
[31m-	public void setStoryManagers(int storyId) {[m
[31m-		Story story = libraryManager.getStory(storyId);[m
[31m-		readManager.setStory(story); // Do we want to just construct a new one?[m
[31m-		editManager.setStory(story);[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Set the ReadStoryManager for the story described in the library by the ID[m
[31m-	 * argument.[m
[31m-	 * [m
[31m-	 * @param storyId[m
[31m-	 *            the ID of the story to be managed.[m
[31m-	 */[m
[31m-	public void setReadManager(int storyId) {[m
[31m-		Story story = libraryManager.getStory(storyId);[m
[31m-		readManager.setStory(story); // Do we want to just construct a new one?[m
[31m-	}[m
[31m-[m
[31m-	/**[m
[31m-	 * Set the EditStoryManager for the story described in the library by the ID[m
[31m-	 * argument.[m
[31m-	 * [m
[31m-	 * @param storyId[m
[31m-	 *            the ID of the story to be managed.[m
[31m-	 */[m
[31m-	public void setEditManager(int storyId) {[m
[31m-		Story story = libraryManager.getStory(storyId);[m
[31m-		editManager.setStory(story); // Do we want to just construct a new one?[m
[31m-	}[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/History.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/History.java[m
[1mindex 31387da..8a0e262 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/History.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/History.java[m
[36m@@ -1,143 +1,20 @@[m
[31m-/*[m
[31m-History class for CreateYourOwnAdventure.[m
[31m-Keeps a memory of past read fragments[m
[31m-[m
[31m-     Copyright  ©2013 Reginald Miller[m
[31m-    <Contact: rmiller3@ualberta.ca>[m
[31m-    [m
[31m-    License GPLv3: GNU GPL Version 3[m
[31m-    <http://gnu.org/licenses/gpl.html>.[m
[31m-    [m
[31m-    This program is free software: you can redistribute it and/or modify[m
[31m-    it under the terms of the GNU General Public License as published by[m
[31m-    the Free Software Foundation, either version 3 of the License, or[m
[31m-    (at your option) any later version.[m
[31m-[m
[31m-    This program is distributed in the hope that it will be useful,[m
[31m-    but WITHOUT ANY WARRANTY; without even the implied warranty of[m
[31m-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the[m
[31m-    GNU General Public License for more details.[m
[31m-[m
[31m-    You should have received a copy of the GNU General Public License[m
[31m-    along with this program. If not, see <http://www.gnu.org/licenses/>.[m
[31m- */[m
[31m-[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.ObjectStreamException;[m
[31m-import java.io.Serializable;[m
 import java.util.ArrayList;[m
 [m
[31m-/**[m
[31m- * An instance of this class exists when a story is[m
[31m- * created, keeping track of which fragment[m
[31m- * was last viewed in read mode by a user.[m
[31m- * [m
[31m- * @author Reginald Miller[m
[31m- *[m
[31m- */[m
[32m+[m[32mpublic class History {[m
 [m
[31m-public class History implements Serializable {[m
[31m-[m
[31m-	private ArrayList<Integer> historyStack;[m
[31m-	[m
[31m-	public History() {[m
[31m-		historyStack = new ArrayList<Integer>();[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Returns the most recently-viewed fragment, or[m
[31m-	 * null if the stack is empty.[m
[31m-	 * [m
[31m-	 * @return Integer ID of last-viewed fragment.[m
[31m-	 */[m
[31m-	public Integer getMostRecent() {[m
[31m-		[m
[31m-		int size = historyStack.size();[m
[31m-				[m
[31m-		if (size > 0) {[m
[31m-			int lastIndex = size - 1;[m
[31m-			return historyStack.get(lastIndex);[m
[31m-		}[m
[31m-		//Returns -1 if stack is empty[m
[31m-		return null;[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Pops the latest-viewed fragment from the stack[m
[31m-	 * and returns the id of the next most recently viewed[m
[31m-	 * fragment.[m
[31m-	 * If on the first-viewed fragment, returns itself.[m
[31m-	 * If stack is empty, returns null.[m
[31m-	 * [m
[31m-	 * @return Second-last-viewed fragment.[m
[31m-	 */[m
[31m-	public Integer goBack() {[m
[31m-		[m
[31m-		int size = historyStack.size();[m
[31m-		[m
[31m-		if (size > 1) {[m
[31m-			int lastIndex = size - 1;[m
[31m-			historyStack.remove(lastIndex);[m
[31m-			return historyStack.get(lastIndex - 1);[m
[31m-		}[m
[31m-		//If currently on first fragment, return itself[m
[31m-		else if (size == 1) {[m
[31m-			int lastIndex = size - 1;[m
[31m-			historyStack.remove(lastIndex);[m
[31m-			return null;[m
[31m-		}[m
[31m-		//If stack is empty[m
[31m-		else {[m
[31m-			return null;[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Pushes the newest fragment being viewed to the stack.[m
[31m-	 * [m
[31m-	 * @param fragment_id   ID of fragment to be pushed to stack.[m
[31m-	 */[m
[31m-	public void pushToStack(Integer fragmentId) {[m
[31m-		historyStack.add(fragmentId);[m
[31m-		return;[m
[31m-	}[m
[32m+[m	[32mprivate ArrayList<String> history_stack;[m
 	[m
[31m-	//Called if user wishes to start from beginning of story[m
[31m-	/**[m
[31m-	 * Clears the stack so that user may start again.[m
[31m-	 */[m
[31m-	public void clearHistory() {[m
[31m-		historyStack.clear();[m
[32m+[m	[32mpublic void get_most_recent() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param out   ObjectOutputStream to write with[m
[31m-	 * @throws IOException[m
[31m-	 */[m
[31m-	private void writeObject(java.io.ObjectOutputStream out)[m
[31m-		     throws IOException {[m
[31m-		out.writeObject(historyStack);[m
[32m+[m	[32mpublic void go_back() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param in   ObjectInputStream to read with[m
[31m-	 * @throws IOException[m
[31m-	 * @throws ClassNotFoundException[m
[31m-	 */[m
[31m-	private void readObject(java.io.ObjectInputStream in)[m
[31m-		     throws IOException, ClassNotFoundException {[m
[31m-		historyStack = (ArrayList<Integer>) in.readObject();[m
[31m-		return;[m
[31m-	}[m
[31m-		 [m
[31m-	private void readObjectNoData() [m
[31m-		     throws ObjectStreamException {[m
[32m+[m	[32mpublic void clear_history() {[m
 		return;[m
 	}[m
 	[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Image.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Image.java[m
[1mindex 4572e6a..fccab2e 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Image.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Image.java[m
[36m@@ -1,12 +1,9 @@[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.Serializable;[m
[31m-[m
 /*[m
  * Class for Image type Media. Uses a String which refers to the resource name.[m
  */[m
[31m-public class Image implements Media<String>, Serializable {[m
[32m+[m[32mpublic class Image implements Media<String>{[m
 	private String content;[m
 	private MediaInteractionManager manager;[m
 [m
[36m@@ -33,13 +30,4 @@[m [mpublic class Image implements Media<String>, Serializable {[m
 	public String toString() {[m
 		return this.content.toString();[m
 	}[m
[31m-	[m
[31m-	private void writeObject(java.io.ObjectOutputStream out) throws IOException {[m
[31m-		out.writeObject(content);[m
[31m-	}[m
[31m-[m
[31m-	private void readObject(java.io.ObjectInputStream in) throws IOException,[m
[31m-			ClassNotFoundException {[m
[31m-		content = (String) in.readObject();[m
[31m-	}[m
 }[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Library.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Library.java[m
[1mindex 803e03f..3158d9f 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Library.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Library.java[m
[36m@@ -1,129 +1,20 @@[m
[31m-/*[m
[31m-Library class for CreateYourOwnAdventure.[m
[31m-This deals with the high-level maintenance of stories and how[m
[31m-they are handled.[m
[31m-[m
[31m-     Copyright  ©2013 Reginald Miller[m
[31m-    <Contact: rmiller3@ualberta.ca>[m
[31m-    [m
[31m-    License GPLv3: GNU GPL Version 3[m
[31m-    <http://gnu.org/licenses/gpl.html>.[m
[31m-    [m
[31m-    This program is free software: you can redistribute it and/or modify[m
[31m-    it under the terms of the GNU General Public License as published by[m
[31m-    the Free Software Foundation, either version 3 of the License, or[m
[31m-    (at your option) any later version.[m
[31m-[m
[31m-    This program is distributed in the hope that it will be useful,[m
[31m-    but WITHOUT ANY WARRANTY; without even the implied warranty of[m
[31m-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the[m
[31m-    GNU General Public License for more details.[m
[31m-[m
[31m-    You should have received a copy of the GNU General Public License[m
[31m-    along with this program. If not, see <http://www.gnu.org/licenses/>.[m
[31m- */[m
[31m-[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.ObjectStreamException;[m
[31m-import java.io.Serializable;[m
 import java.util.ArrayList;[m
 [m
[31m-/**[m
[31m- * This class is designed to maintain all of the[m
[31m- * stories that are stored locally on the user's[m
[31m- * device.[m
[31m- * [m
[31m- * @author Reginald Miller[m
[31m- *[m
[31m- */[m
[31m-[m
[31m-public class Library implements Serializable {[m
[32m+[m[32mpublic class Library {[m
 	[m
[31m-	private StoryList storyList;[m
[32m+[m	[32mprivate ArrayList<Story> story_list;[m
 	[m
[31m-	public Library() {[m
[31m-		this.storyList = new StoryList();[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Creates a new story and adds it to the list[m
[31m-	 */[m
[31m-	public void createNewStory() {[m
[31m-		storyList.addStory(new Story());[m
[32m+[m	[32mpublic void create_new_story() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * Gets the story to the list and passes it to whoever[m
[31m-	 * requests it. Returns null if pos is out of range.[m
[31m-	 * [m
[31m-	 * @param storyId   ID of the story[m
[31m-	 * @return   Returns the story with that ID[m
[31m-	 */[m
[31m-	public Story getStory(int storyId) {[m
[31m-		return storyList.getStory(storyId);[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Deletes the story at a given position in the list[m
[31m-	 * [m
[31m-	 * @param storyId   ID of story to remove[m
[31m-	 */[m
[31m-	public void removeStory(int storyId) {[m
[31m-		storyList.removeStory(storyId);[m
[32m+[m	[32mpublic void delete_story() {[m
 		return;[m
 	}[m
 	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param ArrayList of story IDs to be removed[m
[31m-	 */[m
[31m-	public void removeMultipleStories(ArrayList<Integer> stories) {[m
[31m-		for (int storyId : stories) {[m
[31m-			storyList.removeStory(storyId);[m
[31m-			return;[m
[31m-		}[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * Puts a new or updated story into the list of stories[m
[31m-	 * with the ID[m
[31m-	 * [m
[31m-	 * @param storyId   ID of story to update[m
[31m-	 * @param story   Story that has been updated[m
[31m-	 */[m
[31m-	public void updateStory(int storyId, Story story) {[m
[31m-		storyList.updateStory(storyId, story);[m
[31m-		return;[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param out   ObjectOutputStream to write with[m
[31m-	 * @throws IOException[m
[31m-	 */[m
[31m-	private void writeObject(java.io.ObjectOutputStream out)[m
[31m-		     throws IOException {[m
[31m-		out.writeObject(storyList);[m
[31m-		return;[m
[31m-	}[m
[31m-	[m
[31m-	/**[m
[31m-	 * [m
[31m-	 * @param in   ObjectInputStream to read with[m
[31m-	 * @throws IOException[m
[31m-	 * @throws ClassNotFoundException[m
[31m-	 */[m
[31m-	private void readObject(java.io.ObjectInputStream in)[m
[31m-		     throws IOException, ClassNotFoundException {[m
[31m-		storyList = (StoryList) in.readObject();[m
[31m-		return;[m
[31m-	}[m
[31m-		 [m
[31m-	private void readObjectNoData() [m
[31m-		     throws ObjectStreamException {[m
[32m+[m	[32mpublic void edit_story() {[m
 		return;[m
 	}[m
 [m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentActivity.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentActivity.java[m
[1mnew file mode 100644[m
[1mindex 0000000..35376b8[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentActivity.java[m
[36m@@ -0,0 +1,88 @@[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * List of things to ask team about:[m
[32m+[m[32m *[m[41m [m
[32m+[m[32m * Story/Fragment attributes: manager cannot access them if they are private[m
[32m+[m[32m * Story: getter for a fragment using fragment name at story level?[m
[32m+[m[32m * History_stack: what is the setter for adding to the stack (save history)?[m
[32m+[m[32m * History_stack: what is the setter for deleting most recent of stack?[m
[32m+[m[32m * Fragment: is there a getter for content_list?[m
[32m+[m[32m * MediaHandler: how do I get a media's type?[m
[32m+[m[32m * Choice_map: there's some sort of changes to it afaik?[m
[32m+[m[32m *[m[41m [m
[32m+[m[32m * Self reminder for git usages:[m
[32m+[m[32m * git status[m
[32m+[m[32m * git commit -m -a "comments"[m
[32m+[m[32m * git push http://github.com/CMPUT301F13T01/CreateYourOwnAdventure etai[m
[32m+[m[32m *[m[41m [m
[32m+[m[32m * do not use name of class in a class's locat variable[m
[32m+[m[32m * use camel case (no understore style)[m
[32m+[m[32m */[m
[32m+[m
[32m+[m[32mimport android.app.Activity;[m
[32m+[m[32mimport android.content.Intent;[m
[32m+[m[32mimport android.os.Bundle;[m
[32m+[m[32mimport android.view.Menu;[m
[32m+[m[32mimport android.view.MenuItem;[m
[32m+[m
[32m+[m[32mpublic class ReadFragmentActivity extends Activity {[m
[32m+[m
[32m+[m	[32mReadStoryManager manager;[m
[32m+[m
[32m+[m	[32m/** Called when the activity is first created. */[m
[32m+[m	[32m@Override[m
[32m+[m	[32mprotected void onCreate(Bundle savedInstanceState) {[m
[32m+[m		[32msuper.onCreate(savedInstanceState);[m
[32m+[m
[32m+[m		[32m// intent has the Story, and name of the fragment to display[m
[32m+[m		[32mIntent intent = getIntent();[m
[32m+[m		[32m// receive story via intent[m
[32m+[m		[32mBundle passed_story = intent.getExtras();[m
[32m+[m		[32mStory story = (Story) passed_story.getSerializable("story");[m
[32m+[m		[32m// receive id of story fragment to show[m
[32m+[m		[32mInteger fragment_id = intent.getIntExtra("fragment_id", 0);[m
[32m+[m
[32m+[m		[32m// set the view and controller[m
[32m+[m		[32mfinal ReadFragmentView thisView = new ReadFragmentView(this);[m
[32m+[m		[32mmanager = new ReadStoryManager(story, fragment_id, thisView, this);[m
[32m+[m
[32m+[m		[32m// display the fragment with the view[m
[32m+[m		[32mthis.setContentView(thisView);[m
[32m+[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * when activity pauses, need to save history stack[m
[32m+[m	[32m */[m
[32m+[m	[32mprotected void onPause() {[m
[32m+[m		[32msuper.onPause();[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * deals with user action bar selection[m
[32m+[m	[32m */[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic boolean onCreateOptionsMenu(Menu menu) {[m
[32m+[m		[32m// Inflate the menu; this adds items to the action bar if it is present.[m
[32m+[m		[32mgetMenuInflater().inflate(R.menu.read_menu, menu);[m
[32m+[m		[32mreturn true;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic boolean onOptionsItemSelected(MenuItem item) {[m
[32m+[m
[32m+[m		[32m// Handle presses on the action bar items[m
[32m+[m		[32mswitch (item.getItemId()) {[m
[32m+[m		[32mcase R.id.action_return_to_beginning:[m
[32m+[m			[32mmanager.toBeginning();[m
[32m+[m			[32mreturn true;[m
[32m+[m		[32mcase R.id.action_return_to_previous_page:[m
[32m+[m			[32mmanager.toPrevious();[m
[32m+[m			[32mreturn true;[m
[32m+[m		[32mdefault:[m
[32m+[m			[32mreturn super.onOptionsItemSelected(item);[m
[32m+[m		[32m}[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentView.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentView.java[m
[1mnew file mode 100644[m
[1mindex 0000000..3c249c3[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadFragmentView.java[m
[36m@@ -0,0 +1,84 @@[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m
[32m+[m[32mimport android.content.Context;[m
[32m+[m[32mimport android.widget.AdapterView.OnItemClickListener;[m
[32m+[m[32mimport android.widget.ArrayAdapter;[m
[32m+[m[32mimport android.widget.ImageView;[m
[32m+[m[32mimport android.widget.LinearLayout;[m
[32m+[m[32mimport android.widget.ListView;[m
[32m+[m[32mimport android.widget.ScrollView;[m
[32m+[m[32mimport android.widget.TextView;[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * @author Eddie[m
[32m+[m[32m *[m[41m [m
[32m+[m[32m */[m
[32m+[m[32mpublic class ReadFragmentView extends ScrollView {[m
[32m+[m
[32m+[m	[32m// declaration of variables[m
[32m+[m	[32mLinearLayout ll;[m
[32m+[m	[32mContext context;[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Constructor for the view. A LinearLayout is added to the ScrollView since[m
[32m+[m	[32m * ScrollView only accepts 1 possible view, and we need to populate the view[m
[32m+[m	[32m * with multiple views, depending on the story fragment and its content[m
[32m+[m	[32m * media files.[m
[32m+[m	[32m *[m[41m [m
[32m+[m	[32m * @param context the activity that the view belongs to[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic ReadFragmentView(Context c) {[m
[32m+[m		[32msuper(c);[m
[32m+[m
[32m+[m		[32m// add LinearLayout to ScrollView since ScrollView only accepts 1 view[m
[32m+[m		[32mll = new LinearLayout(context);[m
[32m+[m		[32mll.setOrientation(LinearLayout.VERTICAL);[m
[32m+[m		[32mthis.addView(ll);[m
[32m+[m
[32m+[m		[32m// all views within the ScrollView obviously shares the same context[m
[32m+[m		[32mcontext = c;[m
[32m+[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Add a TextView with content of string s to the current view[m
[32m+[m	[32m *[m[41m [m
[32m+[m	[32m * @param s[m
[32m+[m	[32m *            string to be displayed by the TextView[m
[32m+[m	[32m * @param context[m
[32m+[m	[32m *            the context of the view[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic void setTextView(String s) {[m
[32m+[m		[32mTextView tv = new TextView(context);[m
[32m+[m		[32mtv.setText("s");[m
[32m+[m		[32mll.addView(tv);[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Incomplete ImageView handler[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic void setImageView() {[m
[32m+[m		[32mImageView iv = new ImageView(context);[m
[32m+[m		[32mll.addView(iv);[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Shows the list of choices using their given flavor text[m
[32m+[m	[32m * @param choice_text Arraylist of strings indicating the flavor text[m
[32m+[m	[32m * @param onItemClickListener controller for the views[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic void setChoiceView(ArrayList<String> choice_text,[m
[32m+[m			[32mOnItemClickListener onItemClickListener) {[m
[32m+[m[41m		[m
[32m+[m		[32mListView lv = new ListView(context);[m
[32m+[m		[32mArrayAdapter<String> adapter = new ArrayAdapter<String>(context,[m
[32m+[m				[32mR.layout.list_choices, choice_text);[m
[32m+[m		[32mlv.setAdapter(adapter);[m
[32m+[m
[32m+[m		[32m// set the controller for these list items[m
[32m+[m		[32mlv.setOnItemClickListener(onItemClickListener);[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadStoryManager.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadStoryManager.java[m
[1mnew file mode 100644[m
[1mindex 0000000..d43ff61[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/ReadStoryManager.java[m
[36m@@ -0,0 +1,128 @@[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m
[32m+[m[32mimport android.content.Context;[m
[32m+[m[32mimport android.content.Intent;[m
[32m+[m[32mimport android.view.View;[m
[32m+[m[32mimport android.widget.AdapterView;[m
[32m+[m[32mimport android.widget.AdapterView.OnItemClickListener;[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * @author Eddie[m
[32m+[m[32m *[m
[32m+[m[32m */[m
[32m+[m[32mpublic class ReadStoryManager implements OnItemClickListener {[m
[32m+[m
[32m+[m	[32mReadFragmentView view = null;[m
[32m+[m	[32mFragment fragment = null;[m
[32m+[m	[32mContext context = null;[m
[32m+[m	[32mStory story;[m
[32m+[m
[32m+[m[41m	[m
[32m+[m	[32mpublic ReadStoryManager(final Story story, final Integer fragment_id,[m
[32m+[m			[32mfinal ReadFragmentView view, final ReadFragmentActivity context) {[m
[32m+[m		[32mthis.story = story;[m
[32m+[m		[32mthis.view = view;[m
[32m+[m		[32mthis.context = context;[m
[32m+[m[41m		[m
[32m+[m		[32m// fetch the fragment from the story level[m
[32m+[m		[32mFragmentList fragmentlist = story.getFragmentList();[m
[32m+[m		[32mfragment = fragmentlist.get_fragment(fragment_id);[m
[32m+[m
[32m+[m		[32m// set view's media according to media in fragment[m
[32m+[m[41m		[m
[32m+[m		[32m// get media_list, is there a getter for this?[m
[32m+[m		[32mArrayList<Media> media_list = fragment.content_list;[m
[32m+[m[41m		[m
[32m+[m		[32m// cycle through the media list[m
[32m+[m		[32mfor (int i = 0; i < media_list.size(); i++) {[m
[32m+[m[41m			[m
[32m+[m			[32m//get media's type somehow[m
[32m+[m			[32mClass media_type = media_list.get(i).getClass();[m
[32m+[m			[32mif(media_type == Text.class.getClass())[m
[32m+[m[41m			[m
[32m+[m			[32m//if type is text[m
[32m+[m				[32m//get media content's string as s[m
[32m+[m				[32mString s;[m
[32m+[m				[32mview.setTextView(s);[m
[32m+[m[41m			[m
[32m+[m			[32m//the rest are implemented later for iteration 2[m
[32m+[m[41m			[m
[32m+[m			[32m//else if type is image[m
[32m+[m			[32m//if(media_type == Image.class.getClass())[m
[32m+[m[41m			[m
[32m+[m			[32m//else if type is sound[m
[32m+[m			[32m//if(media_type == Audio.class.getClass())[m
[32m+[m[41m			[m
[32m+[m			[32m//else if type is video[m
[32m+[m			[32m//if(media_type == Video.class.getClass())[m
[32m+[m		[32m}[m
[32m+[m
[32m+[m		[32m// from story level with fragment id, get the choice map[m
[32m+[m		[32m// from choice map, use fragment id to get arraylist of choice object[m
[32m+[m		[32m// choice object has getters for flavor text and destination ID[m
[32m+[m[41m		[m
[32m+[m		[32m// if choice array list isn't null:[m
[32m+[m		[32m// choice = extract flavor text as an arraylist of strings for this fragment[m
[32m+[m[41m		[m
[32m+[m		[32m// if choice array is null, then no choices[m
[32m+[m[41m		[m
[32m+[m		[32m// set the view[m
[32m+[m		[32mview.setChoiceView(choice, this);[m
[32m+[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Go to the beginning of a story[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic void toBeginning() {[m
[32m+[m		[32mInteger first_page_id = story.get_first_page();[m
[32m+[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Go back to the previous page dictated by the history stack[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic void toPrevious() {[m
[32m+[m		[32mInteger previous_page_id = story.history_stack.go_back();[m
[32m+[m		[32m// history remove last from stack by itself[m
[32m+[m[41m		[m
[32m+[m		[32m//[m[41m [m
[32m+[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m	[32m/**[m
[32m+[m	[32m * On selection of a choice in view, direct the user to the next story fragment[m
[32m+[m	[32m * according to the choice map.[m
[32m+[m	[32m */[m
[32m+[m	[32m@Override[m
[32m+[m	[32mpublic void onItemClick(AdapterView<?> parent, View view, int position,[m
[32m+[m			[32mlong id) {[m
[32m+[m		[32m// When a choice is clicked:[m
[32m+[m
[32m+[m		[32m// Create an intent and pass the appropriate destination fragment in[m
[32m+[m		[32m// intent[m
[32m+[m		[32mIntent intent = new Intent(context, ReadFragmentActivity.class);[m
[32m+[m		[32m// destination = destination fragment according to choice map for this[m
[32m+[m		[32m// choice[m
[32m+[m		[32mIntent intent = createIntent(story, destinationId);[m
[32m+[m[41m		[m
[32m+[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m/**[m
[32m+[m	[32m * Create an intent with the story and necessary fragment ID in order to display the[m
[32m+[m	[32m * next necessary fragment[m
[32m+[m	[32m * @param story the Story object that the user is reading at the moment[m
[32m+[m	[32m * @param id the id of the fragment to display next[m
[32m+[m	[32m * @return the intent that has the story and the fragment id in a bundle[m
[32m+[m	[32m */[m
[32m+[m	[32mpublic Intent createIntent(Story story, Integer id) {[m
[32m+[m		[32mIntent intent = new Intent(context, ReadFragmentActivity.class);[m
[32m+[m		[32mintent.putExtra("story", story);[m
[32m+[m		[32mintent.putExtra("fragment_id", id);[m
[32m+[m		[32mreturn intent;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Sound.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Sound.java[m
[1mindex 251469e..d18ce89 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Sound.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Sound.java[m
[36m@@ -1,12 +1,9 @@[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.Serializable;[m
[31m-[m
 /*[m
  * Class for Sound type Media. Uses a String which refers to the resource name.[m
  */[m
[31m-public class Sound implements Media<String>, Serializable {[m
[32m+[m[32mpublic class Sound implements Media<String> {[m
 	private String content;[m
 	private MediaInteractionManager manager;[m
 [m
[36m@@ -33,13 +30,4 @@[m [mpublic class Sound implements Media<String>, Serializable {[m
 	public String toString() {[m
 		return this.content.toString();[m
 	}[m
[31m-	[m
[31m-	private void writeObject(java.io.ObjectOutputStream out) throws IOException {[m
[31m-		out.writeObject(content);[m
[31m-	}[m
[31m-[m
[31m-	private void readObject(java.io.ObjectInputStream in) throws IOException,[m
[31m-			ClassNotFoundException {[m
[31m-		content = (String) in.readObject();[m
[31m-	}[m
 }[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Story.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Story.java[m
[1mindex 934a045..0ac73db 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Story.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Story.java[m
[36m@@ -144,12 +144,12 @@[m [mpublic class Story {[m
 	}[m
 	[m
 	public Integer new_session() {[m
[31m-		this.history_stack.clearHistory();[m
[32m+[m		[32mthis.history_stack.clear_history();[m
 		return this.first_page;[m
 	}[m
 	[m
 	public Integer resume_session() {[m
[31m-		Integer fragment_id = this.history_stack.getMostRecent();[m
[32m+[m		[32mInteger fragment_id = this.history_stack.get_most_recent();[m
 		return fragment_id;[m
 	}[m
 	[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListFragment.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListFragment.java[m
[1mdeleted file mode 100644[m
[1mindex 7df30e2..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListFragment.java[m
[1m+++ /dev/null[m
[36m@@ -1,31 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-import java.util.ArrayList;[m
[31m-[m
[31m-import android.app.ListFragment;[m
[31m-import android.os.Bundle;[m
[31m-import android.view.View;[m
[31m-import android.widget.ListView;[m
[31m-[m
[31m-public class StoryFragmentListFragment extends ListFragment {[m
[31m-	@Override[m
[31m-	public void onCreate(Bundle savedInstanceState) {[m
[31m-		super.onCreate(savedInstanceState);[m
[31m-[m
[31m-		GlobalManager app = (GlobalManager) getActivity().getApplication();[m
[31m-		ArrayList<FragmentInfo> info = app.getEditManager()[m
[31m-				.getFragmentInfoList();[m
[31m-[m
[31m-		FragmentListAdapter adapter = new FragmentListAdapter(getActivity(),[m
[31m-				info);[m
[31m-		setListAdapter(adapter);[m
[31m-	}[m
[31m-[m
[31m-	@Override[m
[31m-	public void onListItemClick(ListView l, View v, int position, long id) {[m
[31m-		StoryFragmentListListener activity = (StoryFragmentListListener) getActivity();[m
[31m-		FragmentListAdapter adapter = (FragmentListAdapter) getListAdapter();[m
[31m-		int storyId = adapter.getIdAtPosition(position)[m
[31m-		activity.onStoryFragmentSelected(storyId);[m
[31m-	}[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListListener.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListListener.java[m
[1mdeleted file mode 100644[m
[1mindex 4baa395..0000000[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryFragmentListListener.java[m
[1m+++ /dev/null[m
[36m@@ -1,16 +0,0 @@[m
[31m-package cmput301.f13t01.createyourownadventure;[m
[31m-[m
[31m-/**[m
[31m- * The StoryFragmentListListener interface is an interface that must be[m
[31m- * implemented by any activity using a StoryFragmentListFragment. This interface[m
[31m- * is to ensure that the activity implements the onStoryFragmentSelected callback[m
[31m- * function to correctly handle item selection in the ListFragment.[m
[31m- * [m
[31m- * @author Jesse Huard[m
[31m- * @version 1.0, 30/10/13[m
[31m- * [m
[31m- */[m
[31m-[m
[31m-public interface StoryFragmentListListener {[m
[31m-	void onStoryFragmentSelected(int storyId);[m
[31m-}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryInformationView.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryInformationView.java[m
[1mnew file mode 100644[m
[1mindex 0000000..0f7964c[m
[1m--- /dev/null[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/StoryInformationView.java[m
[36m@@ -0,0 +1,9 @@[m
[32m+[m[32mpackage cmput301.f13t01.createyourownadventure;[m
[32m+[m
[32m+[m[32mpublic class StoryInformationView {[m
[32m+[m
[32m+[m	[32mpublic StoryInformationView() {[m
[32m+[m		[32m// TODO Auto-generated constructor stub[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Text.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Text.java[m
[1mindex 68a3ead..0837494 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Text.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Text.java[m
[36m@@ -1,15 +1,11 @@[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.Serializable;[m
[31m-[m
 import android.text.SpannableString;[m
 [m
 /*[m
  * Class for Text type Media. Uses a SpannableString so it can be formatted.[m
  */[m
[31m-@SuppressWarnings("serial")[m
[31m-public class Text implements Media<SpannableString>, Serializable {[m
[32m+[m[32mpublic class Text implements Media<SpannableString> {[m
 	private SpannableString content;[m
 	private MediaInteractionManager manager;[m
 [m
[36m@@ -32,18 +28,9 @@[m [mpublic class Text implements Media<SpannableString>, Serializable {[m
 	public void setManager(MediaInteractionManager manager) {[m
 		this.manager = manager;[m
 	}[m
[31m-[m
[32m+[m[41m	[m
 	public String toString() {[m
 		return this.content.toString();[m
 	}[m
 [m
[31m-	private void writeObject(java.io.ObjectOutputStream out) throws IOException {[m
[31m-		out.writeObject(content);[m
[31m-	}[m
[31m-[m
[31m-	private void readObject(java.io.ObjectInputStream in) throws IOException,[m
[31m-			ClassNotFoundException {[m
[31m-		content = (SpannableString) in.readObject();[m
[31m-	}[m
[31m-[m
 }[m
[1mdiff --git a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Video.java b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Video.java[m
[1mindex b77ebe3..52a3081 100644[m
[1m--- a/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Video.java[m
[1m+++ b/CreateYourOwnAdventure/src/cmput301/f13t01/createyourownadventure/Video.java[m
[36m@@ -1,14 +1,9 @@[m
 package cmput301.f13t01.createyourownadventure;[m
 [m
[31m-import java.io.IOException;[m
[31m-import java.io.Serializable;[m
[31m-[m
[31m-import android.text.SpannableString;[m
[31m-[m
 /*[m
  * Class for Video type Media. Uses a String which refers to the resource name.[m
  */[m
[31m-public class Video implements Media<String>, Serializable {[m
[32m+[m[32mpublic class Video implements Media<String> {[m
 	private String content;[m
 	private MediaInteractionManager manager;[m
 [m
[36m@@ -35,13 +30,4 @@[m [mpublic class Video implements Media<String>, Serializable {[m
 	public String toString() {[m
 		return this.content.toString();[m
 	}[m
[31m-	[m
[31m-	private void writeObject(java.io.ObjectOutputStream out) throws IOException {[m
[31m-		out.writeObject(content);[m
[31m-	}[m
[31m-[m
[31m-	private void readObject(java.io.ObjectInputStream in) throws IOException,[m
[31m-			ClassNotFoundException {[m
[31m-		content = (String) in.readObject();[m
[31m-	}[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/src/testSrc/testChoiceMap.java b/CreateYourOwnAdventure/src/testSrc/testChoiceMap.java[m
[1mdeleted file mode 100644[m
[1mindex e50f3f6..0000000[m
[1m--- a/CreateYourOwnAdventure/src/testSrc/testChoiceMap.java[m
[1m+++ /dev/null[m
[36m@@ -1,85 +0,0 @@[m
[31m-package testSrc;[m
[31m-[m
[31m-import java.util.ArrayList;[m
[31m-[m
[31m-import junit.framework.TestCase;[m
[31m-[m
[31m-import org.junit.Before;[m
[31m-import org.junit.Test;[m
[31m-[m
[31m-import cmput301.f13t01.createyourownadventure.Choice;[m
[31m-import cmput301.f13t01.createyourownadventure.ChoiceMap;[m
[31m-[m
[31m-public class testChoiceMap extends TestCase {[m
[31m-[m
[31m-        ChoiceMap choicemap;[m
[31m-        [m
[31m-        @Before[m
[31m-        public void setUp() throws Exception {[m
[31m-                [m
[31m-                super.setUp();[m
[31m-                this.choicemap = new ChoiceMap();[m
[31m-                [m
[31m-        }[m
[31m-[m
[31m-        @Test[m
[31m-        public void testAddChoice() {[m
[31m-                choicemap.addChoice(1, new Choice(1, 2, "Hello"));[m
[31m-                ArrayList<Choice> testList = choicemap.getChoices(1);[m
[31m-                Choice choice = testList.get(0);[m
[31m-                assertTrue(choice.getSourceId() == 1);[m
[31m-                assertTrue(choice.getDestinationId() == 2);[m
[31m-                [m
[31m-                choicemap.addChoice(1, new Choice(1, 3, "Hello Again"));[m
[31m-                testList = choicemap.getChoices(1);[m
[31m-                choice = testList.get(1);[m
[31m-                assertTrue(choice.getSourceId() == 1);[m
[31m-                assertTrue(choice.getDestinationId() == 3);[m
[31m-                choice = testList.get(0);[m
[31m-                assertTrue(choice.getSourceId() == 1);[m
[31m-                assertTrue(choice.getDestinationId() == 2);[m
[31m-                [m
[31m-                testList = choicemap.getChoices(2);[m
[31m-                assertTrue(testList == null);[m
[31m-        }[m
[31m-        [m
[31m-        @Test[m
[31m-        public void testDeleteChoice() {[m
[31m-                choicemap.addChoice(1, new Choice(1, 2, "Hello"));[m
[31m-                choicemap.addChoice(1, new Choice(1, 3, "Hello again"));[m
[31m-                choicemap.addChoice(1, new Choice(1, 4, "Hello goodbye"));[m
[31m-                assertTrue(choicemap.deleteChoice(1, 1));[m
[31m-                ArrayList<Choice> testList = choicemap.getChoices(1);[m
[31m-                Choice choice = testList.get(1);[m
[31m-                assertTrue(choice.getSourceId() == 1);[m
[31m-                assertTrue(choice.getDestinationId() == 4);[m
[31m-        }[m
[31m-        [m
[31m-        @Test[m
[31m-        public void testUpdateChoice() {[m
[31m-                choicemap.addChoice(1, new Choice(1, 2, "Hello"));[m
[31m-                choicemap.addChoice(1, new Choice(1, 3, "Hello again"));[m
[31m-                choicemap.addChoice(1, new Choice(1, 4, "Hello goodbye"));[m
[31m-                assertTrue(choicemap.updateChoice(1, 1, new Choice(1, 5, "Who am I?")));[m
[31m-                ArrayList<Choice> testList = choicemap.getChoices(1);[m
[31m-                Choice choice = testList.get(1);[m
[31m-                assertTrue(choice.getSourceId() == 1);[m
[31m-                assertTrue(choice.getDestinationId() == 5);[m
[31m-        }[m
[31m-        [m
[31m-        @Test[m
[31m-        public void testCleanFragmentReferences() {[m
[31m-                choicemap.addChoice(1, new Choice(1, 2, "Hello"));[m
[31m-                choicemap.addChoice(2, new Choice(2, 3, "What?"));[m
[31m-                choicemap.addChoice(2, new Choice(2, 4, "Hey!"));[m
[31m-                choicemap.addChoice(2, new Choice(2, 4, "Hey2!"));[m
[31m-                choicemap.addChoice(4, new Choice(4, 1, "Haha!"));[m
[31m-                //Should remove all choices referencing fragment 4[m
[31m-                choicemap.cleanFragmentReferences(4);[m
[31m-                ArrayList<Choice> testList = choicemap.getChoices(4);[m
[31m-                assertTrue(testList == null);[m
[31m-                testList = choicemap.getChoices(2);[m
[31m-                assertTrue(testList.size() == 1);[m
[31m-        }[m
[31m-[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/CreateYourOwnAdventure/src/testSrc/testHistory.java b/CreateYourOwnAdventure/src/testSrc/testHistory.java[m
[1mdeleted file mode 100644[m
[1mindex 6f2c49a..0000000[m
[1m--- a/CreateYourOwnAdventure/src/testSrc/testHistory.java[m
[1m+++ /dev/null[m
[36m@@ -1,39 +0,0 @@[m
[31m-package testSrc;[m
[31m-[m
[31m-import junit.framework.TestCase;[m
[31m-[m
[31m-import org.junit.Before;[m
[31m-import org.junit.Test;[m
[31m-[m
[31m-import cmput301.f13t01.createyourownadventure.History;[m
[31m-[m
[31m-public class testHistory extends TestCase {[m
[31m-	[m
[31m-	History history;[m
[31m-[m
[31m-	@Before[m
[31m-	public void setUp() throws Exception {[m
[31m-		this.history = new History();[m
[31m-	}[m
[31m-[m
[31m-	@Test[m
[31m-	public void testGetMostRecent() {[m
[31m-		assertTrue(history.getMostRecent() == null);[m
[31m-		history.pushToStack(1);[m
[31m-		history.pushToStack(2);[m
[31m-		history.pushToStack(3);[m
[31m-		assertTrue(history.getMostRecent() == 3);[m
[31m-	}[m
[31m-	[m
[31m-	@Test[m
[31m-	public void testGoBack() {[m
[31m-		assertTrue(history.goBack() == null);[m
[31m-		history.pushToStack(0);[m
[31m-		assertTrue(history.goBack() == null);[m
[31m-		assertTrue(history.getMostRecent() == null);[m
[31m-		history.pushToStack(0);[m
[31m-		history.pushToStack(1);[m
[31m-		assertTrue(history.getMostRecent() == 1);[m
[31m-	}[m
[31m-[m
[31m-}[m
[1mdiff --git a/doc/minutes/Oct29 Meeting.JPG b/doc/minutes/Oct29 Meeting.JPG[m
[1mdeleted file mode 100644[m
[1mindex 9ee6de7..0000000[m
Binary files a/doc/minutes/Oct29 Meeting.JPG and /dev/null differ
[1mdiff --git a/doc/minutes/Oct29Meeting2.JPG b/doc/minutes/Oct29Meeting2.JPG[m
[1mdeleted file mode 100644[m
[1mindex 01476c7..0000000[m
Binary files a/doc/minutes/Oct29Meeting2.JPG and /dev/null differ
