package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")  //대표 uri
@Log4j
public class SampleController {
	@RequestMapping("")  //서브 uri
	public void basic() {
		log.info("basicgogo");
	}
	
	@GetMapping("ex01")
	public String ex01(SampleDTO dto) {  //커맨드 객체
	// => http://localhost:8081/sample/ex01?name=hong&age=10 값을 요청하게 되면 DTO에 있는 set() 메소드가 동작하게 됨
	// => DTO에서 내부적으로 스프링 컨테이너를 통해 객체 생성 및 파라메터로 넘겨주는 값을 받아오게됨 (dto.setName(name))
	// => 대신 DTO 클래스에는 파라메터로 넘겨주는 매개변수와 동일한 매개변수가 정의되어있어야함
		
		log.info("" + dto);
		
		return "ex01";
	}
}
