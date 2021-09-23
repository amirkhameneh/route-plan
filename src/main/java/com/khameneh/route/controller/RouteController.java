package com.khameneh.route.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khameneh.route.model.dto.ClusteredLocationDTO;
import com.khameneh.route.model.dto.LocationDTO;
import com.khameneh.route.service.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Includes Controllers for Media Management Application
 *   
 * @author Amir Khameneh amirkhameneh@yahoo.com
 */

@Api(value = "Route Planning webservice user manual")
@RestController
@RequestMapping(path = "/api")
public class RouteController {
	
	@Autowired
	RouteService routeService;
	
	
	@ApiOperation(value = "This Api gets a list of steps with their position and cluster then. "
				, notes = "It is assumed that distance between steps equals to their Eucleadian distance")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "route/getclusters", method= RequestMethod.POST)
    public ResponseEntity<List<ClusteredLocationDTO>> getClusters (@RequestBody List<LocationDTO> locations){
        return  new ResponseEntity<>(routeService.clusteringLocations(locations), HttpStatus.OK);

    }
    

}


