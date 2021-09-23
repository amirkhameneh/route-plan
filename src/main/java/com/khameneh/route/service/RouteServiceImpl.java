package com.khameneh.route.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.khameneh.route.exception.InvalidArgumentException;
import com.khameneh.route.exception.RestExceptionHandler;
import com.khameneh.route.model.dto.ClusteredLocationDTO;
import com.khameneh.route.model.dto.LocationDTO;

import smile.clustering.CLARANS;
import smile.clustering.PartitionClustering;
import smile.math.distance.Distance;
import smile.plot.swing.Canvas;
import smile.plot.swing.ScatterPlot;

@Service
public class RouteServiceImpl implements RouteService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@Override
	public List<ClusteredLocationDTO> clusteringLocations(List<LocationDTO> locations) {
		
		double[][] steps = new double[locations.size()][2];
        for (int i = 0; i < locations.size(); i++) {
        	if (locations.get(i).getLng()> 200 || locations.get(i).getLng()< -200|| 
        		locations.get(i).getLat()> 200 || locations.get(i).getLat()< -200) {
                throw new InvalidArgumentException("The value of lat or lng is out of range");
            }
        	
        	steps[i][0] = locations.get(i).getLng();
        	steps[i][1] = locations.get(i).getLat();
        	locations.get(i).setIndex(i);
        }
        
        
		CLARANS<double[]> model = createModel(steps);
		
		printDiagram(steps, model);
		
		return locations.stream()
				.map(l-> {
					return new ClusteredLocationDTO(l.getId(),l.getCity(),l.getZip(),String.valueOf(l.getLat()),String.valueOf(l.getLng()),String.valueOf(model.predict(steps[l.getIndex()])));
				}).collect(Collectors.toList());
		
	}
	
	@Override
	public CLARANS<double[]> createModel(double[][] steps){
		
		
        // Eucledean Distance Matrix
        Distance<double[]> distance1 = (x, y) -> Math.sqrt(Math.pow(y[1]-x[1],2) + Math.pow(y[0]-x[0], 2));
        return PartitionClustering.run(20, () -> CLARANS.fit(steps, distance1, 7, 10));
   
	}
	
	@Override
	public void printDiagram(double[][] steps, CLARANS<double[]> model) {
		
        Canvas plot = ScatterPlot.of(steps, model.y, '*').canvas();
        final BufferedImage image = new BufferedImage ( 1000, 1000, BufferedImage.TYPE_INT_ARGB );
        final Graphics2D graphics2D = image.createGraphics ();
        
        plot.paint(graphics2D, 1000, 1000);
        
        try {
			ImageIO.write ( image, "png", new File ( "./clusters_visualisation.png" ) );
		} catch (IOException e) {
			LOGGER.error("Exception in wirting the file Caused By: ", e);;
		}
	}
}
