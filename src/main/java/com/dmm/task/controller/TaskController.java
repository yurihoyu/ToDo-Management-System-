package com.dmm.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;
import com.dmm.task.form.PostsForm;


public class TaskController {

	@Autowired


	private TasksRepository repo;

	/**
	 * 投稿の一覧表示.
	 *
	 * @param model モデル
	 * @return 遷移先
	 */

	@PostMapping("/main")
	public String posts(Model model) {

		// 逆順で投稿をすべて取得する
		List<Tasks> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));

//    Collections.reverse(list); //普通に取得してこちらの処理でもOK
		model.addAttribute("tasks", list);
		PostsForm postForm = new PostsForm();
		model.addAttribute("postForm", postForm);
		return "/main";
	}





}
