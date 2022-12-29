package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;  //단일 생성자는 자동 의존 주입이 된다.

	@Override
	public void register(BoardVO board) {
		log.info("register...." + board);
		mapper.insertSelectKey(board);
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList....");
//		return mapper.getList();
//	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get....");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify...." + board) ;
		return mapper.update(board) == 1;  //update() 메소드의 return 타입이 int 이므로 boolean 타입으로 나올 수 있도록 만들어줌
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove....") ;
		return mapper.delete(bno) == 1;  //delete() 메소드도 동일
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
}
