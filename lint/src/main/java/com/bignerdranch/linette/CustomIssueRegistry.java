package com.bignerdranch.linette;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.bignerdranch.linette.detectors.EnumDetector;
import com.bignerdranch.linette.detectors.MinSdkDetector;
import com.bignerdranch.linette.detectors.TodoDetector;

import java.util.Arrays;
import java.util.List;

/**
 * The list of issues that will be checked when running <code>lint</code>.
 */
@SuppressWarnings("unused")
public class CustomIssueRegistry extends IssueRegistry {

    private List<Issue> mIssues = Arrays.asList(
            EnumDetector.ISSUE,
            MinSdkDetector.ISSUE,
            TodoDetector.ISSUE
    );

    public CustomIssueRegistry() {
    }

    @Override
    public List<Issue> getIssues() {
        return mIssues;
    }

}
