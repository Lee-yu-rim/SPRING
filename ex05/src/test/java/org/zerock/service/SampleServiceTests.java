package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleService service;
	
//	@Test
//	public void testClass() {
//		log.info(service);
//		log.info(service.getClass().getName());
//	}
	
	@Test
	public void testAdd() throws Exception {
		log.info(service.doAdd("123", "456"));
	}
	//SampleService에 정의된 doAdd() 메소드를 실행 시키면 공통 관심사가 Advice로 정의한 시점에 따라 같이 실행된다.
	//결과값
//	INFO : org.zerock.aop.LogAdvice - Target: org.zerock.service.SampleServiceImpl@1e58512c
//	INFO : org.zerock.aop.LogAdvice - Param: [123, 456]  
//	INFO : org.zerock.aop.LogAdvice - ==============================
//	INFO : org.zerock.aop.LogAdvice - TIME: 1  
//	INFO : org.zerock.aop.LogAdvice - ==============================end
//	INFO : org.zerock.service.SampleServiceTests - 579
}
