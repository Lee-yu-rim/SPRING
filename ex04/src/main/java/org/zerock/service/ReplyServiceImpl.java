package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
//	private ReplyMapper mapper;  //단일 생성자의 자동 의존 주입
	
	//두가지 쿼리문을 실행하기 위해서는 생성자의 의존주입이 아닌 @Setter 어노테이션을 사용해서 의존주입 해야한다.
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;  
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;  

	
	@Transactional  //댓글이 추가/삭제되면 게시글의 댓글 갯수도 추가되어야 하므로 BoardMapper 클래스도 사용해야함 => 트랜잭션 처리
	@Override
	public int register(ReplyVO vo) {
		log.info("register......" + vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1); //댓글 갯수를 추가하는 것이므로 amount는 +1
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get......." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify......." + vo);
		return mapper.update(vo);
	}

	@Transactional  //댓글이 추가/삭제되면 게시글의 댓글 갯수도 추가되어야 하므로 BoardMapper 클래스도 사용해야함 => 트랜잭션 처리
	@Override
	public int remove(Long rno) {  //댓글 삭제시 파라미터 값으로 rno(댓글번호)만 받아옴 => 원글(bno)에 대한 정보를 받지 못함 => bno 값을 받아오려면 Controller 수정 필요함
		log.info("remove........" + rno);
		ReplyVO vo = mapper.read(rno);  //ReplyMapper.xml 에 정의된 read() 메소드를 통해 rno 값에 대한 전체 정보를 받아올 수 있다.
		boardMapper.updateReplyCnt(vo.getBno(), -1); //댓글 갯수를 삭제하는 것이므로 amount는 -1
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

}
