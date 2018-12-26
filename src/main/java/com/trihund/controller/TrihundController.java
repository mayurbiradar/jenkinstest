package com.trihund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trihund.dto.Auth;
import com.trihund.dto.Form;
import com.trihund.dto.Response;
import com.trihund.service.TrihundService;

@RestController
public class TrihundController {

	@Autowired
	TrihundService service;
	
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Response registerUser(@RequestBody Auth reg)
	{
		return service.registerUser(reg);
	}
	
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Response loginUser(@RequestBody Auth req)
	{
		return service.loginUser(req);
	}
    
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/getDocTypes",method=RequestMethod.GET)
	public Response getDocTypes()
	{
		return service.getDocTypes();
	}
    
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/getDocParams",method=RequestMethod.POST)
	public Response getDocParams(@RequestBody Form fo)
	{
		return service.getDocParams(fo);
	}
    
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/submitDoc",method=RequestMethod.POST)
	public Response submitDoc(@RequestBody Form fo)
	{
		return service.submitDoc(fo);
	}
    
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/getSubmittedDocs",method=RequestMethod.POST)
	public Response getSubmittedDocs(@RequestBody Form fo)
	{
		return service.getSubmittedDocs(fo);
	}
    
    @CrossOrigin(origins = "*")
	@RequestMapping(value="/getFilledDetails",method=RequestMethod.POST)
	public Response getFilledDetails(@RequestBody Form fo)
	{
		return service.getFilledDetails(fo);
	}
}
