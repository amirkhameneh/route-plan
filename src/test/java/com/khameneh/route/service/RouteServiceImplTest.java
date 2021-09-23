package com.khameneh.route.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khameneh.route.exception.InvalidArgumentException;
import com.khameneh.route.model.dto.LocationDTO;

import smile.clustering.CLARANS;




@SpringBootTest
public class RouteServiceImplTest {
	
	@InjectMocks
	RouteServiceImpl routeService;
	
	@Test
	public void it_throws_argument_exception_in_route_clustering () {
		// given
		List<LocationDTO> locations =  new ArrayList();
		LocationDTO location1 = new  LocationDTO();
		location1.setLat(-280.0);
		location1.setLng(57.0);
		locations.add(location1);
		
		
		// when, then
		assertThatThrownBy(() -> routeService.clusteringLocations(locations))
        		.isInstanceOf(InvalidArgumentException.class)
        		.hasMessage("The value of lat or lng is out of range");
		
	}
	
	@Test
	public void it_should_return_clustered_model () {
		
		//given
		double[][] steps = {{53.173017,6.607926},{52.214282,5.28576}
							,{51.87478,4.466694},{50.967117,5.826826}
							,{51.208839,6.020873},{51.988965,5.921694}
							,{52.303557,4.859719},{52.362463,4.913075}
							,{52.239233,6.893108},{51.607022,4.78928}
							,{51.841008,5.839595},{52.461168,4.622734}};
		// when 
		CLARANS<double[]> model = routeService.createModel(steps);
		
		//then
		assertTrue(model.k>0);
		
	}	

	@Test
	public void it_should_return_cluster_steps () {
		// given
		List<LocationDTO> locations =  new ArrayList();
		LocationDTO location1 = new  LocationDTO();
		location1.setLat(51.412644);
		location1.setLng(5.397495);
		locations.add(location1);
		
		LocationDTO location2 = new  LocationDTO();
		location2.setLat(53.33226);
		location2.setLng(6.746311);
		locations.add(location2);		
		
		LocationDTO location3 = new  LocationDTO();
		location3.setLat(52.461168);
		location3.setLng(4.622734);
		locations.add(location3);	

		LocationDTO location4 = new  LocationDTO();
		location4.setLat(52.239233);
		location4.setLng(6.893108);
		locations.add(location4);

		LocationDTO location5 = new  LocationDTO();
		location5.setLat(52.362463);
		location5.setLng(4.913075);
		locations.add(location5);
		
		LocationDTO location6 = new  LocationDTO();
		location6.setLat(52.303557);
		location6.setLng(4.859719);
		locations.add(location6);

		LocationDTO location7 = new  LocationDTO();
		location7.setLat(51.988965);
		location7.setLng(5.921694);
		locations.add(location7);

		LocationDTO location8 = new  LocationDTO();
		location8.setLat(51.988965);
		location8.setLng(6.020873);
		locations.add(location8);
		

		LocationDTO location9 = new  LocationDTO();
		location9.setLat(50.967117);
		location9.setLng(5.826826);
		locations.add(location9);
		
		LocationDTO location10 = new  LocationDTO();
		location10.setLat(51.87478);
		location10.setLng(4.466694);
		locations.add(location10);			

		// when 
		
		//then
		assertTrue(routeService.clusteringLocations(locations).size()>0);
		
	}		
	
	

	
	
}
