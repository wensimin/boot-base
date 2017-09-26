package tech.shali.project.app.web.controller.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * 文件管理controller
 * 
 * @author wensimin
 *
 */
@RestController
@RequestMapping("/public/file")
public class FileController{
	@Value("${system.file.base-path}")
	private String fileBasePath;

	@PostMapping("/")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		String fileName = file.getOriginalFilename();
		String name = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		String filepath = fileBasePath + "/" + name;
		File basePath = new File(fileBasePath);
		if (!basePath.exists()) {
			basePath.mkdirs();
		}
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)))) {
			stream.write(file.getBytes());
		}
		return name;
	}
	
	@GetMapping("/{name:.+}")
	public ResponseEntity<?> getFile(@PathVariable String name){
		File file = new File(fileBasePath + "/" + name);
		if(!file.exists()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getName()+ "\"").body(new FileSystemResource(file)); 
	}
}
