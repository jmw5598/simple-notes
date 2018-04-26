package xyz.jasonwhite.notes.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.caliope.udc.DocumentConverter;
import club.caliope.udc.InputFormat;
import club.caliope.udc.OutputFormat;

@Service
public class DocumentServiceImpl implements DocumentService {
    
    private DocumentConverter documentConverter;
    
    @Autowired
    public DocumentServiceImpl(DocumentConverter documentConverter) {
        this.documentConverter = documentConverter;
    }

    @Override
    public File convert(String md, InputFormat inputFormat, OutputFormat outputFormat) {
        File input = this.writeToFile(md);
        File output = this.generateTempFile("pdf");
        this.documentConverter
            .fromFile(input, inputFormat)
            .toFile(output, outputFormat)
            .convert();
        return output;
    }
    
    @Override
    public File convert(File file, InputFormat inputFormat, OutputFormat outputFormat) {
        File output = this.generateTempFile("pdf");
        this.documentConverter
            .fromFile(file, inputFormat)
            .toFile(output, outputFormat)
            .convert();
        return file;
    }
    
    private File writeToFile(String md) {
        File file = this.generateTempFile("md");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(md);
            writer.close();
            System.out.println("PATH : " + file.getAbsolutePath());
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return file;
    }
    
    private File generateTempFile(String format) {
        String path = System.getProperty("user.home");
        File temp = null;
        try {
            temp = File.createTempFile("simple-notes", "." + format, new File(path));
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return temp;
    }
    

}
