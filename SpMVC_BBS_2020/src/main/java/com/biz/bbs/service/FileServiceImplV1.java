package com.biz.bbs.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("fileServiceV1")
public class FileServiceImplV1 implements FileService {
	
	/*
	 * BBsController의 write POST 에서 파일을 수신한 후
	 * 이곳으로 전달하여 실제 서버로 파일을 복사하는 코드를 생성
	 */
	public String fileUp(MultipartFile file) {
		
		//파일 이름 추출
		String fileName = file.getOriginalFilename();
		
		//catalina.home tomecat 서버 home 디렉토리에 파일을 저장하기 위한 코드 
		String rootPath = System.getProperty("catalina.home");
		
		File dir = new File(rootPath, "tmpFolder");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		// tmpFolder 디렉토리에 서버에 전송해라 
		// 이미 있거나 생성된 tmpFolder와 업로드할 파일 이름을 연결하여
		// 파일 정보로 생성
		File serverSaveFile = new File(dir.getAbsolutePath(), fileName);
		
		FileOutputStream outFile;
		try {
			outFile = new FileOutputStream(serverSaveFile);
			BufferedOutputStream outStream = new BufferedOutputStream(outFile);
			
			// BIN파일을 Outputstream을 저장하기
			byte[] fileData = file.getBytes();//파일크기와 데이터 추출
			
			outStream.write(fileData);
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean fileDelete(String b_file) {
		
		
		return false;
	}
	
	
}