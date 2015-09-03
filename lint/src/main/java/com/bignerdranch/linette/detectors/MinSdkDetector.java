package com.bignerdranch.linette.detectors;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;

import java.util.EnumSet;

/**
 * Lint check for a specified Minimum SDK Version.
 */
public class MinSdkDetector extends Detector {

    private static final int SUGGESTED_MIN_SDK_VERSION = 15;

    private static final Class<? extends Detector> DETECTOR_CLASS = MinSdkDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = EnumSet.noneOf(Scope.class);

    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "MinSdk";
    private static final String ISSUE_DESCRIPTION = "Low MinSdk Choice";
    private static final String ISSUE_EXPLANATION = "The minSdkVersion should be at least " + SUGGESTED_MIN_SDK_VERSION;
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 8;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;

    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION
    );

    @Override
    public void afterCheckProject(Context context) {
        super.afterCheckProject(context);
        int minSdk = context.getProject().getMinSdk();
        if (minSdk != -1 && minSdk < SUGGESTED_MIN_SDK_VERSION) {
            context.report(ISSUE, Location.create(context.file), ISSUE.getBriefDescription(TextFormat.TEXT));
        }
    }

}
