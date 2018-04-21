/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package club.caliope.udc;

/**
 * General settings for DocumentConverter.
 */
public class Settings {
    /** The path to the Pandoc executable. */
    private String pandocExec;

    public String getPandocExec() {
        return pandocExec;
    }

    public void setPandocExec(String pandocExec) {
        this.pandocExec = pandocExec;
    }
    
}