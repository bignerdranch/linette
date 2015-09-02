package com.bignerdranch.linette.detectors;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.bignerdranch.linette.detectors.MinSdkDetector;

public class MinSdkDetectorTest extends LintDetectorTest {

    @Override
    protected Detector getDetector() {
        return new MinSdkDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(MinSdkDetector.ISSUE);
    }

    @Override
    protected InputStream getTestResource(String relativePath, boolean expectExists) {
        String path = relativePath; //$NON-NLS-1$
        InputStream stream = MinSdkDetectorTest.class.getResourceAsStream(path);
        if (!expectExists && stream == null) {
            return null;
        }
        return stream;
    }

    public void testShouldDetectNoAncientSdk() throws Exception {
        assertEquals(
                "No warnings.",
                lintFiles("ValidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

    public void testShouldDetectAncientSdk() throws Exception {
        assertEquals(
                "Error: Ancient minSdkVersion detected [AncientMinSdk]\n" + "1 errors, 0 warnings\n",
                lintFiles("InvalidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

}
