package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("upload form");
	}
	
	//첨부파일 저장하기
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename()); 
			log.info("Upload File Size: " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
		}//end for
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("upload ajax");
	}
	
	//ajax 요청을 통한 파일 업로드
//	@PostMapping("/uploadAjaxAction")
//	public void uploadAjaxPost(MultipartFile[] uploadFile) {
//		log.info("update ajax post..........");
//		
//		String uploadFolder = "C:\\upload";
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			log.info("-------------------------------------");
//			log.info("Upload File Name: " + multipartFile.getOriginalFilename()); 
//			log.info("Upload File Size: " + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//			
//			log.info("only file name: " + uploadFileName);
//			
//			File saveFile = new File(uploadFolder, uploadFileName);
//			
//			try {
//				multipartFile.transferTo(saveFile);
//			}catch(Exception e) {
//				log.error(e.getMessage());
//			} //end catch
//		}//end for
//	}
	
	//날짜 정보를 추출해주는 메소드
	private String getFolder() {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator); //separator - 구분자 "\" 대신  "-" 으로 쓰겟다는 뜻
	}
	
	//첨부파일이 이미지 타입인지 확인하는 메소드
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			//Files 클래스 -> File 클래스랑 다름 / mime 타입을 확인
			//mime type 
			//오디오 - mpeg / 텍스트 - xml / 텍스트 - plain / 이미지 - jpeg
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//파일 다운 후 폴더를 년/월/일 단위로 생성하기
//	@PostMapping("/uploadAjaxAction")
//	public void uploadAjaxPost(MultipartFile[] uploadFile) {
//		
//		String uploadFolder = "C:\\upload";
//		
//		//폴더 경로 가져오기
//		File uploadPath = new File(uploadFolder, getFolder());
//		log.info("upload path: " + uploadPath);
//		
//		//오늘 날짜의 폴더가 이미 생성 되어있는지 확인 -> true면 같은 이름의 폴더가 이미 있다는 뜻
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs(); //오늘 날짜의 폴더가 없는 경우 폴더 생성(make directory) - yyyy-MM-dd 폴더
//		} 
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			log.info("-------------------------------------");
//			log.info("Upload File Name: " + multipartFile.getOriginalFilename()); 
//			log.info("Upload File Size: " + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			
//			//인터넷 파일 경로
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//			log.info("only file name: " + uploadFileName);
//			
//			//File saveFile = new File(uploadFolder, uploadFileName);
//			
//			//파일 이름 중복 방지를 위한 UUID 객체 사용
//			UUID uuid = UUID.randomUUID();
//			
//			uploadFileName = uuid.toString() + "_" + uploadFileName;
//			
//			File saveFile = new File(uploadPath, uploadFileName);
//			
//			try {
//				multipartFile.transferTo(saveFile);  //파일 전송(서버에 업로드)
//			}catch(Exception e) {
//				log.error(e.getMessage());
//			} //end catch
//		}//end for
//	}
	
	//파일이 이미지 타입인 경우 섬네일 생성해주기
//	@PostMapping("/uploadAjaxAction")
//	public void uploadAjaxPost(MultipartFile[] uploadFile) {
//		
//		String uploadFolder = "C:\\upload";
//		
//		//폴더 경로 가져오기
//		File uploadPath = new File(uploadFolder, getFolder());
//		log.info("upload path: " + uploadPath);
//		
//		//오늘 날짜의 폴더가 이미 생성 되어있는지 확인 -> true면 같은 이름의 폴더가 이미 있다는 뜻
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs(); //오늘 날짜의 폴더가 없는 경우 폴더 생성(make directory) - yyyy-MM-dd 폴더
//		} 
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			log.info("-------------------------------------");
//			log.info("Upload File Name: " + multipartFile.getOriginalFilename()); 
//			log.info("Upload File Size: " + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			
//			//인터넷 파일 경로
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//			log.info("only file name: " + uploadFileName);
//			
//			//File saveFile = new File(uploadFolder, uploadFileName);
//			
//			//파일 이름 중복 방지를 위한 UUID 객체 사용
//			UUID uuid = UUID.randomUUID();
//			
//			uploadFileName = uuid.toString() + "_" + uploadFileName;
//			
//			//File saveFile = new File(uploadPath, uploadFileName);
//			
//			try {
//				File saveFile = new File(uploadPath, uploadFileName);
//				multipartFile.transferTo(saveFile);  //파일 전송(서버에 업로드)
//				
//				//파일이 이미지 타입인지 체크하기
//				if(checkImageType(saveFile)) {
//					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
//					//파일 이름이 "s_"로 시작하는 섬네일 이미지를 자동으로 복사해서 생성함
//					
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
//					
//					thumbnail.close();
//				}
//			}catch(Exception e) {
//				log.error(e.getMessage());
//			} //end catch
//		}//end for
//	}
	
	//브라우저로 데이터 전송하기
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //json 형식으로 데이터 전송
	@ResponseBody //json 형식으로 데이터를 보내기 위해 사용하는 어노테이션
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder(); //파일이 업로드되면서 실제 만들어진 폴더명
		
		//폴더 경로 가져오기
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadPath);
		
		//오늘 날짜의 폴더가 이미 생성 되어있는지 확인 -> true면 같은 이름의 폴더가 이미 있다는 뜻
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs(); //오늘 날짜의 폴더가 없는 경우 폴더 생성(make directory) - yyyy-MM-dd 폴더
		} 
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
//			log.info("-------------------------------------");
//			log.info("Upload File Name: " + multipartFile.getOriginalFilename()); 
//			log.info("Upload File Size: " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//인터넷 파일 경로
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			
			attachDTO.setFileName(uploadFileName); //업로드된 원본 파일명 받기
			
			//File saveFile = new File(uploadFolder, uploadFileName);
			
			//파일 이름 중복 방지를 위한 UUID 객체 사용
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);  //파일 전송(서버에 업로드)
				
				//파일이 업로드되면서 생성던 폴더의 경로와 uuid DTO에 전달하기
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//파일이 이미지 타입인지 체크하기
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					//파일 이름이 "s_"로 시작하는 섬네일 이미지를 자동으로 복사해서 생성함
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
				}
				
				list.add(attachDTO);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			} //end catch
		}//end for
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//섬네일 데이터 전송하기
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		
		log.info("fileName: " + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		log.info("file: " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//파일 다운로드 하기
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) {
		
		log.info("download file: " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		log.info("resource: " + resource);
		
		String resourceName = resource.getFilename();
		
		//uuid 삭제하기 -> 파일을 다운로드할 때 원본파일명으로 다운할 수 있도록 함
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		//_이 있는 곳의 인덱스 번호 + 1 한 곳 부터 substring(잘라내기) 해라
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			//파일 다운로드 실행 및 파일 이름 지정
			headers.add("Content-Disposition", "attachment; filename=" 
			+ new String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//첨부파일 삭제하기
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("deleteFile: " + fileName);
		
		File file;
		
		try{
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName: " + largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
}
