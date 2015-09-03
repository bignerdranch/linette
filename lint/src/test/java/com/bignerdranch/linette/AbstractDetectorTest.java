package com.bignerdranch.linette;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.utils.SdkUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;

public abstract class AbstractDetectorTest extends LintDetectorTest {

    @Override
    protected InputStream getTestResource(String relativePath, boolean expectExists) {
        String path = File.separator + relativePath; //$NON-NLS-1$
        InputStream stream = AbstractDetectorTest.class.getResourceAsStream(path);
        if (stream == null) {
            File root = getTestDataRootDir();
            assertNotNull(root);
            String pkg = AbstractDetectorTest.class.getName();
            pkg = pkg.substring(0, pkg.lastIndexOf('.'));
            File f = new File(root, "lint/src/test/resources/".replace('/', File.separatorChar) + File.separatorChar + path);
            if (f.exists()) {
                try {
                    return new BufferedInputStream(new FileInputStream(f));
                } catch (FileNotFoundException e) {
                    stream = null;
                    if (expectExists) {
                        fail("Could not find file " + relativePath);
                    }
                }
            }
        }
        if (!expectExists && stream == null) {
            return null;
        }
        return stream;
    }

    private File getTestDataRootDir() {
        CodeSource source = getClass().getProtectionDomain().getCodeSource();
        if (source != null) {
            URL location = source.getLocation();
            try {
                File classesDir = SdkUtils.urlToFile(location);
                return classesDir.getParentFile().getAbsoluteFile().getParentFile().getParentFile();
            } catch (MalformedURLException e) {
                fail(e.getLocalizedMessage());
            }
        }
        return null;
    }

}
