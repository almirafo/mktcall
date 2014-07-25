package br.com.supportcomm.mktcall.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileWalker {

    public List<String> walk( String path ) {
    	List<String> files = new ArrayList<>();
    	
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return null;

        for ( File f : list ) {
            if ( f.isFile() ) {	
            	if(!f.getName().contains("_ERR")){
            	files.add(f.getName());
                System.out.println( "File:" + f.getAbsoluteFile() );
            	}
            }
        }
		return files;
    }

   
}