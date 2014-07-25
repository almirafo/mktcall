package br.com.supportcomm.mktcall.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileUtil
{

    public static void writeToFile(String fileName, InputStream iStream) throws IOException
    {
        writeToFile(fileName, iStream, false);
    }

    /**
     * Writes InputStream to a given <code>fileName<code>.
     * And, if directory for this file does not exists,
     * if createDir is true, creates it, otherwice throws OMDIOexception.
     * 
     * @param fileName - filename save to.
     * @param iStream - InputStream with data to read from.
     * @param createDir (false by default)
     * @throws IOException in case of any error.
     * 
     * @refactored 2002-05-02 by Alexander Feldman - moved from OMDBlob.
     * 
     */
    public static void writeToFile(String fileName, InputStream iStream, boolean createDir) throws IOException
    {
        String me = "Erro ao salvar arquivo";
        if (fileName == null)
        {
            throw new IOException(me + ": nome do arquivo inválido");
        }
        if (iStream == null)
        {
            throw new IOException(me + ": arquivo inválido");
        }

        File theFile = new File(fileName);

        // Check if a file exists.
        if (theFile.exists())
        {
            String msg = theFile.isDirectory() ? "directory"
                                              : (!theFile.canWrite() ? "not writable"
                                                                    : null);
            if (msg != null)
            {
                throw new IOException(me + ": file '" + fileName + "' is " + msg);
            }
        }

        // Create directory for the file, if requested.
        if (createDir && theFile.getParentFile() != null)
        {
            theFile.getParentFile().mkdirs();
        }

        // Save InputStream to the file.
        BufferedOutputStream fOut = null;
        try
        {
            fOut = new BufferedOutputStream(new FileOutputStream(theFile));
            byte[] buffer = new byte[32 * 1024];
            int bytesRead = 0;
            while ((bytesRead = iStream.read(buffer)) != -1)
            {
                fOut.write(buffer, 0, bytesRead);
            }
        }
        catch (Exception e)
        {
            throw new IOException(me + " failed, got: " + e.toString());
        }
        finally
        {
            close(iStream, fOut);
        }
    }

    /**
     * Closes InputStream and/or OutputStream. It makes sure that both streams
     * tried to be closed, even first throws an exception.
     * 
     * @throw IOException if stream (is not null and) cannot be closed.
     * 
     */
    protected static void close(InputStream iStream, OutputStream oStream) throws IOException
    {
        try
        {
            if (iStream != null)
            {
                iStream.close();
            }
        }
        finally
        {
            if (oStream != null)
            {
                oStream.close();
            }
        }
    }

    public static List<String> readFileTxt(InputStream file)
    {
        List<String> lista = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null)
            {
                StringTokenizer token = new StringTokenizer(line, ";");
                String aux = token.nextToken();
                if (aux.charAt(0) == '1')
                {
                    lista.add(aux);
                    System.out.println(aux);
                }
            }
            System.out.println(lista.size());
        }
        catch (Exception e)
        {

        }
        return lista;
    }

    /*
     * public static InputStream getInputStream(List<String> lista){
     * 
     * Convert String to InputStream using ByteArrayInputStream class. This
     * class constructor takes the string byte array which can be done by
     * calling the getBytes() method.
     * 
     * InputStream is; ByteArrayInputStream b = new ByteArrayInputStream(null);
     * try { //is = new ByteArrayInputStream("");//text.getBytes("UTF-8")); }
     * catch (UnsupportedEncodingException e) { e.printStackTrace(); } }
     */
}
