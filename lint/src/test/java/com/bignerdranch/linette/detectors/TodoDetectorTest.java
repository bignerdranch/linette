package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.bignerdranch.linette.AbstractDetectorTest;

import java.util.Arrays;
import java.util.List;


import static com.android.SdkConstants.DOT_JAVA;

public class TodoDetectorTest extends AbstractDetectorTest {

    @Override
    protected Detector getDetector() {
        return new TodoDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(TodoDetector.ISSUE);
    }

    /**
     * Test that an empty Java file has no warnings.
     */
    public void testEmptyCase() throws Exception {
        String expected = "No warnings.";
        String result = lintProject(java(DOT_JAVA, ""));
        assertEquals(expected, result);
    }

    /**
     * Test that a Java file with a to-do has a warning.
     */
    public void testEnumCase() throws Exception {
        TestFile testFile = java(DOT_JAVA,
             String.format("package com.example.lint; public class Pet { // TODO }")
        );

        String expected = "Cdddd";
        String result = lintProject(testFile);
        assertEquals(expected, result);
    }

}
