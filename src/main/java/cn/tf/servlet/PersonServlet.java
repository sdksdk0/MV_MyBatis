package cn.tf.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;




import cn.tf.entity.FilmInfo;
import cn.tf.entity.FilmType;
import cn.tf.mapper.PersonMapper;
import cn.tf.utils.MyBatisUtil;

public class PersonServlet  extends BasicServlet {
	private static final long serialVersionUID = 1L;
     

	SqlSession  session;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		if("findAll".equals(op)){
			findAll(request,response);
		}else if("addMVInfo".equals(op)){
			addMVInfo(request,response);
		}/*else if("updatePerson".equals(op)){
			updatePerson(request,response);
		}*/else if("deltePerson".equals(op)){
			deltePerson(request,response);
		}else if("findfilmType".equals(op)){
			findfilmType(request,response);
		}
	}

	
	//查找影片所有类型
	private void findfilmType(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		session=MyBatisUtil.getSession();
		PersonMapper personMapper=session.getMapper(PersonMapper.class);
		
		List<FilmType> list=personMapper.findfilmType();
		
		this.out(response, list);		
	}

	//删除
	private void deltePerson(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		session=MyBatisUtil.getAutoTransSession();
		PersonMapper personMapper=session.getMapper(PersonMapper.class);
		
		int result=personMapper.delete(Integer.parseInt(id));
		
		MyBatisUtil.close(session);
		this.out(response, result);		
		
	}
/*

	//修改
	private void updatePerson(HttpServletRequest request,
			HttpServletResponse response)  {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String birthday=request.getParameter("birthday");
		String gender=request.getParameter("gender");
		String career=request.getParameter("career");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		
		Person person=new Person();
		person.setName(name);
		person.setBirthday(birthday);
		person.setCareer(career);
		person.setAddress(address);
		person.setMobile(mobile);
		person.setGender(gender);
		person.setId(id);
		
		
		session=MyBatisUtil.getSession();
		PersonMapper personMapper=session.getMapper(PersonMapper.class);
		personMapper.update(person);
		MyBatisUtil.close(session);
		
		this.out(response,1);		
	}

*/
	//添加
	private void addMVInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String filemName=request.getParameter("filemName");
		String typeID=request.getParameter("typeID");
		String actor=request.getParameter("actor");
		String director=request.getParameter("director");
		String ticketPrice=request.getParameter("ticketPrice");
		
		FilmInfo person=new FilmInfo();
		person.setFilemName(filemName);
		person.setTypeID(Integer.parseInt(typeID));
		person.setActor(actor);
		person.setDirector(director);
		person.setTicketPrice(Double.parseDouble(ticketPrice));
		
		session=MyBatisUtil.getAutoTransSession();
		PersonMapper personMapper=session.getMapper(PersonMapper.class);
		int result=personMapper.add(person);
		MyBatisUtil.close(session);
		
		this.out(response,result);
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		session=MyBatisUtil.getSession();
		PersonMapper personMapper=session.getMapper(PersonMapper.class);
		List<FilmInfo>  list=personMapper.findAll();
		this.out(response, list);
		
	}
		

}
