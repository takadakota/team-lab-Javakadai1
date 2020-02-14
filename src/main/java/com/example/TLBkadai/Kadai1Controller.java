package com.example.TLBkadai;

import java.util.Optional;

import javax.annotation.PostConstruct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.TLBkadai.repository.MyDataRepository;
import com.example.TLBkadai.MyData;

@RestController
public class Kadai1Controller {
	
	@Autowired
	MyDataRepository repository;
	
	@PostConstruct
	public void init() {
		MyData d1 = new MyData();
		d1.setTitle("ラーメン");
		d1.setDescription("テスト説明文");
		d1.setPrice(980);
		d1.setImage("kari");
		repository.saveAndFlush(d1);
		MyData d2 = new MyData();
		d2.setTitle("牛丼");
		d2.setDescription("テスト説明文２");
		d2.setPrice(540);
		d2.setImage("kari2");
		repository.saveAndFlush(d2);
		MyData d3 = new MyData();
		d3.setTitle("ステーキ");
		d3.setDescription("テスト説明文３");
		d3.setPrice(1480);
		d3.setImage("kari3");
		repository.saveAndFlush(d3);
	}
	
	@GetMapping("/")
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata,ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","データをCRUD課題、GETホーム画面");
		mav.addObject("formModel",mydata);
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
    }
	@PostMapping("/")
	@Transactional(readOnly=false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata,BindingResult result, ModelAndView mov) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
		repository.saveAndFlush(mydata);
		}else{
			mov.setViewName("index");
			mov.addObject("title","データをCRUD課題、POSTホーム画面");
			Iterable<MyData> list = repository.findAll();
			mov.addObject("datalist",list);
			res = mov;
		}
		return res;
	}
	@GetMapping("/search")
    public ModelAndView search(ModelAndView mav,MyData mydata, String keyword) {
		mav.addObject("formModel",mydata);
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
    }

	@GetMapping("/insert")
	public ModelAndView insert(@ModelAttribute MyData mydata,ModelAndView mav) {
		mav.setViewName("insert");
		mav.addObject("title","データ作成");
		return mav;
	}
	@PostMapping("/insert")
	@Transactional(readOnly=false)
	public ModelAndView create(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@ModelAttribute MyData mydata,@PathVariable long id,ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","データ更新");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@PostMapping("/edit")
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable long id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","データ削除、確認");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@PostMapping("/delete")
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam long id,ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
	
}
