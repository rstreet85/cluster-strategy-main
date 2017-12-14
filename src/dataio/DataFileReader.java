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
package dataio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Static class for returning an array of data values from CSV/TSV files using Apache Commons library.
 * 
 * @author Robert Streetman
 */
public class DataFileReader {
    
    /**
     * This method accepts a CSV file, no headers, and returns an array of values expressed as double.
     * 
     * @param dataFile, CSV file containing only numeric data to be clustered.
     * @return Array of values expressed as double values.
     */
    public static double[][] ReadCSVFile(File dataFile) {
        double[][] data = null;                 //Code calling this method should check for null, signifying error.
        
        try (FileReader reader = new FileReader(dataFile)) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);            
            List records = parser.getRecords();
            
            //Read first record to find number of dimensions.
            CSVRecord firstRecord = (CSVRecord) records.get(0);
            int numRecords = records.size();    //Assuming each line is a data point
            int numDimen = firstRecord.size();  //Assuming all data points have equal dimensionality
            data = new double[numRecords][numDimen];
            
            //Read each row as an n-dimensional data point
            for (int i = 0; i < numRecords; i++) {
                CSVRecord thisRecord = (CSVRecord) records.get(i);
                
                for (int j = 0; j < numDimen; j++) {
                    data[i][j] = Double.parseDouble(thisRecord.get(j)); //Assuming all data is numeric
                }
            }
            
            //TODO: Validate data read for equal dimensionality.
        } catch (IOException ex) {
            System.out.format("Error reading data file: %s...%n", ex.getMessage());
        }
        
        return data;
    }
    
    /**
     * This method accepts a TSV file, no headers, and returns an array of values expressed as double.
     * 
     * @param dataFile, TSV file containing only numeric data to be clustered.
     * @return Array of values expressed as double values.
     */
    public static double[][] ReadTSVFile(File dataFile) {
        double[][] data = null;
        //To-Do
        return data;
    }
    
    /**
     * 
     */
    //TODO
}
