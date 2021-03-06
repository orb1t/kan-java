/*******************************************************************************
 * Copyright (c) 2015 pf-miles.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     pf-miles - initial API and implementation
 ******************************************************************************/
package com.github.pfmiles.kanjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;

import javax.tools.FileObject;

import com.github.pfmiles.kanjava.compile.DynaCompilationException;

/**
 * 用作编译时通过文件路径传入想要放入classpath的jar包
 * 
 * @author pf-miles 2014-4-6 下午6:14:45
 */
public class DiskJarFile implements FileObject {

    private File file;
    private URI uri;

    /**
     * 通过文件系统绝对路径指定jar包位置
     */
    public DiskJarFile(String absolutePath) {
        file = new File(absolutePath);
        if (!file.exists())
            throw new DynaCompilationException("Disk jar file: '" + absolutePath + "' does not exist.");
        this.uri = URI.create("file://" + file.getAbsolutePath());
    }

    public URI toUri() {
        return uri;
    }

    public String getName() {
        return this.file.getName();
    }

    public InputStream openInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public OutputStream openOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    public Writer openWriter() throws IOException {
        throw new UnsupportedOperationException();
    }

    public long getLastModified() {
        return this.file.lastModified();
    }

    public boolean delete() {
        throw new UnsupportedOperationException();
    }

    /**
     * 取得磁盘文件绝对路径
     */
    public String getAbsolutePath() {
        return this.file.getAbsolutePath();
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiskJarFile other = (DiskJarFile) obj;
        if (uri == null) {
            if (other.uri != null)
                return false;
        } else if (!uri.equals(other.uri))
            return false;
        return true;
    }

}
