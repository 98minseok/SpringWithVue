package backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private final Path fileStorageLocation;
	
	public FileService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
		this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
		Files.createDirectories(this.fileStorageLocation);
	}
	
	public String storeFile(MultipartFile file) {
		try {
			Path targetLocation = fileStorageLocation.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), targetLocation);
			return targetLocation.toString();
		} catch (IOException ex) {
			throw new RuntimeException("파일 저장 실패 : " + file.getOriginalFilename(), ex);
		}
	}
}
