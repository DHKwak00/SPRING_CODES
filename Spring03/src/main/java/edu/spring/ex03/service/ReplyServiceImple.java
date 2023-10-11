package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.BoardDAO;
import edu.spring.ex03.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	private ReplyDAO  replyDAO;
	
	// @Transectional
	// - 두 개의 DB의 변경이 생길 때,
	// 위의 쿼리는 수행이 된 상태에서, 아래 쿼리는 에러가 발생하면
	// 위의 쿼리는 rollback 되어야 한다.
	// 이러한 기능을 제공해주는 Spring annotation
	// - root-context.xml에서 설정
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(ReplyVO vo) throws Exception{
		// 댓글(test_reply의 데이터가 증가하면
		// 게시판(test_board)의 댓글 개수(reply_cnt)가 변경되야 함
		// test_reply 테이블에 insert를 수행하면
		// test_board 테이블에 update를 수행한다.
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = replyDAO.insert(vo);
		logger.info(resultInsert + "행 댓글 입력 성공");
		int result = boardDAO.updateReplyCnt(1, vo.getBoardId());
		logger.info(result + "행 수정 성공");
		return 1;
	}

	@Override
	public List<ReplyVO> read(int boardId) {
		logger.info("read() 호출 : boardId = " + boardId);
		return replyDAO.select(boardId);
	}

	@Override
	public int update(int replyId, String replyContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + replyId + ", replyContent = " + replyContent);
		return replyDAO.update(replyId, replyContent);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int replyId, int boardId) throws Exception{
		logger.info("delete() 호출 ; replyId = " + replyId);
		int resultDelete = replyDAO.delete(replyId);
		logger.info(resultDelete + " 행 삭제 성공");
		int result = boardDAO.updateReplyCnt(-1, boardId);
		logger.info(result + " 행 수정 성공");
		return 1;
	}

}
