package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.bignerdranch.linette.AbstractDetectorTest;

import java.util.Arrays;
import java.util.List;

public class MinSdkDetectorTest extends AbstractDetectorTest {

    @Override
    protected Detector getDetector() {
        return new MinSdkDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(MinSdkDetector.ISSUE);
    }



    public void testShouldDetectNoAncientSdk() throws Exception {
        assertEquals(
                "No warnings.",
                lintFiles("ValidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

    public void testShouldDetectAncientSdk() throws Exception {
        assertEquals(
                "MinSdkDetectorTest_testShouldDetectAncientSdk: Warning: Supporting ancient Android versions [MinSdk]\n" +
                        "0 errors, 1 warnings\n",
                lintFiles("InvalidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

}
