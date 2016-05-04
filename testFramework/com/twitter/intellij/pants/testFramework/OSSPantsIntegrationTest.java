// Copyright 2014 Pants project contributors (see CONTRIBUTORS.md).
// Licensed under the Apache License, Version 2.0 (see LICENSE).

package com.twitter.intellij.pants.testFramework;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.testFramework.ThreadTracker;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.List;

abstract public class OSSPantsIntegrationTest extends PantsIntegrationTestCase {
  public OSSPantsIntegrationTest() {
    this(true);
  }

  public OSSPantsIntegrationTest(boolean readOnly) {
    super(readOnly);
    ThreadTracker.longRunningThreadCreated(
      ApplicationManager.getApplication(),
      "Pants-Plugin-Pool",
      "Timer");
  }

  @NotNull
  @Override
  protected List<File> getProjectFoldersToCopy() {
    final File testProjects = new File(PantsTestUtils.findTestPath("testData"), "testprojects");
    return Collections.singletonList(testProjects);
  }

  @NotNull
  @Override
  protected File getProjectFolder() {
    final String ossPantsHome = System.getenv("OSS_PANTS_HOME");
    if (!StringUtil.isEmpty(ossPantsHome)) {
      return new File(ossPantsHome);
    }
    final File workingDir = PantsTestUtils.findTestPath("testData").getParentFile();
    return new File(workingDir.getParent(), "pants");
  }

  protected void assertContainsSubstring(List<String> stringList, String expected) {
    for (String line : stringList) {
      if (line.contains(expected)) {
        return;
      }
    }
    fail(String.format("Compile output %s does not contain substring '%s'.", stringList.toString(), expected));
  }
}
