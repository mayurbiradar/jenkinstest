package com.trihund.service;

import com.trihund.dto.Auth;
import com.trihund.dto.Form;
import com.trihund.dto.Response;

public interface TrihundService {

	Response registerUser(Auth reg);

	Response loginUser(Auth req);

	Response getDocTypes();

	Response getDocParams(Form fo);

	Response submitDoc(Form fo);

	Response getSubmittedDocs(Form fo);

	Response getFilledDetails(Form fo);

}
