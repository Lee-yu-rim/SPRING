package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		log.info(service);
//		assertNotNull(service);
//	}
	
	//게시물 등록하기
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성하는 impl");
//		board.setContent("새로작성하는 내용 impl");
//		board.setWriter("newbie");
//		
//		service.register(board);
//		log.info("추가된 게시물 : " + board.getBno());  //글이 등록될 떄 생성된 시퀀스(key) 값 확인
//	}
	
	//게시글 목록 조회하기
//	@Test
//	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
//	}
	
	//게시글 한 개 조회하기
//	@Test
//	public void testGet() {
//		log.info(service.get(9L));
//	}
	
	
	//게시글 수정하기
//	@Test
//	public void testUpdate() {
//		//수정하려는 게시글이 있는지 확인
//		BoardVO board = service.get(8L);
//		if(board == null) {
//			return;
//		}
//		board.setTitle("제목만 수정하기~");
//		boolean b = service.modify(board);
//		log.info("modify result : " + b);
//	}
	
	//게시글 삭제하기
	@Test
	public void testDelete() {
		boolean b = service.remove(9L);
		log.info("Delete result : " + b);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
