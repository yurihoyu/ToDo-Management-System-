package com.dmm.task.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.repository.TasksRepository;

@Controller
public class TaskController {

	@Autowired
	private TasksRepository repo;

	/**
	 * カレンダーの表示.
	 *
	 * @param model モデル
	 * @return 遷移先
	 */

	@GetMapping("/main")
	public String main(Model model) {
		// 1. 2次元表になるので、ListのListを用意する
		List<List<LocalDate>> month = new ArrayList<>();

		// 2. 1週間分のLocalDateを格納するListを用意する
		List<LocalDate> week = new ArrayList<>();

		// 3. その月の1日のLocalDateを取得する
		//別パターン
//		LocalDate d = LocalDate.now().withDayOfMonth(1);

		LocalDate day;
		day = LocalDate.now();
		//その月の1日のLocalDate
		day = LocalDate.of(day.getYear(), day.getMonthValue(), 1);

		// 4-1. 1日の曜日を表すDayOfWeekを取得
		DayOfWeek w = day.getDayOfWeek();

		// 4-2. 上で取得した1日のLocalDateにその曜日の値（DayOfWeek#getValue 1～7)をマイナスして前月分のLocalDateを求める
		//1日の週の月曜日のLocalDateがわかる
		day = day.minusDays(w.getValue());

		// 5. 1日ずつ増やしてLocalDateを求めていき、2．で作成したListへ格納していき、1週間分詰めたら1．のリストへ格納する
		for (int i = 1; i <= 7; i++) {
			day = day.plusDays(1);
			week.add(day);
		}

		month.add(week);
		week = new ArrayList<>();    // 次週分のリストを用意

		// 6. 2週目以降は単純に1日ずつ日を増やしながらLocalDateを求めてListへ格納していき、
		//土曜日になったら1．のリストへ格納して新しいListを生成する
		//（月末を求めるにはLocalDate#lengthOfMonth()を使う）

		for(int i = 7; i <= day.lengthOfMonth(); i++) {
			for (int j = 1; j <= 7; j++) {
				day = day.plusDays(1);
				week.add(day);
			}

			month.add(week);
			week = new ArrayList<>();    // 次週分のリストを用意

		}


		model.addAttribute("matrix", month);

		return "main";
	}



}
