package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.repository.TasksRepository;


public class TaskController {

	@Autowired


	private TasksRepository repo;

	/**
	 * 投稿の一覧表示.
	 *
	 * @param model モデル
	 * @return 遷移先
	 */

	    @GetMapping("/main")
	    public String main() {
	        return "main";
	    }




}
