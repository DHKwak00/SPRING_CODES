package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;
import edu.spring.ex03.persistence.BoardDAO;

@Service // @Component
public class BoardServiceImple implements BoardService{
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardServiceImple.class);
	
	@Autowired
	private BoardDAO dao;

	@Override
	public int create(BoardVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<BoardVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public BoardVO read(int boardId) {
		logger.info("read() ȣ�� : boardId = " + boardId);
		return dao.select(boardId);
	}

	@Override
	public int update(BoardVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString()); 
		return dao.update(vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() ȣ�� : boardId = " + boardId);
		return dao.delete(boardId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCounts();
	}

}
