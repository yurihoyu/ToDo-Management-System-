package com.dmm.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;



@Controller
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
//	    Collections.reverse(list); //普通に取得してこちらの処理でもOK
			model.addAttribute("tasks", list);

			return "/main";
		}











}
