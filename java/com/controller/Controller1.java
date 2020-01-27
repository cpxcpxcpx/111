package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mapper.ArticleMapper;
import com.pojo.Article;
import com.pojo.Articleclass;
import com.pojo.Comment;
import com.pojo.User;
import com.service.Articleclassservice;
import com.service.Articleservice;
import com.service.Commentservice;
import com.service.Userservice;



@Controller
public class Controller1 {
	@Autowired
	private Userservice us;
	@Autowired
	private Articleclassservice as; 
	@Autowired
	private Articleservice aservice;
	@Autowired
	private Commentservice cs;
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<Articleclass> list1=as.showArticleclass();
		ArrayList<Article> list=aservice.showarticle();
		modelAndView.addObject("list1",list1);
		modelAndView.addObject("list",list);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@RequestMapping("/message")
	public ModelAndView message() {
		System.out.print("进来了");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("message");
		return modelAndView;
	}
	@RequestMapping("/search")
	public ModelAndView search(HttpServletRequest request) {
		System.out.print("进来了");
		String keyword=request.getParameter("keyword");
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<User> list1=us.selectUsersbyname(keyword);
		ArrayList<Article> list=aservice.selectbykeyword(keyword);
		modelAndView.addObject("list1",list1);
		modelAndView.addObject("list",list);
		modelAndView.setViewName("search");
		return modelAndView;
	}
	@RequestMapping("/showarticlebyclass")
	public ModelAndView showarticlebyclass(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String articleclasscontent=request.getParameter("articleclasscontent");
		ArrayList<Article> list1=aservice.selectbyclassname(articleclasscontent);
		modelAndView.addObject("list1",list1);
		modelAndView.addObject("articleclasscontent",articleclasscontent);
		modelAndView.setViewName("category");
		return modelAndView;
	}
	@RequestMapping("/articledetail")
	public ModelAndView articledetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Article article=aservice.selectarticle(Integer.parseInt(request.getParameter("id")));
		ArrayList<Article> list=aservice.showarticle();
		ArrayList<Comment> list1=cs.showcommentbyarticle(Integer.parseInt(request.getParameter("id")));
		modelAndView.addObject("article",article);
		modelAndView.addObject("list",list);
		modelAndView.addObject("list1",list1);
		modelAndView.setViewName("articledetail");
		return modelAndView;
	}
	@RequestMapping("/writearticle")
	public ModelAndView writearticle() {
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<Articleclass> list=as.showArticleclass();
		modelAndView.addObject("list",list);
		modelAndView.setViewName("writearticle");
		return modelAndView;
	}
	@RequestMapping("/registerpage")
	public String registerpage() {
		return "register";
	}
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "login";
	}
	@RequestMapping("/register")
	public String register(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		User u=new User();
		u.setAddress(address);
		u.setPassword(password);
		u.setPhone(phone);
		u.setUsername(username);
		us.sendSms(phone);
		us.insertUser(u);
		return "success";	
	}
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=us.selectUserbyname(username);
		ModelAndView modelAndView = new ModelAndView();
		if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
		HttpSession session=request.getSession();
		session.setAttribute("user", user);
		modelAndView.addObject("user",user);
		modelAndView.setViewName("redirect:index.html");
		System.out.print(user.getId());
		}
		else  modelAndView.setViewName("loginfail");
		return modelAndView;
		
	}
	@RequestMapping("/showname")
	@ResponseBody
	public String showname(HttpServletRequest request) {
		ArrayList<String>list=us.showname();
		System.out.println(list);
		if(list.contains(request.getParameter("username"))) {
			return "<font color='red'>该名称不可用</font>";
		}
		else return "<font color='green'>该名称可用</font>";
			
	}
	@RequestMapping("/submitarticle")
	public String submitarticle(HttpServletRequest request) {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String articleclass=request.getParameter("articleclass");
		int userid=Integer.parseInt(request.getParameter("userid"));
		Article a=new Article();
		a.setTitle(title);
		a.setContent(content);
		a.setUserid(userid);
		a.setArticleclass(articleclass);
		aservice.insertArticle(a);
		return "success";
	}
	@RequestMapping("/submitcomment")
	public String submitcomment(HttpServletRequest request) {
		String content=request.getParameter("content");
		int userid=Integer.parseInt(request.getParameter("userid"));
		int articleid=Integer.parseInt(request.getParameter("articleid"));
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setUserid(userid);
		comment.setArticleid(articleid);System.out.print(content);
		cs.insertcomment(comment);
		return "success";
	}
	
}
