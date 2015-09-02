package com.bignerdranch.linette;

import com.android.tools.lint.detector.api.Issue;
import com.bignerdranch.linette.detectors.EnumDetector;
import com.bignerdranch.linette.detectors.MinSdkDetector;
import com.bignerdranch.linette.detectors.TodoDetector;

import org.junit.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


public class CustomIssueRegistryTest {

    /**
     * Test that the issue registry contains what we expect.
     */
    @Test
    public void testGetIssues() throws Exception {
        CustomIssueRegistry customIssueRegistry = new CustomIssueRegistry();
        List<Issue> actual = customIssueRegistry.getIssues();
        assertThat(actual).contains(EnumDetector.ISSUE);
        assertThat(actual).contains(MinSdkDetector.ISSUE);
        assertThat(actual).contains(TodoDetector.ISSUE);
    }

}
