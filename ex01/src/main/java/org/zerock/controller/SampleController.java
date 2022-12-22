package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")  //대표 uri
@Log4j
public class SampleController {
	@RequestMapping("")  //서브 uri
	public void basic() {
		log.info("basicgogo");
	}
	
	//get 방식으로 넘어오는 파라메터 값을 받는 방식
	
	@GetMapping("ex01")
	public String ex01(SampleDTO dto) {  //커맨드 객체
	// => http://localhost:8081/sample/ex01?name=hong&age=10 값을 요청하게 되면 DTO에 있는 set() 메소드가 동작하게 됨
	// => DTO에서 내부적으로 스프링 컨테이너를 통해 객체 생성 및 파라메터로 넘겨주는 값을 받아옴 (dto.setName(name))
	// => 대신 DTO 클래스에는 파라메터로 넘겨주는 매개변수와 동일한 매개변수가 정의되어있어야함
		
		log.info("" + dto);
		
		return "ex01";
	}
	//결과값
	//INFO : org.zerock.controller.SampleController - SampleDTO(name=hong, age=10)
	
	
	//값을 각각(낱개로) 받아오는 방식
	@GetMapping("ex02")
	public String ex02(@RequestParam("name") String name,@RequestParam("age") int age) {
		
		log.info(name);
		log.info(age);
		
		return "ex02";
	}  
	//결과값
	//INFO : org.zerock.controller.SampleController - hong
	//INFO : org.zerock.controller.SampleController - 10
	
	
	//List 형태의 값을 받아오는 방식
	@GetMapping("ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	} 
	//결과값
	//INFO : org.zerock.controller.SampleController - ids : [aaa, bbb, ccc]
	
	
	//객체 리스트 형태의 값을 받아오는 방식
	@GetMapping("ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		log.info("list : " + list);
		
		return "ex02Bean";
	} 
	//결과값
	//INFO : org.zerock.controller.SampleController - list : SampleDTOList(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=null, age=0), SampleDTO(name=bbb, age=0)])

	
	//날짜 형태의 값을 받아오는 방식
	@GetMapping("ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo : " + todo);
		
		return "ex03";
	} 
	//결과값
	//INFO : org.zerock.controller.SampleController - todo : TodoDTO(title=test, dueDate=Wed Dec 21 00:00:00 KST 2022)

	
	//EL 형태의 값을 받아오는 형식
	@GetMapping("ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page")int page) {  
		//커맨드 객체
		//1. 파라메터 자동으로 받기
		//2. 뷰 페이지로 커맨드 객체의 정보 전달(넘길 때 클래스의 첫 글자를 소문자로 구성해서 전달)
		// => sampleDTO
		//3. 기본형 매개변수의 값을 뷰페이지로 전달하기 위헤 @ModelAttribute를 적용
		// => Model 객체에 담겨서 전달됨
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	} 
	//결과값
	//SAMPLEDTO SampleDTO(name=hong, age=0)
	//PAGE  => page 값은 int 타입으로 선언되었기 때문에 출력되지 않음 => @ModelAttribute 어노테이션을 사용하면 출력할 수 있다.
	
	
	//json 형식으로 값을 전달하는 방식
	@GetMapping("/ex06") 
	public @ResponseBody SampleDTO ex06() {   //@ResponseBody - 원하는 형식의 데이터 타입으로 값을 전달해줌 => json 형태로 데이터가 넘어가게됨
		
		SampleDTO dto = new SampleDTO();
		dto.setName("hong");
		dto.setAge(10);

		return dto;
	} 
	
	
	@GetMapping("/ex07") 
	public ResponseEntity<String> ex07() {   
		
		String msg = "{\"name\":\"홍길동\"}";   //속성과 값이 문자열로 이루어진 json 형태의 객체를 클라이언트로 보냄 = {"name":"홍길동"}
		HttpHeaders header = new HttpHeaders();  
		header.add("Content-type", "application/json;charset=UTF-8"); //header 영역에 값 넣어주기
		
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
	}
	
	
	@GetMapping("/exUpload") 
	public void exUpload() {   
		
		log.info("/exupload.........");
	}
	
	
	//post 방식으로 넘어오는 값 처리
	@PostMapping("/exUploadPost") 
	public void exUploadPost(ArrayList<MultipartFile> files) {   
		//배열형태로 값이 넘어오므로 for문으로 받아오기
		files.forEach(file -> {
			log.info(".....................");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			
		});
		
	}
	
	
}
