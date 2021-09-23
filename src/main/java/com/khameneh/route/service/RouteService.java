package com.khameneh.route.service;

import java.util.List;

import com.khameneh.route.model.dto.ClusteredLocationDTO;
import com.khameneh.route.model.dto.LocationDTO;

import smile.clustering.CLARANS;

public interface RouteService {
	
	public List<ClusteredLocationDTO> clusteringLocations(List<LocationDTO> locations);
	
	public CLARANS<double[]> createModel(double[][] steps);
	
	public void printDiagram(double[][] steps, CLARANS<double[]> model);
	
	
}
