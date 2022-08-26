package com.asm.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asm.service.UploadService;
@Service
public class UploadServiceImpl implements UploadService {
	@Autowired ServletContext app;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/assets/" + folder));
//		Nếu không tồn tại thì tạo thư mục images
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File saveFile = new File(dir, name);
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
