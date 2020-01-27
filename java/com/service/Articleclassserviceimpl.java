package com.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.ArticleclassMapper;
import com.pojo.Articleclass;

@Component
public class Articleclassserviceimpl implements Articleclassservice{

	@Autowired
	ArticleclassMapper am;
	@Override
	public ArrayList<Articleclass> showArticleclass() {
	return am.showarticleclass();
	}

}
