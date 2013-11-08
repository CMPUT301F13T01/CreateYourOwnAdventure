/*
ChoiceListListener interface for CreateYourOwnAdventure.
The ChoiceListListener interface is an interface that must be
implemented by any activity using a ChoiceListFragment. This interface
is to ensure that the activity implements the onChoiceSelected callback
function to correctly handle item selection in the ListFragment.

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

/**
 * The ChoiceListListener interface is an interface that must be implemented by
 * any activity using a ChoiceListFragment. This interface is to ensure that the
 * activity implements the onChoiceSelected callback function to correctly
 * handle item selection in the ListFragment.
 * 
 * @author Jesse Huard
 * @version 1.0, 30/10/13
 * 
 */

public interface ChoiceListListener {

	/**
	 * Function to handle when a choice is selected in the choiceListFragment.
	 * 
	 * @param choice
	 *            the choice selected.
	 * @param position
	 *            the position of the choice in the ChoiceList array.
	 */
	void onChoiceSelected(Choice choice, int position);
}
