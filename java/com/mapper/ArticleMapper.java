package com.mapper;

import java.util.ArrayList;

import com.pojo.Article;

public interface ArticleMapper {
	public void insertArticle(Article a);
	public ArrayList<Article> showarticle();
	public Article selectarticle(int id);
	public ArrayList<Article> selectbyclassname(String articleclasscontent);
	public ArrayList<Article> selectbykeyword(String keyword);
}
