/**
 * Copyright 2017 Robert Streetman
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package distances;

/**
 * Distance Strategy interface for algorithms measuring edit distance.
 *
 * @author Robert Streetman
 */
public interface EditDistanceStrategy {
    /**
     * Standard interface to find distance between two strings.
     * 
     * @param strA    String A
     * @param strB    String B
     * @return double Distance between endpoint A and endpoint B.
     */
    //TODO: Throw exception for unequal/invalid strings.
    public int distance(String strA, String strB); 
}
