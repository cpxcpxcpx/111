package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.ArticleMapper;
import com.pojo.Article;
@Component
public class Articleserviceimpl implements Articleservice{
	@Autowired
	ArticleMapper am;
	public void insertArticle(Article a) {
		am.insertArticle(a);
	}
	public ArrayList<Article> showarticle(){
		return am.showarticle();
	}
	public Article selectarticle(int id) {
		return am.selectarticle(id);
	}
	@Override
	public ArrayList<Article> selectbyclassname(String articleclasscontent) {	
		return am.selectbyclassname(articleclasscontent);
	}
	@Override
	public ArrayList<Article> selectbykeyword(String keyword) {
		return am.selectbykeyword(keyword);
	}
}
