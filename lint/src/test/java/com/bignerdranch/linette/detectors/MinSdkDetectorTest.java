package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;
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

    @Override
    protected String getTestResourceDirectory() {
        return "minsdk";
    }

    /**
     * Test that a valid AndroidManifest.xml has no warnings.
     */
    public void testValidManifest() throws Exception {
        assertEquals(
                NO_WARNINGS,
                lintFiles("ValidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

    /**
     * Test that an invalid AndroidManifest.xml has a warning.
     */
    public void testInvalidManifest() throws Exception {
        String warningMessage = MinSdkDetectorTest.class.getSimpleName()
                + "_"
                + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ": Warning: "
                + MinSdkDetector.ISSUE.getBriefDescription(TextFormat.TEXT)
                + " ["
                + MinSdkDetector.ISSUE.getId()
                + "]\n"
                + "0 errors, 1 warnings\n";
        assertEquals(
                warningMessage,
                lintFiles("InvalidAndroidManifest.xml=>AndroidManifest.xml")
        );
    }

}
