package com.service;


import java.util.ArrayList;

import com.pojo.Comment;

public interface Commentservice {
	public void insertcomment(Comment c);
	public ArrayList<Comment> showcommentbyarticle(int articleid);
}
