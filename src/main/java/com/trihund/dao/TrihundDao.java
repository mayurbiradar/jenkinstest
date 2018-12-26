package com.trihund.dao;

import com.trihund.dto.Auth;
import com.trihund.dto.Form;
import com.trihund.dto.Response;

public interface TrihundDao {

	Response loginUser(Auth req);

	Response registerUser(Auth reg);

	Response getDocTypes();

	Response getDocParams(Form fo);

	Response submitDoc(Form fo);

	Response getSubmittedDocs(Form fo);

	Response getFilledDetails(Form fo);

}
