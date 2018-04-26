package xyz.jasonwhite.notes.services;

import java.io.File;

import club.caliope.udc.InputFormat;
import club.caliope.udc.OutputFormat;
import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;

public interface DocumentService {
    public File convert(String md, InputFormat inputFormat, OutputFormat outputFormat);
    public File convert(File file, InputFormat inputFormat, OutputFormat outputFormat);
}
