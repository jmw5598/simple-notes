/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package club.caliope.udc;

/**
 * These are the supported file format for input files. 
 * Universal Document Converter can read from all these formats.
 */
public enum InputFormat {
    COMMONMARK("commonmark"),
    DOCBOOK("docbook"),
    DOCX("docx"),
    EPUB("epub"),
    HADDOCK("haddock"),
    HTML("html"),
    JSON("json"),
    LATEX("latex"),
    MARKDOWN("markdown"),
    MARKDOWN_GITHUB("markdown_github"),
    MARKDOWN_MMD("markdown_mmd"),
    MARKDOWN_PHPEXTRA("markdown_phpextra"),
    MARKDOWN_STRICT("markdown_strict"),
    MEDIAWIKI("mediawiki"),
    NATIVE("native"),
    ODT("odt"),
    OPML("opml"),
    ORG("org"),
    RST("rst"),
    T2T("t2t"),
    TEXTILE("textile"),
    TWIKI("twiki");

    private String formatName;
    
    private InputFormat(String name) {
        this.formatName = name;
    }

    public String getFormatName() {
        return formatName;
    }
}