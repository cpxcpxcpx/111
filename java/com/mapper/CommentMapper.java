package com.mapper;

import java.util.ArrayList;

import com.pojo.Comment;

public interface CommentMapper {
	public void insertcomment(Comment c);
	public ArrayList<Comment> showcommentbyarticle(int articleid);
}
