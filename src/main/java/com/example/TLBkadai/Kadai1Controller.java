package com.example.TLBkadai;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.TLBkadai.repository.MyDataRepository;

import com.example.TLBkadai.MyData;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
public class Kadai1Controller {
	
	@Autowired
	MyDataRepository repository;
	@Autowired
	private MyDataService service;
	@Autowired
	MyDataBean myDataBean;
	
	@PersistenceContext
	EntityManager entityManager;
	
	MyDataDaoImpl dao;
	
	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);
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
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata,ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","データをCRUD課題、GETホーム画面");
		mav.addObject("msg","MyDataのサンプルです");
		mav.addObject("formModel",mydata);
		List<MyData> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
    }
	@RequestMapping(value="/",method = RequestMethod.POST)
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

	@RequestMapping(value="/insert",method = RequestMethod.GET)
	public ModelAndView insert(@ModelAttribute MyData mydata,ModelAndView mav) {
		mav.setViewName("insert");
		mav.addObject("title","データ作成");
		return mav;
	}
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView create(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata,@PathVariable long id,ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","データ更新");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable long id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","データ削除、確認");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam long id,ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/find",method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","検索");
		mav.addObject("msg","MyDataのサンプル");
		mav.addObject("value","");
		List<MyData> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
    }
	
	@RequestMapping(value="/find",method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if(param == "") {
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title","検索結果");
			mav.addObject("msg","「" + param + "」の検索結果");
			mav.addObject("value",param);
			List<MyData> list = dao.find(param);
			mav.addObject("datalist",list);
		}
		return mav;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  ModelAndView indexById(@PathVariable long id, ModelAndView mav) {
		mav.setViewName("pickup");
		mav.addObject("title","Pickup Page");
		String table = "<table>" + myDataBean.getTableTagById(id) + "</table>";
		mav.addObject("msg","pickup data id = " + id);
		mav.addObject("data",table);
		return mav;
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav,Pageable pageable) {
		mav.setViewName("index");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです。");
		Page<MyData> list = repository.findAll(pageable);
		mav.addObject("datalist", list);
		return mav;
	}
}
