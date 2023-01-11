package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	//private BoardMapper mapper;  //단일 생성자는 자동 의존 주입이 된다.
	
	//두가지 쿼리문을 실행하기 위해서는 생성자의 의존주입이 아닌 @Setter 어노테이션을 사용해서 의존주입 해야한다.
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;

	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register...." + board);
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach ->{
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
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

	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		//게시물 수정시 첨부파일을 모두 삭제한 뒤 새로운 첨부파일을 추가하는 방식으로 설계
		//DB에서 첨부파일 삭제하기
		log.info("modify...." + board) ;
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;  //update() 메소드의 return 타입이 int 이므로 boolean 타입으로 나올 수 있도록 만들어줌
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove....") ;
		attachMapper.deleteAll(bno);
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

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno" + bno);
		return attachMapper.findByBno(bno);
	}
	
}
