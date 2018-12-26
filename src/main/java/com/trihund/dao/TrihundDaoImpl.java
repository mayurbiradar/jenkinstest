package com.trihund.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.trihund.dto.Auth;
import com.trihund.dto.Form;
import com.trihund.dto.FormParam;
import com.trihund.dto.Response;

public class TrihundDaoImpl implements TrihundDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public Response loginUser(Auth a) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rowList=null;
		Response res=new Response();

		try{
			rowList=template.queryForList("select * from userauth.auth where username=? and password=?",new Object[]{a.getUname(),a.getUpass()});

			if(rowList!=null || rowList.isEmpty())
			{
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("uid", rowList.get(0).get("uid").toString());
				m.put("fullname", rowList.get(0).get("first_name").toString()+" "+rowList.get(0).get("last_name").toString());
				res.setData(m);
				res.setSuccess("true");
				res.setMessage("Login Successfull");	
			}else{
				res.setSuccess("false");
				res.setMessage("Login Failed");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Login Failed");
		}
		return res;
	}

	@Override
	public Response registerUser(Auth reg) {
		// TODO Auto-generated method stub
		Response res=new Response();

		try{
			Random rnd = new Random();
			int rn = 100000 + rnd.nextInt(900000);

			int n=template.update("INSERT INTO userauth.auth(uid, first_name, last_name, username, password, security_question, security_answer)	VALUES (?, ?, ?, ?, ?, ?, ?)",new Object[]{rn,reg.getFname(),reg.getLname(),reg.getUname(),reg.getUpass(),reg.getSque(),reg.getSans()});

			if(n>0)
			{
				res.setSuccess("true");
				res.setMessage("Registered Successfull");	
			}else{
				res.setSuccess("false");
				res.setMessage("Registerration Failed");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Registerration Failed");
		}
		return res;
	}

	@Override
	public Response getDocTypes() {
		// TODO Auto-generated method stub

		List<Map<String,Object>> rowList=null;

		Response res=new Response();

		try{
			rowList=template.queryForList("SELECT fid, fname FROM trihund.forms_master ",new Object[]{});

			if(rowList!=null || !rowList.isEmpty())
			{
				res.setData(rowList);
				res.setSuccess("true");
				res.setMessage("Forms List is Retrieved...");	
			}else{
				res.setSuccess("false");
				res.setMessage("Forms List is Retrieval Failed...");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Forms List is Retrieval Failed...");
		}
		return res;
	}

	@Override
	public Response getDocParams(Form fo) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rowList=null;

		Response res=new Response();
		try{
			String s=checkEntry(fo);

			if("yes".equals(s))
			{

			}else{

			}


			rowList=template.queryForList("select fp_id,param_name,param_type,param_rank from trihund.forms_param where fid=? order by param_rank asc", new Object[]{fo.getFid()});

			if(rowList!=null || !rowList.isEmpty())
			{
				res.setData(rowList);
				res.setSuccess("true");
				res.setMessage("Forms Param Retrieved...");	
			}else{
				res.setSuccess("false");
				res.setMessage("Forms Param Retrieval Failed...");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Forms Param Retrieval Failed...");
		}
		return res;
	}

	private String checkEntry(Form fo) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rowList=null;
		String ret="no";
		try{
			rowList=template.queryForList("select * from trihund.user_form_data where fid=? and uid=? ", new Object[]{fo.getFid(),fo.getUid()});

			if(rowList!=null || !rowList.isEmpty())
			{
				ret="yes";
			}else{
				ret="no";
			}
		}catch(Exception e)
		{
			ret="no";
		}

		return ret;
	}

	@Override
	public Response submitDoc(Form fo) {
		// TODO Auto-generated method stub

		Response res=new Response();

		try{

			int n=0;

			for(FormParam f:fo.getObj())
			{
				n=template.update("INSERT INTO trihund.user_form_data(uid, fid, fp_id, param_value)	VALUES ( ?, ?, ?, ?)",new Object[]{fo.getUid(),fo.getFid(),f.getFp_id(),f.getParam_name()});
			}	

			if(n>0)
			{
				res.setSuccess("true");
				res.setMessage("Submitted Successfully");	
			}else{
				res.setSuccess("false");
				res.setMessage("Submission Failed");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Submission Failed");
		}
		return res;
	}

	@Override
	public Response getSubmittedDocs(Form fo) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rowList=null;
		Response res=new Response();

		try{
			rowList=template.queryForList("select x.fid,x.fname,y.submitted_on from trihund.forms_master x,trihund.user_form_data y where x.fid=y.fid and  y.uid=? group by x.fid,x.fname,y.submitted_on",new Object[]{fo.getUid()});

			if(rowList!=null || rowList.isEmpty())
			{
				res.setData(rowList);
				res.setSuccess("true");
				res.setMessage("Fetched Successfully");	
			}else{
				res.setSuccess("false");
				res.setMessage("Failed");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Failed");
		}
		return res;	}

	@Override
	public Response getFilledDetails(Form fo) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rowList=null;
		Response res=new Response();

		try{
			rowList=template.queryForList("select distinct y.param_name,x.param_value,y.param_rank from trihund.user_form_data x,trihund.forms_param y where x.fp_id=y.fp_id and (x.fid=? and x.uid=?) order by y.param_rank",new Object[]{fo.getFid(),fo.getUid()});

			if(rowList!=null || rowList.isEmpty())
			{
				res.setData(rowList);
				res.setSuccess("true");
				res.setMessage("Fetched Successfully");	
			}else{
				res.setSuccess("false");
				res.setMessage("Failed");
			}}catch(Exception e)
		{
				res.setSuccess("false");
				res.setMessage("Failed");
		}
		return res;	
	}

}
