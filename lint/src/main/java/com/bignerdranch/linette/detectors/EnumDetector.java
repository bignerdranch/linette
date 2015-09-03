package com.bignerdranch.linette.detectors;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.EnumConstant;
import lombok.ast.EnumDeclaration;
import lombok.ast.EnumTypeBody;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;

/**
 * Lint check for enum usage.
 */
public class EnumDetector extends Detector implements Detector.JavaScanner {

    private static final Class<? extends Detector> DETECTOR_CLASS = EnumDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "Enum";
    private static final String ISSUE_DESCRIPTION = "Avoid Using Enums";
    private static final String ISSUE_EXPLANATION = "No real Android programmer should ever use enums. EVER.";
    private static final Category ISSUE_CATEGORY = Category.PERFORMANCE;
    private static final int ISSUE_PRIORITY = 5;
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

    /**
     * Constructs a new {@link EnumDetector} check
     */
    public EnumDetector() {
    }

    @Override
    public boolean appliesTo(@NonNull Context context, @NonNull File file) {
        return true;
    }

    @Override
    public EnumSet<Scope> getApplicableFiles() {
        return Scope.JAVA_FILE_SCOPE;
    }

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Arrays.<Class<? extends Node>>asList(
                EnumDeclaration.class,
                EnumTypeBody.class,
                EnumConstant.class
        );
    }

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new EnumChecker(context);
    }

    private static class EnumChecker extends ForwardingAstVisitor {

        private final JavaContext mContext;

        public EnumChecker(JavaContext context) {
            mContext = context;
        }

        @Override
        public boolean visitEnumDeclaration(EnumDeclaration node) {
            mContext.report(ISSUE, Location.create(mContext.file), ISSUE.getBriefDescription(TextFormat.TEXT));
            return super.visitEnumDeclaration(node);
        }

    }

}
