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
 * Context for accessing edit distance algorithms with an EditDistanceStrategy.
 *
 * @author Robert Streetman
 */
public class EditDistanceContext {
    private EditDistanceStrategy editStrategy;
    
    /**
     * Method to set edit distance measure strategy to use.
     * 
     * @param strat The desired edit distance measure strategy.
     */
    public void setDistanceStrategy(EditDistanceStrategy strat) {
        editStrategy = strat;
    }
    
    /**
     * Method to find edit distance between two points with previously-specified strategy.
     * 
     * @param strA  String A
     * @param strB  String B
     * @return int  Distance between string A and string B.
     */
    public int getDistance(String strA, String strB) {
        return editStrategy.distance(strA, strB);
    }
}
