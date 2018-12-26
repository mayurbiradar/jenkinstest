package com.trihund.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.trihund.dao.TrihundDao;
import com.trihund.dto.Auth;
import com.trihund.dto.Form;
import com.trihund.dto.Response;

public class TrihundServiceImpl implements TrihundService {

	@Autowired
	TrihundDao dao;
	
	@Override
	public Response registerUser(Auth reg) {
		// TODO Auto-generated method stub
		return dao.registerUser(reg);
	}

	@Override
	public Response loginUser(Auth req) {
		// TODO Auto-generated method stub
		return dao.loginUser(req);
	}

	@Override
	public Response getDocTypes() {
		// TODO Auto-generated method stub
		return dao.getDocTypes();
	}

	@Override
	public Response getDocParams(Form fo) {
		// TODO Auto-generated method stub
		return dao.getDocParams(fo);
	}

	@Override
	public Response submitDoc(Form fo) {
		// TODO Auto-generated method stub
		return dao.submitDoc(fo);
	}

	@Override
	public Response getSubmittedDocs(Form fo) {
		// TODO Auto-generated method stub
		return dao.getSubmittedDocs(fo);
	}

	@Override
	public Response getFilledDetails(Form fo) {
		// TODO Auto-generated method stub
		return dao.getFilledDetails(fo);
	}

}
