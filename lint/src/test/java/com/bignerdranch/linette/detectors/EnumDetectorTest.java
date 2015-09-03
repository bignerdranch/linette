package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;
import com.bignerdranch.linette.AbstractDetectorTest;

import java.util.Arrays;
import java.util.List;

public class EnumDetectorTest extends AbstractDetectorTest {

    @Override
    protected Detector getDetector() {
        return new EnumDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(EnumDetector.ISSUE);
    }

    @Override
    protected String getTestResourceDirectory() {
        return "enum";
    }

    /**
     * Test that an empty java file has no warnings.
     */
    public void testEmptyCase() throws Exception {
        String file = "EmptyTestCase.java";
        assertEquals(
                NO_WARNINGS,
                lintFiles(file)
        );
    }

    /**
     * Test that a java file with an enum has a warning.
     */
    public void testEnumCase() throws Exception {
        String file = "EnumTestCase.java";
        String warningMessage = file
                + ": Warning: "
                + EnumDetector.ISSUE.getBriefDescription(TextFormat.TEXT)
                + " ["
                + EnumDetector.ISSUE.getId()
                + "]\n"
                + "0 errors, 1 warnings\n";
        assertEquals(
                warningMessage,
                lintFiles(file)
        );
    }

}
