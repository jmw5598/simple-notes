/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package club.caliope.udc;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Converts documents from one format to another. This class needs
 * Pandoc to be installed in your system.
 *
 * Pandoc is a free universal document converter, available for Windows, Linux
 * and Mac OS X. Read more about pandoc at http://pandoc.org/installing.html
 *
 */
public class DocumentConverter {

    private Settings settings;

    private File fromFile;
    private File toFile;
    private String fromFormat;
    private String toFormat;
    private String extraOptions;
    private File workingDirectory;

    /** Creates an instance using default settings. Pandoc needs to be
     * available in PATH for this class to work.
     * Try executing "pandoc --version" from your command line to verify
     * your pandoc installation.
     */
    public DocumentConverter() {
        settings = new Settings();
        settings.setPandocExec("pandoc");
        extraOptions = "";
    }

    /** Creates an instance using custom settings.
     * Useful to specify custom pandoc exectuable path.
     */
    public DocumentConverter(Settings settings) {
        this();
        this.settings = settings;
    }


    public DocumentConverter fromFile(File from, String format) {
        this.fromFile = from;
        this.fromFormat = format;
        return this;
    }

    public DocumentConverter fromFile(File from, InputFormat format) {
        return fromFile(from, format.getFormatName());
    }

    public DocumentConverter toFile(File to, String format) {
        this.toFile = to;
        this.toFormat = format;
        return this;
    }

    public DocumentConverter toFile(File to, OutputFormat format) {
        return toFile(to, format.getFormatName());
    }

    /** Appends a custom option to Pandoc.
     * See Pandoc documentation for more information.
     */
    public DocumentConverter addOption(String option) {
        this.extraOptions += " " + option;
        return this;
    }

    /** Sets the working directory for the execution environment. 
     */
    public DocumentConverter workingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }

    /** Converts the input document.
     */
    public void convert() {
        String command = String.format("%s %s --from=%s --to=%s %s --output=%s", settings.getPandocExec(), fromFile.getAbsoluteFile(), fromFormat, toFormat, extraOptions, toFile.getAbsoluteFile());
        int status;
        try {
            System.out.println("Executing: " + command);
            status = Runtime.getRuntime().exec(command, null, workingDirectory).waitFor();
        } catch (InterruptedException ex) {
            throw new RuntimeException("Could not execute: " + command, ex);
        } catch (IOException ex) {
            throw new UncheckedIOException("Could not execute. Maybe pandoc is not in PATH?: " + command, ex);
        }

        if (status != 0) {
            throw new RuntimeException("Conversion failed with status code: " + status + ". Command executed: " + command);
        }
    }

}