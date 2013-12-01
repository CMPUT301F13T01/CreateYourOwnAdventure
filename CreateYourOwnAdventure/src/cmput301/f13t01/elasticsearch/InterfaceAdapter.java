/*
InterfaceAdapter Class for CreateYourOwnAdventure.

This is an adapter class that allows for the serialization and
deserialization of Media interface objects when converting to and from
Json using Gson.

This code was provided by StackOverflow user Maciek Makowski, at the following link:
http://stackoverflow.com/questions/4795349/how-to-serialize-a-class-with-an-interface

    Copyright  �2013 Maciek Makowski
    <Contact: http://stackoverflow.com/users/424978/maciek-makowski>
    
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
    
    
    
    GSon/HttpClient License: Apache License, Version 2.0, January 2004.
    <http://www.apache.org/licenses/>
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package cmput301.f13t01.elasticsearch;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * This is an adapter to allow Gson to properly serialize and deserialize interface
 * objects so that they may retain knowledge of their type.
 * 
 * @author    Maciek Makowski
 *
 * @param <T>   The object type (this is an interface)
 */

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
	
	/**
	 * This allows for the serialization of objects that use an interface to be wrapped and retain
	 * memory of its type.
	 * 
	 * @return   Returns the JsonElement to be used by Gson
	 */
	public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
        final JsonObject wrapper = new JsonObject();
        wrapper.addProperty("type", object.getClass().getName());
        wrapper.add("data", context.serialize(object));
        return wrapper;
    }

	/**
	 * This allows for the deserialization of objects that use an interface in order to retain
	 * information of the implementing object.
	 * 
	 * @return   Returns the actual implementing object, with memory of its type.
	 */
    public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject wrapper = (JsonObject) elem;
        final JsonElement typeName = get(wrapper, "type");
        final JsonElement data = get(wrapper, "data");
        final Type actualType = typeForName(typeName); 
        return context.deserialize(data, actualType);
    }

    private Type typeForName(final JsonElement typeElem) {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    private JsonElement get(final JsonObject wrapper, String memberName) {
        final JsonElement elem = wrapper.get(memberName);
        if (elem == null) throw new JsonParseException("no '" + memberName + "' member found in what was expected to be an interface wrapper");
        return elem;
    }

}
