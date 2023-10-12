package edu.spring.ex05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.ex05.domain.TestVO;
import edu.spring.ex05.util.FileUploadUtil;
import edu.spring.ex05.util.MediaUtil;

@Controller
public class FileUploadController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadController.class);
	
	// servlet-context.xml 파일에 설정된 문자열 리소스 주입
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/upload")
	public void uploadGET() {
		logger.info("uploadGET() 호출 : " + uploadPath);
	}
	
	@PostMapping("/upload")
	public void uploadPOST(TestVO vo, MultipartFile file) {
		logger.info("uploadPOST() 호출 : " + vo.toString());
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		
		String savedFileName = saveUploadFile(file);
	}
	
	@PostMapping("/upload2")
	public String uploadPost2(MultipartFile[] files) {
		String result = "";
		for(MultipartFile f : files) {
			result += saveUploadFile(f) + " ";
		}
		logger.info("result = " + result);
		return "upload";
	}
	
	@GetMapping("/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() 호출");
	}
	
	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files){
		logger.info("uploadAjaxPOST() 호출");
		
		// 파일 하나만 저장
		String result = null; // result : 파일 경로 및 썸네일 이미지 이름
		
		try {
			result = FileUploadUtil.saveUploadedFile(uploadPath, 
					files[0].getOriginalFilename(), // 경로
					files[0].getBytes()); // 용량
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private String saveUploadFile(MultipartFile file) {
		// UUID : 업로드 하는 파일 이름이 중복되지 않도록 값 생성
		UUID uuid = UUID.randomUUID();
		String saveName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, saveName);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			return saveName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호출");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filepath = uploadPath + fileName;
		
		try {
			in = new FileInputStream(filepath); // InputStream에 넣어서
			
			// 파일 확장자
			String extension = 
					filepath.substring(filepath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			// 응답 헤더(response header)에 Content-type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension)); // 까지가 확장자 인식
			// 데이터 전송
			entity = new ResponseEntity<byte[]>( // ResponseEntity JSON에서 데이터 보낼때 씀
					IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}

}









