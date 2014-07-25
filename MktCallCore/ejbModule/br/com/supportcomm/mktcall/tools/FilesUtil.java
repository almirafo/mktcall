package br.com.supportcomm.mktcall.tools;


import static java.nio.file.StandardCopyOption.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtil {
	/**
	 * Move o arquivo para destino <br/>
	 * Deve-se indicar o caminho completo da origem(sourceFile) e destino(targetFile).
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 */
	public boolean moveFile(String sourceFile, String targetFile){
		boolean retorno = false;
		
		Path source = Paths.get(sourceFile);
		Path target = Paths.get(targetFile);
		try {
			Files.move(source, target, REPLACE_EXISTING);
			retorno = true;
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		return retorno;
	}
	
}
