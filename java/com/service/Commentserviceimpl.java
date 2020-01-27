package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommentMapper;
import com.pojo.Comment;

@Component
public class Commentserviceimpl implements Commentservice {
	@Autowired
	CommentMapper cm;

	@Override
	public void insertcomment(Comment c) {
		cm.insertcomment(c);
	}

	@Override
	public ArrayList<Comment> showcommentbyarticle(int articleid) {
		return cm.showcommentbyarticle(articleid);
	}

}
