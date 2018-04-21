/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package club.caliope.udc;

/**
 * These are the supported file format for output files. 
 * Universal Document Converter can write to all these formats.
 */
public enum OutputFormat {
    ASCIIDOC("asciidoc"),
    BEAMER("beamer"),
    COMMONMARK("commonmark"),
    CONTEXT("context"),
    DOCBOOK("docbook"),
    DOCX("docx"),
    DOKUWIKI("dokuwiki"),
    DZSLIDES("dzslides"),
    EPUB("epub"),
    EPUB3("epub3"),
    FB2("fb2"),
    HADDOCK("haddock"),
    HTML("html"),
    HTML5("html5"),
    ICML("icml"),
    JSON("json"),
    LATEX("latex"),
    MAN("man"),
    MARKDOWN("markdown"),
    MARKDOWN_GITHUB("markdown_github"),
    MARKDOWN_MMD("markdown_mmd"),
    MARKDOWN_PHPEXTRA("markdown_phpextra"),
    MARKDOWN_STRICT("markdown_strict"),
    MEDIAWIKI("mediawiki"),
    NATIVE("native"),
    ODT("odt"),
    OPENDOCUMENT("opendocument"),
    OPML("opml"),
    ORG("org"),
    PDF("pdf"),
    PLAIN("plain"),
    REVEALJS("revealjs"),
    RST("rst"),
    RTF("rtf"),
    S5("s5"),
    SLIDEOUS("slideous"),
    SLIDY("slidy"),
    TEXINFO("texinfo"),
    TEXTILE("textile");
    
    private String formatName;

    private OutputFormat(String name) {
        this.formatName = name;
    }

    public String getFormatName() {
        return formatName;
    }
    
}