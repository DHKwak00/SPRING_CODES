package edu.spring.ex03;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.ReplyDAO;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ReplyDAOTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyDAOTest.class);
	
	@Autowired
	private ReplyDAO dao; // 여기서 필요한 걸 주입 주입될 것이 @Repository가 있는가? 확인
	
	@Test
	public void testDAO() {
//		testInsert();
//		testSelectAll();
//		testUpdate();
		testDelete();
	}

	private void testDelete() {
		int result = dao.delete(1);
		logger.info(result + "행 삭제");
	}

	private void testUpdate() {
		int result = dao.update(1, "변경");
		logger.info(result + "행 변경");
	}

	private void testSelectAll() {
		List<ReplyVO> list = dao.select(1);
		logger.info(list.size() + "");
		for(ReplyVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testInsert() {
		ReplyVO vo = new ReplyVO(0, 1, "test", "하이", null);
		int result = dao.insert(vo);
		logger.info(result + "행 삽입");
	}
} // end ReplyDAOTest
