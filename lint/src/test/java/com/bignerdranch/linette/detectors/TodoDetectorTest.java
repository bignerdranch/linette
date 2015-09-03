package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;
import com.bignerdranch.linette.AbstractDetectorTest;

import java.util.Arrays;
import java.util.List;

public class TodoDetectorTest extends AbstractDetectorTest {

    @Override
    protected Detector getDetector() {
        return new TodoDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(TodoDetector.ISSUE);
    }

    @Override
    protected String getTestResourceDirectory() {
        return "todo";
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
     * Test that a java file with a to-do has a warning.
     */
    public void testTodoCase() throws Exception {
        String file = "TodoTestCase.java";
        String warningMessage = file
                + ":5: Warning: "
                + TodoDetector.ISSUE.getBriefDescription(TextFormat.TEXT)
                + " ["
                + TodoDetector.ISSUE.getId()
                + "]\n"
                + "    // TODO\n"
                + "       ~~~~\n"
                + "0 errors, 1 warnings\n";
        assertEquals(
                warningMessage,
                lintFiles(file)
        );
    }

}
